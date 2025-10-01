package ejercicio4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class otroo {

	public static void main(String[] args) {

		HashMap<String, Double> productos = new HashMap<>();
		productos.put("avena", 2.21);
		productos.put("garbanzos", 2.39);
		productos.put("tomate", 1.59);
		productos.put("jengibre", 3.13);
		productos.put("quinoa", 4.50);
		productos.put("guisantes", 1.60);

		ArrayList<String> listaProductos = new ArrayList<>();
		ArrayList<Integer> listaCantidades = new ArrayList<>();

		Scanner entrada = new Scanner(System.in);
		String nombreProducto;
		int cantidad;
		double total = 0.0;

		// Entrada de datos
		while (true) {
			System.out.println("Introduce el producto (o 'fin' para terminar):");
			nombreProducto = entrada.nextLine().toLowerCase();

			if (nombreProducto.equals("fin")) {
				break;
			}

			if (productos.containsKey(nombreProducto)) {
				System.out.println("Introduce la cantidad:");
				cantidad = entrada.nextInt();
				entrada.nextLine(); // Consumir el salto de línea restante

				listaProductos.add(nombreProducto);
				listaCantidades.add(cantidad);
			} else {
				System.out.println("Producto no válido, intenta nuevamente.");
			}
		}

		// Mostrar factura
		System.out.println("\nProducto Precio Cantidad Subtotal");
		System.out.println("--------------------------------------");

		for (int i = 0; i < listaProductos.size(); i++) {
			String producto = listaProductos.get(i);
			int cantidadProducto = listaCantidades.get(i);
			double precio = productos.get(producto);
			double subtotal = precio * cantidadProducto;

			System.out.printf("%-10s %.2f %8d %10.2f%n", producto, precio, cantidadProducto, subtotal);

			total += subtotal;
		}

		System.out.println("--------------------------------------");
		System.out.printf("TOTAL: %.2f%n", total);

		entrada.close();
	}
}
