package boletin_6_ejercicio_1;

public class Mihilo implements Runnable {
	private static int numeros;

	public synchronized void sumar(int cantidad) {
		numeros += cantidad;
		System.out.println("La cantidad que hay ahora es : " + getNumeros());

	}

	public synchronized void restar(int cantidad) {
		numeros -= cantidad;
		System.out.println("La cantidad que hay ahora es : " + getNumeros());

	}

	public static int getNumeros() {
		return numeros;
	}

	public void setNumeros(int numeros) {
		Mihilo.numeros = numeros;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
