package metodos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class METODOS {

	public void leer_por_linea(String archivo) throws FileNotFoundException, IOException {
		File file = new File(archivo);

		FileReader fr = new FileReader(file);
		BufferedReader bfr = new BufferedReader(fr);
		String texto = "";
		while ((texto = bfr.readLine()) != null) {
			System.out.println(texto);
		}
	}

	public void leer_por_letras() {
	}

	public void escribir_por_linea(String archivo, String texto) throws IOException {
		FileWriter fw = new FileWriter(archivo, true);
		BufferedWriter bfw = new BufferedWriter(fw);

		bfw.write(texto);
		bfw.newLine();
	}

	public void escribir_por_letras(String archivo, String texto) {

		for (int i = 0; i < texto.length(); i++) {

		}
	}

}
