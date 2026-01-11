package ejercicio_1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Metodos {

	public void escribir(String fichero, ArrayList<Integer> lista) throws IOException {
		FileWriter fw = new FileWriter(fichero, true);
		try (BufferedWriter bfw = new BufferedWriter(fw)) {
			for (Integer integer : lista) {
				bfw.write(integer);
				bfw.newLine();
			}
		}

	}
}
