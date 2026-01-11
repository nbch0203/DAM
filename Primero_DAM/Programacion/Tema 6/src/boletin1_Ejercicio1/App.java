package boletin1_Ejercicio1;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Scanner;

import ejercicio7.Empleado;

public class App {
	static Astros[] array = new Astros[88];

	public void iniciar() {

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("1-Añadir un nuevo astro a la lista" + "\n" + "2-Listar todos los astros de la lista"
					+ "\n" + "3-Listar todos los satelites" + "\n" + "4-Listar todos los planetas");
			int eleccion = sc.nextInt();
			sc.nextLine();
			switch (eleccion) {
			case 1:
				System.out.println("El nuevo astro es un planeta o un satelite");
				String esono = sc.next();
				if (esono.equalsIgnoreCase("planeta")) {
					System.out.println("Dime el nombre del nuevo astro");
					String nombre = sc.next();
					System.out.println("Dime la masa del astro");
					Double masa_pla = sc.nextDouble();
					System.out.println("Dime el diametro medio del astro");
					Double diametro_medio_pla = sc.nextDouble();
					System.out.println("Dime la velocidad de rotacion del astro");
					Double rotacion_pla = sc.nextDouble();
					System.out.println("Dime la velocidad de traslacion del astro");
					Double traslacion_pla = sc.nextDouble();
					System.out.println("Dime la distencia media del astro");
					Double distancia_media_pla = sc.nextDouble();
					System.out.println("Dime la distancia al sol");
					Double distancia_sol_pla = sc.nextDouble();
					System.out.println("Dime la velocidad de la orbita al sol");
					Double orbita_al_sol_pla = sc.nextDouble();
					System.out.println("Dime si tiene un satelite");
					String tiene_satelite_pla = sc.next();
					sc.nextLine();
					if (tiene_satelite_pla.equalsIgnoreCase("si")) {
						System.out.println("El nombre del satelite es : \t\t\t" + nombre + "\n"
								+ "La masa es de : \t\t\t\t" + masa_pla + "\n"
								+ "El diametro medio del satelite es de : \t\t" + diametro_medio_pla + "\n"
								+ "La velocidad de rotacion del satelite es de : \t" + rotacion_pla + "\n"
								+ "La velocidad de traslacion es de : \t\t" + traslacion_pla + "\n"
								+ "La distancia media del astro es de : \t\t" + distancia_media_pla + "\n"
								+ "La distancia del planeta hasta el sol es de : \t" + distancia_sol_pla + "\n"
								+ "La velocidad de orbita al sol es de : \t\t" + orbita_al_sol_pla + "\n"
								+ "¿ Tiene algun satelite ? \t\t\t" + tiene_satelite_pla + "\n");

						añadirastro(new Planetas(nombre, masa_pla, diametro_medio_pla, rotacion_pla, traslacion_pla,
								distancia_media_pla, distancia_sol_pla, orbita_al_sol_pla, tiene_satelite_pla));
						break;
//					} else {
//						while (!tiene_satelite_pla.equalsIgnoreCase("si") | !tiene_satelite_pla.equalsIgnoreCase("no")) {
//							System.out.println("Insete si o no");
//
//							System.out.println("El nombre del satelite es : \t\t\t" + nombre + "\n"
//									+ "La masa es de : \t\t\t\t" + masa_pla + "\n"
//									+ "El diametro medio del satelite es de : \t\t" + diametro_medio_pla + "\n"
//									+ "La velocidad de rotacion del satelite es de : \t" + rotacion + "\n"
//									+ "La velocidad de traslacion es de : \t\t" + traslacion + "\n"
//									+ "La distancia media del astro es de : \t\t" + distancia_media + "\n"
//									+ "La distancia del planeta hasta el sol es de : \t" + distancia_sol + "\n"
//									+ "La velocidad de orbita al sol es de : \t\t" + orbita_al_sol + "\n"
//									+ "¿ Tiene algun satelite ? \t\t\t" + tiene_satelite + "\n");
//
//						}
					}

				} else if (esono.equalsIgnoreCase("satelite")) {
					System.out.println("Dime el nombre del nuevo astro");
					String nombre = sc.next();
					sc.nextLine();
					System.out.println("Dime la masa del astro");
					Float masa = sc.nextFloat();
					System.out.println("Dime el diametro medio del astro");
					Double diametro_medio = sc.nextDouble();
					System.out.println("Dime la velocidad de rotacion del astro");
					Double rotacion = sc.nextDouble();
					System.out.println("Dime la velocidad de traslacion del astro");
					Double traslacion = sc.nextDouble();
					System.out.println("Dime la distencia media del astro");
					Double distancia_media = sc.nextDouble();
					System.out.println("Dime la distancia al planeta");
					Double distancia_al_planeta = sc.nextDouble();
					System.out.println("Dime la velocidad del orbita planetaria");
					Double orbita_planetaria = sc.nextDouble();
					System.out.println("Dime el planeta al que pertenece");
					String planeta_al_q_pertenece = sc.next();
					sc.nextLine();
					System.out.println("----------------------------------------------------" + "\n");
					System.out.println("El nombre del satelite es : \t\t\t" + nombre + "\n" + "La masa es de : \t\t\t\t"
							+ masa + "\n" + "El diametro medio del satelite es de : \t\t" + diametro_medio + "\n"
							+ "La velocidad de rotacion del satelite es de : \t" + rotacion + "\n"
							+ "La velocidad de traslacion es de : \t\t" + traslacion + "\n"
							+ "La distancia media del satelite es de : \t" + distancia_media + "\n"
							+ "La distancia al planeta es de : \t\t" + distancia_al_planeta + "\n"
							+ "La velocidad de orbita planetaria es de : \t" + orbita_planetaria + "\n"
							+ "El satelite pertenece al planeta : \t\t" + planeta_al_q_pertenece + "\n");
					añadirastro(new Satelites(nombre, orbita_planetaria, diametro_medio, rotacion, traslacion,
							distancia_media, distancia_al_planeta, orbita_planetaria, planeta_al_q_pertenece));
					
					break;
					
				}

			case 2: {
				listarArray();

				break;
			}
			case 3: {
				listarSatelites();

				break;
			}
			case 4: {
				listarPlanetas();
				break;
			}

			}
		}
		
	}

	public void añadirastro(Astros astro) {

		for (int i = 0; i < array.length; i++) {
			if (array[i]==null) {
				array[i] = astro;
				break;
			}
		}
	}

	public void listarArray() {
		for (int i = 0; i < array.length; i++) {

			if (array[i] instanceof Satelites) {
				Satelites sat = (Satelites) array[i];
				System.out.println(sat.muestra() + "\n");
			} else if (array[i] instanceof Planetas) {
				Planetas pla = (Planetas) array[i];
				System.out.println(pla.muestra() + "\n");

			}

		}
	}

	public void listarSatelites() {
		for (int i = 0; i < array.length; i++) {

			if (array[i] instanceof Satelites) {
				Satelites sat = (Satelites) array[i];
				System.out.println(sat.muestra() + "\n");
			}

		}
	}

	public void listarPlanetas() {
		for (int i = 0; i < array.length; i++) {

			if (array[i] instanceof Planetas) {
				Planetas pla = (Planetas) array[i];
				System.out.println(pla.muestra() + "\n");
			}

		}
	}
}
