package ejercicio1;

import java.io.IOException;
import java.util.Scanner;

import org.basex.api.client.ClientSession;

public class Ejercicio_1 {

	private static final String host = "localhost";
	private static final int puerto = 1984;
	private static final String usuario = "admin";
	private static final String password = "admin";

	// Ejercicio 1
	private static void mostrarLibrosAutores(ClientSession session) {
		System.out.println("Libros baratos mostrando la informacion de cada libro");

		String query = "for $x in //libro\n" + "where $x/precio < 30\n" + "return $x/titulo/text()";

		try {
			// NO abrir aquí, ya está abierta
			System.out.println(session.execute("XQUERY " + query));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Ejercicio 2
	private static void insertarLibro(ClientSession session) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce los datos del libro");
		System.out.println("Introduce la categoria: ");
		String categoria = sc.nextLine();
		System.out.println("Introudce el idioma: ");
		String idioma = sc.nextLine();
		System.out.println("Introduce el titulo: ");
		String titulo = sc.nextLine();
		System.out.println("Introduce el autor: ");
		String autor = sc.nextLine();
		System.out.println("Introduce el año: ");
		int anio = sc.nextInt();

		System.out.println("INtroduce la moneda: ");
		String moneda = sc.nextLine();
		System.out.println("Introduce el precio: ");
		double precio = sc.nextDouble();

		String updateQuery = "insert node " + "<libro categoria='" + categoria + "'>" + "<titulo idioma='" + idioma
				+ "'>" + titulo + "</titulo>" + "<autor>" + autor + "</autor>" + "<anio>" + anio + "</anio>"
				+ "<precio moneda='" + moneda + "'>" + precio + "</precio>" + "</libro> " + "into /biblioteca";

		try {
			session.execute("XQUERY " + updateQuery);
			System.out.println("Libro insertado correctamente");
		} catch (IOException e) {
			e.printStackTrace();
		}

		sc.close();
	}

	public static void main(String[] args) {
		try (ClientSession session = new ClientSession(host, puerto, usuario, password)) {
			// Abrir la BD UNA SOLA VEZ al principio
			session.execute("OPEN clase1");

			mostrarLibrosAutores(session);
			insertarLibro(session);
			System.out.println("\n--- DESPUÉS DE INSERTAR ---");
			mostrarLibrosAutores(session);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}