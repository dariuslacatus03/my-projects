USE FishingApp
GO


INSERT INTO [Species](SpeciesName, MediumSize, Record)
VALUES 
('Chub', 25, 80),
('Brown Trout', 25, 98),
('Rainbow Trout', 27, 106),
('Brook Trout', 22, 80),
('Pike', 45, 161),
('Muskie',60 , 183),
('Zander', 50, 113),
('Perch', 20, 55),
('Grayling', 25, 75),
('European Catfish',70, 285)

INSERT INTO [User](UserID, Username, DateOfBirth)
VALUES
(1, 'dariuselu06', '6-6-2003'),
(2, 'mariola03', '5-26-2003'),
(3, 'vivijay21', '8-21-2003'),
(4, 'jrz', '1-12-2003'),
(5, 'nicholas31', '8-31-2001'),
(6, 'razvanlacatus', '2-1-2006'),
(7, 'roxyfoxy', '3-21-1994')

INSERT INTO [Location](River, LocationName, Popularity)
VALUES
('Lapus', 'Coas', 40),
('Lapus', 'Remetea', 30),
('Lapus', 'Catalina', 85),
('Somes', 'Seini', 90),
('Somes', 'Merisor', 60),
('Somesul Mic', 'Cluj-Napoca', 60),
('Somesul Mic', 'Peripheral area of Cluj-Napoca', 70),
('Sapanta', 'Sapanta', 20),
('Runcu', 'Izvoare', 30),
('Sasar', 'Baia Mare', 16),
('locationToBeDeleted1', 'locationToBeDeleted1', 0),
('locationToBeDeleted2', 'locationToBeDeleted2', 11),
('locationToBeDeleted3', 'locationToBeDeleted3', 12),
('locationToBeDeleted4', 'locationToBeDeleted4', 13)


INSERT INTO [Fish](FishID, Size, SpeciesName, UserID, River, LocationName)
VALUES
(1, 26, 'Chub', 2, 'Lapus', 'Coas'),
(2, 18, 'Chub', 1, 'Lapus', 'Coas'),
(3, 20, 'Chub', 1, 'Lapus', 'Coas'),
(4, 35, 'Chub', 1, 'Lapus', 'Coas'),
(5, 21, 'Chub', 2, 'Lapus', 'Coas'),
(6, 15, 'Chub', 1, 'Lapus', 'Coas'),
(7, 28, 'Brown Trout', 1, 'Sapanta', 'Sapanta'),
(8, 30, 'Brown Trout', 1, 'Sapanta', 'Sapanta'),
(9, 23, 'Brown Trout', 6, 'Sapanta', 'Sapanta'),
(10, 29, 'Brown Trout', 1, 'Sapanta', 'Sapanta'),
(11, 26, 'Rainbow Trout', 1, 'Sapanta', 'Sapanta'),
(12, 27, 'Rainbow Trout', 6, 'Sapanta', 'Sapanta'),
(13, 33, 'Rainbow Trout', 2, 'Sapanta', 'Sapanta'),
(14, 26, 'Rainbow Trout', 6, 'Sapanta', 'Sapanta'),
(15, 55, 'European Catfish', 5, 'Somes', 'Seini'),
(16, 32, 'Chub', 3, 'Somesul Mic', 'Peripheral area of Cluj-Napoca'),
(17, 21, 'Perch', 3, 'Somesul Mic', 'Peripheral area of Cluj-Napoca'),
(18, 31, 'Chub', 3, 'Somesul Mic', 'Peripheral area of Cluj-Napoca'),
(19, 28, 'Chub', 3, 'Somesul Mic', 'Peripheral area of Cluj-Napoca'),
(20, 17, 'Perch', 4, 'Somesul Mic', 'Cluj-Napoca'),
(21, 15, 'Perch', 4, 'Somesul Mic', 'Cluj-Napoca'),
(22, 20, 'Chub', 4, 'Somesul Mic', 'Cluj-Napoca'),
(23, 19, 'Perch', 4, 'Somesul Mic', 'Cluj-Napoca'),
(24, 31, 'Chub', 1, 'Somesul Mic', 'Cluj-Napoca'),
(25, 24, 'Perch', 5, 'Somesul Mic', 'Cluj-Napoca'),
(26, 33, 'Chub', 1, 'Somesul Mic', 'Cluj-Napoca'),
(27, 26, 'Chub', 1, 'Somesul Mic', 'Cluj-Napoca'),
(28, 19, 'Perch', 5, 'Somesul Mic', 'Cluj-Napoca'),
(29, 25, 'Perch', 5, 'Somesul Mic', 'Cluj-Napoca'),
(30, 20, 'Chub', 5, 'Somesul Mic', 'Cluj-Napoca'),
(31, 14, 'Perch', 5, 'Somesul Mic', 'Cluj-Napoca'),
(32, 13, 'Perch', 1, 'Somesul Mic', 'Cluj-Napoca'),
(33, 22, 'Perch', 5, 'Somesul Mic', 'Cluj-Napoca'),
(34, 45, 'Pike', 1, 'Somesul Mic', 'Cluj-Napoca'),
(35, 30, 'Pike', 1, 'Somesul Mic', 'Cluj-Napoca'),
(36, 26, 'Chub', 5, 'Somesul Mic', 'Cluj-Napoca'),
(37, 24, 'Chub', 6, 'Somesul Mic', 'Cluj-Napoca'),
(38, 3, 'Chub', 2, 'Sapanta', 'Sapanta'),
(39, 2, 'Chub', 2, 'Sapanta', 'Sapanta'),
(40, 25, 'Chub', 2, 'Runcu', 'Izvoare'),
(41, 26, 'Chub', 2, 'Sasar', 'Baia Mare')


