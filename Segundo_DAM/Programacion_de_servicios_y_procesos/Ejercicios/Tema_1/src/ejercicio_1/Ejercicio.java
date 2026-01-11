package ejercicio_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Ejercicio {

	public static void main(String[] args) {
		try {
			// Le decimos primero donde estamos
			File directorio = new File(".//bin");

			// Preparamos el proceso con la informacion
			ProcessBuilder pb = new ProcessBuilder("java", "ejercicio_1.Main", args[0], args[1]);
			
			// Le decimos que trabaje en ese directorio
			pb.directory(directorio);
			
			// Le decimos que redirija el flujo de errores
			pb.redirectErrorStream(true);
			
			// Creamos el proceso y lo iniciamos
			Process proceso = pb.start();

			// Creamos un lector del flujo de la entrada
			BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

			// Leemos como siempre con el bufferedReader
			String linea;

			while ((linea = br.readLine()) != null) {
				System.out.println("Resultado del proceso: " + linea);
			}
			
			// Esperamos a que el proceso termine
			proceso.waitFor();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
