package boletin_repaso_Ejercicio10;

import java.util.HashMap;
import java.util.Scanner;

public class App {
	public void iniciar() {

		Scanner sc = new Scanner(System.in);
		HashMap<Integer, Habitaciones> mapa = new HashMap<Integer, Habitaciones>();
		while (true) {
			System.out.println("1-Reservar una habitacion" + "/n");
			System.out.println("2-Cancelar una habitacion" + "/n");

			int entrada = sc.nextInt();
			sc.nextLine();
			switch (entrada) {
			case 1: {
				System.out.println("Que tipo de habitacion quiere reservar? Estandar o Suit" + "/n");
				String habitacion = sc.next();
				System.out.println("Digame el numero de habitacion?");
				int numero_habi = sc.nextInt();
				sc.nextLine();
				System.out.println("Dime tu nombre");
				String nombre = sc.next();
				if (habitacion.equalsIgnoreCase("Estandar")) {
					System.out.println("Cuantas camas va a reservar?");
					int numero_camas = sc.nextInt();
					mapa.put(numero_habi, new Estandar("codigo", nombre, "fecha_ini", "fecha_out", numero_camas));
					break;
				} else {
					System.out.println("De cuantos metros quiere la habitacion?");
					int tamano = sc.nextInt();
					mapa.put(numero_habi, new Suites("codigo", nombre, "fecha_ini", "fecha_out", tamano));
					break;
				}

			}
			case 2: {
				System.out.println("Dime el numero de la habitacion");
				int numero_habita_reser = sc.nextInt();

				break;
			}
			case 3: {
				for (int i = 0; i < mapa.size(); i++) {
					mapa.containsKey(i);
				}
			}
			}
		}
	}
}
