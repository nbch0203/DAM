alter table customer
add column num_rentals int default 0,
add column sum_payments decimal(10,2) default 0.00,
add column balance decimal(10,2) default 0.00; -- numero de alquileres - num pagos


-- El trigger no ha funcionado pero los select si funcionan
delimiter //
create trigger  examen_triggers
after insert on rental
for each row
begin

	declare calculo_total_pagos decimal(10,2);
	declare numero_total_alquileres int;
	declare balan int;
-- select para sacar la cantidad de rentas de cada cliente

select count(rental_id) into numero_total_alquileres from rental r
where r.customer_id= new.customer_id;

-- select para sacar el calculo total de pagos de cada cliente

select sum(amount) into calculo_total_pagos from payment p
where p.customer_id= new.customer_id;

-- insertamos los calculos sacados previamente 

insert into customer values(
num_rentals=numero_total_alquileres,sum_payments=calculo_total_pagos);

-- seteamos el valor del balance numero de alquileres- numero de pagos

set balan=numero_total_alquileres-(select count(payment_id) 
from payment p where p.customer_id=new.customer_id);

update customer set num_rentals=numero_total_alquileres,
sum_payments=calculo_total_pagos,
balance=balan,
last_update = now()
where customer_id=new.customer_id;

end//
Delimiter ;


-- Ver los cambios en el cliente
SELECT customer_id, first_name, last_name, num_rentals, sum_payments, balance 
FROM customer 
WHERE customer_id = 2;





-- --------------------------------------------------------------Indices---------------------------------------------------------------
/*
											Film

En la tabla film añadiria un indice a film_id,rental_duration,rental_date y por ultimo last_update
ya que cada una de esas columnas las solemos usar para identificar o sacar informacion, ya sea por
medio de un trigger que ya hemos creado o uno que sera de utilidad

											Payment

En la tabla payment añadiria un indice a payment_id,customer_id,staff_id,rental_id y amount,payment_date
añadiria un indice a todas estas columnas ya que por lo general nos interesa saber desde la cantidad cuando
y quien lo pago para llevar un registro logico de toda esa informacion , no añadiria last_update por que
no es tan relevante como el resto de informacion normalmente intentaras conseguir la informacion de cuando
se produjo el pago.


*/

CREATE VIEW pagos_realizados_por_cliente AS
SELECT 
    c.customer_id,
    c.first_name,
    c.last_name,
    COUNT(p.payment_id) AS total_de_pagos,
    MAX(p.payment_date) AS fecha_ultimo_pago,
    AVG(p.amount) AS promedio_de_pagos,
    SUM(p.amount) AS total_pagado
FROM customer c
JOIN payment p ON p.customer_id = c.customer_id
GROUP BY c.customer_id, c.first_name, c.last_name;

select * from pagos_realizados_por_cliente;
drop view pagos_realizados_por_cliente;