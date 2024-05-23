USE LeagueManagement;
GO

DROP TABLE IF EXISTS TeamPlayer
DROP TABLE IF EXISTS Team
DROP TABLE IF EXISTS Player
DROP TABLE IF EXISTS Logs

-- Create Player table
CREATE TABLE Player (
    PlayerID INT IDENTITY PRIMARY KEY,
    PlayerName VARCHAR(100),
    PlayerOrigin VARCHAR(100),
    PhoneNR VARCHAR(10)
);

-- Create Team table
CREATE TABLE Team (
    TeamID INT IDENTITY PRIMARY KEY,
    TName VARCHAR(70),
    TPoints INT CHECK (TPoints >= 0),
    MatchesPlayed INT CHECK (MatchesPlayed >= 0),
    CoachID INT,
    LeagueID INT
);

-- Create TeamPlayer table
CREATE TABLE TeamPlayer (
    TeamID INT REFERENCES Team(TeamID),
    PlayerID INT REFERENCES Player(PlayerID),
    PRIMARY KEY (TeamID, PlayerID)
);


-- Create Logs table
CREATE TABLE Logs (
    LID INT IDENTITY PRIMARY KEY,
    OperationType VARCHAR(1000),
    OperationTable VARCHAR(50),
    ExecDate DATETIME,
    ErrorMsg VARCHAR(2000)
);
GO

-- Create validation function for PlayerName
CREATE OR ALTER FUNCTION uf_ValidatePlayerName (@input VARCHAR(100)) RETURNS INT AS
BEGIN
    DECLARE @ok INT = 0;
    IF (@input COLLATE Latin1_General_BIN LIKE '[A-Z]%' AND @input NOT LIKE '%[^A-Za-z]%')
        SET @ok = 1;
    RETURN @ok;
END;
GO

-- Create validation function for PhoneNumber
CREATE OR ALTER FUNCTION uf_ValidatePhoneNumber (@phone VARCHAR(10)) RETURNS INT AS
BEGIN
    DECLARE @ok INT = 0;
    IF (@phone LIKE '[0-9]%' AND @phone NOT LIKE '%[^0-9]%')
        SET @ok = 1;
    RETURN @ok;
END;
GO


-- Stored procedure with full rollback on failure
CREATE OR ALTER PROCEDURE sp_AddPlayerAndTeamWithRollback
    @PlayerName VARCHAR(100),
    @PlayerOrigin VARCHAR(100),
    @PhoneNR VARCHAR(10),
    @TName VARCHAR(70),
    @TPoints INT,
    @MatchesPlayed INT,
    @CoachID INT,
    @LeagueID INT
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION;

        -- Validate parameters
        IF dbo.uf_ValidatePlayerName(@PlayerName) = 0
            RAISERROR('The Player name is not valid.', 16, 1);
        IF dbo.uf_ValidatePlayerName(@PlayerOrigin) = 0
            RAISERROR('The Player origin is not valid.', 16, 1);
        IF dbo.uf_ValidatePhoneNumber(@PhoneNR) = 0
            RAISERROR('The phone number is not valid.', 16, 1);

        -- Insert Player
        INSERT INTO Player (PlayerName, PlayerOrigin, PhoneNR) 
        VALUES (@PlayerName, @PlayerOrigin, @PhoneNR);
        DECLARE @PlayerID INT = SCOPE_IDENTITY();

        -- Insert Team
        INSERT INTO Team (TName, TPoints, MatchesPlayed, CoachID, LeagueID) 
        VALUES (@TName, @TPoints, @MatchesPlayed, @CoachID, @LeagueID);
        DECLARE @TeamID INT = SCOPE_IDENTITY();

        -- Insert into TeamPlayer
        INSERT INTO TeamPlayer (TeamID, PlayerID) 
        VALUES (@TeamID, @PlayerID);

        COMMIT TRANSACTION;
        INSERT INTO Logs (OperationType, OperationTable, ExecDate, ErrorMsg) 
        VALUES ('Success Insert', 'Player, Team, TeamPlayer', GETDATE(), 'Transaction successful');
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;
        INSERT INTO Logs (OperationType, OperationTable, ExecDate, ErrorMsg) 
        VALUES ('Error Insert', 'Player, Team, TeamPlayer', GETDATE(), ERROR_MESSAGE());
        THROW;
    END CATCH
