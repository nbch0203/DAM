package ejemplos;

public class Demonio extends Thread {
	private static final int TAMANIO = 10;
	private Thread[] t = new Thread[TAMANIO];

	public Demonio() {
		setDaemon(true);
		start();
	}

	public void run() {
		for (int i = 0; i < TAMANIO; i++) {
			t[i] = new EnjendrarDemonio(i);
		}
		for (int i = 0; i < TAMANIO; i++) {
			System.out.println("t[" + i + "].isDaemon() = " + t[i].isDaemon());
		}

		while (true)
			Thread.yield();
	}
}