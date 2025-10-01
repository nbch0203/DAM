package boletin3_Ficheros_Ejercicio1;

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

	public static void escrituraObjetos(File f) throws FileNotFoundException, IOException {

		if (!f.exists())
			f.createNewFile();

		FileOutputStream fo = new FileOutputStream(f);

		try (ObjectOutputStream datos = new ObjectOutputStream(fo)) {
			Persona[] array = { new Persona("123", "Nixon", 22), new Persona("456", "David", 21) };
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

	public static void lecturaObjetos(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
		if (!f.exists())
			System.out.println("El fichero puesto no existe");

		FileInputStream fi = new FileInputStream(f);

		ObjectInputStream datos = new ObjectInputStream(fi);

		Persona persona;

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
