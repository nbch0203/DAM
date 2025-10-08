package boletin3_Ficheros_Ejercicio2;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;

public class Main {

	// Metodo para la escritura de los objetos en un fichero

	public static void escrituraObjetos(File f) throws FileNotFoundException, IOException {

		if (!f.exists())
			f.createNewFile();

		FileOutputStream fo = new FileOutputStream(f);

		try (ObjectOutputStream datos = new ObjectOutputStream(fo)) {
			Persona[] array = { new Empleado("123", "Nixon", 22, 1000.55), new Empleado("456", "David", 21, 955.86) };
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null) {
					Persona aux = array[i];
					datos.writeObject(aux);
				}
			}
		} catch (ObjectStreamException e) {
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

		Persona persona;

		// La lectura de los objetos siempre tiene que ser infinita
		// pero se termina con la EOFException para que finalize

		try {
			while (true) {
				persona = (Persona) datos.readObject();
				System.out.println(persona.toString());
			}
		} catch (EOFException e) {
			System.out.println("Es el final de la lectura");
			datos.close();
		}

	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		File fichero = new File("fichero.dat.");
		escrituraObjetos(fichero);
		lecturaObjetos(fichero);
	}

}
