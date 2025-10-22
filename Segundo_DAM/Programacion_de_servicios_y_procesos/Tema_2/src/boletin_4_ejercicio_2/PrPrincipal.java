package boletin_4_ejercicio_2;

public class PrPrincipal {
	public static void main(String[] args) {
		ThreadGroup grupo = new ThreadGroup("contadores");

		Contador contador = new Contador();

		Thread h1 = new Thread(grupo, contador, "Contador 1");
		Thread h2 = new Thread(grupo, contador, "Contador 2");
		Thread h3 = new Thread(grupo, contador, "Contador 3");

		h1.start();
		h2.start();
		h3.start();
		System.out.println("Hay :" + Thread.activeCount() + " hilos activos");

	}

}
