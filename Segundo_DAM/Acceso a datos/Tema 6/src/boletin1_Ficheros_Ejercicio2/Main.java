package boletin1_Ficheros_Ejercicio2;

import java.io.File;
import java.util.Scanner;

public class Main {

	public static void muestraInfoRuta(File ruta) {

		if (ruta.isFile()) {
			System.out.println(ruta.getName());
		} else if (ruta.isDirectory()) {
			String[] archivos = ruta.list();
			for (int i = 0; i < archivos.length; i++) {
//				System.out.println(archivos[i].);
			}

		}
	}

	public static void iniciar() {

		String[] archivos;
		Scanner entrada = new Scanner(System.in);
		while (true) {
			System.out.println("Introduce el nombre del fichero o directorio");
			String ruta = entrada.next();
			File f = new File(ruta);
			archivos = f.list();
			try {
				for (int i = 0; i < archivos.length; i++) {
					muestraInfoRuta(f);
					break;
					
				}
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public static void main(String[] args) {
		iniciar();
	}
}
