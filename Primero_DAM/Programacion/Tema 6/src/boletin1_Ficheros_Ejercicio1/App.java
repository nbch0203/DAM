package boletin1_Ficheros_Ejercicio1;

import java.io.File;
import java.util.Scanner;

public class App {

	public void iniciar() {

		String[] archivos;
		Scanner entrada = new Scanner(System.in);
		while (true) {
			System.out.println("Introduce el nombre del fichero o directorio");
			String nombre = entrada.next();
			File f = new File(nombre);
			archivos = f.list();
			try {
				
				for (int i = 0; i < archivos.length; i++) {
					if (nombre.equals(f.getName())) {
						System.out.println(f.getName());
						System.out.println(f.getAbsolutePath());
						System.out.println(f.getTotalSpace());
						System.out.println(f.canRead());
						System.out.println(f.canWrite());
						System.out.println(f.isFile());
						System.out.println(f.isDirectory());
						break;
						
					} else
						System.out.println("Introduzca un nombre valido");
				}
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
			}

		}
	}
}
