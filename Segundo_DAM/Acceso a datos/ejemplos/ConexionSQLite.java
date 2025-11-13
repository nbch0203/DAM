package ejemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionSQLite {
	private static Connection conn = null;

	public static Connection conectar() {
		try {
			// Ruta de la BD (si no existe, se crea automáticamente)
			String url = "jdbc:sqlite:miBD.db";
			conn = DriverManager.getConnection(url);
			System.out.println("Conexión establecida con SQLite.");
		} catch (SQLException e) {
			System.out.println("Error en la conexión: " + e.getMessage());
		}
		return conn;
	}

	public static void desconectar() {
		try {
			if (conn != null) {
				conn.close();
				System.out.println("Conexión cerrada.");
			}
		} catch (SQLException e) {
			System.out.println("Error al cerrar conexión: " + e.getMessage());
		}
	}

	public static void crearTablas() {
		String sql = "CREATE TABLE IF NOT EXISTS alumnos (" + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "nombre TEXT NOT NULL," + "edad INTEGER)";
		try (Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			System.out.println("Tabla 'alumnos' creada correctamente.");
		} catch (SQLException e) {
			System.out.println("Error al crear tabla: " + e.getMessage());
		}

		sql = "CREATE TABLE IF NOT EXISTS cursos (\r\n" + "    id INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "    nombre TEXT NOT NULL,\r\n" + "    horas INTEGER\r\n" + ")";
		try (Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			System.out.println("Tabla 'cursos' creada correctamente.");
		} catch (SQLException e) {
			System.out.println("Error al crear tabla: " + e.getMessage());
		}

		sql = "CREATE TABLE IF NOT EXISTS matriculas (\r\n"
				+ "    id INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "    alumno_id INTEGER,\r\n"
				+ "    curso_id INTEGER,\r\n"
				+ "    FOREIGN KEY(alumno_id) REFERENCES alumnos(id),\r\n"
				+ "    FOREIGN KEY(curso_id) REFERENCES cursos(id)\r\n"
				+ ")";
		try (Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			System.out.println("Tabla 'matriculas' creada correctamente.");
		} catch (SQLException e) {
			System.out.println("Error al crear tabla: " + e.getMessage());
		}
	}
	
	// INSERT en alumnos
    public static void insertarAlumnos() {
        String sql = "INSERT INTO alumnos(nombre, edad) VALUES(?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "Lucía");
            ps.setInt(2, 20);
            ps.executeUpdate();

            ps.setString(1, "Carlos");
            ps.setInt(2, 22);
            ps.executeUpdate();

            ps.setString(1, "Isabel");
            ps.setInt(2, 19);
            ps.executeUpdate();

            System.out.println("Alumnos insertados correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar alumnos: " + e.getMessage());
        }
    }

    // INSERT en cursos
    public static void insertarCursos() {
        String sql = "INSERT INTO cursos(nombre, horas) VALUES(?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "Programación en Java");
            ps.setInt(2, 120);
            ps.executeUpdate();

            ps.setString(1, "Bases de Datos");
            ps.setInt(2, 100);
            ps.executeUpdate();

            ps.setString(1, "Entornos de Desarrollo");
            ps.setInt(2, 80);
            ps.executeUpdate();

            System.out.println("Cursos insertados correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar cursos: " + e.getMessage());
        }
    }

    // INSERT en matriculas (relación alumno-curso)
    public static void insertarMatriculas() {
        String sql = "INSERT INTO matriculas(alumno_id, curso_id) VALUES(?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // Ejemplo: asignamos alumnos a cursos
            ps.setInt(1, 1); // Lucía
            ps.setInt(2, 1); // Programación en Java
            ps.executeUpdate();

            ps.setInt(1, 2); // Carlos
            ps.setInt(2, 2); // Bases de Datos
            ps.executeUpdate();

            ps.setInt(1, 3); // Isabel
            ps.setInt(2, 1); // Programación en Java
            ps.executeUpdate();

            System.out.println("Matrículas insertadas correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar matrículas: " + e.getMessage());
        }
    }

	
	public static void borrarAlumno(int id) {
	    String sql = "DELETE FROM alumnos WHERE id = ?";
	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, id);
	        ps.executeUpdate();
	        System.out.println("Alumno eliminado.");
	    } catch (SQLException e) {
	        System.out.println("Error al eliminar: " + e.getMessage());
	    }
	}

	public static void actualizarAlumno(int id, String nuevoNombre) {
	    String sql = "UPDATE alumnos SET nombre = ? WHERE id = ?";
	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, nuevoNombre);
	        ps.setInt(2, id);
	        ps.executeUpdate();
	        System.out.println("Alumno actualizado.");
	    } catch (SQLException e) {
	        System.out.println("Error en actualización: " + e.getMessage());
	    }
	}


	public static void mostrarAlumnos() {
	    String sql = "SELECT id, nombre, edad FROM alumnos";
	    try (Statement st = conn.createStatement();
	         ResultSet rs = st.executeQuery(sql)) {
	        while (rs.next()) {
	            System.out.println(rs.getInt("id") + " - " +
	                               rs.getString("nombre") + " - " +
	                               rs.getInt(3)); //también puedo pasar por parámetro el número de la columna resultado que quiero obtener
	        }
	    } catch (SQLException e) {
	        System.out.println("Error en consulta: " + e.getMessage());
	    }
	}



	// Consulta con JOIN: alumnos y cursos
	public static void consultaJoin() {
		String sql = "SELECT a.nombre AS Alumno, c.nombre AS Curso " + "FROM alumnos a "
				+ "INNER JOIN matriculas m ON a.id = m.alumno_id " + "INNER JOIN cursos c ON c.id = m.curso_id";
		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			System.out.println("Listado de alumnos y sus cursos:");
			while (rs.next()) {
				System.out.println(rs.getString("Alumno") + " - " + rs.getString("Curso"));
			}
		} catch (SQLException e) {
			System.out.println("Error en consulta JOIN: " + e.getMessage());
		}
	}

	// Consulta con HAVING: cursos con más de un alumno
	public static void consultaHaving() {
		String sql = "SELECT c.nombre AS Curso, COUNT(m.alumno_id) AS NumAlumnos " + "FROM cursos c "
				+ "INNER JOIN matriculas m ON c.id = m.curso_id " + "GROUP BY c.nombre "
				+ "HAVING COUNT(m.alumno_id) > 1";
		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			System.out.println("Cursos con más de un alumno matriculado:");
			while (rs.next()) {
				System.out.println(rs.getString("Curso") + " - " + rs.getInt("NumAlumnos") + " alumnos");
			}
		} catch (SQLException e) {
			System.out.println("Error en consulta HAVING: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		ConexionSQLite.conectar();
		crearTablas();
		insertarAlumnos();
		insertarCursos();
		insertarMatriculas();
		mostrarAlumnos();
		System.out.println("*****************************************");
		actualizarAlumno(1, "Lucía Hernández");
		consultaJoin();
		System.out.println("*****************************************");
		consultaHaving();
		System.out.println("*****************************************");
		borrarAlumno(1);
		mostrarAlumnos();
		ConexionSQLite.desconectar();
	}

}
