# 3. Вычислить общую ЗП всех Java разработчиков.

SELECT skills.*, sum(developers.salary) AS total_amount
FROM developers
JOIN developers_skills ON developers.id = developers_skills.developers_id
JOIN skills ON skills.id = developers_skills.skills_id
WHERE skills.name LIKE "JAva";

SELECT sum(developers.salary) 
FROM developers 
WHERE id IN (SELECT developers_id FROM developers_skills WHERE skills_id IN (SELECT id FROM skills WHERE skills.name LIKE "JAVA"));


SELECT skills.*, sum(developers.salary) 
FROM developers, skills, developers_skills WHERE
skills.name LIKE "java" AND developers_skills.skills_id= skills.id AND developers_skills.developers_id= developers.id