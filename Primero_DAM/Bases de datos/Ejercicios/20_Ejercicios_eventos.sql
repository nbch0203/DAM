-- 20 Ejercicios de Eventos en MySQL (Sakila)
 
-- ----------------------------------- Nivel Basico ----------------------------------------

-- Crear un evento que inserte la fecha y hora actual cada minuto.

delimiter //
create event evento_fecha_hora 
on schedule every 1 minute
starts current_timestamp
do
begin
update customer_backup_events set last_update=now() where customer_id ='1';
end //

drop event evento_fecha_hora;

select customer_id,last_update from customer_backup_events where customer_id='1';

-- Crear un evento que inserte un mensaje en una tabla log_eventos cada hora.

create table log_eventos(
id_evento smallint primary key auto_increment not null,
log varchar(255)
);

delimiter //
create event insertar_log
on schedule every 1 hour
starts current_timestamp
do 
begin

insert into log_eventos (log)values ('Se ha ejecutado el evento');
end//

drop event insertar_log;
select * from log_eventos;

-- Habilitar el programador de eventos (event_scheduler).

SET GLOBAL event_scheduler = ON;

-- Crear un evento que corra una sola vez dentro de 10 minutos e inserte una fila en log_eventos.

delimiter //
create event insertar_en_log_eventos
on schedule every 10 minute
starts current_timestamp
do 
begin
declare contador int default'0';

if contador=0 then
insert into log_eventos (log) values ('Se ha ejecutado el evento unico');
end if;
set contador=1;
end//

drop event insertar_en_log_eventos;
select * from log_eventos;

-- Crear un evento que borre registros de payment donde amount = 0 una vez al día.

delimiter //
create event borrar_payment
on schedule every 1 day
starts current_timestamp
do 
begin
declare v_select int;

set v_select=(select max(payment_id) from payment where amount=0);

if v_select then
delete from payment where amount='0'; 
end if;

end//

drop event borrar_payment;
select payment_id,amount from payment where payment_id ='16050';

-- Crear un evento deshabilitado por defecto.

delimiter //
create event evento_desabilitado
on schedule every 1 minute
starts current_timestamp
disable
do
begin

insert into log_eventos (log) values ('evento desabilitado por defecto');

end//
drop event evento_desabilitado;
select * from log_eventos;

-- Listar todos los eventos definidos en la base de datos Sakila.

show events;


-- -------------------------------------- Nivel Intermedio ----------------------------------


-- Crear un evento que, cada semana, cuente cuántas películas están alquiladas (rental) y lo registre en log_eventos.

delimiter //
create event contar_alquileres
on schedule every 1 week
starts current_timestamp
do
begin

declare v_select int default 0;
set v_select=(select count(rental_id) from rental );

insert into log_eventos (log) values (v_select);

end//

-- Crear un evento que actualice el campo last_update de todos los actores una vez por día.
delimiter //
create event actualizar_fecha
on schedule every 1 day
starts current_timestamp()
do
begin

update copia_actores set last_update =now();

end//

select * from copia_actores;

drop event actualizar_fecha;

-- Crear un evento que borre registros antiguos de la tabla rental (más de 5 años).
delimiter //
create event borrar_registros_rental
on schedule every 1 day
starts current_timestamp()
do
begin
declare v_select timestamp;
set v_select =(select timestampdiff(year,curdate(),last_update) from copia_rental);

delete from copia_rental where v_select>5;
end//
drop event borrar_registros_rental;
select * from copia_rental;
-- Crear una tabla log_bajas y un evento que mueva clientes inactivos de customer allí cada mes.

create table log_bajas(select * from customer);
select * from log_bajas;
delete from log_bajas where customer_id is not null; 

delimiter //
create event insertar_clientes
on schedule every 1 month
starts current_timestamp()
do
begin

insert into log_bajas values((select * from customer where active='1'));

end//


-- Crear un evento que actualice automáticamente el estado de los pagos (payment) con fecha futura a “pendiente”.
delimiter //
create event actualizar_pagos
on schedule every 1 day
starts current_timestamp()
do
begin

declare v_select timestamp;
set v_select=(select timestampdiff());
select * from copia_payment;
end//

-- Crear un evento que actualice cada día los precios de películas de más de 10 años con un 10% de descuento.
delimiter //
create event actualizar_precios
on schedule every 1 day
starts current_timestamp()
do
begin

declare v_select smallint;

-- devuelve mas de una fila
set v_select=(select film_id from film where (select timestampdiff(year,now(),release_year)>'10' from film) group by film_id);

end//

-- Crear un evento que inserte estadísticas de uso (películas más alquiladas) cada semana en una tabla de resumen.

-- listar la cliente _id nombre c nombre producto y cantidad veces compradas en amazon