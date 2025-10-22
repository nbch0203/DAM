package ejemplos;

import java.io.IOException;

public class Demonios {
 
	public static void main(String[] args) throws IOException {
		Thread d = new Demonio();
		System.out.println("d.isDaemon() = " + d.isDaemon());
		System.out.println("presione intro para terminar");
		System.in.read();
	}
}
