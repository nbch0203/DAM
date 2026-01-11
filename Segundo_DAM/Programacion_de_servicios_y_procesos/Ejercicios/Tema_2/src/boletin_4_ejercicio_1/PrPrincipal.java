package boletin_4_ejercicio_1;

public class PrPrincipal {
	public static void main(String[] args) {

		ThreadGroup grupo = new ThreadGroup("Equipo");

		Persona p = new Persona();

		Thread h1 = new Thread(grupo, p, "Hilo 1");
		Thread h2 = new Thread(grupo, p, "Hilo 2");
		Thread h3 = new Thread(grupo, p, "Hilo 3");

		h1.start();
		h2.start();
		h3.start();

	}

}
