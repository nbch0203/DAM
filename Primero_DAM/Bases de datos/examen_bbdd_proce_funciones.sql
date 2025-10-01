create table statitics(
estadisticas_id int not null auto_increment primary key,
entidad enum('film','store'),
id_entidad int not null,
total_rentals decimal(10,2),
total_income decimal(10,2),
date_recorded datetime
);

drop table estadisticas;
select * from statitics


--                                                          Funciones de las peliculas

delimiter //
create function total_rentals_by_film(p_film_id int)
returns int
deterministic
begin
	declare v_select int unsigned;
    
	select count(i.film_id) into v_select from inventory i join rental r on i.inventory_id=r.inventory_id;
	return v_select;
end//

drop function total_rentals_by_film;
select total_rentals_by_film(1);

---------------------------------------------------------------------------------------

delimiter //
create function total_income_by_film(p_film_id int)
returns double
reads sql data
begin
declare v_select decimal(5,2);
set v_select=(
select sum(p.amount) from payment p
join rental r on r.rental_id=p.rental_id
join inventory i on i.inventory_id=r.inventory_id where i.film_id=p_film_id);
return v_select;
end//

drop function total_income_by_film;
select total_income_by_film(1);

--                                                          Funciones de las tiendas

delimiter //
create function total_rentals_by_store(p_store_id int)
returns int
reads sql data
begin
declare v_select int;
select count(r.rental_id) into v_select from rental r
join inventory i on i.inventory_id=r.inventory_id
join store s on s.store_id=i.store_id
where s.store_id=p_store_id;
return v_select;
end //

drop function total_rentals_by_store;
select total_rentals_by_store(1);

delimiter //
create function total_income_by_store (p_store_id int)
returns double
reads sql data
begin
declare v_select double;

select sum(p.amount) into v_select from payment p
join rental r on p.rental_id=r.rental_id
join staff s on r.staff_id=s.staff_id
join store st on s.store_id = st.store_id
where st.store_id=p_store_id;
return v_select;
end//

select total_income_by_store(2);



-- 													  ----- Procedimiento -----
-- 															No funciona

delimiter //
create procedure save_statistics()
begin
	declare id_films smallint unsigned;
	declare id_store smallint unsigned;
  
    declare done boolean default 0;
    
    declare cur cursor for select film_id from film ;
    declare cuur cursor for select store_id from store;

	declare continue handler for sqlexception
		begin
	end;
    
    declare continue handler for sqlwarning
    begin
    end;
        
    declare continue handler for not found set done= true;

open cur;

read_loop:loop
fetch cur into id_films;

	if done then
		leave read_loop;
    end if;
 
insert into statitics (entidad,id_entidad,total_rentals,total_income,date_recorded) 
values ('film',id_films,(select total_rentals_by_film(id_films)),
(select total_income_by_film(id_films)),now());

end loop;
close cur;

open cuur;
read_lopp:loop
fetch cuur into id_store;
if done then
		leave read_lopp;
	end if;

insert into statitics (entidad,id_entidad,total_rentals,total_income,date_recorded) 
values ('store',id_store,(select total_rentals_store(id_store)),
(select total_income_store(id_store)),now());

end loop;
close cuur;
end//

select * from statitics