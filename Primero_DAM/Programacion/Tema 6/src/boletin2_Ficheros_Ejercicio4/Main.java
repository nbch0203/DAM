package boletin2_Ficheros_Ejercicio4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void app() {
		Scanner entrada = new Scanner(System.in);
		String texto;
		do {
			System.out.println("Introduce el nombre de un fichero");
			String nombre = entrada.next();
			File archivo = new File(nombre);
			if (archivo.exists()) {
				archivo.delete();
			}
			System.out.println("Introduce el texto que quieras escribir en el fichero");
			texto = entrada.next();
			File fichero = new File(nombre);
			try {
				FileWriter escribir = new FileWriter(fichero);
//				for (int j = 65; j >= 65 && j <= 90; j++) {
//					char caracter = (char) j;
//					for (int i = 0; i < texto.length(); i++) {
//
//						if (texto.charAt(i) == caracter) {
//							texto.toLowerCase().charAt(i);
//
//						} else
//							texto.toUpperCase().charAt(i);
//						escribir.write(texto);
//					}
//
//				}
				
				

				leer(archivo);

			} catch (IOException e) {
				System.out.println("No se ha podido escribir en el fichero:" + nombre);
			}
		} while (texto.equalsIgnoreCase("Fin"));

	}

	public static void leer(File f) {
		File archivo = f;

		BufferedReader linea = null;
		try {

			FileReader lectura = new FileReader(archivo);
			linea = new BufferedReader(lectura);
			String st;
			while ((st = linea.readLine()) != null) {

				System.out.print(st);

			}
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el fichero");
		} catch (IOException e) {
			System.out.println("Eror de entrada salida");
		} finally {
			try {
				linea.close();
			} catch (IOException e2) {
				System.out.println("Error en el cierre");
			}
		}
	}

	public static void main(String[] args) {
		app();
	}

}
