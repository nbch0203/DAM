use sakila;
--Obtener una lista de clientes que han alquilado peliculas de la categoria ACTION y que vivien en London

select cus.customer_id,cus.first_name,cus.last_name,cus.email,ad.address,c.city 
from customer cus
join address ad on cus.address_id=ad.address_id
join city c on ad.city_id=c.city_id
join rental r on cus.customer_id=r.customer_id
join inventory inven on  r.inventory_id=inven.inventory_id
where c.city='London' ANd
( select f.film_id from film_category f
join category c on f.category_id = c.category_id
where c.name = 'Action') ;

-- Solucion del primer ejercicio

use sakila

select c.customer_id,c.first_name,c.last_name,c.email,a.address,c2.city from customer c
join address a on a.address_id=c.address_id
join city c2 on c2.city_id=a.city_id
where c2.city='London'
& c.customer_id in (
SELECT c.customer_id FROM customer c
JOIN rental r ON c.customer_id=r.customer_id
JOIN inventory i ON r.inventory_id = i.inventory_id
JOIN film f ON i.film_id =f.film_id
JOIN film_category fc ON fc.film_id = f.film_id
JOIN category ct ON fc.category_id =ct.category_id
WHERE ct.name='Action');


--

use sakila;

select invent.film_id,f.title,ftext.description,ca.name
from film f join inventory invent  on f.film_id=invent.film_id
join film_text ftext on invent.film_id=ftext.film_id
join film_category fcat on f.film_id=fcat.film_id
join category ca on fcat.category_id=ca.category_id
having cantidad > 10 in(
select count(inven.film_id) as cantidad from inventory inven
join rental r on inven.inventory_id=r.inventory_id
group by inven.film_id);


--Solucion 

use sakila;

select invent.film_id,f.title,ftext.description,ca.name
from film f join inventory invent  on f.film_id=invent.film_id
join film_text ftext on invent.film_id=ftext.film_id
join film_category fcat on f.film_id=fcat.film_id
join category ca on fcat.category_id=ca.category_id
WHERE c.name='drama' AND (
select count(inven.film_id) as cantidad from inventory inven
join rental r on inven.inventory_id=r.inventory_id
group by inven.film_id
HAVING count (f.film_id)>10);

--Obtener una lista de clientes que han alquilado peliculas que tienen
--una duración mayor de 120 min y que pertenecen a la categoria
use sakila;

SELECT c.customer_id,c.first_name,c.last_name FROM customer c
JOIN rental r ON  r.customer_id =c.customer_id
JOIN inventory i ON i.inventory_id=r.inventory_id
JOIN film f ON f.film_id = i.film_id
WHERE f.film_id IN  (SELECT fc.film_id FROM film_category fc
JOIN category c ON c.category_id =fc.category_id
WHERE c.name ='comedy') AND f.LENGTH >120;

--Otener una lista de tiendas que han alquilado costo remplazo > 20 y son Sci-Fi
SELECT *FROM store s
JOIN address a ON a.address_id =s.address_id
JOIN city c ON c.city_id =a.city_id
JOIN customer c2 ON s.store_id=c2.
JOIN rental r ON r.customer_id=c2.customer_id
JOIN inventory i ON i.inventory_id =r.inventory_id
(SELECT * FROM film f
JOIN film_category fc ON fc.film_id = f.film_id
JOIN category c ON c.category_id = fc.category_id
WHERE f.replacement_cost >20 AND c.name ='Sci-Fi');



show processlist;