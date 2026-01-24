package ejercicio_4_plus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        File file = new File("alumnos.dat");
        Metodos metodos = new Metodos();

        try {
            // 1 y 2 - Crear alumnos y guardarlos en el fichero
            Alumno a1 = new Alumno(7, "Carlos");
            Alumno a2 = new Alumno(9, "Lucia");
            Alumno a3 = new Alumno(5, "Pedro");

            metodos.escribir(file, a1);
            metodos.escribir(file, a2);
            metodos.escribir(file, a3);

            System.out.println("Alumnos guardados en el fichero.\n");

            // 3 - Mostrar contenido del fichero
            System.out.println("Listado de alumnos en alumnos.dat:");
            imprimirAlumnos(metodos.leerTodos(file));

            // 4 - Modificar nota de un alumno por nombre
            if (metodos.modificarNota(file, "Lucia", 10)) {
                System.out.println("\nNota de Lucia modificada a 10.");
            }

            // 5 - Modificar nota del último alumno
            if (metodos.modificarUltimo(file, 8)) {
                System.out.println("Nota del último alumno modificada a 8.");
            }

            // 6 - Mostrar contenido actualizado del fichero
            System.out.println("\nListado actualizado de alumnos en alumnos.dat:");
            imprimirAlumnos(metodos.leerTodos(file));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método auxiliar para imprimir alumnos
    private static void imprimirAlumnos(ArrayList<Alumno> lista) {
        for (Alumno a : lista) {
            System.out.println("Nombre: " + a.getNombre() + ", Nota: " + a.getNota());
        }
    }
}
