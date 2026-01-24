package ejercicio_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
	static final File file = new File("enteros.dat");

	public static void mostrarFichero() throws FileNotFoundException, IOException {
		System.out.println("=== Lista de numeros enteros Guardados ===");

		RandomAccessFile fr = null;
		int num;
		int posicion = 0;

		fr = new RandomAccessFile(file, "r");

		while (fr.getFilePointer() < fr.length()) {

			fr.seek(posicion);
			num = fr.readInt();

			System.out.println("Numeros: " + num);
			posicion += 4;

		}
		fr.close();

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("El contenido del archivo es el siguiente:");

		try {
			mostrarFichero();
		} catch (IOException e) {
			// TODO Auto-generated catch block

		}

		RandomAccessFile fr = null;

		int numeros = 0;

		try {
			fr = new RandomAccessFile(file, "rw");

			System.out.println("Escribe un numero:");

			numeros = sc.nextInt();
			fr.seek(file.length());

			fr.writeInt(numeros);

			fr.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
