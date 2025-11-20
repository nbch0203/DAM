package ejercicio_repaso_semaforos_barreras;

import java.util.concurrent.Semaphore;

public class Pasajero implements Runnable {
	private String nombre;
	private String destino;

	public Pasajero(String nombre, String destino) {
		this.nombre = nombre;
		this.destino = destino;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void entrarSalir() {
		Semaphore permisos = new Taxi().getPermisos();

		boolean lleno = false;
		int contador = 0;

		while (lleno == false) {
			for (int i = 0; i < 3; i++) {
				if (permisos.tryAcquire(i)) {
					try {
						System.out.println("He entrado en el taxi:" + nombre + " con destino: " + destino);
						
						if (lleno) {
							Thread.sleep(2000);

							System.out.println("He llegado a mi destino");

						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
