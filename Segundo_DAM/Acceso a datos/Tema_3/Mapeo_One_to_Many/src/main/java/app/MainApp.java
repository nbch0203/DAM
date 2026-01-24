package app;

import modelos.Cliente;
import modelos.Direccion;
import modelos.Pedido;
import servicios.ClienteService;
import servicios.DireccionService;
import servicios.PedidoService;

import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ClienteService clienteService = new ClienteService();
		DireccionService direccionService = new DireccionService();
		PedidoService pedidoService = new PedidoService();

		int opcion = -1;

		while (opcion != 0) {
			System.out.println("\n======== MENU GENERAL ========");
			System.out.println("1. CRUD Cliente");
			System.out.println("2. CRUD Dirección");
			System.out.println("3. CRUD Pedido");
			System.out.println("0. Salir");
			System.out.print("Opción: ");

			opcion = Integer.parseInt(sc.nextLine());

			if (opcion == 1)
				menuCliente(sc, clienteService);
			if (opcion == 2)
				menuDireccion(sc, direccionService);
			if (opcion == 3)
				menuPedido(sc, pedidoService);
		}

		sc.close();
		System.out.println("FIN");
	}

	// --------------------------------------
	// SUBMENÚ CLIENTE
	// --------------------------------------
	static void menuCliente(Scanner sc, ClienteService service) {

		Long idCliente = null;
		int opc = -1;

		while (opc != 0) {

			System.out.println("\n--- CLIENTE ---");
			System.out.println("1. Crear cliente");
			System.out.println("2. Mostrar cliente");
			System.out.println("3. Modificar nombre");
			System.out.println("4. Eliminar dirección del cliente");
			System.out.println("5. Eliminar pedido del cliente");
			System.out.println("6. Borrar cliente");
			System.out.println("0. Volver");
			System.out.print("Opción: ");

			opc = Integer.parseInt(sc.nextLine());

			if (opc == 1) {
				System.out.print("Nombre: ");
				Cliente c = new Cliente();
				c.setNombre(sc.nextLine());

				System.out.print("Calle: ");
				String calle = sc.nextLine();

				System.out.print("Ciudad: ");
				String ciudad = sc.nextLine();

				Direccion d = new Direccion(calle, ciudad);

				System.out.print("Número de pedidos: ");
				int num = Integer.parseInt(sc.nextLine());

				int i = 0;
				java.util.ArrayList<Pedido> pedidos = new java.util.ArrayList<Pedido>();

				while (i < num) {
					System.out.print("Descripción pedido " + (i + 1) + ": ");
					pedidos.add(new Pedido(sc.nextLine()));
					i++;
				}

				idCliente = service.crearClienteCompleto(c, d, pedidos);
				System.out.println("Cliente creado. ID: " + idCliente);
			}

			if (opc == 2) {
				System.out.print("ID cliente: ");
				Long id = Long.parseLong(sc.nextLine());
				Cliente c = service.obtenerCliente(id);
				if (c == null)
					System.out.println("No existe");
				else {
					System.out.println("Nombre: " + c.getNombre());

					System.out
							.println("Direccion: " + (c.getDireccion() != null ? c.getDireccion().getCalle() : "NULL"));

					System.out.println("Pedidos:");
					int i = 0;
					while (i < c.getPedidos().size()) {
						Pedido p = c.getPedidos().get(i);
						System.out.println("  " + i + ": " + p.getDescripcion());
						i++;
					}
				}
			}

			if (opc == 3) {
				System.out.print("ID cliente: ");
				Long id = Long.parseLong(sc.nextLine());

				System.out.print("Nuevo nombre: ");
				service.modificarNombre(id, sc.nextLine());
				System.out.println("Nombre actualizado.");
			}

			if (opc == 4) {
				System.out.print("ID cliente: ");
				Long id = Long.parseLong(sc.nextLine());

				service.eliminarDireccion(id);
				System.out.println("Dirección eliminada.");
			}

			if (opc == 5) {
				System.out.print("ID cliente: ");
				Long id = Long.parseLong(sc.nextLine());

				System.out.print("Índice del pedido: ");
				int idx = Integer.parseInt(sc.nextLine());

				service.eliminarPedido(id, idx);
				System.out.println("Pedido eliminado.");
			}

			if (opc == 6) {
				System.out.print("ID cliente: ");
				Long id = Long.parseLong(sc.nextLine());
				service.borrarCliente(id);
				System.out.println("Cliente borrado.");
			}
		}
	}

	// --------------------------------------
	// SUBMENÚ DIRECCION
	// --------------------------------------
	static void menuDireccion(Scanner sc, DireccionService service) {

		int opc = -1;

		while (opc != 0) {
			System.out.println("\n--- DIRECCIÓN ---");
			System.out.println("1. Crear dirección");
			System.out.println("2. Mostrar dirección");
			System.out.println("3. Modificar dirección");
			System.out.println("4. Eliminar dirección");
			System.out.println("0. Volver");
			System.out.print("Opción: ");

			opc = Integer.parseInt(sc.nextLine());

			if (opc == 1) {
				System.out.print("Calle: ");
				String calle = sc.nextLine();
				System.out.print("Ciudad: ");
				String ciudad = sc.nextLine();

				Direccion d = new Direccion(calle, ciudad);
				Long id = service.crearDireccion(d);
				System.out.println("Dirección creada con ID = " + id);
			}

			if (opc == 2) {
				System.out.print("ID dirección: ");
				Direccion d = service.obtenerDireccion(Long.parseLong(sc.nextLine()));
				if (d == null)
					System.out.println("No existe");
				else
					System.out.println(d.getCalle() + ", " + d.getCiudad());
			}

			if (opc == 3) {
				System.out.print("ID dirección: ");
				Long id = Long.parseLong(sc.nextLine());
				Direccion d = service.obtenerDireccion(id);
				if (d != null) {
					System.out.print("Nueva calle: ");
					d.setCalle(sc.nextLine());
					System.out.print("Nueva ciudad: ");
					d.setCiudad(sc.nextLine());
					service.actualizarDireccion(d);
					System.out.println("Actualizada.");
				}
			}

			if (opc == 4) {
				System.out.print("ID dirección: ");
				service.borrarDireccion(Long.parseLong(sc.nextLine()));
				System.out.println("Dirección eliminada.");
			}
		}
	}

	// --------------------------------------
	// SUBMENÚ PEDIDO
	// --------------------------------------
	static void menuPedido(Scanner sc, PedidoService service) {

		int opc = -1;

		while (opc != 0) {
			System.out.println("\n--- PEDIDO ---");
			System.out.println("1. Crear pedido");
			System.out.println("2. Mostrar pedido");
			System.out.println("3. Modificar pedido");
			System.out.println("4. Eliminar pedido");
			System.out.println("0. Volver");
			System.out.print("Opción: ");

			opc = Integer.parseInt(sc.nextLine());

			if (opc == 1) {
				System.out.print("Descripción: ");
				Pedido p = new Pedido(sc.nextLine());
				Long id = service.crearPedido(p);
				System.out.println("Pedido creado con ID = " + id);
			}

			if (opc == 2) {
				System.out.print("ID pedido: ");
				Pedido p = service.obtenerPedido(Long.parseLong(sc.nextLine()));
				if (p == null)
					System.out.println("No existe");
				else
					System.out.println("Descripcion: " + p.getDescripcion());
			}

			if (opc == 3) {
				System.out.print("ID pedido: ");
				Long id = Long.parseLong(sc.nextLine());
				Pedido p = service.obtenerPedido(id);
				if (p != null) {
					System.out.print("Nueva descripción: ");
					p.setDescripcion(sc.nextLine());
					service.actualizarPedido(p);
					System.out.println("Pedido actualizado.");
				}
			}

			if (opc == 4) {
				System.out.print("ID pedido: ");
				service.borrarPedido(Long.parseLong(sc.nextLine()));
				System.out.println("Pedido eliminado.");
			}
		}
	}
}
