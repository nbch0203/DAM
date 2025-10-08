package Tema4Ejercicio9;

import java.util.Random;

public class Tema4Ejercicio8 {

	Random rm = new Random();
	int[][] matriz = new int[5][8]; // Declaramos una matriz de 5 filas y 8 columnas

	public void tabla(int escaner) {
		// Llenar la matriz con valores aleatorios entre 1 y 100
		for (int i = 0; i < matriz.length; i++) { // Recorrer filas
			for (int j = 0; j < matriz[i].length; j++) { // Recorrer columnas
				matriz[i][j] = rm.nextInt(100) + 1; // Generar número aleatorio entre 1 y 100
			}
		}

		// Imprimir la matriz
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + "\t"); // Usar tabulación para mejor formato
			
			}
			System.out.println();
		}
		
		
//		for (int fila = 0; fila < matriz.length; fila++) {
//			for (int colum = 0; colum < matriz[fila].length; colum++) {
//				System.out.println(matriz[fila][colum]+" ");
//			}
//		}
//		System.out.println();
//	}
	}
}
