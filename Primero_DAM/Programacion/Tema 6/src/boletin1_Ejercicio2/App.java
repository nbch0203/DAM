package boletin1_Ejercicio2;

import java.util.ArrayList;
import java.util.Iterator;

public class App {

	static ArrayList<Mascotas> ListaMascotas = new ArrayList<Mascotas>();

	public void insertarAnimales() {
		ListaMascotas.add(new Gato("Codi", 2, "vivo", "10/2/2020", "tricolor", "si"));
		ListaMascotas.add(new Loro("pepe", 1, "muerto", "1/5/2024", "africa", "hola"));
	}

	public void mostrarAnimales() {

		for (Mascotas mascotas : ListaMascotas) {
			System.out.println(mascotas.muestra());
			System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		}
	}

	public void mostrarAnimal(String nombre) {
		for (Mascotas mascotas : ListaMascotas) {
			if (mascotas.getNombre().equalsIgnoreCase(nombre)) {
				System.out.println(mascotas.muestra());
				System.out.println(
						"--------------------------------------------------------------------------------------------------------------------s");
			}
		}
	}

	public void eliminarAnimales(String nombre) {
		for (Mascotas mascotas : ListaMascotas) {
			if (mascotas.getNombre().equalsIgnoreCase(nombre)) {
				mascotas = null;
				System.out.println("Se ha eliminado el animal : " + nombre + " con exito");
			}
		}
	}

	public void vaciarInventario() {
		for (Mascotas mascotas : ListaMascotas) {
			ListaMascotas.remove(mascotas);
			
		}
		System.out.println("\n"+"La lista de animales se ha vaciado con exito");
	}
	

}
