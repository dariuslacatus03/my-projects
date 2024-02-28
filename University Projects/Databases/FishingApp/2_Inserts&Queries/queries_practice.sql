USE FishingApp
GO

-- ***********************************************************

--a. 2 queries with the union operation; use UNION [ALL] and OR

-- ***********************************************************
--Toate locatiile in care s-a prins un peste de peste 30 de cm
--sau in care s-a tinut un turneu.
SELECT River, LocationName
FROM [Fish]
WHERE (Size >= 30)
UNION
SELECT River, LocationName 
FROM [HeldIn]


SELECT DISTINCT F.River, F.LocationName
FROM [Fish] AS F, [HeldIn] AS HLD
WHERE NOT (F.Size < 30) OR (F.River = HLD.River AND F.LocationName = HLD.LocationName)


--Toate speciile care au marimea medie de peste 60 de cm
--sau care au fost targetate ca prima specie intr-un turneu
--cu premiul minim 1500ron
SELECT SpeciesName 
FROM [Species]
WHERE (MediumSize>=60)
UNION
SELECT TargetSpecies1 
FROM [Championship]
WHERE [Prize(ron)]>=1500


SELECT DISTINCT SpeciesName
FROM [Species] AS S, [Championship] AS C
WHERE NOT (MediumSize<60) OR (S.SpeciesName = C.TargetSpecies1
								AND C.[Prize(ron)] >= 1500)



-- ***********************************************************

-- b. 2 queries with the intersection operation; use INTERSECT and IN

-- ***********************************************************
--Toate locatiile in care s-a tinut un turneu sau cu acces la raul Lapus
--si s-a prins orice in afara de pastrav indigen, curcubeu sau fantanel.
SELECT River, LocationName
FROM [Fish]
WHERE SpeciesName NOT IN ('Brown Trout', 'Brook Trout', 'Rainbow Trout')
INTERSECT
SELECT L.River, L.LocationName 
FROM [HeldIn] H, [Location] L
WHERE (L.River = H.River AND L.LocationName = H.LocationName)
	OR L.River = 'Lapus'

SELECT DISTINCT F.River, F.LocationName
FROM [Fish] AS F, [HeldIn] AS H
WHERE SpeciesName NOT IN ('Brown Trout', 'Brook Trout', 'Rainbow Trout') 
	AND ((F.River = H.River AND F.LocationName = H.LocationName)
		OR F.River = 'Lapus')


--Toate speciile care s-au prins in Lapus sau Somes
-- si au recordul de 3 ori mai mare decat dimensiunea medie
SELECT SpeciesName 
FROM [Fish]
WHERE River IN ('Lapus', 'Somes')
INTERSECT
SELECT SpeciesName 
FROM [Species]
WHERE Record>MediumSize*3

SELECT DISTINCT F.SpeciesName
FROM [Fish] AS F, [Species] AS S
WHERE F.River IN ('Lapus', 'Somes') AND (S.Record>S.MediumSize*3)



-- ***********************************************************

-- c. 2 queries with the difference operation; use EXCEPT and NOT IN

-- ***********************************************************
--Toate lansetele userilor care au participat la un campionat
--cu exceptia lansetelor brandurilor cu popularitate mai mica de 70
SELECT RodBrandName, RodName
FROM [Rod] as R, [Participated] AS P
WHERE R.UserID = P.UserID
EXCEPT
SELECT R.RodBrandName, R.RodName
FROM [Rod] as R, [RodBrand] as RB
WHERE R.RodBrandName = RB.RodBrandName AND RB.RodBrandPopularity<70


--Toate mulinetele userilor care au prins un clean peste medie
--cu exceptia mulinetelor brandurilor cu popularitate mai mica sau egala cu 70
SELECT DISTINCT ReelBrandName, R.ReelName
FROM [Reel] AS R, [Fish] AS F, [Species] AS S
WHERE R.UserID = F.UserID AND F.SpeciesName = S.SpeciesName
	AND S.SpeciesName = 'Chub' AND F.Size > S.MediumSize
	AND R.ReelBrandName NOT IN
	(SELECT R2.ReelBrandName
	FROM [Reel] AS R2, [ReelBrand] AS RB
	WHERE R2.ReelBrandName = RB.ReelBrandName AND RB.ReelBrandPopularity<=70)



