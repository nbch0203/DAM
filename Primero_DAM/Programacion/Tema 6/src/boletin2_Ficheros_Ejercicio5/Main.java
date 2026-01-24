package boletin2_Ficheros_Ejercicio5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void app() throws IOException {
		Scanner entrada = new Scanner(System.in);
		while (true) {
			System.out.println("Dime el nombre de los dos ficheros");
			String fich1 = entrada.next();
			System.out.println("Dime el nombre del sgundo fichero");
			String fich2 = entrada.next();
			File archivo1 = new File(fich1);
			File archivo2 = new File(fich2);

			if (archivo1.exists() && archivo2.exists()) {
				FileReader lectura_fich1 = new FileReader(archivo1);
				BufferedReader lectura1 = new BufferedReader(lectura_fich1);
				FileReader lectura_fich2 = new FileReader(archivo2);
				BufferedReader lectura2 = new BufferedReader(lectura_fich2);

				String linea_fich1 = lectura1.readLine();
				String linea_fich2 = lectura2.readLine();

				String nombre = fich1 + "_" + fich2;
				File concate = new File(nombre);
				if (!concate.exists()) {
					concate.createNewFile();
				}

				FileWriter concate_escr = new FileWriter(concate);
				BufferedWriter escritura = new BufferedWriter(concate_escr);
				escritura.write(linea_fich1 + " " + linea_fich2);

			} else {
				try {
					archivo1.createNewFile();
					archivo2.createNewFile();
				} catch (IOException e) {
					System.out.println("No se ha podido crear el archivo 1 o 2");
				}
			}

		}
	}

	public static void main(String[] args) {
		try {
			app();
		} catch (Exception e) {
			System.out.println("Error");
		}
	}
}
