package boletin_5_ejercicio_2;

public class PrPrincipal {
	public static void main(String[] args) {
		Thread h = new Thread(new ContadorInterrumpible());

		h.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		h.interrupt();
	}

}
