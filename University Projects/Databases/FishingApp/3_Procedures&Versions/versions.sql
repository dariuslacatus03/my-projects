DROP TABLE [Versions]

DROP TABLE [CurrentVersion]

CREATE TABLE [CurrentVersion]
	(currentVersion INT)

INSERT INTO [CurrentVersion](currentVersion) VALUES(0)

CREATE TABLE [Versions]
	([Update] VARCHAR(60),
	Downgrade VARCHAR(60),
	targetVersion SMALLINT)

INSERT INTO [Versions]([Update], Downgrade, targetVersion)
VALUES
('createTableTrophy', 'dropTableTrophy', 1),
('addWeightColumn', 'removeWeightColumn', 2),
('modifyWeightType', 'restoreWeightType', 3),
('addDefaultConstraintToWeight', 'removeDefaultConstraintFromWeight', 4),
('addPrimaryKey', 'removePrimaryKey', 5),
('addForeignKeys', 'removeForeignKeys', 6),
('addCandidateKey', 'removeCandidateKey', 7)



SELECT * FROM [Versions]

SELECT * FROM [CurrentVersion]


