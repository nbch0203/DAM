package dao;

import modelos.Pedido;

public interface PedidoDAO {

	public Pedido obtener(Long id);

	public Pedido crear(Pedido p);

	public void actualizar(Pedido p);

	public void eliminar(Long id);

}
