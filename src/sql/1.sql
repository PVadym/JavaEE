# 1. Добавить разаработчикам поле (salary - зарплата).

ALTER TABLE developers ADD COLUMN salary INT;

UPDATE developers SET salary=CASE
WHEN id=2 THEN 1000
WHEN id%3=0 THEN 1500
WHEN name LIKE "%V%" THEN 1750
    WHEN id%5=0 THEN 2150
    WHEN email LIKE "%d%" THEN 2215
    ELSE 2000
    END;