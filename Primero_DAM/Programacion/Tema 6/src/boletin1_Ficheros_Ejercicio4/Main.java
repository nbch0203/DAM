package boletin1_Ficheros_Ejercicio4;

import java.io.File;

public class Main {

	public static void cambiarNombre(String carpeta, String Docs, String Fotografias, String Fotos, String Libros,
			String Lecturas) {
		File file = new File(".");
		String[] archivos = file.list();

		// Bucle para recorrer el array de File

		for (String string : archivos) {
			File aux = new File(string);

			// IF en caso de que el directorio se
			// llame como se ha indicado

			if (aux.isDirectory() && aux.getName().equalsIgnoreCase(carpeta)) {
				File aux2 = new File(Docs);
				aux.renameTo(aux2);

				// Intentamos buscar el directorio pasado
				// por parametros y cambiar su nombre

				for (File file2 : aux2.listFiles()) {

					if (file2.getName().equalsIgnoreCase(Fotografias)) {
						File fotos = new File(Fotos);

						file2.renameTo(fotos);
						System.out.println(file2.getName());
					}
					if (file2.getName().equalsIgnoreCase(Libros)) {
						File lect = new File(Lecturas);
						lect.renameTo(lect);
						System.out.println(file2.getName());
					}
				}

//				if () {
//
//				}

				// Bucle para mostrar los cambios

				for (String string2 : archivos) {
					System.out.println(string2);
				}
			}
		}
	}

	public static void main(String[] args) {
		cambiarNombre("Doc", "Docs", "Docs/Fotografias", "Docs/Fotos", "Docs/Libros", "Docs/Lecturas");
	}

}
