package ejercicio_10;

import java.io.File;
import java.io.IOException;

public class Ejercutor {
	public static void main(String[] args) {
		File script = new File("script.bat");
		if (!script.exists()) {
			System.out.println("El script no existe");

		}

		ProcessBuilder pb = new ProcessBuilder("cmd");
		try {
			pb.redirectInput(script);

			Process proceso = pb.start();
			int salida = proceso.waitFor();

			if (salida == 1) {
				System.out.println("La carpeta se ha creado correctamente");
			} else
				System.out.println("La carpetea no se ha podido crear");

		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
