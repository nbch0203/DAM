package ejericiorepaso1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ArrayList<Coche> Coches = new ArrayList<Coche>();

		int entrada;

		while (true) {
			System.out.println("\n" + "1-Registrar un nuevo coche" + "\n" + "2-Buscar un coche reparado" + "\n"
					+ "3-Listar los coches del taller" + "\n" + "4-Ordenar los coches por marca y matricula" + "\n"
					+ "5-Ordenar los coches por matrícula" + "\n" + "6-Salir del programa");
			entrada = sc.nextInt();
			sc.nextLine();

			switch (entrada) {
			case 1: {
				System.out.println("Digame el nombre del propietario :");
				String entradapropietario = sc.nextLine();

				System.out.println("Digame el numero de matrícula :");
				String entradamatricula = sc.nextLine();

				System.out.println("Digame la marca del coche :");
				String entradamarca = sc.nextLine();

				System.out.println("Ha sido reparado ya? :");
				Boolean entradareparado = sc.nextBoolean();

				Coches.add(new Coche(entradapropietario, entradamatricula, entradamarca, entradareparado));

				break;
			}
			case 2: {
				System.out.println("O");
			}
			case 3: {
				for (Coche coche : Coches) {
					System.out.println(coche);
				}
				break;
			}
			case 4: {
				
			}
			case 5: {

			}
			case 6: {
				System.out.println("Desea realmente salir del programa?:");
				String entradaconfirm = sc.nextLine();
				if (entradaconfirm.equalsIgnoreCase("Si")) {
					sc.close();
					break;
				}
			}
			}
		}

	}
}