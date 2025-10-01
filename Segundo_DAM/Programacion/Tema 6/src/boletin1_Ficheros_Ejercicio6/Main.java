package boletin1_Ficheros_Ejercicio6;

import java.io.File;

public class Main {

	public static boolean borrarTodo(File f) throws Exception {

		if (f.exists()) {
			if (f.isFile()) {
				f.delete();
				return true;

			} else {
				File[] archivos;
				archivos = f.listFiles();
				for (File file : archivos) {
					file.delete();
				}
				f.delete();
			}
		} else {
			throw new Exception();
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		File file = new File("Docs/miscosas/Fotos");
		try {
			borrarTodo(file);
		} catch (Exception e) {
			System.out.println("Error");
		}

	}

}
