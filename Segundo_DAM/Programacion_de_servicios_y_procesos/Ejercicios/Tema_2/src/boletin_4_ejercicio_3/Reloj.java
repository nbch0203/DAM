package boletin_4_ejercicio_3;

public class Reloj extends Thread {
	@Override
	public void run() {
		// TODO Auto-generated method stub

		for (int i = 1; i < 5; i++) {
			if (i == 4) {
				interrupt();
				break;
			}

			try {

				System.out.println("tic tac " + i + Thread.currentThread().toString() + "\n");
				System.out.println(Thread.currentThread().getState());
				sleep(1000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}

	}

}
