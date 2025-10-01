package ejercicio4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Double preciofinal = null;
		int cantidad = 0;

		HashMap<Integer, Producto> map = new HashMap<Integer, Producto>();
		ArrayList<String> listaproductos = new ArrayList<String>();
		List<Integer> listacantidad = new ArrayList<Integer>();

		Scanner entrada = new Scanner(System.in);

		Producto avena = new Producto("avena", 2.21);
		Producto Garbanzos = new Producto("Garbanzos", 2.39);
		Producto Tomate = new Producto("Tomate", 1.59);
		Producto Jengibre = new Producto("Jengibre", 3.13);
		Producto Quinoa = new Producto("Quinoa", 4.5);
		Producto Guisantes = new Producto("Guisantes", 1.6);

		map.put(1, avena);
		map.put(2, Garbanzos);
		map.put(3, Tomate);
		map.put(4, Jengibre);
		map.put(5, Quinoa);
		map.put(6, Guisantes);

		int entradaint = 0;
		String entradas;

		do {

			System.out.println("Introduce el Producto");
			entradas = entrada.nextLine();
			
			
			if (!entradas.equalsIgnoreCase("Fin")) {

				listaproductos.add(entradas);

				System.out.println("Introduce la cantidad");
				entradaint = entrada.nextInt();

				listacantidad.add(entradaint);
			} else {

				System.out.println("\t" + "Lista de la compra" + "\n");

				for (int i = 0; i < listaproductos.size(); i++) {
					System.out.println("Producto: " + listaproductos.get(i));
					for (int o = 0; o < listacantidad.size(); o++) {
						System.out.println("Cantidad: " + entradaint);
					}

				}
				System.out.println("Producto Precio Cantidad Subtotal");
				System.out.println("--------------------------------------");
//			for (Producto producto : lista) {
//				System.out
//						.println(producto.getNombre() + "\t" + producto.getPrecio() + "\t" + cantidad + "\t" + preciofinal);
//			}
//			System.out.println("--------------------------------------");
				//
//			for (Producto producto : lista) {
//				preciofinal = producto.getPrecio() * cantidad;
//			}
				System.out.println("TOTAL: " + preciofinal);
			}
			entrada.close();
		} while (true);

	}

}
