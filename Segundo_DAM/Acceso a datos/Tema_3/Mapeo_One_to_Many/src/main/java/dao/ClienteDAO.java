package dao;

import modelos.Cliente;
import modelos.Pedido;

public interface ClienteDAO {
	public void crear(Cliente cliente);

	public void setDireccion(DireccionDAO direccion);

	public void addPedido(Pedido p);

	public Cliente obtenerClienteConInicializacion(Long id);

	public Cliente obtener(Long idCliente);

	public void actualizar(Cliente c);

	public void eliminar(Long idCliente);
	
	

}
