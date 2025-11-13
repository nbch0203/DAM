package ejercicios_repaso_monitores_barreras;

import java.util.concurrent.Semaphore;

public class Tarea implements Runnable {

	private boolean confirmacion = false;
	private int contador;
	private Semaphore sm = new Semaphore(3);

	@Override
	public void run() {

		try {
			sm.acquire();

			wait(1000);

		} catch (Exception e) {

		}

	}
}
