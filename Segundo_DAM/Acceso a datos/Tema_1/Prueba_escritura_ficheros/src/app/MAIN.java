package app;

import java.util.Scanner;

public class MAIN {
	public MAIN() {
		// TODO Auto-generated constructor stub
		Scanner sc = new Scanner(System.in);
		String archivo = "";

		do {

			System.out.println("1- Leer por linea\n2- Leer por letra\n3- Escribir por linea\n4- Escribir por letra");

			int opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Dime el nombre del archivo");
				archivo = sc.next();

				break;

			case 2:
				System.out.println("Dime el nombre del archivo");
				archivo = sc.next();

				break;
			case 3:
				System.out.println("Dime el nombre del archivo");
				archivo = sc.next();
				System.out.println("Ya puedes escribir");
				String texto = "";
				
				break;
			case 4:
				System.out.println("Dime el nombre del archivo");
				archivo = sc.next();
				System.out.println("Ya puedes escribir");

				break;

			default:
				sc.close();
				break;
			}

		} while (true);

	}

}
