package boletin2_Ficheros_Ejercicio1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Lecura_caracter_por_caracter {

	public static void lectura() {
		File archivo = null;
		FileReader lectura = null;
		int i;

		try {
			archivo = new File("Docs/fichero.txt");
			lectura = new FileReader(archivo);
			while ((i = lectura.read()) != -1) {
				char caracter = (char) i;
//				char st = " ";
				if (i != 32) {
					System.out.print(caracter);
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el fichero");
		} catch (IOException e) {
			System.out.println("Eror de entrada salida");
		} finally {
			try {
				lectura.close();
			} catch (IOException e2) {
				System.out.println("Error en el cierre");
			}
		}

	}

	public static void main(String[] args) {
		lectura();
	}

}
