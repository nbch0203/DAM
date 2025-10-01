package ejemplos;

import java.io.File;
import java.io.IOException;

public class Ejemplo8_2 {
	public static void main(String args[]) throws IOException {
//		ProcessBuilder pb = new ProcessBuilder("CMD");
		ProcessBuilder pb = new ProcessBuilder("sh");

		File fBat = new File("fichero.sh");
		File fOut = new File("salida.txt");
		File fErr = new File("error.txt");

		pb.redirectInput(ProcessBuilder.Redirect.from(fBat));
		pb.redirectOutput(ProcessBuilder.Redirect.to(fOut));
		pb.redirectError(ProcessBuilder.Redirect.appendTo(fErr));

		Process p = pb.start();
	}
}
