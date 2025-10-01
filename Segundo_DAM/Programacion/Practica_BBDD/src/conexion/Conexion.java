//package conexion;
//
//import java.sql.Connection;
//
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//
//import modelo.Alumno;
//
//public class Conexion {
//
//	// Variables con los datos de la base de datos
//
//	private static String bd = "xe";
//	private static String login = "C##COLEGIO";
//	private static String password = "password";
//
//	// Ruta del servidor, nosotros localhost
//
//	private static String url = "jdbc:oracle:thin:@localhost:1521:" + bd;
//	static Connection connection = null;
//	private static Statement st;
//	private static ResultSet rs;
//
//	// Metodo para conectar con la base de datos con la que se
//	// realizara todas la operaciones
//
//	public boolean conectar() {
//		boolean conectado = false;
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			connection = DriverManager.getConnection(url, login, password);
//			if (connection != null) {
//				System.out.println("Conexion realizada con exito\n");
//				return conectado = true;
//			} else {
//				System.out.println("Conexion fallida\n");
//			}
//		} catch (ClassNotFoundException e) {
//
//			System.out.println("No se ha podido encontrar la base de datos \n");
//		} catch (SQLException e) {
//
//			System.out.println("Los datos de la base de datos estan mal introducidos \n");
//		}
//		return conectado;
//	}
//
//	// Metodo para cerrar la conexion con la base de datos
//
//	public void cerrar() {
//		if (rs != null) {
//			try {
//				rs.close();
//				if (st != null) {
//					st.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//				System.out.println("Error en el cierre de la conexion \n");
//			}
//
//		}
//		System.out.println("Conexion cerrada \n");
//	}
//
//	// Metodo para mostrar todos los alumnos existentes en la BBDD
//
//	public void ejecutarConsulta() {
//
//		// Variables con los datos a introducir
//		int id_alumno, edad_alumno;
//		String nombre, query = ("select ID,nombre,EDAD,NOTA from alumnos ORDER BY id desc");
//		Double nota_alumno;
//
//		// seteamos la conexion para poder crear la query
//
//		try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(query);) {
//
//			// Imprimir línea superior
//			System.out.println("+------------+---------------+---------------+---------------+");
//
//			// Imprimir encabezados
//			System.out.printf("| %-10s | %-13s | %-13s | %-13s |\n", "Id", "Nombre", "Edad", "Nota");
//
//			// Bucle para recorrer la query
//
//			while (rs.next()) {
//
//				// Valores a introducir con el bucle por medio
//				// de la informacion de la query
//				id_alumno = rs.getInt("ID");
//				nombre = rs.getString("nombre");
//				edad_alumno = rs.getInt("EDAD");
//				nota_alumno = rs.getDouble("NOTA");
//
//				// Línea de separación
//				System.out.println("+------------+---------------+---------------+---------------+");
//
//				// Imprimir valores
//				System.out.printf("| %-10d | %-13s | %-13d | %-13.2f |\n", id_alumno, nombre, edad_alumno, nota_alumno);
//
//				// Línea inferior
//				System.out.println("+------------+---------------+---------------+---------------+");
//			}
//			System.out.println("\n");
//
//		} catch (SQLException e) {
//			System.out.println("Error de sql al intentar ejecutar la consulta \n");
//		}
//
//	}
//
//	// Metodo para insertar el alumno que se pasara por medio del escaner
//
//	public void insertarAlumno(Alumno alumno) {
//
//		// Query para insertar los datos
//		String query = "INSERT INTO alumnos (nombre,edad,nota) VALUES (?,?,?)";
//
//		try (Connection connection = DriverManager.getConnection(url, login, password);
//				PreparedStatement pstm = connection.prepareStatement(query)) {
//
//			// Datos a introducir con sus respectivas columnas
//
//			pstm.setString(1, alumno.getNombre());
//			pstm.setInt(2, alumno.getEdad());
//			pstm.setDouble(3, alumno.getNota());
//			pstm.executeUpdate();
//
//			// Imprimir línea superior
//			System.out.println("+------------+---------------+---------------+---------------+");
//
//			// Imprimir encabezados
//			System.out.printf("| %-10s | %-13s | %-13s | %-13s |\n", "Id", "Nombre", "Edad", "Nota");
//
//			// Línea de separación
//			System.out.println("+------------+---------------+---------------+---------------+");
//
//			// Imprimir valores
//			System.out.printf("| %-10d | %-13s | %-13d | %-13.2f |\n", alumno.getId(), alumno.getNombre(),
//					alumno.getEdad(), alumno.getNota());
//
//			// Línea inferior
//			System.out.println("+------------+---------------+---------------+---------------+");
//			System.out.println("\n");
//
//		} catch (SQLException e) {
//			System.out.println("Error de sql al intentar insertar un nuevo alumno \n");
//		}
//	}
//
//	// Metodo para actualizar la informacion de un alumno
//
//	public void actualizarAlumno(int id, String nombre, int edad, double nota) {
//
//		// Query para actualizar la informacion de un alumno
//
//		String query = "UPDATE alumnos SET nombre=?, edad=?, nota=? WHERE id=?";
//
//		try (Connection cn = DriverManager.getConnection(url, login, password);
//				PreparedStatement pstm = cn.prepareStatement(query)) {
//
//			pstm.setString(1, nombre);
//			pstm.setInt(2, edad);
//			pstm.setDouble(3, nota);
//			pstm.setInt(4, id);
//			pstm.executeUpdate();
//			// Imprimir línea superior
//			System.out.println("+------------+---------------+---------------+---------------+");
//
//			// Imprimir encabezados
//			System.out.printf("| %-10s | %-13s | %-13s | %-13s |\n", "Id", "Nombre", "Edad", "Nota");
//
//			// Línea de separación
//			System.out.println("+------------+---------------+---------------+---------------+");
//
//			// Imprimir valores
//			System.out.printf("| %-10d | %-13s | %-13d | %-13.2f |\n", id, nombre, edad, nota);
//
//			// Línea inferior
//			System.out.println("+------------+---------------+---------------+---------------+");
//			System.out.println("\n");
//
//		} catch (SQLException e) {
//			System.out.println("Error de sql en la actualizacion del alumno \n");
//		}
//	}
//
//	// Metodo para eliminar un alumno de la base de datos
//
//	public void borrarAlumno(int id) {
//
//		// Query de eliminacion del alumno por id
//
//		String query = "DELETE FROM alumnos WHERE id= ?";
//
//		try (Connection connection = DriverManager.getConnection(url, login, password);
//				PreparedStatement pstmt = connection.prepareStatement(query)) {
//
//			// Eliminamos al alumno por medio del id y actualizamos para
//			// salvar los cambios
//
//			pstmt.setInt(1, id);
//			pstmt.executeUpdate();
//
//			System.out.println("Alumno borrado con exito \n");
//
//		} catch (SQLException e) {
//
//			System.out.println("Error de sql al intentar borrar al alumno \n");
//		}
//	}
//
//	// Metodo para listar a los alumnos con una nota superior al pasado por escaner
//
//	public void listarAlumnosSuperior(Double n_superior) {
//		int id, edad_alumno;
//		String nombre, query = ("select id,nombre,EDAD,NOTA from alumnos");
//		Double nota_alumno;
//
//		ArrayList<Alumno> lista = new ArrayList<Alumno>();
//
//		try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(query)) {
//
//			// Bucle para recorrer la query
//
//			while (rs.next()) {
//				id = rs.getInt("id");
//				nombre = rs.getString("nombre");
//				edad_alumno = rs.getInt("EDAD");
//				nota_alumno = rs.getDouble("NOTA");
//				Alumno alumno = new Alumno(id, nombre, edad_alumno, nota_alumno);
//				lista.add(alumno);
//			}
//
//			// Imprimir línea superior
//			System.out.println("+------------+---------------+---------------+---------------+");
//
//			// Imprimir encabezados
//			System.out.printf("| %-10s | %-13s | %-13s | %-13s |\n", "Id", "Nombre", "Edad", "Nota");
//
//			System.out.println("+------------+---------------+---------------+---------------+");
//
//			for (Alumno alumno : lista) {
//				if (alumno.getNota() >= n_superior) {
//
//					// Línea de separación
//
//					// Imprimir valores
//					System.out.printf("| %-10d | %-13s | %-13d | %-13.2f |\n", alumno.getId(), alumno.getNombre(),
//							alumno.getEdad(), alumno.getNota());
//
//					// Línea inferior
//					System.out.println("+------------+---------------+---------------+---------------+");
//
//				}
//			}
//
//			System.out.println("\n");
//
//		} catch (SQLException e) {
//			System.out.println("Error de sql al listar a los alumnos \n");
//		}
//
//	}
//
//	// Metodo para listar a los alumnos por la edad
//	// pasada por escaner
//
//	public void listarAlumnosEdad(int edad) {
//
//		// Query para mostrar los datos de los alumnos que cumplan la condicion
//
//		String query = ("Select id,nombre,edad,nota from alumnos where edad=?");
//
//		try (Connection cn = DriverManager.getConnection(url, login, password);
//				PreparedStatement pstm = cn.prepareStatement(query)) {
//
//			pstm.setInt(1, edad);
//			rs = pstm.executeQuery();
//
//			// Encabezado
//			System.out.println("+------------+---------------+---------------+---------------+");
//			System.out.printf("| %-10s | %-13s | %-13s | %-13s |\n", "Id", "Nombre", "Edad", "Nota");
//			System.out.println("+------------+---------------+---------------+---------------+");
//
//			// Recorrer resultados de la query
//			while (rs.next()) {
//				int id = rs.getInt("id");
//				String nombre = rs.getString("nombre");
//				int edadAlumno = rs.getInt("edad");
//				double nota = rs.getDouble("nota");
//
//				System.out.println("+------------+---------------+---------------+---------------+");
//
//				// Datos del alumno
//				System.out.printf("| %-10d | %-13s | %-13d | %-13.2f |\n", id, nombre, edadAlumno, nota);
//			}
//
//			System.out.println("+------------+---------------+---------------+---------------+");
//			System.out.println("\n");
//
//		} catch (SQLException e) {
//			System.out.println("Error de sql al realizar el listado de alumnos \n");
//		}
//
//	}
//
//	// Metod para mostrar a los 2 alumnos con mejor nota ordenados de mayora menor
//
//	public void mostrarAlumnosMejorNota() {
//		int edad_alumno;
//		String nombre;
//		double nota_alumno;
//
//		// Query que nos dara los 3 primeros alumnos con mejor nota
//		String query = ("SELECT Nombre, Edad, Nota FROM alumnos ORDER BY nota DESC FETCH FIRST 3 ROWS ONLY");
//
//		try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(query)) {
//
//			// Encabezado
//			System.out.println("+---------------+---------------+---------------+");
//
//			System.out.printf("| %-13s | %-13s | %-13s |\n", "Nombre", "Edad", "Nota");
//
//			System.out.println("+---------------+---------------+---------------+");
//
//			// Bucle para recorrer la query
//			while (rs.next()) {
//				nombre = rs.getString("Nombre");
//				edad_alumno = rs.getInt("Edad");
//				nota_alumno = rs.getDouble("Nota");
//
//				// Datos del mejor alumno
//				System.out.printf("| %-13s | %-13d | %-13.2f |\n", nombre, edad_alumno, nota_alumno);
//				System.out.println("+---------------+---------------+---------------+");
//			}
//			System.out.println("\n");
//		} catch (SQLException e) {
//			System.out.println("Error de SQL al intentar mostrar al alumno con mejor nota");
//		}
//	}
//
//	// Metodo para mostrar todas la estadisticas generales de los alumnos
//
//	public void mostrarEstadisticasgenerales() {
//
//		// Variables para almacenar toda la informacion que nos interesa
//
//		int edad_alumno, numero_aprobados = 0, numero_suspensos = 0;
//		String nombre, query = ("select nombre,EDAD,NOTA from alumnos");
//		Double nota_alumno, nota_media = 0.0;
//
//		ArrayList<Alumno> lista = new ArrayList<Alumno>();
//
//		try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(query);) {
//
//			// Bucle para recorrer la query
//
//			while (rs.next()) {
//				nombre = rs.getString("nombre");
//				edad_alumno = rs.getInt("EDAD");
//				nota_alumno = rs.getDouble("NOTA");
//				Alumno alumno = new Alumno(nombre, edad_alumno, nota_alumno);
//				lista.add(alumno);
//			}
//			// Bucle para recorrer la lista y
//			// saber los numeros de aprobados y suspensos
//
//			for (int i = 0; i < lista.size(); i++) {
//				nota_media = lista.get(i).getNota() + nota_media;
//				if (lista.get(i).getNota() > 5) {
//					numero_aprobados++;
//				} else {
//					numero_suspensos++;
//				}
//			}
//			nota_media = nota_media / lista.size();
////
////			System.out.printf("\n La nota media general es : %.1f", nota_media);
////			System.out.println("\n El numero de aprobados es de : " + numero_aprobados
////					+ "\n El numero de suspensos es de : " + numero_suspensos + "\n");
//			// Imprimir línea superior
//			System.out.println("+------------+---------------------+---------------------+");
//
//			// Imprimir encabezados
//			System.out.printf("| %-10s | %-13s | %-13s |\n", "Nota media", "Numero de aprobados",
//					"Numero de suspensos");
//
//			// Línea de separación
//			System.out.println("+------------+---------------------+---------------------+");
//
//			// Imprimir valores
//			System.out.printf("| %-10.2f | %-19d | %-19d | \n", nota_media, numero_aprobados, numero_suspensos);
//
//			// Línea inferior
//			System.out.println("+------------+---------------------+---------------------+");
//			System.out.println("\n");
//
//		} catch (SQLException e) {
//			System.out.println("Error de sql al mostrar la estadisticas generales \n");
//		}
//
//	}
//
//}
