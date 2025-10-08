package ejercicio4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class otro {

	public static void main(String[] args) {
		// Crear el HashMap con los productos y precios
		HashMap<String, Double> productos = new HashMap<>();
		productos.put("avena", 2.21);
		productos.put("garbanzos", 2.39);
		productos.put("tomate", 1.59);
		productos.put("jengibre", 3.13);
		productos.put("quinoa", 4.5);
		productos.put("guisantes", 1.6);

		// Crear listas para guardar los productos y cantidades introducidos
		ArrayList<String> listaProductos = new ArrayList<>();
		ArrayList<Integer> listaCantidades = new ArrayList<>();

		// Leer datos del usuario
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Producto: ");
			String producto = scanner.nextLine().toLowerCase();

			if (producto.equals("fin")) {
				break;
			}

			if (!productos.containsKey(producto)) {
				System.out.println("El producto no existe. Intente nuevamente.");
				continue;
			}

			System.out.print("Cantidad: ");
			int cantidad;
			try {
				cantidad = Integer.parseInt(scanner.nextLine());
				if (cantidad <= 0) {
					System.out.println("La cantidad debe ser mayor a 0. Intente nuevamente.");
					continue;
				}
			} catch (NumberFormatException e) {
				System.out.println("Por favor, introduzca un número válido.");
				continue;
			}

			listaProductos.add(producto);
			listaCantidades.add(cantidad);
		}

		// Mostrar factura
		System.out.println("\nProducto   Precio   Cantidad   Subtotal");
		System.out.println("---------------------------------------");

		double total = 0;
		for (int i = 0; i < listaProductos.size(); i++) {
			String producto = listaProductos.get(i);
			int cantidad = listaCantidades.get(i);
			double precio = productos.get(producto);
			double subtotal = precio * cantidad;

			System.out.printf("%-10s %.2f    %d          %.2f\n", producto, precio, cantidad, subtotal);
			total += subtotal;
		}

		System.out.println("---------------------------------------");
		System.out.printf("TOTAL: %.2f\n", total);

		scanner.close();
	}
}
