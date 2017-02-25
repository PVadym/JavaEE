#2. Найти самый дорогой проект (исходя из ЗП разработчиков).

SELECT projects.*, sum(developers.salary) summ FROM projects
JOIN projects_developers ON projects_developers.projects_id = projects.id
JOIN developers on developers.id = projects_developers.developers_id
group by projects.name ORDER by summ DESC LIMIT 1;

