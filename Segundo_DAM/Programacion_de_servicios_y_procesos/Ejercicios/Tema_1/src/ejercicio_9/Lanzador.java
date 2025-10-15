package ejercicio_9;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lanzador {
	public static void main(String[] args) {

		File directorio = new File(".//bin");

		ProcessBuilder pb = new ProcessBuilder("java", "ejercicio_9.Programa", "../texto.txt");

		try {
			pb.directory(directorio);

			Process proceso = pb.start();

			String aux;

			InputStreamReader isr = new InputStreamReader(proceso.getInputStream());
			BufferedReader linea = new BufferedReader(isr);
			while ((aux = linea.readLine()) != null) {

				System.out.println(aux);

			}

			BufferedReader error = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
			String lineaError;
			while ((lineaError = error.readLine()) != null) {
				System.err.println("ERROR: " + lineaError);
			}
			try {
				proceso.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
