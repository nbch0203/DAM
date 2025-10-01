package boletin6;

import java.util.Scanner;

public class Boletin6 {

	public static void main(String[] args) {
		int piramide;
		char letra;
		System.out.println("Dibujar una piramide");
		System.out.println("...................");
		Scanner entrada = new Scanner(System.in);
		System.out.print("Dime que altura debe tener la piramide: ");
		piramide = entrada.nextInt();
	
		System.out.print("Dime el simbolo de la piramide: ");
		letra = entrada.next().charAt(0);
		entrada.close();
	        int i = 1;
	        int espacios = piramide - 1;
	        while (i <= piramide) {
	            int numCaracteres = i;
	            int espacioActual = espacios;
	            while (espacioActual > 0) {
	                System.out.print(" ");
	                espacioActual--;
	            }
	            espacios--;

	            while (numCaracteres > 0) {
	                System.out.print(letra);
	                if (numCaracteres != 1) System.out.print(letra);
	                numCaracteres--;
	            }

	            System.out.println();
	            i++;
		
		
	        }
	    }
	    }