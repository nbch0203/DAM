//package app;
//
//import java.util.Scanner;
//
//import conexion.Conexion;
//import modelo.Alumno;
//
//public class App {
//
//	public static void main(String[] args) {
//		Conexion cnn = new Conexion();
//
//		try (Scanner sc = new Scanner(System.in)) {
//			while (cnn.conectar()) {
//
//				System.out.println("╔═════════════════════════════════════════════════════════════════╗");
//				System.out.println("║                           MENÚ DE ALUMNOS                       ║");
//				System.out.println("╠═════════════════════════════════════════════════════════════════╣");
//				System.out.println("║  1. Listar todos los alumnos ordenados por ID                   ║");
//				System.out.println("║  2. Insertar un nuevo alumno (nombre, edad, nota)               ║");
//				System.out.println("║  3. Actualizar un alumno existente por ID                       ║");
//				System.out.println("║  4. Eliminar un alumno por ID                                   ║");
//				System.out.println("║  5. Listar alumnos con nota superior o igual a un valor dado    ║");
//				System.out.println("║  6. Listar alumnos de una edad concreta                         ║");
//				System.out.println("║  7. Mostrar los 3 alumnos con mejor nota                        ║");
//				System.out.println("║  8. Mostrar estadísticas generales                              ║");
//				System.out.println("╚═════════════════════════════════════════════════════════════════╝");
//				System.out.print("► Selecciona una opción: ");
//				int opcion = sc.nextInt();
//				switch (opcion) {
//				case 1:
//
//					cnn.ejecutarConsulta();
//
//					break;
//				case 2:
//					System.out.println("Dime el nombre del nuevo alumno: ");
//					String nombre = sc.next();
//
//					System.out.println("Dime la edad del nuevo alumno: ");
//					int edad = sc.nextInt();
//
//					while (edad < 0) {
//						System.out.println("Introduzca una edad valida: ");
//						edad = sc.nextInt();
//					}
//
//					System.out.println("Dime la nota del nuevo alumno: ");
//					Double nota = sc.nextDouble();
//					while (nota < 0) {
//						System.out.println("Introduzca una nota valida: ");
//						nota = sc.nextDouble();
//					}
//
//					cnn.insertarAlumno(new Alumno(nombre, edad, nota));
//					break;
//				case 3:
//					System.out.println("Dime el id del alumno a actualizar: ");
//					int id_alumno = sc.nextInt();
//
//					while (id_alumno < 0) {
//						System.out.println("Introduzca un id valido: ");
//						id_alumno = sc.nextInt();
//					}
//
//					System.out.println("Dime el nuevo nombre del alumno: ");
//					String n_nombre = sc.next();
//					System.out.println("Dime la nueva edad del alumno: ");
//					int n_edad = sc.nextInt();
//
//					while (n_edad < 0) {
//						System.out.println("Introduzca una edad valida: ");
//						n_edad = sc.nextInt();
//					}
//					System.out.println("Dime la nueva nota del alumno");
//					Double n_nota = sc.nextDouble();
//
//					while (n_nota < 0) {
//						System.out.println("Introduzca una nota valida: ");
//						n_nota = sc.nextDouble();
//					}
//
//					cnn.actualizarAlumno(id_alumno, n_nombre, n_edad, n_nota);
//					break;
//
//				case 4:
//					System.out.println("Introduce el id del alumno a borrar: ");
//					int id_br = sc.nextInt();
//
//					while (id_br < 0) {
//						System.out.println("Introduzca un id valido: ");
//						id_br = sc.nextInt();
//					}
//
//					cnn.borrarAlumno(id_br);
//					break;
//				case 5:
//					System.out.println("Dime la nota a filtrar: ");
//					Double nota_filt = sc.nextDouble();
//
//					while (nota_filt < 0) {
//						System.out.println("Introduzca una nota valida: ");
//						nota_filt = sc.nextDouble();
//					}
//					cnn.listarAlumnosSuperior(nota_filt);
//
//					break;
//
//				case 6:
//
//					System.out.println("Introduce la edad por la que listar: ");
//					int edad_listar = sc.nextInt();
//
//					while (edad_listar < 0) {
//						System.out.println("Introduzca una edad valida: ");
//						edad_listar = sc.nextInt();
//					}
//
//					cnn.listarAlumnosEdad(edad_listar);
//					break;
//				case 7:
//
//					cnn.mostrarAlumnosMejorNota();
//
//					break;
//				case 8:
//
//					cnn.mostrarEstadisticasgenerales();
//
//					break;
//				default:
//					System.out.println("\nCerrando el programa \n");
//					cnn.cerrar();
//					sc.close();
//					return;
//
//				}
//			}
//		}
//	}
//
//}
