SELECT COUNT(*)
FROM (
 SELECT Н_ЛЮДИ.ДАТА_РОЖДЕНИЯ
 FROM Н_ЛЮДИ
 GROUP BY Н_ЛЮДИ.ДАТА_РОЖДЕНИЯ
) AS ДатаРождения; 