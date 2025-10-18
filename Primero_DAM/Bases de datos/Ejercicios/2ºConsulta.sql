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