END;
GO

-- Stored procedure with partial commit on failure
CREATE OR ALTER PROCEDURE sp_AddPlayerAndTeamWithPartialCommit
    @PlayerName VARCHAR(100),
    @PlayerOrigin VARCHAR(100),
    @PhoneNR VARCHAR(10),
    @TName VARCHAR(70),
    @TPoints INT,
    @MatchesPlayed INT,
    @CoachID INT,
    @LeagueID INT
AS
BEGIN
    DECLARE @PlayerID INT = NULL;
    DECLARE @TeamID INT = NULL;
    DECLARE @PlayerError NVARCHAR(4000) = NULL;
    DECLARE @TeamError NVARCHAR(4000) = NULL;
    DECLARE @FinalError NVARCHAR(4000) = '';

    -- Insert Player
    BEGIN TRY
        BEGIN TRANSACTION;

        -- Validate parameters
        IF dbo.uf_ValidatePlayerName(@PlayerName) = 0
            RAISERROR('The Player name is not valid.', 16, 1);
        IF dbo.uf_ValidatePlayerName(@PlayerOrigin) = 0
            RAISERROR('The Player origin is not valid.', 16, 1);
        IF dbo.uf_ValidatePhoneNumber(@PhoneNR) = 0
            RAISERROR('The phone number is not valid.', 16, 1);

        INSERT INTO Player (PlayerName, PlayerOrigin, PhoneNR) 
        VALUES (@PlayerName, @PlayerOrigin, @PhoneNR);
        SET @PlayerID = SCOPE_IDENTITY();

        COMMIT TRANSACTION;
        INSERT INTO Logs (OperationType, OperationTable, ExecDate, ErrorMsg) 
        VALUES ('Success Insert', 'Player', GETDATE(), 'Transaction successful');
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;
        SET @PlayerError = ERROR_MESSAGE();
        INSERT INTO Logs (OperationType, OperationTable, ExecDate, ErrorMsg) 
        VALUES ('Error Insert', 'Player', GETDATE(), ERROR_MESSAGE());
    END CATCH

    -- Insert team
    BEGIN TRY
        BEGIN TRANSACTION;

        INSERT INTO Team (TName, TPoints, MatchesPlayed, CoachID, LeagueID) 
        VALUES (@TName, @TPoints, @MatchesPlayed, @CoachID, @LeagueID);
        SET @TeamID = SCOPE_IDENTITY();

        COMMIT TRANSACTION;
        INSERT INTO Logs (OperationType, OperationTable, ExecDate, ErrorMsg) 
        VALUES ('Success Insert', 'Team', GETDATE(), 'Transaction successful');
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;
        SET @TeamError = ERROR_MESSAGE();
        INSERT INTO Logs (OperationType, OperationTable, ExecDate, ErrorMsg) 
        VALUES ('Error Insert', 'Team', GETDATE(), ERROR_MESSAGE());
    END CATCH

    -- Insert into TeamPlayer
    IF @PlayerID IS NOT NULL AND @TeamID IS NOT NULL
    BEGIN
        BEGIN TRY
            BEGIN TRANSACTION;

            INSERT INTO TeamPlayer (TeamID, PlayerID) 
            VALUES (@TeamID, @PlayerID);

            COMMIT TRANSACTION;
            INSERT INTO Logs (OperationType, OperationTable, ExecDate, ErrorMsg) 
            VALUES ('Success Insert', 'TeamPlayer', GETDATE(), 'Transaction successful');
        END TRY
        BEGIN CATCH
            IF @@TRANCOUNT > 0
                ROLLBACK TRANSACTION;
            SET @FinalError = ERROR_MESSAGE();
            INSERT INTO Logs (OperationType, OperationTable, ExecDate, ErrorMsg) 
            VALUES ('Error Insert', 'TeamPlayer', GETDATE(), ERROR_MESSAGE());
        END CATCH
    END

    IF @PlayerError IS NOT NULL
        SET @FinalError = @FinalError + @PlayerError + ' ';
    IF @TeamError IS NOT NULL
        SET @FinalError = @FinalError + @TeamError + ' ';
    IF @FinalError != ''
        RAISERROR(@FinalError, 16, 1);
