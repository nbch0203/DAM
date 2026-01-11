package Piramide;

import java.util.Scanner;

public class Proyecto {

	public static void main(String[] args) {
		int altura;
		int contador = 0;

		System.out.print("Dime un numero entero:");
		Scanner entrada = new Scanner(System.in);

		altura = entrada.nextInt();
		entrada.nextLine();
		
			if (altura >= 1) {
				// Bucle para crear la altura de la piramide
				for (int piramide = 1; piramide < altura; piramide++) {
					contador++;
					System.out.println(piramide+"");

					// Bucle para crear horizontalmente la piramide
					for (int horizon = 1; horizon < altura; horizon++) {
						System.out.print(horizon);
					}
				}

				

			}entrada.close();
		}
	}