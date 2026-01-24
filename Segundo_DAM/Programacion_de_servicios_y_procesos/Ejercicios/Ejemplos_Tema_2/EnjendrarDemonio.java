package ejemplos;

class EnjendrarDemonio extends Thread {

	public EnjendrarDemonio(int i) {
		System.out.println("Enjendrando demonio " + i);
		start();
	}

	public void run() {
		while (true) {
			Thread.yield();
		}
	}
}