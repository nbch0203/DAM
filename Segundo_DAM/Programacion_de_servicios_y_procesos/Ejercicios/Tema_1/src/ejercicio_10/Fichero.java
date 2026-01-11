package ejercicio_10;

import java.io.File;
import java.io.IOException;

public class Fichero {
	public static void main(String[] args) {

		ProcessBuilder pb = new ProcessBuilder("java", "sh");
		File archivo = new File("script.sh");
		pb.redirectInput(archivo);
		try {
			Process proceso = pb.start();
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
