package cliente;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class AppCliente {
	private static final String URL_API = "http://localhost:8080/api/tareas";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcion = 0;
		System.out.println("--- GESTOR DE TAREAS (CLIENTE HTTP) ---");
		while (opcion != 5) {
			System.out.println("\n1. Ver tareas (GET)");
			System.out.println("2. Crear tarea (POST)");
			System.out.println("3. Marcar como hecha (PUT)");
			System.out.println("4. Borrar tarea (DELETE)");
			System.out.println("5. Salir");
			System.out.print("> Elige: ");
			try {
				opcion = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				opcion = 0;
			}
			switch (opcion) {
			case 1:
				hacerGet();
				break;
			case 2:
				System.out.print("Título: ");
				hacerPost(scanner.nextLine());
				break;
			case 3:
				System.out.print("ID a completar: ");
				hacerPut(Long.parseLong(scanner.nextLine()));
				break;
			case 4:
				System.out.print("ID a borrar: ");
				hacerDelete(Long.parseLong(scanner.nextLine()));
				break;
			case 5:
				System.out.println("Cerrando...");
				break;
			default:
				System.out.println("Opción incorrecta");
			}
		}
		scanner.close();
	}// --- MÉTODOS DE CONEXIÓN CON EL SERVIDOR---

	private static void hacerGet() {
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL_API)).GET().build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			// Formateo visual simple para que no salga todo en una línea
			String json = response.body();
			if (json.equals("[]")) {
				System.out.println("La lista está vacía.");
			} else {
				System.out.println("\n--- TAREAS ---");
				// “Truco” para imprimir cada objeto en una línea nueva
				System.out.println(json.replace("},{", "},\n{"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void hacerPost(String titulo) {
		try {
			// Construimos el JSON a mano
			String json = String.format("{\"titulo\": \"%s\", \"hecha\": false}", titulo);
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL_API))
					.header("Content-Type", "application/json")// Avisamos que es JSON
					.POST(HttpRequest.BodyPublishers.ofString(json)).build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("Creada: " + response.body());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void hacerPut(long id) {
		try {
			String json = "{\"titulo\": \"x\", \"hecha\": true}"; // Solo nos importa 'hecha'
			String url = URL_API + "/" + id; // URL específica: .../api/tareas/1
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
					.header("Content-Type", "application/json").PUT(HttpRequest.BodyPublishers.ofString(json)).build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("Resultado: " + response.statusCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void hacerDelete(long id) {
		try {
			String url = URL_API + "/" + id;
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).DELETE().build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("Borrado: " + response.body());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Método para pedir tareas con filtro
	private static void hacerGetConFiltro(boolean soloHechas) {
		try {
			// Construimos la URL con el parámetro ?hecha=true o false
			String url = URL_API + "?hecha=" + soloHechas;
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("\n--- TAREAS FILTRADAS (" + soloHechas + ") ---");
			String json = response.body();
			if (json.equals("[]")) {
				System.out.println("No se encontraron tareas con ese estado.");
			} else {
				System.out.println(json.replace("},{", "},\n{"));
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}