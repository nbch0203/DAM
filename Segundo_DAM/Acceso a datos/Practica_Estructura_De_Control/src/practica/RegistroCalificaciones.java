package practica;

import java.util.*;
import java.lang.Comparable;

public class RegistroCalificaciones {
	double[][] matriz = new double[3][5];

	List<Estudiante> Listaestudiantes = new ArrayList<Estudiante>();
	HashMap<String, Double> Notasestudiante = new HashMap<String, Double>();
	List<Estudiante> Listaestudiantespro = new ArrayList<Estudiante>();

	public void registrarEstudiante(int id, String nombre, double[] calificaciones) {
//		if (!Listaestudiantes.contains(nombre)) {
			Listaestudiantes.add(new Estudiante(id, nombre, calificaciones));

//		} else {
//			System.out.println("El estudiante con el nombre " + nombre + " ya esta registrado");
//
//		}
		
//		for (int i = 0; i < matriz.length; i++) {
//			for (int j = 0; j < matriz[i].length; j++) {
//				if (matriz[i][j] == 0.0) {
//					matriz[j] = calificaciones;
//					System.out.println(matriz);
//				}
//			}
//		}
	}

	public Double calcularPromedios() {
		for (Estudiante estu : Listaestudiantespro) {
			Double califpro = calcularPromedios();
			if (califpro >= 8.00) {
				if (!Listaestudiantes.contains(estu)) {
					Listaestudiantespro.add(estu);
				}
			}
		}
		return null;

	}

	public void ordenarEstudiantesConPromedioMayorA8() {
//		Listaestudiantespro.sort((o1, o2) -> );
	}

	public String buscarEstudiantePorId(int id) {

		for (Estudiante estudi : Listaestudiantes) {
			if (estudi.getId() == id) {
				return "Estudiante :" + estudi.getId() + estudi.getNombre();
			}
		}
		return null;

	}

	public void mostrarInformacion() {
		for (Estudiante estu : Listaestudiantes) {
			System.out.println("Estudiante nº: " + estu.getId() + " nombre: " + estu.getNombre() + " , Calificaciones :"
					+ matriz + " , Promedio: " + calcularPromedios());
		}

	}

	@Override
	public String toString() {
		return "RegistroCalificaciones [Listaestudiantes=" + Listaestudiantes + "]";
	}

	public static void main(String[] args) {
		RegistroCalificaciones registro = new RegistroCalificaciones();

		// Registrar estudiantes
		registro.registrarEstudiante(1, "Juan", new double[] { 8.5, 7.8, 9.0 });
		registro.registrarEstudiante(2, "María", new double[] { 9.5, 8.2, 9.8 });
		registro.registrarEstudiante(3, "Pedro", new double[] { 6.5, 7.0, 6.8 });
		registro.registrarEstudiante(4, "Pedro", new double[] { 7.2, 6.8, 7.5 });
		registro.registrarEstudiante(4, "Ana", new double[] { 8.0, 8.0, 8.0 });
		registro.registrarEstudiante(5, "Luis", new double[] { 7.2, 6.8, 7.5 });

		// Calcular promedios
		registro.calcularPromedios();

		// Mostrar información
		System.out.println("\n=== Mostrar Información antes de ordenar ===");
		registro.mostrarInformacion();

		// Ordenar estudiantes con promedio mayor a 8
		registro.ordenarEstudiantesConPromedioMayorA8();

		// Buscar un estudiante por ID
		System.out.println("\n=== Buscar Estudiantes ===");
		System.out.println(registro.buscarEstudiantePorId(2)); // Ejemplo para ID 2
		System.out.println(registro.buscarEstudiantePorId(1)); // Ejemplo para ID 1

		// Mostrar información
		System.out.println("\n=== Mostrar Información después de ordenar===");
		registro.mostrarInformacion();
	}

}
