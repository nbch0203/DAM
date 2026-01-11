package boletin4_Ficheros_Ejercicio7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
	static File txt = new File("clientes.txt");
	static File dat = new File("clientes.dat");

	public static void escribirDat() throws FileNotFoundException, IOException {
		FileOutputStream escribir = new FileOutputStream(dat);
		ObjectOutputStream esc_obj = new ObjectOutputStream(escribir);
		if (!dat.exists()) {
			dat.createNewFile();
		}

		for (int i = 0; i < 4; i++) {
			Cliente cliente = new Cliente(i, "prueba " + i);
			esc_obj.writeObject(cliente);
		}

	}

	public static void leerDat() throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream leer = new FileInputStream(dat);
		ObjectInputStream leer_obj = new ObjectInputStream(leer);

		System.out.println(leer_obj.readObject());
	}

	public static void escribirTxt(int id, String nombre) throws FileNotFoundException, IOException {
		FileWriter escribir = new FileWriter(txt);
		BufferedWriter escribir_linea = new BufferedWriter(escribir);
		escribir_linea.write(id + "," + nombre);

	}

	public static void leerTxt() throws FileNotFoundException, IOException {
		FileReader leer = new FileReader(txt);
		BufferedReader leer_lineas = new BufferedReader(leer);
		System.out.println(leer_lineas.read());
	}

	public static void main(String[] args) {
		try {
			escribirDat();
			leerDat();
			
			escribirTxt(1, "Nixon");
			leerTxt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
