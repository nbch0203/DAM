package ejercicio2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListaProductos {
	 List<Producto> Productos= new ArrayList<Producto>();

	public ListaProductos(List<Producto> productos) {
		Productos = productos;
	}

	public List<Producto> getProductos() {
		return Productos;
	}

	public void setProductos(List<Producto> productos) {
		Productos = productos;
	}
	 
	public void add(Producto o) {
		Productos.add(o);
	}
	
	
//	Scanner sc = new Scanner(System.in);
//	System.out.println("**********Cantidad*****Precio*******Total");
//
//	String productos = sc.next();
//
//	while (productos != "FIN") {
//		while (it.hasNext()) {
//			System.out.println(it.next());
//		}
//	}
//	sc.close();
//
//}
	 
}
