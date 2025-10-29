package boletin_5_ejercicio_2;

public class ContadorInterrumpible implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			for (int i = 1; i <= 100; i++) {
				System.out.println("Numero : " + i);
				Thread.sleep(200);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Interrumpido");

		}

	}
}
