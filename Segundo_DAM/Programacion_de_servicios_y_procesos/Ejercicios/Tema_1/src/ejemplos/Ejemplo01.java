package ejemplos;
import java.io.IOException;

public class Ejemplo01 {

	public static void main(String[] args) throws IOException {
		ProcessBuilder pb=new ProcessBuilder ("pluma");
		Process p=pb.start();
		
		ProcessBuilder pb2=new ProcessBuilder ("galculator");
		Process p2=pb2.start();
	
	}

}
