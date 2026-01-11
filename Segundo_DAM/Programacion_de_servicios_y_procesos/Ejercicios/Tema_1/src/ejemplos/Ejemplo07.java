package ejemplos;

import java.io.File;
import java.io.IOException;

public class Ejemplo07 {
	public static void main(String args[]) throws IOException {
//		    ProcessBuilder pb = new ProcessBuilder("CMD","/C" ,"DIR");
		ProcessBuilder pb = new ProcessBuilder("ls");

		File fOut = new File("salida.txt");
		File fErr = new File("error.txt");

		pb.redirectOutput(fOut); // es el stream que recogemos cocn getinputstream
		pb.redirectError(fErr);
		pb.start();
	}
}
