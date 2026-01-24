import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class EjemploHttpPantalla {
	public static void main(String[] args) {

		// 1. URL destino (Usamos una sencilla para el ejemplo, otras tienen seguridad y
		// detectan que no somos humanos navegando realmente)
//		String direccionWeb = "http://codigoelectronica.com/blog/http";
		String direccionWeb = "https://www.google.com";
//		String direccionWeb = "https://es.wikipedia.org/wiki/Hola_(saludo)";

		System.out.println("---------------------------------------------------");
		System.out.println("Conectando a: " + direccionWeb);
		System.out.println("---------------------------------------------------");

		try {
			// 2. Crear la URL de forma segura (URI -> URL)
			// Esto evita problemas con espacios o caracteres raros en la dirección
			URL url = URI.create(direccionWeb).toURL();

			// 3. Abrir la conexión y configurarla
			// HttpsURLConnection es hija (hereda) de HttpURLConnection. De esta manera
			// admite direcciones HTTP y HTTPS (polimorfismo)
			HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
			conexion.setRequestMethod("GET"); // Queremos LEER datos
			/*
			 * Le estás diciendo al servidor:
			 * "Hola, soy un navegador web estándar compatible con la tecnología Mozilla".
			 * Aunque sea mentira (porque eres un programa Java), el servidor baja la
			 * guardia y te entrega el mismo HTML que le entregaría a un usuario normal.
			 */
			conexion.setRequestProperty("User-Agent", "Mozilla/5.0"); // Nos disfrazamos de navegador

			// 4. Analizar la respuesta del servidor
			int codigoRespuesta = conexion.getResponseCode();
			System.out.println("Código de respuesta del servidor: " + codigoRespuesta);

			
			if (codigoRespuesta == HttpURLConnection.HTTP_OK) { // Equivale al código 200 = OK (Todo ha ido bien)

				// 5. Preparar la lectura del flujo de datos (Stream)
				InputStream flujoEntrada = conexion.getInputStream();

				// Usamos UTF_8 para intentar evitar los "rombos" al leer
				InputStreamReader lector = new InputStreamReader(flujoEntrada, StandardCharsets.UTF_8);
				BufferedReader buffer = new BufferedReader(lector);

				String linea;

				System.out.println("\n--- INICIO DEL DOCUMENTO HTML ---\n");

				// 6. Bucle de lectura: Leemos línea a línea y pintamos en pantalla
				while ((linea = buffer.readLine()) != null) {
					System.out.println(linea);
				}

				System.out.println("\n--- FIN DEL DOCUMENTO HTML ---");

				// Cerramos los recursos
				buffer.close();
				lector.close();
				flujoEntrada.close();

			} else {
				System.out.println("Error al conectar. El servidor respondió: " + codigoRespuesta);
			}

			// Cerrar la conexión de red
			conexion.disconnect();

		} catch (Exception e) {
			System.out.println("Ocurrió un error durante la conexión:");
			e.printStackTrace();
		}
	}
}
