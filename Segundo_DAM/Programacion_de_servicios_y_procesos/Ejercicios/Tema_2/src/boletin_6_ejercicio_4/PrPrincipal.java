package boletin_6_ejercicio_4;

public class PrPrincipal {
	public static void main(String[] args) {
		Ascensor a1 = new Ascensor("entrar");
		Ascensor a2 = new Ascensor("entrar");
		Ascensor a3 = new Ascensor("entrar");

		Ascensor b1 = new Ascensor("salir");
		Ascensor b2 = new Ascensor("salir");
		Ascensor b3 = new Ascensor("salir");

		Thread h1 = new Thread(a1, "Empleado1-Entra");
		Thread h2 = new Thread(a2, "Empleado2-Entra");
		Thread h3 = new Thread(a3, "Empleado3-Entra");
		Thread h4 = new Thread(b1, "Empleado4-Sale");
		Thread h5 = new Thread(b2, "Empleado5-Sale");
		Thread h6 = new Thread(b3, "Empleado6-Sale");

		h1.start();
		h2.start();
		h3.start();

		h4.start();
		h5.start();
		h6.start();

		try {
			h1.join();
			h2.join();
			h3.join();
			h4.join();
			h5.join();
			h6.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
