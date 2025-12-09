package servicios;

import dao.PedidoDAO;

import dao.PedidoDAOImpl;
import modelos.Pedido;

public class PedidoService {

	private final PedidoDAO dao = new PedidoDAOImpl();

	private void validar(Pedido p) {
		if (p == null)
			throw new IllegalArgumentException("El pedido no puede ser null");

		if (p.getDescripcion() == null || p.getDescripcion().trim().equals(""))
			throw new IllegalArgumentException("La descripción es obligatoria");
	}

	public Long crearPedido(Pedido p) {
		validar(p);
		dao.crear(p);
		return p.getId();
	}

	public Pedido obtenerPedido(Long id) {
		return dao.obtener(id);
	}

	public void actualizarPedido(Pedido p) {
		validar(p);
		dao.actualizar(p);
	}

	public void borrarPedido(Long id) {
		dao.eliminar(id);
	}
}
