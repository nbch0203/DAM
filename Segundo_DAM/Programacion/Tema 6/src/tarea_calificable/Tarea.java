package tarea_calificable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Tarea {

	// Método para ejecutar el menú interactivo
	public static void ejecutarMenu() {
		Scanner scanner = new Scanner(System.in);
		int opcion;
		String directoryPath = "";
		String filePath = "";

		do {
			// Preguntar por la ruta del directorio y archivo al inicio del bucle
			System.out.print("\nIngrese la ruta del directorio: ");
			directoryPath = scanner.nextLine();
			System.out.print("Ingrese el nombre del archivo (sin la ruta): ");
			String nombreArchivo = scanner.nextLine();
			filePath = directoryPath + File.separator + nombreArchivo;

			// Crear el directorio y el archivo si no existen
			crearDirectorioYArchivo(directoryPath, filePath);

			System.out.println("\n--- Menú ---");
			System.out.println("1. Escribir en el archivo");
			System.out.println("2. Leer el archivo");
			System.out.println("3. Renombrar directorio o fichero");
			System.out.println("4. Mover fichero a otro directorio");
			System.out.println("5. Borrar fichero o directorio");
			System.out.println("6. Cambiar ruta del archivo/directorio");
			System.out.println("7. Salir");
			System.out.print("Seleccione una opción: ");
			opcion = scanner.nextInt();
			scanner.nextLine(); // Limpiar el buffer del scanner

			switch (opcion) {
			case 1:
				System.out.print("Ingrese el texto a escribir en el archivo: ");
				String contenido = scanner.nextLine();
				escribirArchivo(filePath, contenido);
				break;
			case 2:
				leerArchivo(filePath);
				break;
			case 3:
				System.out.print("Ingrese la ruta completa del directorio o archivo a renombrar: ");
				String rutaOriginal = scanner.nextLine();
				System.out.print("Ingrese el nuevo nombre (sin la ruta): ");
				String nuevoNombre = scanner.nextLine();
				renombrarDirectorioOArchivo(rutaOriginal, nuevoNombre);
				break;
			case 4:
				System.out.print("Ingrese la ruta completa del fichero a mover: ");
				String rutaFichero = scanner.nextLine();
				System.out.print("Ingrese la ruta completa del directorio destino: ");
				String rutaDestino = scanner.nextLine();
				moverFichero(rutaFichero, rutaDestino);
				break;
			case 5:
				System.out.print("Ingrese la ruta completa del fichero o directorio a borrar: ");
				String rutaABorrar = scanner.nextLine();
				borrarFicheroODirectorio(rutaABorrar);
				break;
			case 6:
				// No hacer nada, el bucle volverá a preguntar por la ruta
				System.out.println("Cambiando ruta del archivo/directorio...");
				break;
			case 7:
				System.out.println("Saliendo del programa...");
				break;
			default:
				System.out.println("Opción no válida. Intente de nuevo.");
			}
		} while (opcion != 7);

		scanner.close();
	}

	// Método para crear un directorio y un archivo si no existen
	public static void crearDirectorioYArchivo(String directoryPath, String filePath) {
		// Crear el directorio si no existe
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			if (directory.mkdir()) {
				System.out.println("Directorio creado: " + directoryPath);
			} else {
				System.out.println("No se pudo crear el directorio: " + directoryPath);
				return;
			}
		} else {
			System.out.println("El directorio ya existe: " + directoryPath);
		}

		// Crear el archivo si no existe
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				if (file.createNewFile()) {
					System.out.println("Archivo creado: " + filePath);
				} else {
					System.out.println("No se pudo crear el archivo: " + filePath);
				}
			} catch (IOException e) {
				System.out.println("Ha habido un error en la entrada/salida en la creacion del archivo");
			}
		} else {
			System.out.println("El archivo ya existe: " + filePath);
		}
	}

	// Método para escribir en un archivo
	public static void escribirArchivo(String filePath, String contenido) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write(contenido);
			System.out.println("Texto escrito en el archivo.");
		} catch (IOException e) {
			System.out.println("Ha habido un problema al escribir en el archivo");
		}
	}

	// Método para leer el contenido de un archivo
	public static void leerArchivo(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			System.out.println("Contenido del archivo:");
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			System.out.println("Ha habido un problema con la entrada/salida en la lectura del archivo");
		}
	}

	// Método para renombrar un directorio o archivo
	public static void renombrarDirectorioOArchivo(String rutaOriginal, String nuevoNombre) {
		File archivoODirectorio = new File(rutaOriginal);

		// Verificar si la ruta contiene al menos dos carpetas anidadas
		if (archivoODirectorio.getParentFile() == null || archivoODirectorio.getParentFile().getParentFile() == null) {
			System.out.println("Error: La ruta debe contener al menos dos carpetas anidadas.");
			return;
		}

		// Obtener la ruta del directorio padre
		File directorioPadre = archivoODirectorio.getParentFile();
		String nuevaRuta = directorioPadre.getAbsolutePath() + File.separator + nuevoNombre;

		// Renombrar el archivo o directorio
		if (archivoODirectorio.renameTo(new File(nuevaRuta))) {
			System.out.println("Renombrado exitosamente a: " + nuevaRuta);
		} else {
			System.out.println("Error: No se pudo renombrar el archivo o directorio.");
		}
	}

	// Método para mover un fichero a otro directorio
	public static void moverFichero(String rutaFichero, String rutaDestino) {
		File fichero = new File(rutaFichero);
		File directorioDestino = new File(rutaDestino);

		// Verificar si el fichero existe
		if (!fichero.exists()) {
			System.out.println("Error: El fichero no existe.");
			return;
		}

		// Crear el directorio destino si no existe
		if (!directorioDestino.exists()) {
			if (directorioDestino.mkdirs()) {
				System.out.println("Directorio destino creado: " + rutaDestino);
			} else {
				System.out.println("Error: No se pudo crear el directorio destino.");
				return;
			}
		}

		// Mover el fichero
		String nuevaRuta = rutaDestino + File.separator + fichero.getName();
		if (fichero.renameTo(new File(nuevaRuta))) {
			System.out.println("Fichero movido exitosamente a: " + nuevaRuta);
		} else {
			System.out.println("Error: No se pudo mover el fichero.");
		}
	}

	// Método para borrar un fichero o directorio
	public static void borrarFicheroODirectorio(String ruta) {
		File archivoODirectorio = new File(ruta);

		// Verificar si existe
		if (!archivoODirectorio.exists()) {
			System.out.println("Error: La ruta no existe.");
			return;
		}

		// Si es un directorio, borrar su contenido primero
		if (archivoODirectorio.isDirectory()) {
			borrarContenidoDirectorio(archivoODirectorio);
		}

		// Borrar el archivo o directorio vacío
		if (archivoODirectorio.delete()) {
			System.out.println("Borrado exitosamente: " + ruta);
		} else {
			System.out.println("Error: No se pudo borrar " + ruta);
		}
	}

	// Método para borrar el contenido de un directorio (recursivo)
	public static void borrarContenidoDirectorio(File directorio) {
		File[] archivos = directorio.listFiles();
		if (archivos != null) {
			for (File archivo : archivos) {
				if (archivo.isDirectory()) {
					borrarContenidoDirectorio(archivo); // Llamada recursiva para subdirectorios
				}
				if (!archivo.delete()) {
					System.out.println("Error: No se pudo borrar " + archivo.getAbsolutePath());
				}
			}
		}
	}

	public static void main(String[] args) {
		// Ejecutar el menú interactivo
		ejecutarMenu();
	}

}