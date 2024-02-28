--UPDATE 0 TO 1
ALTER PROCEDURE createTableTrophy
AS
CREATE TABLE [Trophy]
	(TrophyID INT NOT NULL,
	FishID INT,
	UserID INT,
	TrophyName VARCHAR(100))
GO

--DOWNGRADE 1 TO 0
ALTER PROCEDURE dropTableTrophy
AS
DROP TABLE [Trophy]
GO


--UPDATE 1 TO 2
ALTER PROCEDURE addWeightColumn
AS
ALTER TABLE [Trophy]
ADD [Weight] INT
GO

--DOWNGRADE 2 TO 1
ALTER PROCEDURE removeWeightColumn
AS
ALTER TABLE [Trophy]
DROP COLUMN [Weight]
GO


--UPDATE 2 TO 3
ALTER PROCEDURE modifyWeightType
AS
ALTER TABLE [Trophy]
ALTER COLUMN [Weight] SMALLINT
GO

-- DOWNGRADE 3 TO 2
ALTER PROCEDURE restoreWeightType
AS
ALTER TABLE [Trophy]
ALTER COLUMN [Weight] INT
GO


--UPDATE 3 TO 4
CREATE OR ALTER PROCEDURE addDefaultConstraintToWeight
AS
ALTER TABLE [Trophy]
ADD CONSTRAINT trophyDefOnWeight DEFAULT NULL for [Weight]
GO

--DOWNGRADE 4 TO 3
CREATE OR ALTER PROCEDURE removeDefaultConstraintFromWeight
AS
ALTER TABLE [Trophy]
DROP CONSTRAINT trophyDefOnWeight
GO


--UPDATE 4 TO 5
ALTER PROCEDURE addPrimaryKey
AS
ALTER TABLE [Trophy]
ADD CONSTRAINT PK_Constraint PRIMARY KEY(TrophyID)
GO

--DONWGRADE 5 TO 4
ALTER PROCEDURE removePrimaryKey
AS
ALTER TABLE [Trophy]
DROP CONSTRAINT PK_Constraint
GO


--UPDATE 5 TO 6
ALTER PROCEDURE addForeignKeys
AS
ALTER TABLE [Trophy]
ADD CONSTRAINT User_FG_Constraint 
FOREIGN KEY(UserID) REFERENCES [User](UserID)

ALTER TABLE [Trophy]
ADD CONSTRAINT Fish_FG_Constraint
FOREIGN KEY(FishID) REFERENCES [Fish](FishID)
GO

--DOWNGRADE 6 TO 5
ALTER PROCEDURE removeForeignKeys
AS
ALTER TABLE [Trophy]
DROP CONSTRAINT User_FG_Constraint

ALTER TABLE [Trophy]
DROP CONSTRAINT Fish_FG_Constraint
GO


--UPDATE 6 TO 7
ALTER PROCEDURE addCandidateKey
AS
ALTER TABLE [Trophy]
ADD CONSTRAINT UniqueTrophyName UNIQUE(TrophyName)
GO

--DOWNGRADE 7 TO 6
ALTER PROCEDURE removeCandidateKey
AS
ALTER TABLE [Trophy]
DROP CONSTRAINT UniqueTrophyName
GO

EXEC createTableTrophy
EXEC addWeightColumn
EXEC modifyWeightType
EXEC addDefaultConstraintToWeight
EXEC addPrimaryKey
EXEC addForeignKeys
EXEC addCandidateKey

EXEC removeCandidateKey
EXEC removeForeignKeys
EXEC removePrimaryKey
EXEC removeDefaultConstraintFromWeight
EXEC restoreWeightType
EXEC removeWeightColumn
EXEC dropTableTrophy
