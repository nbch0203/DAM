package ejercicio_3;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {

	private static File file = new File("alumnos.dat");

	public static void leer(File file) throws IOException, ClassNotFoundException {

		Alumno alumno;

		if (!file.exists()) {
			file.createNewFile();
		}

		FileInputStream fis = new FileInputStream(file);
		try (ObjectInputStream oos = new ObjectInputStream(fis)) {
			while (true) {
				alumno = (Alumno) oos.readObject();

				System.out.println(alumno.toString());
			}

		} catch (EOFException e) {
			System.out.println("Ha finalizado la lectura");
		}

	}

	public static void escribir(File file) throws IOException {
		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		lista.add(new Alumno("1", "Pedro", "sanchez", "mates", new Fecha(1, "enero", 2003)));
		lista.add(new Alumno("2", "Antonio", "cruz", "lengua", new Fecha(2, "febrero", 2004)));
		lista.add(new Alumno("3", "Lucia", "ramirez", "historia", new Fecha(3, "marzo", 2005)));

		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		if (!file.exists()) {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);

		} else {
			fos = new FileOutputStream(file, true);
			oos = new ObjectOutputStreamSinCabecera(fos);

		}
		for (Alumno alumno : lista) {
			oos.writeObject(alumno);
		}
		oos.close();

	}

	public static void main(String[] args) {

		try {
			escribir(file);
			leer(file);

		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
