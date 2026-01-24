package ejercicio_repaso_semaforos_barreras;

import java.util.concurrent.Semaphore;

public class Taxi {
	private Semaphore permisos = new Semaphore(4);

	public Semaphore getPermisos() {
		return permisos;
	}

	public void setPermisos(Semaphore permisos) {
		this.permisos = permisos;
	}
	
	

}
