package boletin7;

import java.util.Scanner;

public class Boletin7 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numero;

		System.out.print("Introduce un número entero y positivo: ");
		numero = scanner.nextInt();

		if (numero < 0) {
			System.out.println("El número debe ser positivo.");
			return;
		}

		// Sumar los 100 números siguientes
		int suma = 0;
		for (int i = 1; i <= 100; i++) {
			suma += (numero + i);
		}

		// Mostrar el resultado
		System.out.println("La suma de los 100 números siguientes a " + numero + " es: " + suma);
	}
}
