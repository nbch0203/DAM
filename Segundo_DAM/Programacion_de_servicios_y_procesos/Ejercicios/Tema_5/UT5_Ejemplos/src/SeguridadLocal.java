
import java.security.*;
import javax.crypto.*;
import java.util.Scanner;

public class SeguridadLocal {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n--- LABORATORIO DE SEGURIDAD 2º DAM ---");
			System.out.println("1. Pruebas de Validación (Input seguro)");
			System.out.println("2. Hashing (Integridad)");
			System.out.println("3. Cifrado Simétrico (AES)");
			System.out.println("4. Cifrado Asimétrico (RSA)");
			System.out.println("5. Firma Digital");
			System.out.println("0. Salir");
			System.out.print("Elige una opción: ");

			// PRÁCTICA DE SEGURIDAD: Validación de entrada del menú
			if (scanner.hasNextInt()) {
				int opcion = scanner.nextInt();
				scanner.nextLine(); // Limpiar buffer

				try {
					switch (opcion) {
					case 1:
						pruebaValidacion(scanner);
						break;
					case 2:
						pruebaHash(scanner);
						break;
					case 3:
						pruebaSimetrico();
						break;
					case 4:
						pruebaAsimetrico();
						break;
					case 5:
						pruebaFirma();
						break;
					case 0:
						System.out.println("Saliendo...");
						return;
					default:
						System.out.println("Opción no válida.");
					}
				} catch (Exception e) {
					// PRÁCTICA DE SEGURIDAD: No mostrar e.printStackTrace() al usuario
					System.out.println("ERROR DE SEGURIDAD: " + e.getMessage());
				}
			} else {
				System.out.println("Error: Debes introducir un número.");
				scanner.next(); // Limpiar entrada inválida
			}
		}
	}

	// 1. VALIDACIÓN DE ENTRADAS
	private static void pruebaValidacion(Scanner sc) {
		System.out.print("Introduce una edad (0-120): ");
		if (sc.hasNextInt()) {
			int edad = sc.nextInt();
			if (edad >= 0 && edad <= 120) {
				System.out.println("Dato válido procesado: " + edad);
			} else {
				System.out.println("ALERTA: Intento de introducir edad fuera de rango.");
			}
		} else {
			System.out.println("ALERTA: El tipo de dato no es un entero.");
			sc.next();
		}
	}

	// 2. HASHING (MessageDigest)
	private static void pruebaHash(Scanner sc) throws Exception {
		System.out.print("Texto para hacer Hash: ");
		String texto = sc.nextLine();

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hash = md.digest(texto.getBytes());

		// Convertir bytes a Hexadecimal para verlo
		StringBuilder hexString = new StringBuilder();
		for (byte b : hash)
			/*El %02x sirve para obligar a que cada número se escriba siempre con dos cifras en hexadecimal. 
			 * Si el número es pequeño (como el 5), le pone un cero delante (05) 
			 * para que no quede feo ni rompa la cadena.*/
			hexString.append(String.format("%02x", b));

		System.out.println("Resumen SHA-256: " + hexString.toString());
	}

	// 3. CIFRADO SIMÉTRICO (AES)
	private static void pruebaSimetrico() throws Exception {
		String mensaje = "Secreto de Estado";

		// Generar clave
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);
		SecretKey clave = keyGen.generateKey();

		// Cifrar
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, clave);
		byte[] cifrado = cipher.doFinal(mensaje.getBytes());
		System.out.println("Texto cifrado (bytes): " + cifrado);

		// Descifrar
		cipher.init(Cipher.DECRYPT_MODE, clave);
		byte[] original = cipher.doFinal(cifrado);
		System.out.println("Texto descifrado: " + new String(original));
	}

	// 4. CIFRADO ASIMÉTRICO (RSA)
	private static void pruebaAsimetrico() throws Exception {
		System.out.println("Generando claves RSA (puede tardar un poco)...");
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(2048);
		KeyPair par = keyGen.generateKeyPair();

		String mensaje = "Datos Confidenciales";

		// Cifrar con PÚBLICA
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, par.getPublic());
		byte[] cifrado = cipher.doFinal(mensaje.getBytes());

		// Descifrar con PRIVADA
		cipher.init(Cipher.DECRYPT_MODE, par.getPrivate());
		byte[] descifrado = cipher.doFinal(cifrado);

		System.out.println("Descifrado con clave privada éxito: " + new String(descifrado));
	}

	// 5. FIRMA DIGITAL
	private static void pruebaFirma() throws Exception {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(2048);
		KeyPair par = keyGen.generateKeyPair();

		String documento = "Contrato Firmado";

		// FIRMAR (Con Privada)
		Signature firma = Signature.getInstance("SHA256withRSA");
		firma.initSign(par.getPrivate());
		firma.update(documento.getBytes());
		byte[] firmaBytes = firma.sign();

		System.out.println("Documento firmado digitalmente.");

		// VERIFICAR (Con Pública)
		Signature verifica = Signature.getInstance("SHA256withRSA");
		verifica.initVerify(par.getPublic());
		verifica.update(documento.getBytes());
		boolean esValido = verifica.verify(firmaBytes);

		System.out.println("¿Es la firma auténtica?: " + esValido);
	}
}