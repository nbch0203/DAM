package repasoForWhile;

public class RepasoForWhile {

	public static void main(String[] args) {
		// Ejercicio 1

		int esprimo = 1;
		int contador = 2;

		for (int i = 2; i < 100; i++) {
			while ((contador < i) && (esprimo != 0)) {
				esprimo = i % contador;
				contador++;
			}
			if (esprimo != 0) {
				System.out.println(i + " ");
			}
		}
	}
}
