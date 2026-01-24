package ejemplos;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Ejemplo03 {
	  public static void main(String[] args) throws IOException {
	
			//creamos objeto File al directorio donde esta Ejemplo2
			File directorio = new File(".//bin");	

			//El proceso a ejecutar es Ejemplo02			
			ProcessBuilder pb = new ProcessBuilder("java", "ejemplos.Ejemplo02");

		    //se establece el directorio donde se encuentra el ejecutable
		    pb.directory(directorio);
		        
			System.out.printf("Directorio de trabajo: %s%n",pb.directory());

		    //se ejecuta el proceso
			Process p = pb.start();
			
			
		   //obtener la salida devuelta por el proceso
			try {
				InputStream is = p.getInputStream();
				int c;
				while ((c = is.read()) != -1)
					System.out.print((char) c);
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		  }
}
