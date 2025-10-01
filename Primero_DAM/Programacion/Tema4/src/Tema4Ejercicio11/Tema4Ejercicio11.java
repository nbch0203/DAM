package Tema4Ejercicio11;

public class Tema4Ejercicio11 {
	public static void main(String[] args) {
		int valor = 3;
		int valor2 = 5;
		int[][] matriz = new int[valor][valor2];

		boolean parimpar = false;
		if (valor % 2 == 0 && valor2 % 2 == 0) {
			parimpar = true;
		} else {
			parimpar = false;
		}

		for (int f = 0; f < matriz.length; f++) {
			for (int c = 0; c < matriz.length; c++) {

				if (parimpar = true) {
					System.out.println("Es simetrica");
					break;
				} else {
					System.out.println("No lo es");
					break;
				}
			}
		}
	}
}
