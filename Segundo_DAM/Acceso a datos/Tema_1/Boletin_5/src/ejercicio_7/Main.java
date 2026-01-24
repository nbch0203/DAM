package ejercicio_7;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	static File ficheroAleatorio = new File("utensilios.dat");
	static File ficheroObjetos = new File("utensiliosObj.dat");
	static File ficheroTexto = new File("utensilios.txt");

	// --- 1. Crear fichero de acceso aleatorio ---
	public static void crearFicheroAleatorio() throws IOException {
		try (RandomAccessFile raf = new RandomAccessFile(ficheroAleatorio, "rw")) {
			// Sobrescribimos desde cero
			raf.setLength(0);

			// Datos de ejemplo
			String[] nombres = { "Cuchara", "Tenedor", "Cuchillo", "Plato", "Vaso" };
			int[] unidades = { 10, 20, 15, 8, 12 };

			for (int i = 0; i < nombres.length; i++) {
				// Guardamos en cada línea: nombre,unidades
				raf.writeUTF(nombres[i]);
				raf.writeInt(unidades[i]);
			}
		}
	}

	// --- 2. Mostrar utensilio por número de línea ---
	public static void mostrarPorLinea(int numLinea) throws IOException {
		try (RandomAccessFile raf = new RandomAccessFile(ficheroAleatorio, "r")) {
			raf.seek(0);
			for (int i = 0; i < numLinea; i++) {
				String nombre = raf.readUTF();
				int unidades = raf.readInt();
				if (i == numLinea - 1) {
					System.out.println("Línea " + numLinea + ": " + nombre + " - " + unidades);
				}
			}
		}
	}

	// --- 3 y 4. Pasar a array de Utensilio ---
	public static Utensilio[] cargarArrayDesdeFichero() throws IOException {
		List<Utensilio> lista = new ArrayList<>();
		try (RandomAccessFile raf = new RandomAccessFile(ficheroAleatorio, "r")) {
			while (raf.getFilePointer() < raf.length()) {
				String nombre = raf.readUTF();
				int unidades = raf.readInt();
				lista.add(new Utensilio(nombre, unidades));
			}
		}
		return lista.toArray(new Utensilio[0]);
	}

	// --- 5. Volcar array a fichero de objetos ---
	public static void volcarAFicheroObjetos(Utensilio[] array) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroObjetos))) {
			for (Utensilio u : array) {
				oos.writeObject(u);
			}
		}
	}

	// --- 6. Mostrar información del fichero de objetos ---
	public static void mostrarFicheroObjetos() throws IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroObjetos))) {
			while (true) {
				try {
					Utensilio u = (Utensilio) ois.readObject();
					System.out.println(u);
				} catch (EOFException e) {
					break;
				}
			}
		}
	}

	// --- 7. Contar unidades de cada utensilio y mostrar en consola ---
	public static void resumenConsola() throws IOException, ClassNotFoundException {
		Map<String, Integer> resumen = new HashMap<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroObjetos))) {
			while (true) {
				try {
					Utensilio u = (Utensilio) ois.readObject();
					resumen.put(u.getNombre(), resumen.getOrDefault(u.getNombre(), 0) + u.getUnidades());
				} catch (EOFException e) {
					break;
				}
			}
		}
		System.out.println("Resumen:");
		for (Map.Entry<String, Integer> e : resumen.entrySet()) {
			System.out.println(e.getKey() + " -> " + e.getValue() + " unidades");
		}
	}

	// --- 8. Guardar resumen en fichero de texto ---
	public static void resumenATexto() throws IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroObjetos));
				PrintWriter pw = new PrintWriter(new FileWriter(ficheroTexto))) {

			Map<String, Integer> resumen = new HashMap<>();

			while (true) {
				try {
					Utensilio u = (Utensilio) ois.readObject();
					resumen.put(u.getNombre(), resumen.getOrDefault(u.getNombre(), 0) + u.getUnidades());
				} catch (EOFException e) {
					break;
				} catch (ClassNotFoundException e) {
					throw new IOException("Error leyendo objeto", e);
				}
			}

			for (Map.Entry<String, Integer> e : resumen.entrySet()) {
				pw.println(e.getKey() + " -> " + e.getValue() + " unidades");
			}
		}
	}

	// --- MAIN ---
	public static void main(String[] args) {
		try {
			crearFicheroAleatorio();
			mostrarPorLinea(3);

			Utensilio[] array = cargarArrayDesdeFichero();
			volcarAFicheroObjetos(array);

			mostrarFicheroObjetos();
			resumenConsola();
			resumenATexto();

			System.out.println("✅ Ejercicio 7 completado");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}