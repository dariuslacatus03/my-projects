USE FishingApp
GO

/*
	CREATING THE
		VIEWS
vvvvvvvvvvvvvvvvvvvvvv
*/
-- a view with a SELECT statement operating on one table;
-- ALL THE FISH
CREATE OR ALTER VIEW AllFish 
AS
	SELECT * FROM Fish
GO

-- a view with a SELECT statement that operates on at least
-- 2 different tables and contains at least one JOIN operator
-- ALL THE FISH WITH THE SIZE BIGGER THAN 50
CREATE OR ALTER VIEW UsersWithBigFish
AS
	SELECT U.UserId, U.Username, F.FishID, F.SpeciesName
	FROM 
		[User] U INNER JOIN
		[Fish] F ON (U.UserID = F.UserID AND F.Size > 50)

GO

-- a view with a SELECT statement that has a GROUP BY clause,
-- operates on at least 2 different tables and contains at
-- least one JOIN operator
-- STATISTICS OF FISH CAUGHT BY USERS
CREATE OR ALTER VIEW UserFishStatistics 
AS
	SELECT
		U.UserID,
		U.Username,
		COUNT(F.FishID) AS NumberOfFish,
		AVG(F.Size) AS AverageFishSize,
		MAX(F.Size) AS LargestFishSize
	FROM
		[User] U INNER JOIN
		Fish F ON U.UserID = F.UserID
	GROUP BY
		U.UserID, U.Username;
GO
/*
^^^^^^^^^^^^^^^^^^^^^^
	CREATING THE
		VIEWS
*/



/* 
	PREPARING THE 
	TESTING TABLES
vvvvvvvvvvvvvvvvvvvvvv
*/
DELETE FROM [TestViews]
DELETE FROM [Views]
DELETE FROM [TestTables]
DELETE FROM [Tests]
DELETE FROM [Tables]

INSERT INTO [Tables]([Name])
VALUES ('User'), ('Species'), ('Location'), ('Fish')

INSERT INTO [Tests]([Name])
VALUES ('Test 1'), ('Test 2'), ('Test 3')


INSERT INTO [TestTables]([TestID], [TableID], [NoOfRows], [Position])
VALUES
	(1,1,300,1),
	(1,2,300,2),
	(1,3,300,3),
	(1,4,600,4),
	(2,1,400,1),
	(2,2,400,2),
	(2,3,400,3),
	(2,4,800,4),
	(3,1,500,1),
	(3,2,500,2),
	(3,3,500,3),
	(3,4,1000,4)


INSERT INTO [Views]([Name])
VALUES
	('AllFish'),
	('UsersWithBigFish'),
	('UserFishStatistics')

INSERT INTO [TestViews]([TestID], [ViewID])
VALUES
	(1,1),
	(1,2),
	(1,3),
	(2,1),
	(2,2),
	(2,3),
	(3,1),
	(3,2),
	(3,3)

SELECT * FROM [Tests]
SELECT * FROM [Tables]
SELECT * FROM [TestTables]
SELECT * FROM [Views]
SELECT * FROM [TestViews]
/* 
^^^^^^^^^^^^^^^^^^^^^^
	PREPARING THE 
	TESTING TABLES

*/




/*
	INSERT DATA IN
	User TABLE
vvvvvvvvvvvvvvvvvvvvvv
*/
GO
CREATE OR ALTER PROC InsertDataIntoUser
@NumberOfRows INT
AS
BEGIN
	DECLARE @userID INT
	SET @userID = (SELECT MAX(UserID)+1 FROM [User])
	if @userID is NULL
		SET @userID = 1
	
	WHILE @NumberOfRows > 0
	BEGIN
		DECLARE @username VARCHAR(50) = 'User' + CAST(@NumberOfRows AS VARCHAR(10))
		DECLARE @RandomDOB DATE = DATEADD(DAY, -CAST(RAND() * 365 * 50 AS INT), GETDATE())
	
		INSERT INTO [User] (UserID, Username, DateOfBirth)
		VALUES (@userID, @username, @RandomDOB)

		SET @userID = @userID + 1
		SET @NumberOfRows = @NumberOfRows - 1
	END
