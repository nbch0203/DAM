package boletin2_Ficheros_Ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Lectura_linea_por_linea {

	public static void leer() {
		File archivo = new File("Docs/fichero.txt");

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
		leer();
	}
}
