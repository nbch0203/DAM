package Parte2Ejercicio6;

public class Ejercicio6Class {
	EjercicioClass2 tomate = new EjercicioClass2("tomate", 2, 0.1);
	EjercicioClass2 pera = new EjercicioClass2("Pera", 1.50, 0.17);
	EjercicioClass2 manzana = new EjercicioClass2("manzana", 3.30, 0.1);
	String nombre;
	String direccion;
	int NIF;
	boolean decision;	
	int cantidad;
	String producto;
	int cantidadTomate = 0;
	int cantidadPera = 0;
	int cantidadManzana = 0;
	// Constructor

	public Ejercicio6Class(String nombre, String direccion, int NIF) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.NIF = NIF;
	}

//	 1ºmetodo añadir y quitar productos

	public void realizaPedido(boolean decision, int cantidad, String producto) {
		this.cantidad = cantidad;
		this.producto = producto;
		if (decision = true) {
			if (producto.equalsIgnoreCase(tomate.nombre)) {
				cantidadTomate += cantidad;
				System.out.println("Se han añadido: " + cantidad + " " + tomate.nombre + " " + tomate.IVA + "% de IVA");
			}
			if (producto.equalsIgnoreCase(pera.nombre)) {
				cantidadPera += cantidad;
				System.out.println("Se han añadido: " + cantidad + " " + pera.nombre + " " + pera.IVA + "% de IVA");
			}
			if (producto.equalsIgnoreCase(manzana.nombre)) {
				cantidadManzana += cantidad;
				System.out
						.println("Se han añadido: " + cantidad + " " + manzana.nombre + " " + manzana.IVA + "% de IVA");
			}

		}
		if (decision = false) {
			System.out.println("----------------------------------------------------------------");
			if (producto.equalsIgnoreCase(tomate.nombre)) {
				cantidadTomate -= cantidad;
				System.out.println("Se han quitado: " + cantidad + " " + tomate.nombre);
			}
			if (producto.equalsIgnoreCase(pera.nombre)) {
				cantidadPera -= cantidad;
				System.out.println("Se han quitado: " + cantidad + " " + pera.nombre);
			}
			if (producto.equalsIgnoreCase(manzana.nombre)) {
				cantidadManzana -= cantidad;
				System.out.println("Se han quitado: " + cantidad + " " + manzana.nombre + " " + manzana.IVA + "% de IVA");
			}
		}

	}

//	Get cantidad de productos
	public int getCantidad() {
		return cantidad;
	}

//	Mostrar la lista de la compra con cada producto

	public void mostrarLista(boolean mostrar) {
		double preciototal = 0;
		if (mostrar) {
			if (cantidadTomate >= 0) {
				System.out.println("----------------------------------------------------------------");
				System.out.println(cantidadTomate + " " + tomate.nombre + " " + (tomate.IVA * 100) + "% de IVA");
				preciototal += cantidadTomate * tomate.preciobase * (1 + tomate.IVA);
			}
			if (cantidadPera >= 0) {
				System.out.println(cantidadPera + " " + pera.nombre + " " + (pera.IVA * 100) + "% de IVA");
				preciototal += cantidadPera * pera.preciobase * (1 + pera.IVA);
			}
			if (cantidadManzana >= 0) {
				System.out.println(cantidadManzana + " " + manzana.nombre + " " + (manzana.IVA * 100) + "% de IVA");
				preciototal += cantidadManzana * manzana.preciobase * (1 + manzana.IVA);
			}
			System.out.println("------------------------------------------------------------------");
			System.out.printf("El precio total es de: %.2f\n", preciototal);
		} else {
			System.out.println("No se puede mostrar la lista si no hay productos");
		}
	}
}
