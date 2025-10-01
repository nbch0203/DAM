package Tema4Ejercicio12;

import IntentodeArray.intento1;

public class Tema4Ejercicio12 {
	public static void main(String[] args) {
		Tema4Ejercicio12 intento = new Tema4Ejercicio12();
		int[][] matriz = new int[4][5];

		for (int f = 0; f < matriz.length; f++) {
			for (int c = 0; c < matriz.length; c++) {
				matriz[f][c] = 1;
				intento.suma(matriz[f]);

			}
			System.out.println(" ");
		}
	}

	public void suma(int[] matriz) {
		int resultado = 0;
		int resultado2=0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				resultado2=matriz[j];
				resultado = matriz[i];
			}
		}
		System.out.print(resultado);
		System.out.print(resultado2);
	}
}
