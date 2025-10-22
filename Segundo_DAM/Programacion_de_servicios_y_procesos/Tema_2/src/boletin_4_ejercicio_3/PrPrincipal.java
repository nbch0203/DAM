package boletin_4_ejercicio_3;

public class PrPrincipal {
	public static void main(String[] args) {
		ThreadGroup grupo = new ThreadGroup("Relojes");

		Reloj reloj = new Reloj();

		Thread h1 = new Thread(grupo, reloj, "reloj 1");
		Thread h2 = new Thread(grupo, reloj, "reloj 2");
		Thread h3 = new Thread(grupo, reloj, "reloj 3");

		h1.start();
		h2.start();
		h3.start();
		h1.setName("hilo 1");
		h2.setName("hilo 2");
		h3.setName("hilo 3");

		System.out.println(grupo.activeCount());

		System.out.println(grupo.activeCount());

	}

}
