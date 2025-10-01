package boletin4_Ficheros_Ejercicio6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
	
	
	// Tengo que dividir cada operacion en metodos

	public static void app() throws FileNotFoundException, IOException, ClassNotFoundException {
		File archivo = new File("log.txt");

		FileInputStream leer = new FileInputStream(archivo);
		ObjectInputStream leer_obje = new ObjectInputStream(leer);
		Scanner sc = new Scanner(System.in);

		CuentaBancaria cuenta = new CuentaBancaria("Nixon", 0.00);

		while (true) {

			System.out.println(
					"Que operacion quiere realizar: \n" + "1- Depositar \n" + "2- Retirar \n" + "3- Consultar saldo");
			int opcion = sc.nextInt();
			switch (opcion) {
			case 1:

				System.out.println("Introduzca la cantidad que quiere ingresar: ");
				double ingreso = sc.nextDouble();

				cuenta.depositar(ingreso);

				if (archivo.exists()) {
					FileOutputStream escribir = new FileOutputStream(archivo);
					ObjectOutputStream esc_obje = new ObjectOutputStream(escribir);
					esc_obje.writeObject(LocalDateTime.now() + " - " + cuenta.getTitular() + " - Ingreso - " + ingreso);

				} else {
					FileOutputStream escribir = new FileOutputStream(archivo, true);
					MiobjectOutputStream esc_obje = new MiobjectOutputStream(escribir);
					esc_obje.writeObject(LocalDateTime.now() + " - " + cuenta.getTitular() + " - Ingreso - " + ingreso);

				}

				break;

			case 2:
				System.out.println("Introduzca la cantidad a retirar: ");
				double retiro = sc.nextDouble();
				cuenta.retirar(retiro);
				if (archivo.exists()) {
					FileOutputStream escribir = new FileOutputStream(archivo);
					ObjectOutputStream esc_obje = new ObjectOutputStream(escribir);
					esc_obje.writeObject(LocalDateTime.now() + " - " + cuenta.getTitular() + " - Retiro - " + retiro);

				} else {
					FileOutputStream escribir = new FileOutputStream(archivo, true);
					MiobjectOutputStream esc_obje = new MiobjectOutputStream(escribir);
					esc_obje.writeObject(LocalDateTime.now() + " - " + cuenta.getTitular() + " - Retiro - " + retiro);

				}
				break;

			case 3:
				try {
					System.out
							.println(leer_obje.readObject() + "\n" + "----------------------------------------------");

				} catch (Exception e) {
					System.out.println("Error exception");
				}
				break;
			}
		}

	}

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		app();
	}

}
