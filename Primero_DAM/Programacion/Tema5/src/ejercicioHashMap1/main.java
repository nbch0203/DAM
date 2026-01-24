package ejercicioHashMap1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class main {

	public static void main(String[] args) {

		String usuario;
		String contraseña;
		boolean acceso;
		HashMap<String, String> mapa = new HashMap<String, String>();

		Scanner sc = new Scanner(System.in);

		// Creacion de los usuarios y contraseñas

		mapa.put("nixon", "hola");
		mapa.put("antonio", "adios");
		mapa.put("floripondio", "entrada");
		do {
			System.out.println("Introduzca un usuario y contraseña");
			usuario = sc.nextLine();
			contraseña = sc.nextLine();

			if (mapa.containsKey(usuario)) {
				if (mapa.get(usuario).equals(contraseña)) {
					System.out.println("Ha accedido al área restringida");
				}
			}
		} while (acceso = false);
	}
}