END;
GO

DELETE FROM TeamPlayer;
DELETE FROM Team;
DELETE FROM Player;
DELETE FROM Logs;

-- Happy Scenario for Grade 3 (Full Rollback)
-- This test case should insert a Player, a team, and their relationship successfully.
-- All data is valid, so the transaction should commit and log a success.
EXEC sp_AddPlayerAndTeamWithRollback 
    @PlayerName = 'PlayerOne', 
    @PlayerOrigin = 'USA', 
    @PhoneNR = '1234567890', 
    @TName = 'Team Alpha', 
    @TPoints = 10, 
    @MatchesPlayed = 5, 
    @CoachID = 1, 
    @LeagueID = 1;
GO

-- Error Scenario for Grade 3: Invalid Player Name (Full Rollback)
-- This test case should fail because the Player name is invalid (contains numbers).
-- The transaction should roll back and log an error.
EXEC sp_AddPlayerAndTeamWithRollback 
    @PlayerName = '123Player', 
    @PlayerOrigin = 'USA', 
    @PhoneNR = '1234567890', 
    @TName = 'Team Alpha', 
    @TPoints = 10, 
    @MatchesPlayed = 5, 
    @CoachID = 1, 
    @LeagueID = 1;
GO

-- Happy Scenario for Grade 5 (Partial Commit)
-- This test case should insert a Player, a team, and their relationship successfully.
-- All data is valid, so the transaction should commit and log a success.
EXEC sp_AddPlayerAndTeamWithPartialCommit 
    @PlayerName = 'PlayerTwo', 
    @PlayerOrigin = 'Canada', 
    @PhoneNR = '0987654321', 
    @TName = 'Team Beta', 
    @TPoints = 20, 
    @MatchesPlayed = 10, 
    @CoachID = 2, 
    @LeagueID = 2;
GO

-- Error Scenario for Grade 5: Invalid Player Name (Partial Commit)
-- This test case should fail because the Player name is invalid (contains numbers).
-- The Player insert should roll back, but no team or relationship should be inserted.
EXEC sp_AddPlayerAndTeamWithPartialCommit 
    @PlayerName = '123Player', 
    @PlayerOrigin = 'Canada', 
    @PhoneNR = '0987654321', 
    @TName = 'Team Beta', 
    @TPoints = 20, 
    @MatchesPlayed = 10, 
    @CoachID = 2, 
    @LeagueID = 2;
GO

-- Error Scenario for Grade 5: Valid Player, Invalid Team Data (Partial Commit)
-- This test case should insert the Player but fail to insert the team due to invalid points (negative value).
-- The Player should be inserted, but the team insert should roll back and log an error.
EXEC sp_AddPlayerAndTeamWithPartialCommit 
    @PlayerName = 'PlayerThree', 
    @PlayerOrigin = 'UK', 
    @PhoneNR = '1122334455', 
    @TName = 'Team Gamma', 
    @TPoints = -10, 
    @MatchesPlayed = 10, 
    @CoachID = 3, 
    @LeagueID = 3;
GO

-- Check Logs
-- This query will display the logs to verify the outcomes of the test cases.
SELECT * FROM Logs;
GO
