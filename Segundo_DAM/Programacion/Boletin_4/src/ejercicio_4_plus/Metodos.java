package ejercicio_4_plus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Metodos {
	private static final int TAM_NOMBRE = 10; // caracteres fijos
	private static final int TAM_REGISTRO = 4 + (2 * TAM_NOMBRE); // 4 bytes nota + 20 bytes nombre = 24

	public void escribir(File file, Alumno alumno) throws FileNotFoundException, IOException {
		RandomAccessFile fr = new RandomAccessFile(file, "rw");

		int nota = alumno.getNota();
		String nombre = alumno.getNombre();

		fr.seek(file.length());
		fr.writeInt(nota);

		StringBuilder buffer = new StringBuilder(nombre);
		buffer.setLength(10);
		fr.writeChars(buffer.toString());

		fr.close();

	}

	public ArrayList<Alumno> leerTodos(File file) throws IOException {
		ArrayList<Alumno> lista = new ArrayList<>();
		RandomAccessFile fr = new RandomAccessFile(file, "r");

		while (fr.getFilePointer() < fr.length()) {
			int nota = fr.readInt();

			char[] nombre = new char[TAM_NOMBRE];
			for (int i = 0; i < TAM_NOMBRE; i++) {
				nombre[i] = fr.readChar();
			}
			String nombreS = new String(nombre);

			lista.add(new Alumno(nota, nombreS));
		}

		fr.close();
		return lista;
	}

//	public void buscar(File file, String buscar, String nuevo) throws IOException, FileNotFoundException {
//		RandomAccessFile fr = new RandomAccessFile(file, "rw");
//
//		int posicion = 0;
//
//		String nombreS = null;
//
//		Alumno alumno = leer(file);
//
//		if (alumno.getNombre().equalsIgnoreCase(buscar)) {
//			fr.seek(posicion - 4);
//			StringBuilder buffer = new StringBuilder(nuevo);
//			buffer.setLength(10);
//			fr.writeChars(buffer.toString());
//
//		}
//
//		posicion = posicion + 24;
//
//		fr.close();
//
//	}

	public boolean modificarNota(File file, String nombreBuscado, int nuevaNota) throws IOException {
		RandomAccessFile fr = new RandomAccessFile(file, "rw");

		long posicion = 0;

		while (fr.getFilePointer() < fr.length()) {
			int nota = fr.readInt();

			char[] nombre = new char[TAM_NOMBRE];
			for (int i = 0; i < TAM_NOMBRE; i++) {
				nombre[i] = fr.readChar();
			}
			String nombreS = new String(nombre);

			if (nombreS.equalsIgnoreCase(nombreBuscado)) {
				// Volvemos al inicio de este registro para reescribir la nota
				fr.seek(posicion);
				fr.writeInt(nuevaNota);
				fr.close();
				return true;
			}

			posicion += TAM_REGISTRO;
		}

		fr.close();
		return false; // no encontrado
	}

	public boolean modificarUltimo(File file, int nuevaNota) throws IOException {
		RandomAccessFile fr = new RandomAccessFile(file, "rw");

		if (fr.length() == 0) {
			fr.close();
			return false; // fichero vacío
		}

		// Posicionarse en el último registro
		long ultimaPos = fr.length() - TAM_REGISTRO;
		fr.seek(ultimaPos);

		// Reescribir solo la nota
		fr.writeInt(nuevaNota);

		fr.close();
		return true;
	}

}
