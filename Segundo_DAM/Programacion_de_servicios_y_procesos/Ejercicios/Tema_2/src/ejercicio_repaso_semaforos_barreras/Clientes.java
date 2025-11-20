package ejercicio_repaso_semaforos_barreras;

import java.util.concurrent.Semaphore;

public class Clientes implements Runnable {
	private String nombre;
	private Tienda tienda;
	private int contador = 0;

	public Clientes(String nombre, Tienda tienda) {

		this.nombre = nombre;
		this.tienda = tienda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void entrarSalir() {
//		while (array[contador].availablePermits() > 0) {
//			try {
//				array[contador].acquire();
//				System.out.println("He entrado en un probador:" + getNombre());
//				wait(3000);
//				System.out.println("El probador es el :" + contador);
//
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//			}
//			contador++;
//		}

		Semaphore[] probadores = tienda.getProbadores();
		boolean haEntrado = false;

		while (!haEntrado) {
			// Intentar cada probador
			for (int i = 0; i < probadores.length; i++) {
				if (probadores[i].tryAcquire()) { // Intenta sin bloquear
					try {
						System.out.println(nombre + " ha entrado en el probador " + i);
						Thread.sleep(3000);
						System.out.println(nombre + " ha salido del probador " + i);
						haEntrado = true;
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						probadores[i].release();
					}
					break; // Sale del for cuando consigue un probador
				}
			}

// Si no consiguió ningún probador, espera un poco antes de reintentar
			if (!haEntrado) {
				try {
					Thread.sleep(100); // Pequeña pausa antes de reintentar
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		entrarSalir();

	}
}
