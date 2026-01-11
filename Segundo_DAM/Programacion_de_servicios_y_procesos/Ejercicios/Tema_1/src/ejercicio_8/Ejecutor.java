package ejercicio_8;

// EjecutorPalindromos.java
import java.io.*;
import java.util.*;

public class Ejecutor {
	public static void main(String[] args) {
		try {

			File finput = new File("input.txt");
			File foutput = new File("salida08.txt");
			// Paso 1: Ejecutar comando cat para volcar inputEj08.txt a salida08.txt
			ProcessBuilder pbCat = new ProcessBuilder("java", "cat");
			pbCat.redirectInput(finput);
			pbCat.redirectOutput(foutput);
			Process procesoCat = pbCat.start();
			procesoCat.waitFor();

			System.out.println("Fichero salida08.txt creado correctamente");

			// Paso 2: Esperar 5 segundos para asegurar que el fichero está escrito
			System.out.println("Esperando 5 segundos...");
			Thread.sleep(5000);

			// Paso 3: Leer salida08.txt línea por línea
			File archivoSalida = new File("salida08.txt");
			BufferedReader reader = new BufferedReader(new FileReader(archivoSalida));
			String linea;

			System.out.println("\n--- RESULTADOS DE PALÍNDROMOS ---");

			// Paso 4: Ejecutar PalindromoChecker para cada línea
			while ((linea = reader.readLine()) != null) {
				if (!linea.trim().isEmpty()) {
					ejecutarVerificadorPalindromo(linea.trim());
				}
			}

			reader.close();

		} catch (IOException e) {
			System.err.println("Error de E/S: " + e.getMessage());
		} catch (InterruptedException e) {
			System.err.println("Proceso interrumpido: " + e.getMessage());
		}
	}

	// Método para ejecutar el verificador de palíndromos
	private static void ejecutarVerificadorPalindromo(String palabra) {
		try {
			// Crear ProcessBuilder para ejecutar PalindromoChecker
			ProcessBuilder pb = new ProcessBuilder("java", "Verificador", palabra);

			// Redirigir error stream
			pb.redirectErrorStream(true);

			// Iniciar proceso
			Process proceso = pb.start();

			// Leer la salida del proceso
			BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			String linea;

			while ((linea = reader.readLine()) != null) {
				System.out.println(linea);
			}

			// Esperar a que termine el proceso
			int exitCode = proceso.waitFor();

		} catch (IOException e) {
			System.err.println("Error al ejecutar PalindromoChecker: " + e.getMessage());
		} catch (InterruptedException e) {
			System.err.println("Proceso interrumpido: " + e.getMessage());
		}
	}
}