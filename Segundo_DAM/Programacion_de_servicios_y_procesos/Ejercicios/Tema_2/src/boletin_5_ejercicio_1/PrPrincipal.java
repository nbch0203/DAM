package boletin_5_ejercicio_1;

public class PrPrincipal {
	public static void main(String[] args) {
		Contador c = new Contador();

		Thread t = new Thread((Runnable) c);

	}

}