END
/*
^^^^^^^^^^^^^^^^^^^^^^
	INSERT DATA IN
	User TABLE
*/



/*
	INSERT DATA IN
	Species TABLE
vvvvvvvvvvvvvvvvvvvvvv
*/
GO
CREATE OR ALTER PROC InsertDataIntoSpecies
@NumberOfRows INT
AS
BEGIN
	WHILE @NumberOfRows > 0
	BEGIN
		DECLARE @RandomSpeciesName VARCHAR(30) = 'Species' + CAST(@NumberOfRows AS VARCHAR(10));
        DECLARE @RandomMediumSize INT = CAST(RAND() * 100 AS INT);
        DECLARE @RandomRecord INT = CAST(RAND() * 1000 AS INT);

		INSERT INTO [Species] (SpeciesName, MediumSize, Record)
		VALUES (@RandomSpeciesName, @RandomMediumSize, @RandomRecord)

		SET @NumberOfRows = @NumberOfRows - 1
	END
END
/*
^^^^^^^^^^^^^^^^^^^^^^
	INSERT DATA IN
	Species TABLE
*/



/*
	INSERT DATA IN
	Location TABLE
vvvvvvvvvvvvvvvvvvvvvv
*/
GO
CREATE OR ALTER PROC InsertDataIntoLocation
@NumberOfRows INT
AS
BEGIN
	WHILE @NumberOfRows > 0
	BEGIN
		DECLARE @RandomRiver VARCHAR(30) = 'River' + CAST(@NumberOfRows AS VARCHAR(10));
        DECLARE @RandomLocationName VARCHAR(50) = 'Location' + CAST(@NumberOfRows AS VARCHAR(10));
        DECLARE @RandomPopularity INT = CAST(RAND() * 100 AS INT)

        INSERT INTO [Location] (River, LocationName, Popularity)
        VALUES (@RandomRiver, @RandomLocationName, @RandomPopularity);

		SET @NumberOfRows = @NumberOfRows - 1
	END
END
/*
^^^^^^^^^^^^^^^^^^^^^^
	INSERT DATA IN
	Location TABLE
*/



/*
	INSERT DATA IN
	Fish TABLE
vvvvvvvvvvvvvvvvvvvvvv
*/
GO
CREATE OR ALTER PROC insertDataIntoFish
@nrOfRows INT
AS
BEGIN
	DECLARE @fishID INT
	SET @fishID = (SELECT MAX(FishID)+1 FROM [Fish])
	if @fishID is NULL
		SET @fishID = 1
	
	WHILE @nrOfRows > 0
	BEGIN
		DECLARE @RandomSize INT = CAST(RAND() * 100 AS INT);
        DECLARE @RandomSpeciesName VARCHAR(30) = (SELECT TOP 1 SpeciesName FROM [Species] ORDER BY NEWID());
        DECLARE @RandomUserID INT = (SELECT TOP 1 UserID FROM [User] ORDER BY NEWID());
        DECLARE @RandomRiver VARCHAR(30) = (SELECT TOP 1 River FROM [Location] ORDER BY NEWID());
        DECLARE @RandomLocationName VARCHAR(50) = (SELECT TOP 1 LocationName FROM [Location] WHERE River = @RandomRiver ORDER BY NEWID());

		INSERT INTO [Fish] (FishID, Size, SpeciesName, UserID, River, LocationName)
        VALUES (@fishID, @RandomSize, @RandomSpeciesName, @RandomUserID, @RandomRiver, @RandomLocationName);

		SET @fishID = @fishID + 1
		SET @nrOfRows = @nrOfRows - 1
	END
END
/*
^^^^^^^^^^^^^^^^^^^^^^
	INSERT DATA IN
	Fish TABLE
*/



