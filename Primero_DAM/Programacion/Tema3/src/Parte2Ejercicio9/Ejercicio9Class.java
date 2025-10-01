package Parte2Ejercicio9;

public class Ejercicio9Class {
	public void pruebaParametros(String... nombres) {
		System.out.println("El numero de parametros es: " + nombres.length);
		for (int i = 0; i < nombres.length; i++) {
			System.out.println("Posicion: "+i+":"+nombres);
		}

	}

}
