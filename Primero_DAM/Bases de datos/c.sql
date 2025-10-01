use bbdd;
create view clientes_pe as
Select cliente.nombre,pedido.fecha
from cliente join pedido on cliente.id=pedido.cliente_id
group by cliente.nombre,pedido.fecha;
