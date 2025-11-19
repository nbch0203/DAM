package ejercicio_repaso_semaforos_barreras;

import java.util.concurrent.Semaphore;

public class Clientes implements Runnable {
	private String nombre;
	private int contador = 0;

	public Clientes(String nombre) {

		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * for (int i = 0; i < array.length; i++) { if (array[i].availablePermits() > 0)
	 * { try { array[i].acquire(1); System.out.println("He entrado en un probador:"
	 * + getNombre()); wait(3000); System.out.println("El probador es el :" + i);
	 * array[i].release(); } catch (InterruptedException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); }
	 * 
	 * } else { try { wait(); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 * 
	 * }
	 */

	public synchronized void entrarSalir(Semaphore[] array) {
		while (array[contador].availablePermits() > 0) {
			try {
				array[contador].acquire();
				System.out.println("He entrado en un probador:" + getNombre());
				wait(3000);
				System.out.println("El probador es el :" + contador);
				array[contador].release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				try {
					wait();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
				}
			}
			contador++;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Tienda t = new Tienda();
		t.rellenar(t.getProbadores());
		entrarSalir(t.getProbadores());

	}
}
