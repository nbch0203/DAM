
-- Alquileres por encima de la media

select r.rental_id,p.amount from rental r join payment p on r.rental_id = p.rental_id
where p.amount > (Select avg(p.amount) from payment p)
;

-- Clientes con mas peliculas alquiladas que algino de los que empiezan por 'a'
select c.customer_id,c.first_name,count(r.rental_id) as total_alquiladas from customer c
join rental r on r.customer_id = c.customer_id
group by c.customer_id
having total_alquiladas > any
(select count(r.rental_id) 
from customer c 
join rental r on r.customer_id = c.customer_id
where c.first_name like 'A%'
group by c.customer_id);

-- Clientes que tengan mas peliculas que los que empiezan por alter

-- Actores que no hayan trabajado en peliculas de accion
select a.first_name from actor 

(select a.actor_id from actor a
join film_actor f on f.actor_id=a.actor_id
join film fi on fi.film_id = f.film_id
join film_category fica on fica.film_id = fi.film_id
join category cat on cat.category_id= fica.category_id
where cat.name = 'ACTION'
group by a.actor_id
);

SELECT * FROM actor a where a.actor_id not in
(select fa.actor_id
from film_actor fa
join film_category fc on fc.film_id=fa.film_id
join category c on c.category_id=fc.category_id
where c.name like 'ACTION');