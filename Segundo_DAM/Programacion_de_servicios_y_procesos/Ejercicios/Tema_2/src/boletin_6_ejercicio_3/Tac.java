package boletin_6_ejercicio_3;

public class Tac implements Runnable {
	@Override
	public synchronized void run() {

		while (true) {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			notify();
		}

	}
}