-- ***********************************************************

-- d. 4 queries with INNER JOIN, LEFT JOIN, RIGHT JOIN, and FULL JOIN 
-- (one query per operator); one query will join at least 3 tables, 
-- while another one will join at least two many-to-many relationships

-- ***********************************************************
--Find all the users who participated in a championship held
--in Cluj-Napoca. Extend the number of days for each one of
--them with 1
SELECT U.Username, P.CS_ID, C.CS_Name, NewNrOfDays = NrOfDays+1, L.LocationName
FROM [User] U
INNER JOIN [Participated] P ON U.UserID = P.UserID
INNER JOIN [Championship] C ON P.CS_ID = C.CS_ID
INNER JOIN [HeldIn] H ON H.CS_ID = C.CS_ID
INNER JOIN [Location] L ON H.LocationName = L.LocationName
						AND H.River = L.River
						AND L.LocationName = 'Cluj-Napoca'


--Find the top 25% largest fish caught by users, include in the answer 
--the user's username, fish size, the location where it was caught
--and the medium size for the caught species. Also show the users
--with 0 catches
SELECT TOP 25 PERCENT U.Username, F.SpeciesName, F.Size, F.River, F.LocationName, S.MediumSize
FROM [User] U 
LEFT JOIN [Fish] F ON U.UserID = F.UserID
LEFT JOIN [Species] S ON F.SpeciesName = S.SpeciesName
ORDER BY F.Size DESC

--Find all the species targeted in championships. Show the
--championship's name and prize. Double the prize.
--Also show the species that were not targeted in any championship
SELECT S.SpeciesName, C.CS_Name, 2*C.[Prize(ron)] AS NewPrize
FROM [Championship] C
RIGHT JOIN [Species] S ON S.SpeciesName = C.TargetSpecies1
							OR S.SpeciesName = C.TargetSpecies2
							OR S.SpeciesName = C.TargetSpecies3


--Show all the user's rods. Also show the user without any rods
--and the rods that don't have an owner.
SELECT * 
FROM [User] U
FULL JOIN [Rod] R ON R.UserID = U.UserID



-- ***********************************************************

--e. 2 queries with the IN operator and a subquery in the 
--WHERE clause; in at least one case, the subquery must 
--include a subquery in its own WHERE clause;

-- ***********************************************************
--All the users who caught a fish in a river from a location
--having the popularity lower than 30
SELECT U.Username
FROM [User] U
WHERE U.UserID IN
	(SELECT F.UserID
	FROM [Fish] F
	WHERE F.River IN
		(SELECT L.River
		FROM [Location] L
		WHERE L.Popularity < 30))


--All the users who own a rod with the maximum casting weight
--greater than 10g
SELECT U.Username
FROM [User] U
WHERE U.UserID IN
	(SELECT R.UserID
	FROM [Rod] R
	WHERE R.[MaxCastingWeight(g)]>10)



-- ***********************************************************

--f. 2 queries with the EXISTS operator and a subquery in the 
--WHERE clause;

-- ***********************************************************
--All the users who've won a championship
SELECT U.Username
FROM [User] U
WHERE EXISTS
	(SELECT *
	FROM [Championship] C
	WHERE C.Winner = U.UserID)


--All the locations in which was caught a fish over 50cm
SELECT L.River, L.LocationName
FROM [Location] L
WHERE EXISTS
	(SELECT *
	FROM [Fish] F
	WHERE L.River = F.River AND L.LocationName = F.LocationName 
		AND F.Size > 50)



-- ***********************************************************

--g. 2 queries with a subquery in the FROM clause;                         

-- ***********************************************************
--Show all the rods having brand with popularity greater than 80
--and show the popularity by increasing its value by one
SELECT R.RodBrandName, R.RodName, NewPopularity=T.RodBrandPopularity+1
FROM [Rod] R INNER JOIN
	(SELECT *
	FROM [RodBrand] RB
	WHERE RodBrandPopularity > 80) T
ON R.RodBrandName = T.RodBrandName


--Show all the reels having brand with popularity lower than 40
--and show the popularity by decreasing its value by one
SELECT R.ReelBrandName, R.ReelName, NewPopularity=T.ReelBrandPopularity-1
FROM [Reel] R INNER JOIN
	(SELECT *
	FROM [ReelBrand] RB
	WHERE ReelBrandPopularity < 80) T
