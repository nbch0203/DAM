package boletin1;

import java.util.Scanner;

public class EntradaDesdeTeclado {

	public static void main(String[] args) {
	//	Scanner entrada = new Scanner(System.in);
		//System.out.print("Introduzca cantidad: ");
//		int cantidad;
	//	cantidad = entrada.nextInt();
//		System.out.print("Introduzca precio: ");
	//	double precio = entrada.nextDouble();
		//entrada.close();
		
//		Scanner entrada = new Scanner(System.in);
//		// obtener la primera entrada
//		System.out.print("¿ Cual es su nombre?");
//		String nombre = entrada.nextLine();
//		// leer la segunda entrada
//		System.out.print("¿ Cual es su edad?");
//		int edad = entrada.nextInt();
//		// visualizar salida
//		System.out.println("Buenos días " + nombre + "; años " + edad);
		
		Scanner entrada = new Scanner(System.in);
		System.out.println("¿ Cual es tu edad ?");
		int edad;
		edad = entrada.nextInt();
		System.out.println("¿ Cual es el precio de una chocolatina ?");
		double precio;
		precio = entrada.nextDouble();
		System.out.println("¿ Dime un rio ?");
		String rio;
		rio = entrada.next();
		System.out.printf("Tu edad es = %d%n "+"El precio es:%.2f%n "+"El rio es:%s%n",edad,precio,rio);
entrada.close();
	
//Primero te pregunta un numero entero para es es el %d 
//luego te pregunta un numero con decimales con el %(decimales)f 
//y con el %n salta de linea el resultado siguiente
		
		
	}

}
