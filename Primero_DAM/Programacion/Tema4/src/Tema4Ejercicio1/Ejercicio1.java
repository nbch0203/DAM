package Tema4Ejercicio1;

public class Ejercicio1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numeros = { 2, 3, 5, 8, 9 };
		System.out.println(calcular(numeros));
		System.out.println(calcularMayor(numeros));
		System.out.println(calcularTotal(numeros));
	}

	public static int calcular(int numeros[]) {
		int menor = numeros[0];
		for (int i = 0; i < numeros.length; i++) {

			if (numeros[i] < menor) {
				menor = numeros[i];
			}
		}
		return menor;
	}

	public static int calcularMayor(int numeros[]) {
		int mayor =0;
		for (int o = 0; o < numeros.length; o++) {
			if (numeros[o] > mayor) {
				mayor = numeros[o];
			}

		}
		return mayor;
	}

	public static int calcularTotal(int numeros[]) {
		int total = 0;
		for (int j = 0; j < numeros.length; j++) {
			total += numeros[j];
		}
		return total;
	}

}
