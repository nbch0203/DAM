--                                                                 Ra Programacion avanzada


--									                         	Trigger

-- Primer trigger
insert into rental (rental_date,inventory_id,customer_id,return_date,staff_id,last_update) values (now(),'1','431',now(),'1',now());

delimiter //
create trigger alquileres
before insert on rental
for each row
begin
declare v_select int;
declare v_select2 int;
declare message_text varchar(255);


set v_select2=(select i.inventory_id from  inventory i where i.inventory_id=new.inventory_id);

set v_select=(select r.return_date from rental r join  inventory i on r.inventory_id=i.inventory_id where r.rental_id=new.rental_id);

if v_select is null  then
signal sqlstate '45000';
set message_text='No se puede insertar un nuevo rental si ya existe';

end if;

end//
drop trigger alquileres;
select * from rental order by inventory_id asc;
select * from inventory;



--   Segundo trigger


delimiter //
create trigger examen
before delete on customer
for each row
begin

declare message_text varchar(255);

declare v_select int default 0;

set v_select =(select c.customer_id from customer c 
join rental r on c.customer_id=r.customer_id 
where datediff(curdate(),r.rental_date)>0 group by c.customer_id);

if v_select<0 then
signal sqlstate '45000';
set message_text='No se puede eliminar a un cliente con alquileres activos';

end if;
end//


drop trigger examen;

insert into customer (customer_id,store_id,first_name,last_name,email,address_id,active,create_date,last_update) values ('700','1','a','e','e','1','1',now(),now());

select * from customer where customer_id=700 ;

select datediff(curdate(),r.rental_date) from rental r;




-- 													Evento


-- Primer evento 

create table top_rented_films_mothly(
id int auto_increment primary key,
film_id smallint unsigned not null,
title varchar(255) not null,
rental_count int not null,
report_date date not null
);

delimiter //
create event peliculas
on schedule at '2025-06-01 00:01:00'
do
begin

insert into top_rented_films_mothly (film_id,title,rental_count,report_date)
select f.film_id,f.title,count(f.film_id) as cantidad,now() from film f 
join inventory i on f.film_id=i.film_id 
join rental r on i.inventory_id=r.inventory_id 
group by f.film_id order by cantidad desc limit 10;

end;

select * from top_rented_films_mothly;



--         Segundo evento


create table monthly_store_revenue(
id int auto_increment primary key,
store_id tinyint unsigned not null,
revenue_month date not null,
total_revenue decimal(10,2) not null,
created_at timestamp default current_timestamp
);
delimiter //
create event revenue_shops
on schedule at '2025-06-01 00:01:00'
do
begin
insert into monthly_store_revenue (store_id,revenue_month,total_revenue,created_at)
select str.store_id,(select date_sub('2025-06-01 00:01:00',interval 31 day)
),sum(p.amount),now() from payment p 
join staff st on p.staff_id=st.staff_id 
join store str on st.store_id= str.store_id group by str.store_id;

end//

drop table monthly_store_revenue;
select * from monthly_store_revenue;