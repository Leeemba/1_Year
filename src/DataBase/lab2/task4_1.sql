SELECT u.ОТЧЕСТВО,o.КОРОТКОЕ_ИМЯ, COUNT(*)
FROM Н_ЛЮДИ AS u, Н_ОТДЕЛЫ AS o,             
WHERE КОРОТКОЕ_ИМЯ='ВТ'            
GROUP BY ОТЧЕСТВО, КОРОТКОЕ_ИМЯ
HAVING COUNT(*) = 10 





SELECT u.ОТЧЕСТВО
FROM Н_ЛЮДИ AS u, 
WHERE u.ИД IN(
    SELECT ЧЛВК_ИД
    FROM Н_УЧЕНИКИ
    WHERE ПЛАН_ИД IN(
        SELECT ИД
        FROM Н_ПЛАНЫ
        WHERE ОТД_ИД IN(
            SELECT ИД
            FROM Н_ОТДЕЛЫ
            WHERE КОРОТКОЕ_ИМЯ ='ВТ'
        )
    )

)                         
GROUP BY ОТЧЕСТВО, КОРОТКОЕ_ИМЯ
HAVING COUNT(*) = 10 






SELECT u.ОТЧЕСТВО, u.ИД
FROM Н_ЛЮДИ AS u
JOIN Н_УЧЕНИКИ AS uch ON u.ИД = uch.ЧЛВК_ИД
JOIN Н_ПЛАНЫ AS pl ON uch.ПЛАН_ИД = pl.ИД
JOIN Н_ОТДЕЛЫ AS otd ON pl.ОТД_ИД = otd.ИД
WHERE otd.КОРОТКОЕ_ИМЯ = 'ВТ'
GROUP BY u.ОТЧЕСТВО, u.ИД
HAVING COUNT(*) = 10;


SELECT "ОТЧЕСТВА".ОТЧЕСТВА
FROM(SELECT Н_ЛЮДИ.ОТЧЕСТВА,COUNT("Н_ЛЮДИ"))