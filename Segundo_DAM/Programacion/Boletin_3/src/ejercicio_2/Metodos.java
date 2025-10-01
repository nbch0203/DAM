package ejercicio_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

public class Metodos {

	public void escribir(File file) throws IOException {
		FileWriter fw = new FileWriter(file);
		String texto = "";
		try (BufferedWriter bfw = new BufferedWriter(fw)) {

			bfw.write(texto);
			bfw.newLine();
		}

	}

	public Boolean verificar(int numero, File file) throws FileNotFoundException {

		FileReader fr = new FileReader(file);
		boolean veri = false;

		try (BufferedReader bfr = new BufferedReader(fr)) {

			while (bfr.readLine() != null) {
				if (bfr.read() == numero) {
					return veri = true;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return veri;
	}

	public int generaAleatorio(int menor, int mayor, int numero_veces) {

		Random r = new Random();
		int valor = 0;
		for (int i = 0; i < numero_veces; i++) {
			while (valor < menor || valor > mayor) {
				valor = r.nextInt(10);

			}

		}

		return valor;
	}

}
