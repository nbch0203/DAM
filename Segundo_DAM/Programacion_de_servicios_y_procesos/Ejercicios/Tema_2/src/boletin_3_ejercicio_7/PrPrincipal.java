package boletin_3_ejercicio_7;

public class PrPrincipal {
	public static void main(String[] args) {

		ThreadGroup g = new ThreadGroup("grupo");

		Pitufo p = new Pitufo();

		Thread h = new Thread(g, p, "pitu");
		h.start();

	}
}
