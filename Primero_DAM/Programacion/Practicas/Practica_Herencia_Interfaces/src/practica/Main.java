package practica;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		//ArayList para guardar los dos tipos de producto 
		ArrayList<Producto> inventario = new ArrayList<Producto>();

		//Agregamos los dos tipos de productos dentro del ArrayList
		inventario.add(new Electronico("2441F", "Ordena", 4.25, 40, 13));
		inventario.add(new Electronico("22121F", "Ordena", 4.25, 40, 9));
		inventario.add(new Electronico("232F", "Ordena", 4.25, 40, 2));
		inventario.add(new Electronico("1F", "Ordena", 4.25, 40, 14));
		inventario.add(new Perecedero("22P", "Manzana", 1.2, 200, 4));
		inventario.add(new Perecedero("23P", "Manzana", 1.2, 200, 6));
		inventario.add(new Perecedero("24P", "Manzana", 1.2, 200, 55));
		inventario.add(new Perecedero("25P", "Manzana", 1.2, 200, 2));
		
		//Bucle para mostrar la informacion
		for (Producto producto : inventario) {
			producto.mostrarInfo();
		}
		//Espacio
		System.out.println("\n");
		
		//Probamos los dos metodos heredados por las dos interfaces
		inventario.get(5).vender(20);
		inventario.get(3).reabastecer(300);
		
		//Espacio
		System.out.println("\n");
		
		//Calculamos el descuento 
		for (Producto producto : inventario) {
			producto.calcularDescuento();
		}
		//Espacio
		System.out.println("\n");
		//Mostramos los productos con el descuento aplicado
		for (Producto producto : inventario) {
			producto.mostrarInfo();
		}

	}	
}
