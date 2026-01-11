package ejercicio_repaso_sincronizacion_hilos;

public class PrPrincipal {

	public static void main(String[] args) {
		// Crear 4 hilos de descarga
		DescargaCancion cancion1 = new DescargaCancion("Canción 1");
		DescargaCancion cancion2 = new DescargaCancion("Canción 2");
		DescargaCancion cancion3 = new DescargaCancion("Canción 3");
		DescargaCancion cancion4 = new DescargaCancion("Canción 4");
		
		//Establecemos prioridades
		cancion2.setPriority(10);
		cancion1.setPriority(6);
		cancion3.setPriority(6);
		cancion4.setPriority(6);
		
		//Arrancamos los hilos
		cancion1.start();
		cancion2.start();
		cancion3.start();
		cancion4.start();
		
		//Mientras que alguno este vivo mostramos el estado de los hilos cada dos segundos
		while (cancion1.isAlive() || cancion2.isAlive() || cancion3.isAlive() || cancion4.isAlive()) {
			try {
				System.out.println("Estado de los hilos:");
				System.out.println(cancion1.getName() + " -> " + cancion1.getState());  
				System.out.println(cancion2.getName() + " -> " + cancion2.getState());
				System.out.println(cancion3.getName() + " -> " + cancion3.getState());
				System.out.println(cancion4.getName() + " -> " + cancion4.getState());
				System.out.println("-----------------------------");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println("Todas las descargas han finalizado.");
		System.out.println("Tiempo total de descarga: " + System.currentTimeMillis() + " milisegundos.");

	}
	
}