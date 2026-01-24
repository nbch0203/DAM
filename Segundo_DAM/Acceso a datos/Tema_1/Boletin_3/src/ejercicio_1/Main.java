package ejercicio_1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> numeros = new ArrayList<Integer>();

		Metodos mt = new Metodos();
		int numero;

		System.out.println("Introduce un fichero");
		String fichero = sc.next();
		do {

			System.out.println("Introduce un numero");
			numero = sc.nextInt();
			numeros.add(numero);

		} while (numero >= 0);
		try {
			mt.escribir(fichero, numeros);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
