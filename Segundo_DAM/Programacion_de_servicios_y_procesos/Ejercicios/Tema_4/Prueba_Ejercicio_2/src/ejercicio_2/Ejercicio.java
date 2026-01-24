package ejercicio_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.Scanner;

public class Ejercicio {

	public static void iniciar(String direccionWeb) {
		Scanner sc = new Scanner(System.in);
		File f = new File("fichero.html");

		if (!f.exists()) {
			System.out.println("Creando el fichero");
			f.createNewFile();
		}
		System.out.println("Escribiendo en el fichero");

		System.out.println("Escribe una direccion web:");
		direccionWeb = sc.nextLine();

		try {
			System.out.println("---------------------------------------------------");
			System.out.println("Conectando a: " + direccionWeb);
			System.out.println("---------------------------------------------------");

			URL url = URI.create(direccionWeb).toURL();
			HttpURLConnection conexion = url.openConnection();

			conexion.setRequestMethod("GET");

			conexion.setRequestProperty("User-Agent", "Mozilla/5.0");

			int codigoRespuesta = conexion.getResponseCode();

			System.out.println("Código de respuesta del servidor: " + codigoRespuesta);

			if (codigoRespuesta == HttpURLConnection.HTTP_OK) {

				InputStream flujoEntrada = conexion.getInputStream();

				InputStreamReader lector = new wInputStreamReader(flujoEntrada);

				BufferedReader bufferReader = new BufferedReader(lector);

				String linea;
				String texto;

				FileWriter fileouput = new FileWriter(f);
				BufferedWriter bufferWriter = new BufferedWriter(fileouput);

				while ((linea = bufferReader.readLine()) != null) {
					bufferWriter.write(linea);
				}
				System.out.println("\n--- FIN DEL DOCUMENTO HTML ---");

				// Cerramos los recursos
				bufferReader.close();
				bufferWriter.close();
				lector.close();
				flujoEntrada.close();

			} else {
				System.out.println("Error al conectar. El servidor respondió: " + codigoRespuesta);
			}
			conexion.disconnect();

		} catch (Exception e) {
			System.out.println("Ocurrió un error durante la conexión:");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		iniciar("https://www.google.com");

	}

}
