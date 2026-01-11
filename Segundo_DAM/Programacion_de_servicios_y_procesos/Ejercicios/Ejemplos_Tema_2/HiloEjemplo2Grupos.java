package ejemplos;


public class HiloEjemplo2Grupos extends Thread {

	public void run() {
		System.out.println("Informacion del hilo: " + Thread.currentThread().toString());
		//Informacion del hilo: Thread[#23,Hilo 2,5,Grupo de hilos]
		// id+nombre hilo+prioridad hilo+nombre grupo


		for (int i = 0; i < 1000; i++)
			i++;

		System.out.println(Thread.currentThread().getName() + " Finalizando la ejecución.");
	}// run

	//
	public static void main(String[] args) {

		Thread.currentThread().setName("Principal");
		System.out.println(Thread.currentThread().getName());
		System.out.println(Thread.currentThread().toString());

		ThreadGroup grupo = new ThreadGroup("Grupo de hilos");
		HiloEjemplo2Grupos h = new HiloEjemplo2Grupos();

		Thread h1 = new Thread(grupo, h, "Hilo 1");
		Thread h2 = new Thread(grupo, h, "Hilo 2");
		Thread h3 = new Thread(grupo, h, "Hilo 3");		

		h1.start();
		h2.start();
		h3.start();

		System.out.println("3 HILOS CREADOS...");
		System.out.println("Hilos activos: " + Thread.activeCount());
		
		// Interrumpir todos los hilos del grupo
		grupo.interrupt();
		System.out.println("3 HILOS INTERRUMPIDOS...");
		System.out.println("Hilos activos: " + Thread.activeCount());
	}//

}// Fin HiloEjemplo2Grupos
