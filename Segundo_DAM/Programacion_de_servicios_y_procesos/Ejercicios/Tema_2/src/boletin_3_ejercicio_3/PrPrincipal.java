package boletin_3_ejercicio_3;

import java.util.Scanner;

public class PrPrincipal {
	public static void main(String[] args) {
		ThreadGroup grupo = new ThreadGroup("Atletas");
		Atleta a = new Atleta();

		Scanner sc = new Scanner(System.in);

		System.out.println("Dime el nombre del atleta:");

		String nombre = sc.next();

		Thread h = new Thread(grupo, a, nombre);
		h.start();
		
		
		sc.close();

	}

}
