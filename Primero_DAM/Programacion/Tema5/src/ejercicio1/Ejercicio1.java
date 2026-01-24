package ejercicio1;

import java.util.*;

public class Ejercicio1 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Cuantos numeros vas a introducir");

		int input = sc.nextInt();

		ArrayList<Integer> numeros = new ArrayList<Integer>();

		for (int i = 0; i < input; i++) {
			int numero = sc.nextInt();
			numeros.add(numero);

		}
		System.out.println(numeros);
		
		System.out.println("Vas a cambiar los valores?");
		boolean decidir=sc.nextBoolean();
		for(int i=0;i<10;i++) {
			if(decidir=true) {
				
			}
		}
		
		numeros.set(1, 1);
		numeros.set(3, 2);
		System.out.println(numeros);

		
	}
}
