package boletin_6_ejercicio_4;

public class Ascensor implements Runnable {
	private static int cantidad_empleados_saliendo = 0;
	private static boolean entrando = false;
	private static int cantidad_empleados_entrando = 0;
	private String accion;

	public Ascensor(String accion) {
		this.accion = accion;
	}

	public synchronized void entrar() throws InterruptedException {
		System.out.println("Pido entrar : " + Thread.currentThread().getName());
		while (cantidad_empleados_saliendo > 0 && cantidad_empleados_entrando > 9) {
			System.out.println("No puedo entrar todavia");
			wait();
		}

		entrando = true;
		cantidad_empleados_entrando++;
		System.out.println("Estoy entrando " + Thread.currentThread().getName() + " hay entrando: " + entrando
				+ " y saliendo: " + cantidad_empleados_saliendo);

		notifyAll();

	}

	public synchronized void salir() throws InterruptedException {
		System.out.println("Pido salir " + Thread.currentThread().getName());
		if (entrando == true || cantidad_empleados_entrando > 0) {
			System.out.println("No puedo salir todavia");
			wait();
		} else {
			cantidad_empleados_saliendo++;
			System.out.println("Estoy saliendo " + Thread.currentThread().getName() + " hay entrando: " + entrando
					+ " y saliendo: " + cantidad_empleados_saliendo);
			notifyAll();
		}
	}

	@Override
	public void run() {
		try {
			if (accion.equals("entrar")) {
				entrar();
			} else if (accion.equals("salir")) {
				salir();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
