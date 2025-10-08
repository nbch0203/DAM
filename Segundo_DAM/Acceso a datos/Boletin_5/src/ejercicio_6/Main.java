package ejercicio_6;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final String FICHERO_BINARIO = "numeros.dat";
    private static final String CADENA_FIN = "fin";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalCaracteresNoNumericos = 0;
        int totalNumerosGuardados = 0;

        // Crear el fichero binario (obligatorio según el enunciado)
        crearFicheroBinario();

        System.out.println("Introduce cadenas de texto (escribe '" + CADENA_FIN + "' para terminar):");

        while (true) {
            System.out.print("Cadena: ");
            String entrada = scanner.nextLine().trim();

            // Condición de salida
            if (entrada.equalsIgnoreCase(CADENA_FIN)) {
                break;
            }

            // Verificar si es convertible a entero
            if (esConvertibleAEntero(entrada)) {
                // Convertir y guardar en fichero binario
                int numero = Integer.parseInt(entrada);
                guardarNumeroEnFichero(numero);
                totalNumerosGuardados++;
                System.out.println("Número guardado: " + numero);
            } else {
                // Contabilizar caracteres no numéricos
				totalCaracteresNoNumericos += entrada.length();
                System.out.println("Cadena no numérica - Longitud: " + entrada.length() + " caracteres");
            }
        }

        scanner.close();

        // Mostrar resultados finales
        mostrarResumen(totalCaracteresNoNumericos, totalNumerosGuardados);
        mostrarNumerosSinRepetidos();
    }

    /**
     * Crea el fichero binario (requerimiento obligatorio)
     */
    private static void crearFicheroBinario() {
        try {
            File file = new File(FICHERO_BINARIO);
            if (file.createNewFile()) {
                System.out.println("Fichero creado: " + FICHERO_BINARIO);
            }
        } catch (IOException e) {
            System.err.println("Error creando el fichero: " + e.getMessage());
        }
    }

    /**
     * Verifica si una cadena es convertible a número entero
     */
    private static boolean esConvertibleAEntero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Guarda un número en el fichero binario con acceso aleatorio
     */
    private static void guardarNumeroEnFichero(int numero) {
        try (RandomAccessFile raf = new RandomAccessFile(FICHERO_BINARIO, "rw")) {
            // Posicionarse al final del fichero
            raf.seek(raf.length());
            // Escribir el número (4 bytes para int)
            raf.writeInt(numero);
        } catch (IOException e) {
            System.err.println("Error guardando número: " + e.getMessage());
        }
    }

    /**
     * Muestra el resumen de caracteres no numéricos
     */
    private static void mostrarResumen(int totalCaracteres, int totalNumeros) {
        System.out.println("\n=== RESUMEN FINAL ===");
        System.out.println("Total caracteres en cadenas no numéricas: " + totalCaracteres);
        System.out.println("Total números guardados en el fichero: " + totalNumeros);
    }

    /**
     * Muestra los números del fichero omitiendo repetidos
     */
    private static void mostrarNumerosSinRepetidos() {
        Set<Integer> numerosUnicos = new HashSet<>();

        try (RandomAccessFile raf = new RandomAccessFile(FICHERO_BINARIO, "r")) {
            // Calcular cuántos números hay en el fichero
            long fileSize = raf.length();
            int cantidadNumeros = (int) (fileSize / 4); // Cada int ocupa 4 bytes

            System.out.println("\n=== NÚMEROS EN EL FICHERO (SIN REPETIDOS) ===");

            if (cantidadNumeros == 0) {
                System.out.println("El fichero está vacío.");
                return;
            }

            // Leer todos los números y agregarlos al conjunto (elimina duplicados automáticamente)
            for (int i = 0; i < cantidadNumeros; i++) {
                int numero = raf.readInt();
                numerosUnicos.add(numero);
            }

            // Mostrar números únicos
            if (numerosUnicos.isEmpty()) {
                System.out.println("No hay números para mostrar.");
            } else {
                numerosUnicos.forEach(numero -> System.out.print(numero + " "));
                System.out.println(); // Salto de línea final
            }

        } catch (IOException e) {
            System.err.println("Error leyendo el fichero: " + e.getMessage());
        }
    }
}