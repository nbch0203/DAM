package boletin2_Ejercicio13;

import java.util.Scanner;

public class Main {
	static final int numero = 5;
	static Alumno2[] array = new Alumno2[numero];

	public static void main(String[] args) {

		preguntar();
		for (int i = 0; i < array.length; i++) {
			Alumno2 aux= array[0];
			System.out.println(array[i]);
			array[i].esIgual(aux);
			array[i].esMayor(aux);
			array[i].esMayor(aux);
			System.out.println("************************************************************************");
		}
		
		

	}

	public static void preguntar() {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < array.length; i++) {

			System.out.println("Digame los datos del alumno");
			String nombre = sc.next();
			sc.nextLine();
			System.out.println("Digame la edad del alumno");
			int edad = sc.nextInt();
			añadirAlumno(new Alumno2(nombre, edad));
		}
	}

	public static void añadirAlumno(Alumno2 alumno) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				array[i] = alumno;
				break;
			}
		}
	}
}
