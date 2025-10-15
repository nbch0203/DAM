package ejercicio_9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Programa {

	public static ArrayList<String> leerfichero(File file, ArrayList<String> texto)
			throws IOException, FileNotFoundException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		String linea;

		if (!file.exists()) {
			System.out.println("El fichero no exsite");
		}

		while ((linea = br.readLine()) != null) {

			if (linea.equalsIgnoreCase("*")) {
				String nextline = br.readLine();
				if (nextline == null) {
					break;
				}
			} else
				texto.add(linea);

		}
		return texto;

	}

	public static void main(String[] args) {

		ArrayList<String> text = new ArrayList<String>();

		String rutaFichero = (args [0]);
		File fichero = new File(rutaFichero);

		if (!fichero.exists()) {
			try {
				fichero.createNewFile();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			leerfichero(fichero, text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (String string : text) {
			System.out.println(string);
		}

	}

}
