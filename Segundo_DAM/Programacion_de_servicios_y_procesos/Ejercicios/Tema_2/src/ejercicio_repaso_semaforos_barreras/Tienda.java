package ejercicio_repaso_semaforos_barreras;

import java.util.concurrent.Semaphore;

public class Tienda {
	private Semaphore[] probadores = new Semaphore[4];

	public Semaphore[] getProbadores() {
		return probadores;
	}

	public void setProbadores(Semaphore[] probadores) {
		this.probadores = probadores;
	}

	public void rellenar(Semaphore[] array) {
		for (int i = 0; i < array.length; i++) {

			array[i] = new Semaphore(1);
		}
	}

}
