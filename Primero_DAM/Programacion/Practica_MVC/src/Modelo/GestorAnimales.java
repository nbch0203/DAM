package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GestorAnimales {

	private static final File f1 = new File("animales.txt");
	private static final File f2 = new File("animales.dat");

	public static void guardarAnimalestexto(Animal a) throws IOException {
		FileWriter fw = new FileWriter(f1, true);
		BufferedWriter b1 = new BufferedWriter(fw);
		b1.write(a.toString() + "\n");

		b1.close();
		fw.close();
	}

	public boolean guardarAnimalesobjeto(Animal a) throws IOException {
		boolean primer = false;
		FileOutputStream fos = new FileOutputStream(f2);
		ObjectOutputStream ous = new ObjectOutputStream(fos);
		ObjectOutputStreamSinCabecera ousSIN = new ObjectOutputStreamSinCabecera(fos);

		if (primer) {
			ousSIN.writeObject(a);
			ousSIN.close();
			return true;

		}
		ous.writeObject(a);
		ous.close();
		return true;

	}

	public static String cargarAnimaletxt() throws IOException {
		String aux = "";
		String total = "";
		FileReader fr1 = new FileReader(f1);
		BufferedReader br1 = new BufferedReader(fr1);

		aux = br1.readLine();
		while (aux != null) {
			total += aux;
			aux = br1.readLine();
		}

		br1.close();
		fr1.close();
		return aux;
	}

	public static String cargarAnimalobjeto() throws IOException {
		String aux = "";
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f2))) {
			while (true) {
				Animal a2 = (Animal) ois.readObject();
				aux += a2.toString();
			}

		} catch (Exception e) {
			System.out.println("Archivo cerrado");

		}
		return aux;
	}
}
