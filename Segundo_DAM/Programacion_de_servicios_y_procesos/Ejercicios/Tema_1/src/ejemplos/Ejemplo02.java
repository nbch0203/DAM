package ejemplos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejemplo02 {

	public static void main(String[] args) throws IOException {

		ProcessBuilder builder = new ProcessBuilder();

		//Podemos indicar al process builder los comandos a usar.
		//Para windows
//		builder.command("cmd.exe", "/c", "DIR");
		//Para linux
		builder.command("pwd");

		//ejecutamos el proces builder y nos genera un proceso
		Process p = builder.start();

		//Otros ejemplos de comandos
//		Process p = new ProcessBuilder("ifconfig").start();
//		Process p = new ProcessBuilder("bash", "-c", "date").start();
//		Process p = new ProcessBuilder("ps", "-AF").start();
		
		try {
			// Usamos este método para capturar el stream de salida del proceso p
			InputStream is = p.getInputStream();

			// mostramos en pantalla caracter a caracter leyendo con read()
//			 int c;
//			 while ((c = is.read()) != -1)
//				System.out.print((char) c);
//			 is.close();

			//Leemos linea a linea de texto usando buffer
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// COMPROBACION DE ERROR - 0 bien - 1 mal
		int exitVal;
		try {
			// waitFor() hace que el proceso actual espere
			// hasta que el subproceso p finalice
			// Este método recoge lo que System.exit() devuelve
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
