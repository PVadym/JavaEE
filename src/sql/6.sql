# 6. Вычислить, среднюю ЗП программистов в проекте, который приносит наименьшую прибыль.

SELECT projects.*,avg(developers.salary) FROM developers
join projects_developers on projects_developers.developers_id= developers.id
join projects on projects.id = projects_developers.projects_id
group by projects.name order by projects.cost limit 1;