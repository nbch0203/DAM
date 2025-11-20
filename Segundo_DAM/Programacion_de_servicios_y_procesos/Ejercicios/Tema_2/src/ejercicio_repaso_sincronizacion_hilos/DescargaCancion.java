package ejercicio_repaso_sincronizacion_hilos;

public class DescargaCancion extends Thread {
	public DescargaCancion(String nombre) {
		//Utilizamos el constructor de Thread
		super(nombre);
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {	//Progreso de 0 a 100 (multiplicamos *10)
			try {
				sleep(20); //Delay para que imprima primero el estado de los hilos
				System.out.println("Descargando " + getName() + ": " + (i*10) + "% completado" );
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//Cancion descargada
		System.out.println(">>> " + getName() + " está lista");
	}

}
