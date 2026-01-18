package servicios;

import dao.ClienteDAO;

import dao.ClienteDAOImpl;
import modelos.Cliente;
import modelos.Direccion;
import modelos.Pedido;

import java.util.List;

public class ClienteService {

	private final ClienteDAO dao = new ClienteDAOImpl();

	// =======================
	// VALIDACIONES
	// =======================

	private void validarCliente(Cliente c) {
		if (c == null)
			throw new IllegalArgumentException("El cliente no puede ser null");

		if (c.getNombre() == null || c.getNombre().trim().equals(""))
			throw new IllegalArgumentException("El nombre del cliente es obligatorio");
	}

	private void validarPedido(Pedido p) {
		if (p == null)
			throw new IllegalArgumentException("El pedido no puede ser null");

		if (p.getDescripcion() == null || p.getDescripcion().trim().equals(""))
			throw new IllegalArgumentException("La descripción del pedido es obligatoria");
	}

	// =======================
	// OPERACIONES
	// =======================

	public Long crearClienteCompleto(Cliente c, Direccion d, List<Pedido> pedidos) {

		validarCliente(c);

		c.setDireccion(d);

		int i = 0;
		while (i < pedidos.size()) {
			Pedido p = pedidos.get(i);
			validarPedido(p);
			c.addPedido(p);
			i++;
		}

		dao.crear(c);
		return c.getId();
	}

	public Cliente obtenerCliente(Long id) {
		return dao.obtenerClienteConInicializacion(id);
//        return dao.obtener(id);
	}

	public void eliminarDireccion(Long idCliente) {
		Cliente c = dao.obtener(idCliente);
		if (c == null)
			throw new IllegalArgumentException("No existe un cliente con ese ID");

		c.setDireccion(null); // orphanRemoval
		dao.actualizar(c);
	}

	public void eliminarPedido(Long idCliente, int index) {
		Cliente c = dao.obtener(idCliente);
		if (c == null)
			throw new IllegalArgumentException("Cliente no encontrado");

		if (index < 0 || index >= c.getPedidos().size())
			throw new IllegalArgumentException("Índice de pedido no válido");

		Pedido p = c.getPedidos().get(index);
		c.removePedido(p); // orphanRemoval

		dao.actualizar(c);
	}

	public void modificarNombre(Long idCliente, String nuevoNombre) {
		Cliente c = dao.obtener(idCliente);
		if (c == null)
			throw new IllegalArgumentException("Cliente no encontrado");

		if (nuevoNombre == null || nuevoNombre.trim().equals(""))
			throw new IllegalArgumentException("El nuevo nombre no puede estar vacío");

		c.setNombre(nuevoNombre);
		dao.actualizar(c);
	}

	public void borrarCliente(Long idCliente) {
		dao.eliminar(idCliente);
	}
}
