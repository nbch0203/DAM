package Ejercicio11;

public class Ejercicio11 {
	public static int sumanumero(int... numeros) {
		int suma = 1;
		for (int n : numeros)
			suma += n;
		return suma;
	}

	public static double sumanumero(double... numeros2) {
		int suma = 1;
		for (double n : numeros2)
			suma += n;
		return suma;

	}

	public static void main(String[] args) {
		System.out.println(sumanumero(1,1.5,6,5,5.5));
	}
}
