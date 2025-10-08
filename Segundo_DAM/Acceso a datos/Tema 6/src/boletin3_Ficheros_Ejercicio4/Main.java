package boletin3_Ficheros_Ejercicio4;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.util.Iterator;

public class Main {
	static String[] array = { "1234-ABC", "2345-FGT", "7412-RET", "5285-NBN", "7896-JNB", "9632-POI", "1289-GTR" };
	static long km[] = { 100000, 200000, 150000, 1200, 3000, 40000, 180000 };
	static int caballos[] = { 95, 110, 150, 125, 110, 150, 180 };

	// Metodo para la escritura de los objetos en un fichero

	public static void escrituraObjetos(File f) throws FileNotFoundException, IOException {

		if (!f.exists())
			f.createNewFile();

		FileOutputStream fo = new FileOutputStream(f);

		try (ObjectOutputStream datos = new ObjectOutputStream(fo)) {
			for (int i = 0; i < array.length; i++) {

				if (array[i] != null) {

					Vehiculo aux = new Vehiculo(i + 1, array[i], km[i], caballos[i]);
					datos.writeObject(aux);
				}
			}
		} catch (

		ObjectStreamException e) {
			System.out.println("Error de onjetos");
		} catch (IOException e) {
			System.out.println("Error de entrada salida");
		}
	}

	// Metodo para la lectura de los objetos del fichero

	public static void lecturaObjetos(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
		if (!f.exists())
			System.out.println("El fichero puesto no existe");

		FileInputStream fi = new FileInputStream(f);

		ObjectInputStream datos = new ObjectInputStream(fi);

		Vehiculo vehiculo;

		// La lectura de los objetos siempre tiene que ser infinita
		// pero se termina con la EOFException para que finalize

		try {
			while (true) {
				vehiculo = (Vehiculo) datos.readObject();
				System.out.println(vehiculo.toString());
				for (int i = 0; i < array.length; i++) {
					if (vehiculo.getMatricula().equals(array[i]) && vehiculo.getKm() == km[i]
							&& vehiculo.getCaballos() == caballos[i]) {

						System.out.println("Los datos del vehiculo con el id =" + i + " son correctos");
						System.out.println("-------------------------------------------------------------------");
					}

				}

			}
		} catch (EOFException e) {
			System.out.println("Es el final de la lectura");
			datos.close();
		}

	}

	public static void buscarVehiculo(File f, int id)
			throws IOException, FileNotFoundException, ClassNotFoundException {

		FileInputStream fi = new FileInputStream(f);

		ObjectInputStream datos = new ObjectInputStream(fi);

		Vehiculo vehiculo;
		try {
			while (true) {
				vehiculo = (Vehiculo) datos.readObject();
				for (int i = 0; i < array.length; i++) {
					if (vehiculo.getId() == id) {
						System.out.println(vehiculo.toString());
						System.out.println("-------------------------------------------------------------------");
						break;
					}

				}

			}
		} catch (EOFException e) {
			System.out.println("Es el final de la lectura");
			datos.close();
		}

	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		File fichero = new File("Ejercicio4.dat.");
		escrituraObjetos(fichero);
		lecturaObjetos(fichero);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		buscarVehiculo(fichero, 3);
	}

}
