package app;

import java.util.Scanner;

import dao.LibroDAOImpl;
import modelos.DetalleLibro;
import modelos.Libro;
import servicio.LibroService;

public class Menu {
	private static LibroService libroService;
	private static Scanner sc = new Scanner(System.in);;

	public static void mostrarMenu() {

		int opcion = -1;
		while (opcion != 0) {

			System.out.println("\n--- Gestión de Libros ---");
			System.out.println("1. Crear Libro");
			System.out.println("2. Leer Libro");
			System.out.println("3. Actualizar Libro");
			System.out.println("4. Eliminar Libro");
			System.out.println("0. Salir");
			System.out.print("Opción: ");

			opcion = sc.nextInt();
			sc.nextLine(); // Limpiar buffer

			switch (opcion) {
			case 1:
				crear();
				break;
			case 2:
//					leer();
				break;
			case 3:
				actualizar();
				break;
			case 4:
//					eliminar();
				break;
			case 0:
				System.out.println("Saliendo del sistema...");
				break;
			default:
				System.out.println("Opción no válida.");
			}

		}

	}

	public static void crear() {
		try {
			Libro l = new Libro();
			DetalleLibro d = new DetalleLibro();

			System.out.println("Titulo del libro: ");
			l.setTitulo(sc.nextLine());
			System.out.println("Nombre del autor: ");
			l.setAutor(sc.nextLine());

			System.out.println("Sipnosis del libro: ");
			d.setSipnosis(sc.nextLine());
			d.setLibro(l);
			l.setDetalle(d);

			System.out.println("Guardando el libro........");

			libroService.registrarLibro(l);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al guardar el libro: " + e.getMessage());
		}
	}

	public static void actualizar() {
		System.out.println("Introduzca el id del libro: ");
		Long id = sc.nextLong();
		sc.nextLine();

		Libro l = null;
		try {
			l = libroService.obtenerLibro(id);

			if (l == null) {
				System.out.println("No existe ese libro");
			}

			System.out.println("--- Editando Libro " + id + " ---");
			System.out.println("Título actual: " + l.getTitulo() + ". Ingrese nuevo título (o Enter para mantener):");
			String nuevoTitulo = sc.nextLine();
			if (!nuevoTitulo.isEmpty())
				l.setTitulo(nuevoTitulo);

			System.out.println("Autor actual: " + l.getAutor() + ". Ingrese nuevo autor (o Enter para mantener):");
			String nuevoAutor = sc.nextLine();
			if (!nuevoAutor.isEmpty())
				l.setAutor(nuevoAutor);

			libroService.modificarLibro(l);
			System.out.println("Libro ID " + id + " actualizado.");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error al actualizar: " + e.getMessage());
		}

	}

	public static void main(String[] args) {

		mostrarMenu();

	}

}