ON R.ReelBrandName = T.ReelBrandName



-- ***********************************************************

--h. 4 queries with the GROUP BY clause, 3 of which also contain
--the HAVING clause; 2 of the latter will also have a subquery in 
--the HAVING clause; use the aggregation operators: 
--COUNT, SUM, AVG, MIN, MAX;                        

-- ***********************************************************
--Show the top 3 species by the number of fish caught from that species
SELECT TOP 3 F.SpeciesName, COUNT(FishID) AS FishCaught
FROM [Fish] F
GROUP BY F.SpeciesName
ORDER BY FishCaught DESC


--Show all the rivers where more then 10 fish of a species have been caught.
--Also show the average size of the caught fish and the sum of sizes of fish caught
SELECT L.River, F.SpeciesName, AVG(F.Size) AS AverageOfCatches, SUM(F.Size) AS SumOfCatches
FROM [Location] L INNER JOIN [Fish] F ON L.River = F.River 
									AND L.LocationName = F.LocationName
GROUP BY L.River, F.SpeciesName
HAVING 200 < SUM(F.Size)


--Show all the rivers where more fish have been caught 
--than in the Lapus river. Also show the river record and the smallest
--fish caught in the given rivers. Order DESC by the river record
SELECT L.River, MAX(F.Size) AS RiverRecord, MIN(F.Size) AS SmallestFish
FROM [Location] L INNER JOIN [Fish] F ON L.River = F.River
										AND L.LocationName = F.LocationName
GROUP BY L.River
HAVING COUNT(F.FishID) >
	(SELECT COUNT(F.FishID)
	FROM [Fish] F
	WHERE F.River = 'Lapus')
ORDER BY RiverRecord DESC


--Show all the rivers where less fish have been caught than in the
--Somesul Mic river. Show the number of fish caught. Order by that.
SELECT L.River, COUNT(F.FishID) AS FishCaught
FROM [Location] L INNER JOIN [Fish] F ON L.River = F.River
										AND L.LocationName = F.LocationName
GROUP BY L.River
HAVING COUNT(F.FishID) <
	(SELECT COUNT(F.FishID)
	FROM [Fish] F
	WHERE F.River = 'Somesul Mic')
ORDER BY FishCaught



-- ***********************************************************

--i. 4 queries using ANY and ALL to introduce a subquery in the
--WHERE clause (2 queries per operator); rewrite 2 of them with
--aggregation operators, and the other 2 with IN / [NOT] IN.                        

-- ***********************************************************
--Show all the fish except the chubs.
SELECT *
FROM [Fish] F
WHERE F.SpeciesName <> ALL
	(SELECT F2.SpeciesName
	FROM [Fish] F2
	WHERE F2.SpeciesName = 'Chub')
--REWRITE WITH NOT IN
SELECT *
FROM [Fish] F
WHERE F.SpeciesName NOT IN ('Chub')


--Show all the species that have been caught until now
SELECT *
FROM [Species] S
WHERE S.SpeciesName = ANY
	(SELECT F.SpeciesName
	FROM [Fish] F)
--REWRITE WITH IN
SELECT *
FROM [Species] S
WHERE S.SpeciesName IN
	(SELECT F.SpeciesName
	FROM [Fish] F)


--Show all the fish bigger than all the perch caught until now
SELECT F.SpeciesName, F.Size
FROM [Fish] F
WHERE F.Size > ALL
	(SELECT F2.Size
	FROM [Fish] F2
	WHERE F2.SpeciesName = 'Perch')
--REWRITE WITH MAX
SELECT F.SpeciesName, F.Size
FROM [Fish] F
WHERE F.Size > (SELECT MAX(F2.Size)
				FROM [Fish] F2
				WHERE F2.SpeciesName = 'Perch')


--Show all the fish smaller than any pike caught until now
SELECT F.SpeciesName, F.Size
FROM [Fish] F
WHERE F.Size > ANY
	(SELECT F2.Size
	FROM [Fish] F2
	WHERE F2.SpeciesName = 'Pike')
--REWRITE WITH MIN
SELECT F.SpeciesName, F.Size
FROM [Fish] F
WHERE F.Size > (SELECT MIN(F2.Size)
				FROM [Fish] F2
				WHERE F2.SpeciesName = 'Pike')
