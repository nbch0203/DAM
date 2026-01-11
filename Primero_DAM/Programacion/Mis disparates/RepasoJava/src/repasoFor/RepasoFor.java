package repasoFor;

//import java.util.Scanner;

public class RepasoFor {

	public static void main(String[] args) {
		// Ejercicio 1

		// Ejercicio 2
//		for (int i = 320; i >= 160; i--)
//			System.out.println(i);

		// Ejercicio 3

//		Scanner entrada = new Scanner(System.in);
//		System.out.println("Introduce un numero: ");
//		int numero = entrada.nextInt();
//		entrada.close();
//final int o =1;
//		for (int i = 0;;i+o);
//			System.out.println(i);

//		int a = 0, b = 1;
//		for (int i = 0; i < numero; i++) {
//			System.out.println(a);
//			int temp = a;
//			a = b;
//			b = temp + b;
//		}
//
//		entrada.close();

		// Ejercicio 10

//		Scanner teclado = new Scanner(System.in);
//		System.out.println();
//		int valor1 = teclado.nextInt();
//
//		System.out.println("Introduce otro numero");
//		int valor2 = teclado.nextInt();
//		for (int i = 1; i < valor1; i++) {
//			if (i % valor2 != 0) {
//				System.out.println(i + " ");
//			}teclado.close();
//		}

		// Ejercicio 11

//		Codifica un programa que pida un número entero, entre 0 y 20, y que muestre en pantalla una
//		secuencia de números, repitiendo cada número tantas veces como el valor que representa.

//		Scanner entrada = new Scanner(System.in);
//
//		int numero;
//
//		// Pedir un número entre 0 y 20
//		System.out.println("Dime un número entero entre 0 y 20: ");
//		numero = entrada.nextInt();
//		if (numero >= 0 && numero <= 20) {
//			for (int i = 0; i <= numero; i++) {
//				for (int n = 0; n <= i; n++) {
//					System.out.print(i); // Usar print para no saltar de línea
//				}
//				System.out.println(); // Salto de línea después de cada número
//			}
//		} else {
//			System.out.println("El número debe estar entre 0 y 20. Inténtalo de nuevo.");
//		}
//		entrada.close();

		// Acepta el reto

//		String navidad = "25 12";
//		String reyesmagos = "6 1";
//		System.out.println("Introduce cuantas fechas vas a preguntar:");
//
//		Scanner entrada = new Scanner(System.in);
//		int cantidad = entrada.nextInt();
//		System.out.println("Dime la fecha:");
//		Scanner entrada2 = new Scanner(System.in);
//		String fecha = entrada2.nextLine();
//
//		switch (cantidad) {
//		case 1:
//			
//			if (navidad.equals(fecha)) {
//				System.out.println("SI");
//			} else {
//				System.out.println("NO");
//			}
//			break;
//
//		case 2:
//			if (navidad.equals(fecha) || reyesmagos.equals(fecha)) {
//				System.out.println("SI");
//			} else {
//				System.out.println("NO");
//			}
//			break;
//
//		}
//		entrada2.close();
//		entrada.close();

		// Acepta el reto 2

//		String frase1 = "que bien me lo paso";
//		String frase2 = "molo";
//		String frase3 = "adios a todos";
//		String frase4 = "sos";
//
//		Scanner entrada = new Scanner(System.in);
//		System.out.println("Introduce cuántos casos de prueba vas a realizar: ");
//		int casos = entrada.nextInt();
//		entrada.nextLine();
//
//		System.out.println("Introduce la frase oculta: ");
//		String fraseoculta = entrada.nextLine();
//
//		switch (casos) {
//		case 1:
//			if (fraseoculta.equals(frase1) || fraseoculta.equals(frase2) || fraseoculta.equals(frase3)
//					|| fraseoculta.equals(frase4)) {
//				System.out.println("SI");
//			} else {
//				System.out.println("NO");
//			}
//			break;
//
//		case 2:
//			if (!fraseoculta.equals(frase1) && !fraseoculta.equals(frase2) && !fraseoculta.equals(frase3)
//					&& !fraseoculta.equals(frase4)) {
//				System.out.println("SI");
//			} else {
//				System.out.println("NO");
//			}
//			break;
//		}
//
//		entrada.close();

		// Acepta el reto 3


	}
}
