package ejercicio_4;

import java.io.*;
import java.util.Scanner;

public class Main {

	private static final String FICHERO = "vehiculos.dat";

	public static void main(String[] args) {
		try {
			introduceDatos();
			muestraDatos();
		} catch (IOException e) {
			System.err.println("Error de E/S: " + e.getMessage());
		}
	}

	// Método para introducir datos en el fichero
	public static void introduceDatos() throws IOException {
		Scanner sc = new Scanner(System.in);

		System.out.print("Introduce matrícula: ");
		String matricula = sc.nextLine();

		System.out.print("Introduce marca: ");
		String marca = sc.nextLine();

		System.out.print("Introduce tamaño del depósito (double): ");
		double deposito = sc.nextDouble();
		sc.nextLine(); // limpiar salto de línea

		System.out.print("Introduce modelo: ");
		String modelo = sc.nextLine();

		// Usamos FileOutputStream con true para no sobrescribir
		try (DataOutputStream dos = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(FICHERO, true)))) {
			dos.writeUTF(matricula);
			dos.writeUTF(marca);
			dos.writeDouble(deposito);
			dos.writeUTF(modelo);
		}

		System.out.println("Datos guardados correctamente.\n");
	}

	// Método para leer y mostrar los datos del fichero
	public static void muestraDatos() throws IOException {
		System.out.println("=== Lista de Vehículos Guardados ===");

		try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(FICHERO)))) {

			while (true) {
				String matricula = dis.readUTF();
				String marca = dis.readUTF();
				double deposito = dis.readDouble();
				String modelo = dis.readUTF();

				System.out.println("Matrícula: " + matricula + " | Marca: " + marca + " | Depósito: " + deposito
						+ " | Modelo: " + modelo);
			}

		} catch (EOFException e) {
			// fin del fichero, terminamos lectura
			System.out.println("=== Fin de la lista ===");
		}
	}
}
