package ejercicio_7;

import java.io.File;

public class Lanzador {
	public static void main(String[] args) {

		try {

			File directorio = new File(".//bin");
			ProcessBuilder pb = new ProcessBuilder("java", "Main", args[0], args[1]);

			pb.directory(directorio);

			Process proceso = pb.start();

			int valor = proceso.waitFor();
			System.out.println("El programa ha finalizado: " + valor);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
