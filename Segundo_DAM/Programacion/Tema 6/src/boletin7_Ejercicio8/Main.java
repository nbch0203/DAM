package boletin7_Ejercicio8;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		Alumno[] lista = new Alumno[5];
		for (int i = 0; i < lista.length; i++) {

			System.out.println("Intoduce el nombre del alumno");
			String nombre = entrada.next();
			entrada.nextLine();
			System.out.println("Introduce la edad del alumno");
			int edad = entrada.nextInt();
			System.out.println("Introduce la nota del alumno");
			double nota = entrada.nextDouble();
			for (int o = 0; o < lista.length; o++) {
				try {
					lista[i] = new Alumno(nombre, edad, nota);

				} catch (EdadNoValidaException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} catch (NotaNoValidaException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
		}

		for (int i = 0; i < lista.length; i++) {
			System.out.println(lista[i].toString());
		}
	}
}
