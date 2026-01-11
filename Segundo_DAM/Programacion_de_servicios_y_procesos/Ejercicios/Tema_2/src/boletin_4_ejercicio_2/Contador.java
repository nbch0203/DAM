package boletin_4_ejercicio_2;

import java.util.Iterator;

public class Contador extends Thread {
	@Override
	public void run() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 10; i++) {

		}
		
		System.out.println("Hola soy el contador: "+ Thread.currentThread().toString());
	}
}
