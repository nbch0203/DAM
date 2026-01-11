use bbdd;
select cliente.nombre,pedido.fecha,pedido.total
from cliente 
join pedido

where month(pedido.fecha)='2';