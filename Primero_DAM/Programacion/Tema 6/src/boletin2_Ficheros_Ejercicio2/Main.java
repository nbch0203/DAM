package boletin2_Ficheros_Ejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.classfile.Opcode;
import java.util.Scanner;

public class Main {

	public static void lectura() {
		File archivo = new File("Docs/fichero.txt");
		FileReader lectura = null;
		BufferedReader linea = null;

		try {
			linea = new BufferedReader(lectura);
			String st;
			while ((st = linea.readLine()) != null) {
				System.out.print(st);
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo");
		} catch (IOException e) {
			System.out.println("Ha habido un error de lectura");
		} finally {
			try {
				linea.close();
			} catch (NullPointerException e) {
				System.out.println("Error de invocacion");
			} catch (IOException e2) {
				System.out.println("Error en el cierre de la lectura");
			}
		}

	}

	public static void escritura(String entrada) {
		File archivo = new File("Docs/fichero.txt");
		FileWriter escritura = null;
		BufferedWriter lineas = null;

		try {
			escritura = new FileWriter(archivo);
			lineas = new BufferedWriter(escritura);
			lineas.write(entrada);

		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el fichero en la escritura");
		} catch (IOException e) {
			System.out.println("Ha habido un problema con la escritura");
		} finally {
			try {
				escritura.close();
			} catch (IOException e2) {
				System.out.println("Error en el cierre de la excritura");
			}

		}

	}

	public static void app() {
		Scanner entrada = new Scanner(System.in);
		while (entrada != null) {
			System.out.println(
					"1-Mostrar por pantalla lo que contiene el archivo" + "\n" + "2-Escribir algo en el archivo");
			int opcion = entrada.nextInt();
			switch (opcion) {
			case 1: {
				lectura();
				break;
			}
			case 2: {
				System.out.println("Que quiere escribir en el archivo?");
				String linea = entrada.next();
				escritura(linea);
			}

			}
		}
	}

	public static void main(String[] args) {
		lectura();
	}
}
