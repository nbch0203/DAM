use bbdd;

select * from cliente;
select * from pedido;
delete from cliente where nombre='1';
select * from cliente;
select * from pedido;


use bbdd;
select * from cliente;
select * from pedido;
update cliente
set id= 8,nombre ='Nixon Bolivar',email='nixonbcruzh@gmail.com',telefono='611254628'
 WHERE id='3';
select * from cliente;
select * from pedido;


use bbdd;
select count(distinct nombre)from cliente;

