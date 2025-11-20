package ejercicio_repaso_sincronizacion_hilos;

public class Mihilo implements Runnable {
	private static int calculo;

	public static int getCalculo() {
		return calculo;
	}

	public void setCalculo(int calculo) {
		Mihilo.calculo = calculo;
	}

	public void sumar(int cantidad) {
		calculo += cantidad;
		System.out.println(getCalculo());
	}

	public void restar(int cantidad) {
		calculo -= cantidad;
		System.out.println(getCalculo());

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