/*
	INSERT DATA IN A
	SPECIFIC TABLE
vvvvvvvvvvvvvvvvvvvvvv
*/
GO
CREATE OR ALTER PROC insertData
@testRunID INT,
@testID INT,
@tableID INT
AS
BEGIN
	--starting time before test runs
	DECLARE @startTime DATETIME = SYSDATETIME()

	-- we get the name of the table based on the tableID
	DECLARE @tableName VARCHAR(50) = (
		SELECT [Name] FROM [Tables] WHERE TableID = @tableID
	)

	PRINT 'Insert data into table ' + @tableName

	-- we get the number of rows we have to insert based on the tableID and on the testID
	DECLARE @nrOfRows INT = (
		SELECT [NoOfRows] FROM TestTables  
			WHERE TestID = @testID AND TableID = @tableID
	)
	if @tableName = 'User'
		EXEC InsertDataIntoUser @nrOfRows

	else if @tableName = 'Species'
		EXEC InsertDataIntoSpecies @nrOfRows

	else if @tableName = 'Location'

		EXEC InsertDataIntoLocation @nrOfRows
	
	else if @tableName = 'Fish'
		EXEC InsertDataIntoFish @nrOfRows
	
	-- we get the end time of the test
	DECLARE @endTime DATETIME = SYSDATETIME()

	-- we insert the performance
	INSERT INTO TestRunTables(TestRunID, TableID, StartAt, EndAt)
		VALUES (@testRunID, @tableID, @startTime, @endTime)

END
/*
^^^^^^^^^^^^^^^^^^^^^^
	INSERT DATA IN A
	SPECIFIC TABLE
*/



/*
	INSERT DATA INTO
		ALL TABLES
vvvvvvvvvvvvvvvvvvvvvv
*/
GO
CREATE OR ALTER PROCEDURE insertDataIntoAllTables
@testRunID INT,
@testID INT
AS
BEGIN
	DECLARE @tableID INT
	DECLARE cursorForInsert CURSOR LOCAL FOR
		SELECT TableID
		FROM TestTables TT
			INNER JOIN Tests T on TT.TestID = T.TestID
		WHERE T.TestID = @testID
		ORDER BY TT.Position ASC
	
	OPEN cursorForInsert
	FETCH cursorForInsert INTO @tableID
	WHILE @@FETCH_STATUS = 0
	BEGIN
		EXEC insertData @testRunID, @testID, @tableID

		FETCH NEXT FROM cursorForInsert INTO @tableID
	END
	CLOSE cursorForInsert
END
/*
^^^^^^^^^^^^^^^^^^^^^^
	INSERT DATA INTO
		ALL TABLES
*/



/*
	SELECT DATA FROM
	A SPECIFIC VIEW
vvvvvvvvvvvvvvvvvvvvvv
*/
GO
CREATE OR ALTER PROC SelectDataView
@viewID INT,
@testRunID INT
AS
BEGIN
	DECLARE @startTime DATETIME = SYSDATETIME()

	DECLARE @viewName VARCHAR(100) = (
		SELECT [Name] FROM [Views]
			WHERE ViewID = @viewID
	)

	PRINT 'Selecting from view ' + @viewName

	DECLARE @query NVARCHAR(200) = N'SELECT * FROM '  + @viewName
	EXEC sp_executesql @query

	-- ending time after test
	DECLARE @endTime DATETIME = SYSDATETIME()

	INSERT INTO TestRunViews(TestRunID, ViewID, StartAt, EndAt)
		VALUES(@testRunID, @viewID, @startTime, @endTime)

END
/*
^^^^^^^^^^^^^^^^^^^^^^
	SELECT DATA FROM
	A SPECIFIC VIEW
*/



