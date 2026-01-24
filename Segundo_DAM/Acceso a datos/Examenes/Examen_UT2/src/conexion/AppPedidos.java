package conexion;

import gestor.GestorBD;

public class AppPedidos {

	public static void main(String[] args) {
		GestorBD gestor = new GestorBD();

		// Criterio d: 1. Conexión
		gestor.conectar();
		if (gestor.getConn() == null) //si falla la conexión que no intente seguir avanzando en la ejecución
			return;

		// --- TAREA 1 (Criterio k) y TAREA 2 (Criterio f, j) ---
		// Tarea 1: Consultar stock después de pedidos
		gestor.consultarStock(1); // Teclado (debe ser 25)
		gestor.consultarStock(2); // Monitor (debe ser 20)
		
		//Tarea 2:
		// Realizar pedidos (Se crean logs y se modifican stocks)
		// Se pasa el primer parámtetro el idProducto y el segundo la cantidad
		gestor.procesarPedido(1, 5); // OK: Teclado (50 -> 45)
		gestor.procesarPedido(2, 10); // OK: Monitor (30 -> 20)
		gestor.procesarPedido(1, 20); // OK: Teclado (45 -> 25). Demanda total de Teclados: 25.

		// Demostrar deshacer cambios (Intentar pedir stock de un producto que no existe o
		// con error)
		gestor.procesarPedido(99, 5); // Falla UPDATE, deshacer cambios.


		// --- TAREA 4 (Criterio h, g) Consulta 1---
		gestor.listarPedidosDetallados();

		// --- TAREA 5 (Criterio h, g) Consulta 2---
		// Productos cuya demanda total sea mayor a 15
		gestor.productosAltaDemanda(15);

		// --- TAREA 3 (Criterio i): Desconexión y Cierre de Recursos ---
		gestor.desconectar();
	}
}