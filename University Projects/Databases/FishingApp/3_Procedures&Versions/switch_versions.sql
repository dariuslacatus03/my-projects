
CREATE OR ALTER PROCEDURE switchToVersion (@targetVersion INT)
AS
	DECLARE @currVersion INT
	SELECT @currVersion = currentVersion FROM [CurrentVersion]
	PRINT(@currVersion)
	DECLARE @procedureName VARCHAR(60);

	IF (@targetVersion > (SELECT MAX(targetVersion) FROM [Versions]) OR @targetVersion < 0)
		RAISERROR('Wrong version selected', 10, 1)
	ELSE
	BEGIN
		IF @targetVersion = @currVersion
			BEGIN
				PRINT('Already in the target version')
			END
		IF @targetVersion > @currVersion
		BEGIN
			WHILE @targetVersion > @currVersion
			BEGIN
				SELECT @procedureName = [Update] FROM [Versions] WHERE targetVersion = @currVersion + 1
				PRINT('Executing procedure: ' + @procedureName)
				EXEC @procedureName
				SET @currVersion = @currVersion + 1
				PRINT(@currVersion)
			END
		END
		IF @targetVersion < @currVersion
		BEGIN
			WHILE @targetVersion < @currVersion
			BEGIN
				SELECT @procedureName = Downgrade FROM [Versions] WHERE targetVersion = @currVersion
				PRINT('Executing procedure: ' + @procedureName)
				EXEC @procedureName
				SET @currVersion = @currVersion - 1
				PRINT(@currVersion)
			END
		END
		UPDATE [CurrentVersion] SET [currentVersion] = @targetVersion
	END
GO


EXEC switchToVersion 0