INSERT INTO [RodBrand](RodBrandName, RodBrandPopularity, FoundingYear)
VALUES
('Shimano', 100, 1921),
('Daiwa', 95, 1958),
('Savage Gear', 70, 2005),
('Delphin', 70, 2004),
('Balzer', 55, 1949),
('Abu Garcia', 65, 1921)

INSERT INTO [ReelBrand](ReelBrandName, ReelBrandPopularity, FoundingYear)
VALUES
('Shimano', 99, 1921),
('Daiwa', 95, 1958),
('Savage Gear', 70, 2005),
('Delphin', 70, 2004),
('Balzer', 55, 1949),
('Abu Garcia', 65, 1921),
('Sakura', 75, 1949)

INSERT INTO [Rod](RodID, RodBrandName, RodName, [Length(m)], Category, [Action], [MaxCastingWeight(g)], UserID)
VALUES
(1, 'Savage Gear', 'Custom UL', 1.98, 'Ultra-Light', 'Moderate-Slow', 8, 1),
(2, 'Delphin', 'Code X', 2.28, 'Ultra-Light', 'Moderate-Slow', 8, 2),
(3, 'Balzer', 'UL' , 2.40, 'Ultra-Light', 'Slow', 4, 6),
(4, 'Shimano', 'Beastmaster FX Predator ML', 2.40, 'Medium-Light', 'Moderate-Fast', 21, 5),
(5, 'Shimano', 'Zodias UL', 1.93, 'Ultra-Light', 'Moderate-Slow', 7, 5),
(6, 'Daiwa', 'Silver Creek UL', 2.10, 'Ultra-Light', 'Moderate', 10, 3),
(7, 'Abu Garcia', 'Veritas-SensiS 702UL', 2.13, 'Ultra-Light', 'Moderate', 10, 4),
(8, 'Abu Garcia', 'Veritas-SensiS 702UL', 2.13, 'Ultra-Light', 'Moderate', 10, NULL)

INSERT INTO [Reel](ReelID, ReelBrandName, ReelName, Size, BallBearings, [MaxDrag(kg)], UserID)
VALUES
(1, 'Sakura', 'Lomax 2004fd', 2000, 4, 2.5, 1),
(2, 'Delphin', 'Antigravity 2T', 2000, 11, 3, 2),
(3, 'Shimano', 'Catana 2500FD', 2500, 3, 4, 6),
(4, 'Daiwa', 'Ninja LT', 2500, 4, 2.5, 3),
(5, 'Daiwa', 'Ninja LT', 2500, 4, 2.5, 4),
(6, 'Shimano', 'Catana 2500FE', 2500, 4, 4, 5),
(7, 'Daiwa', 'Revros 19LT', 4000, 5, 12, 5)


INSERT INTO [Championship](CS_ID, CS_Name, [Prize(ron)], TargetSpecies1, TargetSpecies2, TargetSpecies3, NrOfDays, Winner)
VALUES
(1, 'Trout Fishing Championship', 2000, 'Brown Trout', 'Rainbow Trout', 'Brook Trout', 1, 1),
(2, 'Fishing Championship Cluj-Napoca', 1000, 'Perch', 'Chub', null, 1, 3),
(3, 'Street Fishing Championship Cluj-Napoca', 1500, 'Perch', 'Chub', 'Pike', 1, 5)

INSERT INTO [Participated](UserID, CS_ID)
VALUES
(1, 1),
(2, 1),
(6, 1),
(1, 3),
(5, 3),
(3, 2),
(4, 2)


INSERT INTO [HeldIn](CS_ID, River, LocationName)
VALUES
(1, 'Sapanta', 'Sapanta'),
(2, 'Somesul Mic', 'Cluj-Napoca'),
(2, 'Somesul Mic', 'Peripheral area of Cluj-Napoca'),
(3, 'Somesul Mic', 'Cluj-Napoca')


SELECT * FROM [User]
SELECT * FROM [RodBrand]
SELECT * FROM [ReelBrand]
SELECT * FROM [Rod]
SELECT * FROM [Reel]
SELECT * FROM [Species]
SELECT * FROM [Fish]
SELECT * FROM [Location]
SELECT * FROM [Championship]
SELECT * FROM [Participated]
SELECT * FROM [HeldIn]


--VIOLATING THE REFERENTIAL INTEGRITY CONSTRAINT
INSERT INTO [Rod](RodID, RodBrandName, RodName, [Length(m)], Category, [Action], [MaxCastingWeight(g)], UserID)
VALUES
(8, 'Sakura', 'Custom UL', 1.98, 'Ultra-Light', 'Moderate-Slow', 8, 1)


UPDATE [Reel]
SET [MaxDrag(kg)]=5
WHERE ReelName NOT LIKE 'R%';


UPDATE [Championship]
SET TargetSpecies3='Pike'
WHERE TargetSpecies3 IS NULL;

Update [Fish]
SET Size=6, SpeciesName='Rainbow Trout', UserID=6
WHERE (Size BETWEEN 1 AND 5) OR River IN ('Runcu', 'Sasar');

SELECT * FROM [Reel]
SELECT * FROM [Championship]
SELECT * FROM [Fish]

DELETE FROM [Reel] WHERE [MaxDrag(kg)] <> 5;
DELETE FROM [Location] WHERE (Popularity=0) OR (Popularity > 10 AND Popularity < 15);
DELETE FROM [Fish] WHERE (Size >= 6) AND (Size <=10);

SELECT * FROM [Reel]
SELECT * FROM [Location]
SELECT * FROM [Fish]