/*
	SELECT DATA FROM
	ALL VIEWS
vvvvvvvvvvvvvvvvvvvvvv
*/
GO
CREATE OR ALTER PROCEDURE SelectDataFromAllViews
@testRunID INT,
@testID INT
AS
BEGIN
	PRINT 'Select data from all the views for test = ' + convert(VARCHAR, @testID)

	DECLARE @viewID INT

	DECLARE cursorForViews CURSOR LOCAL FOR
		SELECT TV.ViewID FROM TestViews TV
			INNER JOIN Tests T on T.TestID = TV.TestID
		WHERE TV.TestID = @testID

	OPEN cursorForViews
	FETCH cursorForViews INTO @viewID
	WHILE @@FETCH_STATUS = 0
	BEGIN
		-- We select the view
		EXEC SelectDataView @viewID, @testRunID
		FETCH NEXT FROM cursorForViews INTO @viewID
	END
	CLOSE cursorForViews
END
/*
^^^^^^^^^^^^^^^^^^^^^^
	SELECT DATA FROM
	ALL VIEWS
*/



/* 
	DELETE DATA FROM
	A TABLE
vvvvvvvvvvvvvvvvvvvvvv
*/
GO
CREATE OR ALTER PROC DeleteData
@table_id INT
AS
BEGIN
	DECLARE @table_name NVARCHAR(50) = (
		SELECT [Name] FROM [Tables] WHERE TableID = @table_id
	)

	DECLARE @query NVARCHAR(MAX)
	SET @query = N'DELETE FROM ' + @table_name

	EXEC sp_executesql @query
END
/* 
^^^^^^^^^^^^^^^^^^^^^^
	DELETE DATA FROM
	A TABLE
*/



/*
	DELETE DATA FROM
	ALL TABLES
vvvvvvvvvvvvvvvvvvvvvv
*/
GO
CREATE OR ALTER PROC deleteDataFromAllTables
@test_id INT
AS
BEGIN
	DECLARE @tableID INT
	DECLARE cursorForDelete cursor local for
		SELECT TT.TableID
		FROM TestTables TT
			INNER JOIN Tests T ON TT.TestID = T.TestID
		WHERE T.TestID = @test_id
		ORDER BY TT.Position DESC

	OPEN cursorForDelete
	FETCH cursorforDelete INTO @tableID
	WHILE @@FETCH_STATUS = 0
	BEGIN
		exec DeleteData @tableID

		FETCH NEXT FROM cursorForDelete INTO @tableID
	END
	CLOSE cursorForDelete
END
/*
^^^^^^^^^^^^^^^^^^^^^^
	DELETE DATA FROM
	ALL TABLES
*/



/*
	Run a test
vvvvvvvvvvvvvvvvvvvvvv
*/
GO
CREATE OR ALTER PROCEDURE RunTest
@testID INT,
@description VARCHAR(5000)
AS
BEGIN
	PRINT 'Running test with id: ' + CONVERT(VARCHAR, @testID) + ' with description: ' + @description

	-- We create a test run
	INSERT INTO TestRuns([Description]) values (@description)

	-- Get the current testRunID
	DECLARE @testRunID INT = (SELECT MAX(TestRunID) FROM TestRuns)

	-- Get the starting time before the test runs
	DECLARE @startTime DATETIME = SYSDATETIME()

	--Run all the inserts
	EXEC InsertDataIntoAllTables @testRunID, @testID


	--Run all the views
	EXEC SelectDataFromAllViews @testRunID, @testID

	--Get the ending time after the test runs
	DECLARE @endTIME DATETIME = SYSDATETIME()

	--Delete all the data
	EXEC DeleteDataFromAllTables @testID


	-- Now we update the start time and the end time for the entire RUN
	UPDATE [TestRuns] SET StartAt = @startTime, EndAt = @endTIME
END
/*
^^^^^^^^^^^^^^^^^^^^^^
	Run a test
*/

EXEC RunTest 1, 'Description of RunTest1'
EXEC RunTest 2, 'Description of RunTest2'
EXEC RunTest 3, 'Description of RunTest3'


SELECT * FROM [TestRuns]
SELECT * FROM [TestRunTables]
SELECT * FROM [TestRunViews]

SELECT * FROM AllFish
SELECT * FROM UsersWithBigFish
SELECT * FROM UserFishStatistics