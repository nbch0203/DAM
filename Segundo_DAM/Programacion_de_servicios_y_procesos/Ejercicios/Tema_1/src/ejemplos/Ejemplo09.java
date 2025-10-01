package ejemplos;

import java.io.IOException;

public class Ejemplo09 {
	public static void main(String args[]) throws IOException {
		
//		ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "DIR");
		ProcessBuilder pb = new ProcessBuilder("ls");

		//la salida a consola
		pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);	    
		Process p = pb.start();		
	
	}
}
