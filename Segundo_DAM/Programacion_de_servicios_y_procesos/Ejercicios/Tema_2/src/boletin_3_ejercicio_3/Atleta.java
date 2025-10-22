package boletin_3_ejercicio_3;

public class Atleta implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i <= 10; i++) {

			try {
				System.out.print(i + " km ");
				Thread.currentThread().sleep(3500);

				if (i == 10) {
					System.out.println("\n" + Thread.currentThread().getName() + " ha llegado a la meta");
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
