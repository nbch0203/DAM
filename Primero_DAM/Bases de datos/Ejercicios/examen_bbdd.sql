create table customer_balance(
customer_id  int not null,
total_spent double default 0.0,
last_update timestamp
);

select * from customer_balance;
delimiter //
create trigger trigg 
after insert on payment
for each row
begin

declare v_select int;
declare v_select2 int;

select customer_id into v_select from customer_balance where customer_id = customer_id;
select customer_id into v_select2 from payment where customer_id = v_select;

if v_select2=v_select then
 update customer_balance set customer_id=new.customer_id,total_spent=(select sum(p.amount) from payment join customer c on p.customer_id=c.customer_id),now();
end if;
end//

delimiter //
create function NumeroClientesPorCiudad(ciudad varchar(50))
returns int
reads sql data
begin
return (select count(customer_id) from customer c
join address ad on c.address_id=ad.address_id
join city ci on ci.city_id=ad.city_id
where ci.city='Dundee');
end//

drop function NumeroClientesPorCiudad;
select NumeroClientesPorCiudad('Dundee');
select * from city

delimiter //
create function NumeroAlquileresPorCliente (customerid int)
returns int
reads sql data
begin
return(select count(r.rental_id) from rental r 
join customer c on r.customer_id=c.customer_id
where c.customer_id=customerid);
end//
select NumeroAlquileresPorCliente(1);
drop function NumeroAlquileresPorCliente;


delimiter //
create function Gasto_total_Cliente (customerid int)
returns double
reads sql data
begin

return (select sum(p.amount) from payment p 
join customer c on p.customer_id=c.customer_id where c.customer_id=customerid);

end//

drop function Gasto_total_Cliente;
select Gasto_total_Cliente(1)


delimiter //
create procedure procedimiento ()
begin
declare done boolean default false;
declare v_customers int default 0;
declare cur cursor for select customer_id from customer;
declare continue handler for not found set done=true;
create temporary table if not exists gasto_ciudad(
ciudad varchar(50),
total_gasto double
);
delete from gasto_ciudad;

open cur;
read_loop:loop
fetch cur into v_customers;
if done then
leave read_loop;
end if;

insert into gasto_ciudad (ciudad,total_gasto) values ((select city from customer c
join address ad on c.address_id=ad.address_id
join city ci on ci.city_id=ad.city_id
where c.customer_id=v_customers),(select Gasto_total_Cliente(v_customers)));
end loop;
close cur;
end//

select * from gasto_ciudad order by total_gasto desc;
drop procedure procedimiento
call procedimiento;
set sql_safe_updates=off;