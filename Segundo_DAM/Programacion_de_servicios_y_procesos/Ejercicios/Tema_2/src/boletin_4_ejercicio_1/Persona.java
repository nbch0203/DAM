package boletin_4_ejercicio_1;

public class Persona extends Thread {
	@Override
	public void run() {
		// TODO Auto-generated method stub

		System.out.println("Hola soy el hilo " + this.currentThread().toString());

	}

}
