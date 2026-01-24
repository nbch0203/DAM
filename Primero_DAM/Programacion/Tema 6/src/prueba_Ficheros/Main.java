package prueba_Ficheros;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		
		System.out.println("Ficheros en el directorio actual: ");
		File f= new File("C:\\Users\\nixon\\AppData\\Local");
		String [] archivos = f.list();
		for (int i = 0; i < archivos.length; i++) {
			System.out.println(archivos[i]);
		}
	}
}
