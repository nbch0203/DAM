package Ejercicio1º1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ejercicio1 {
	static ArrayList<Double> Listatemp = new ArrayList<Double>();
	static Double temperatura;
	static int entrada = 0;

	public static void iniciar() {
		Scanner sc = new Scanner(System.in);

		while (entrada != 7) {

			System.out.printf("1. Añadir temperatura " + "\n" + "2. Mostrar temperatura media" + "\n"
					+ "3. Mostrar temperaturas extremas" + "\n" + "4. Mostrar la lista de temperaturas" + "\n"
					+ "5. Busca la temperatura y muestra su posición" + "\n" + "6. Borrar la temperatura" + "\n"
					+ "7. Salir" + "\n");
			 if (sc.hasNextInt()) {
	                entrada = sc.nextInt();
	            }

			switch (entrada) {
			case 1:
				if (sc.hasNextDouble()) {
					temperatura = sc.nextDouble();
					añadir(temperatura);
				}
				break;
			case 2:
				mostrarTempMedia();
				break;
			case 3:
				mostrarTempExtrema();
				break;
			case 4:
				System.out.println(mostrartemp());
				break;
			case 5:

				break;
			case 6:

				break;
			}
		}

		sc.close();
	}

	public static void añadir(Double temperatura) {
		Listatemp.add(temperatura);
		System.out.println(temperatura);
	}

	public static void mostrarTempMedia() {
		System.out.println("Temperatura media");
		for (int i = 0; i < Listatemp.size(); i++) {
			int aux = 0;
			aux += Listatemp.get(i) / Listatemp.size();
		}
	}

	public static void mostrarTempExtrema() {
		System.out.println("Temmperaturas extremas de la lista");
		for (Double temperatura : Listatemp) {
			Double tempextAlta = 0.0;
			Double tempextBaja = 0.0;
			if (temperatura > tempextAlta) {
				tempextAlta = temperatura;
				System.out.println("Temperatura mas alta " + tempextAlta);
				if (temperatura < tempextBaja) {
					tempextBaja = temperatura;
					System.out.println("Temperatura mas baja " + tempextBaja);
				}
			}
		}
	}

	public static boolean mostrartemp() {
		for (Double n : Listatemp) {
			System.out.println(n + "\n");
		}
		return false;
	}

	public void buscartemp(Double temp) {
		for (Double temperatura : Listatemp) {
			if (temp == temperatura) {
				System.out.println("Encontrado");
			} else {
				System.out.println("No existe");
			}
		}
	}
//	public void borrartemp(Double tempr) {
//		Double aux=0.00;
//		buscartemp(tempr);
//		if(tempr=) {}
//	}

	@Override
	public String toString() {
		return "ejercicio1 [" + (Listatemp != null ? "Listatemp=" + Listatemp + ", " : "")
				+ (temperatura != null ? "temperatura=" + temperatura + ", " : "") + "entrada=" + entrada + "]";
	}
	public static void main(String[] args) {
		
		iniciar();
	}
}
