package ejercicio_2;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Metodos mt = new Metodos();
		System.out.println("Dime un numero menor");
		int menor = sc.nextInt();
		System.out.println("Dime un numero mayor");
		int mayor = sc.nextInt();
		System.out.println("Cuantos numeros quieres generar?");
		int cantidad = sc.nextInt();
		System.out.println("El numero aleatorio es:");
		System.out.println(mt.generaAleatorio(menor, mayor, cantidad));

		System.out.println("El numero ya existia en el ");

	}

}
