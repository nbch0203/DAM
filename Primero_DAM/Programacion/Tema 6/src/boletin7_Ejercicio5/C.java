package boletin7_Ejercicio5;

import java.io.IOException;

public class C {

	private static int metodo() {
		int valor = 0;
		
		try {
			valor = valor + 1;
			valor = valor + Integer.parseInt("w");
			valor = valor + 1;
		
			System.out.println("Valor al final del try: " + valor);
			
			throw new IOException();
		
		} catch (IOException e) {
			valor = valor + Integer.parseInt("42");
			System.out.println("Valor al final del catch: " + valor);
		} finally {
			valor = valor + 1;
			System.out.println("Valor al final de finally : " + valor);
		}
		valor = valor + 1;
		System.out.println("Valor antes del return: " + valor);
		return valor;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println(metodo());
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception en metodo()");
			e.printStackTrace();
		}
	}

}
