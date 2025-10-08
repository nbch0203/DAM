package ejercicio_4;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {

    static File file = new File("enteros.dat");

    public static void escribir() throws IOException {
        try (RandomAccessFile fr = new RandomAccessFile(file, "rw");
             Scanner sc = new Scanner(System.in)) {

            int totalEnteros = (int) (fr.length() / 4);

            if (totalEnteros == 0) {
                System.out.println("El fichero está vacío, no hay nada que modificar.");
                return;
            }

            int posicion;
            do {
                System.out.print("Dime la posición del entero a cambiar (1-" + totalEnteros + "): ");
                posicion = sc.nextInt();
            } while (posicion < 1 || posicion > totalEnteros);

            // Calcular la posición en bytes
            long offset = (posicion - 1) * 4;

            // Leer valor actual
            fr.seek(offset);
            int valorActual = fr.readInt();
            System.out.println("Valor actual en la posición " + posicion + ": " + valorActual);

            // Pedir nuevo valor
            System.out.print("Introduce el nuevo valor: ");
            int nuevo_valor = sc.nextInt();

            // Volver a la misma posición y sobrescribir
            fr.seek(offset);
            fr.writeInt(nuevo_valor);

            System.out.println("Valor modificado correctamente.");
        }
    }

    public static void leer() throws IOException {
        try (RandomAccessFile fr = new RandomAccessFile(file, "r")) {
            System.out.println("=== Lista de números enteros Guardados ===");

            int index = 1;
            while (fr.getFilePointer() < fr.length()) {
                int num = fr.readInt();
                System.out.println("Posición " + index + ": " + num);
                index++;
            }
        }
    }

    public static void main(String[] args) {

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            leer();
            escribir();
            leer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
