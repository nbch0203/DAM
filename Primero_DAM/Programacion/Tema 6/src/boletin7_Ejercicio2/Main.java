package boletin7_Ejercicio2;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		final int NUM = 5;
		int[] enteros = new int[NUM];
		int posicion = 0;

		int cont = 0;
		int divisor = 0;

		while (cont < NUM) {
			try {

				// Realizar la división dentro del bloque try
				rellenarArray(enteros);
				cont++; // Incrementar el contador solo si todo está correcto
			} catch (ArithmeticException e) {
				System.out.println("Error: División por 0.");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Error: Posición fuera de rango.\n");
			}
		}

		System.out.println("El contenido del array de enteros es:");
		mostrar(enteros);
	}

	public static void rellenarArray(int vector[]) {
		Scanner teclado = new Scanner(System.in);
		try {
			System.out.println("Introduce una posición del array:");
			int posicion = Integer.parseInt(teclado.nextLine());

			// Verificar si la posición está dentro del rango del array
			if (posicion < 0 || posicion >= 5) {
				throw new ArrayIndexOutOfBoundsException("Revisa tus datos");
			}

			System.out.println("Introduce un divisor:");
			int divisor = Integer.parseInt(teclado.nextLine());
			if (divisor == 0) {
				throw new ArithmeticException();
//				vector[posicion] = 100 / divisor;
			}
		} catch (NumberFormatException e) {
			System.out.println("No puede ser una letra");
		}
	}

	public static void mostrar(int vector[]) {
		for (int i = 0; i < vector.length; i++) {
			System.out.println(vector[i]);
		}
	}
}