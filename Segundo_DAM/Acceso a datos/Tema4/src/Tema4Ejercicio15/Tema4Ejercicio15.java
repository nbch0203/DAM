package Tema4Ejercicio15;

public class Tema4Ejercicio15 {

	public static void main(String[] args) {
		double[][] nota = new double[4][4];

		for (int f = 0; f < nota.length; f++) {
			for (int c = 0; c < nota[f].length; c++) {
				nota[f][c] = 2 * c;
			}
		}

		for (double[] fila : nota) {
			for (double not : fila) {
				System.out.println(not + " ");
				double suma=not;
			}
			System.out.println("");
		}
//		for (int f = 0; f < nota.length; f++) {
//			for (int c = 0; c < nota[f].length; c++) {
//				System.out.print(nota[f][c]+"	");
//			}System.out.println("");
//		}
	}
}