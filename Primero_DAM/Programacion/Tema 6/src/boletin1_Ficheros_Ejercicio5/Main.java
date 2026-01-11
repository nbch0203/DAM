package boletin1_Ficheros_Ejercicio5;

import java.io.File;

public class Main {

	public static void crearDir() {
		File documento = new File("Docs");
		File alfa = new File("Docs/Alfabeto");
		File miscosas = new File("Docs/miscosas");

		miscosas.mkdirs();
		if (miscosas.exists()) {
			File aux = new File("Docs/miscosas/Lecturas");
			File aux2 = new File("Docs/miscosas/Fotos");
			File fotos = new File("Docs/Fotografias");
			File lecturas = new File("Docs/Libros");

			fotos.renameTo(aux);
			lecturas.renameTo(aux2);
			alfa.mkdir();
			for (int i = 65; i >= 65 && i <= 90; i++) {
				char caracter = (char) i;
				File letras = new File("Docs/Alfabeto/" + caracter);
				letras.mkdir();

			}
		}

	}

	public static void main(String[] args) {
		crearDir();

	}

}
