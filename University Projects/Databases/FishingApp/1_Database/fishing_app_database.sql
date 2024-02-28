USE FishingApp
GO

DROP TABLE [Rod]
DROP TABLE [RodBrand]

DROP TABLE [Reel]
DROP TABLE [ReelBrand]

DROP TABLE [HeldIn]
DROP TABLE [Participated]
DROP TABLE [Championship]

DROP TABLE [Fish]
DROP TABLE [Species]
DROP TABLE [Location]
DROP TABLE [User]


CREATE TABLE [User]
	(UserID INT PRIMARY KEY,
	Username VARCHAR(50),
	DateOfBirth DATE)


CREATE TABLE [Location]
	(River VARCHAR(30),
	LocationName VARCHAR(50),
	Popularity INT CHECK(0 <= Popularity AND Popularity <= 100),
	PRIMARY KEY(River, LocationName))


CREATE TABLE [Species]
	(SpeciesName VARCHAR(30) PRIMARY KEY,
	MediumSize INT,
	Record INT)


CREATE TABLE [Fish]
	(FishID INT PRIMARY KEY,
	Size INT,
	SpeciesName VARCHAR(30) REFERENCES [Species](SpeciesName),
	UserID INT REFERENCES [User](UserID),
	River VARCHAR(30),
	LocationName VARCHAR(50),
	FOREIGN KEY (River, LocationName) REFERENCES
	[Location](River, LocationName))


CREATE TABLE [Championship]
	(CS_ID INT PRIMARY KEY,
	CS_Name VARCHAR(60),
	[Prize(ron)] INT,
	TargetSpecies1 VARCHAR(30) REFERENCES [Species](SpeciesName),
	TargetSpecies2 VARCHAR(30) REFERENCES [Species](SpeciesName),
	TargetSpecies3 VARCHAR(30) REFERENCES [Species](SpeciesName),
	NrOfDays INT,
	Winner INT REFERENCES [User](UserID))


CREATE TABLE [Participated]
	(UserID INT REFERENCES [User](UserID),
	CS_ID INT REFERENCES [Championship](CS_ID),
	PRIMARY KEY(UserID, CS_ID))


CREATE TABLE [HeldIn]
	(CS_ID INT REFERENCES [Championship](CS_ID),
	River VARCHAR(30),
	LocationName VARCHAR(50),
	FOREIGN KEY(River, LocationName) REFERENCES
	[Location](River, LocationName),
	PRIMARY KEY(CS_ID, River, LocationName))


CREATE TABLE [ReelBrand]
	(ReelBrandName VARCHAR(30) PRIMARY KEY,
	ReelBrandPopularity INT CHECK(ReelBrandPopularity >= 0 AND ReelBrandPopularity <= 100),
	FoundingYear INT)


CREATE TABLE [Reel]
	(ReelID INT PRIMARY KEY,
	ReelBrandName VARCHAR(30) REFERENCES [ReelBrand](ReelBrandName),
	ReelName VARCHAR(30),
	Size INT,
	BallBearings INT,
	[MaxDrag(kg)] FLOAT,
	UserID INT REFERENCES [User](UserID))


CREATE TABLE [RodBrand]
	(RodBrandName VARCHAR(30) PRIMARY KEY,
	RodBrandPopularity INT CHECK(RodBrandPopularity >= 0 AND RodBrandPopularity <= 100),
	FoundingYear INT)


CREATE TABLE [Rod]
	(RodID INT PRIMARY KEY,
	RodBrandName VARCHAR(30) REFERENCES [RodBrand](RodBrandName),
	RodName VARCHAR(30),
	[Length(m)] FLOAT,
	Category VARCHAR(30),
	[Action] VARCHAR(30),
	[MaxCastingWeight(g)] INT,
	UserID INT REFERENCES [User](UserID))


