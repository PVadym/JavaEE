# 4. Добавить поле (cost - стоимость) в таблицу Projects .

ALTER TABLE projects ADD COLUMN cost INT;
SET SQL_SAFE_UPDATES = 0;
UPDATE projects SET cost = 152000 WHERE name LIKE "%FUTU%" ;
UPDATE projects SET cost = 54510 WHERE name LIKE "%BUS%" ;
UPDATE projects SET cost = 128610 WHERE name LIKE "%NEW%" ;
UPDATE projects SET cost = 89500 WHERE name LIKE "%Big%" ;
UPDATE projects SET cost = 102000 WHERE name LIKE "%tree%" ;