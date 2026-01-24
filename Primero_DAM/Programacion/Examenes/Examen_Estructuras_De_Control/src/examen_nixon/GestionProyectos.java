package examen_nixon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Set;

public class GestionProyectos {
	static List<Proyecto> lista = new ArrayList<Proyecto>();
	static HashMap<Integer, Double> mapa = new HashMap<Integer, Double>();
	static double array[] = new double[10];

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int opcion;

		do {
			System.out.println("\nSeleccione una opción:");
			System.out.println("1. Alta de Proyecto");
			System.out.println("2. Baja de Proyecto");
			System.out.println("3. Modificar Proyecto");
			System.out.println("4. Consulta de Proyectos");
			System.out.println("5. Consultar Proyecto con Menor Presupuesto");
			System.out.println("6. Salir");
			opcion = scanner.nextInt();
			scanner.nextLine(); // Para limpiar el buffer de entrada

			switch (opcion) {
			case 1:
				altaProyecto(scanner);
				break;
			case 2:
				bajaProyecto(scanner);
				break;
			case 3:
				modificarProyecto(scanner);
				break;
			case 4:
				consultaProyectos();
				break;
			case 5:
				double resul = consultarMenorPresupuesto();
				if (resul == -1) {
					System.out.println("No hay proyectos registrados.");
				} else {
					System.out.println("ID del proyecto con menor presupuesto: " + resul);
				}
				break;
			case 6:
				System.out.println("¡Hasta luego!");
				break;
			default:
				System.out.println("Opción no válida. Intente nuevamente.");
			}
		} while (opcion != 6);
	}

	private static void altaProyecto(Scanner scanner) {

		System.out.println("Ingrese el ID del proyecto:");
		int id = scanner.nextInt();
		scanner.nextLine(); // Limpiar buffer
		System.out.println("Ingrese el nombre del proyecto:");
		String nombre = scanner.nextLine();
		System.out.println("Ingrese la fecha de inicio del proyecto (dd/mm/aaaa):");
		String fechaInicio = scanner.nextLine();
		System.out.println("Ingrese la fecha de fin del proyecto (dd/mm/aaaa):");
		String fechaFin = scanner.nextLine();
		System.out.println("Ingrese el estado del proyecto (En progreso, Completado, Pendiente):");
		String estado = scanner.nextLine();
		System.out.println("Ingrese el presupuesto del proyecto:");
		double presupuesto = scanner.nextDouble();

		// Bucle para comprobar que no existe otro proyecto igual

		for (Proyecto Proyecto : lista) {
			if (!lista.contains(Proyecto)) {
				lista.add(Proyecto);

				break;
			}
		}
		System.out.println("EL proyecto se ha añadido con exito a la lista");

		// Añadir la key y el valor al Hashmap

		mapa.put(id, presupuesto);
		System.out.println("El proyecto se ha añadido con exito al mapa");

		// Bucle para añadir el presupuesto del proyecto al array

		for (int i = 0; i < array.length; i++) {

			if (array[i] == 0) {
				array[i] = presupuesto;

			}
		}

		System.out.println("EL proyecto se ha añadido con exito al array");
	}

	private static void bajaProyecto(Scanner scanner) {
		System.out.println("Ingrese el ID del proyecto a eliminar:");
		int id = scanner.nextInt();
		scanner.nextLine(); // Limpiar buffer

		// Bucle para buscar el proyecto por la id en la lista

		for (Proyecto proyecto : lista) {
			if (proyecto.getId() == id) {
				lista.remove(id);

			}

		}

		// Eliminar el proyecto del Hashmap por id
		mapa.remove(id);

		// Bucle para poner a cero el valor del presupuesto en el array

		for (int i = 0; i < array.length; i++) {
			if (array[i] == id) {
				array[i] = 0;
			}
		}

	}

	private static void modificarProyecto(Scanner scanner) {
		System.out.println("Ingrese el ID del proyecto a modificar:");
		int id = scanner.nextInt();
		scanner.nextLine(); // Limpiar buffer

		// Bucle para comfirmar la existencia del proyecto a cambiar

		for (Proyecto proyecto : lista) {
			if (id == proyecto.getId()) {

				// Introducir el nuevo nombre del proyecto a cambiar
				System.out.println("Introduzca el nuevo nombre del proyecto");
				String nombre = scanner.next();
				proyecto.setNombre(nombre);

				// Introducir la nueva fecha de inicio del proyecto
				System.out.println("Introduzca la nueva fecha de inicio del proyecto");
				String fechaini = scanner.next();
				proyecto.setFechaInicio(fechaini);

				// Introduzcir la nueva fecha de fin del proyecto
				System.out.println("Introduzca la nueva fecha de fin del proyecto");
				String fechaf = scanner.next();
				proyecto.setFechaFin(fechaf);

				// Introduzcir el nuevo estado del proyecto
				System.out.println("Introduzca el nuevo estado del proyecto");
				String est = scanner.next();
				proyecto.setEstado(est);

				// Introduzcir el nuevo presupuesto del proyecto
				System.out.println("Introduzca el nuevo presupuesto del proyecto");
				double presu = scanner.nextDouble();
				mapa.replace(id, proyecto.getPresupuesto(), presu);
				proyecto.setPresupuesto(presu);

				break;
			}

		}

	}

	private static void consultaProyectos() {

		// Iterator para recorrer la lista y consultar los proyectos

//		Iterator<Proyecto> iterator =new 
//		
//		Collections.sort(lista);
//		if (pro.hasNext()) {
//			System.out.println(pro.next());
//		}

	}

	public ListIterator<Proyecto> listIterator(int index) {
		return lista.listIterator(index);
	}

	public Iterator<Proyecto> iterator() {
		return lista.iterator();
	}

	private static double consultarMenorPresupuesto() {
		double presupuestoMin = -1;

		return presupuestoMin;

	}
}