package Tema4Ejercicio14;

public class Tema4Ejercicio14 {
	public static void main(String[] args) {
		int[][] matriz = new int[10][10];

		for (int f = 0; f < matriz.length; f++) {
			for (int c = 0; c < matriz[f].length; c++) {
				if (f == 0) {
					matriz[f][c] = c + 1;
				} else if (c == 0) {
					matriz[f][c] = matriz[f - 1][matriz[f].length - 1];
				} else {
					matriz[f][c] = matriz[f - 1][c - 1];
				}
			}
		}

		for (int fi = 0; fi < matriz.length; fi++) {
			for (int col = 0; col < matriz[fi].length; col++) {
				System.out.printf("%d\t", matriz[fi][col]);
			}System.out.println("");
		}
	}
}