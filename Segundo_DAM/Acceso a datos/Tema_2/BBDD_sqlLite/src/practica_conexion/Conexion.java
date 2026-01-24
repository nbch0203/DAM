package practica_conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	private static Connection conexion = null;

	public static Connection conectar() {
		try {
			String url = "jdbc:sqlite:musica.db";
			conexion = DriverManager.getConnection(url);
			System.out.println("La conexion ha sido establecidad");

		} catch (SQLException e) {
			System.out.println("No se ha podido conectar : " + e.getMessage());
		}
		return conexion;

	}

	public static void desconectar() {
		try {
			if (conexion != null) {
				conexion.close();
				System.out.println("Conexion cerrada con exito");
			}

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al cerrar la conexion: " + e.getMessage());
		}
	}

	public static void creartablas() {
		String sql = "CREATE TABLE IF NOT EXISTS canciones "
				+ "(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT NOT NULL,"
				+ "artista TEXT, genero TEXT, duracion_segundos INT,año_lanzamiento INT)";

		try (Statement stmt = conexion.createStatement()) {
			stmt.execute(sql);
			System.out.println("Tabla creada correctamente");

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("No se ha podido crear la tabla: " + e.getMessage());
		}
	}

	public static void añadirCancion() {
		String sql = "INSERT INTO canciones (titulo,artista,genero,duracion_segundos,año_lanzamiento) values (?,?,?,?,?)";

		try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
			pstmt.setString(1, "Call me maybe");
			pstmt.setString(2, "Nixon");
			pstmt.setString(3, "musica");
			pstmt.setInt(4, 2);
			pstmt.setInt(5, 2003);

			pstmt.executeUpdate();
			System.out.println("Se han insertado los registros en las tablas");

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("NO se ha podido insertar los registros: " + e.getMessage());
		}
	}

	public static void consultarCancionesPorGenero(String genero) {
		String sql = "Select titulo,artista,duracion_segundos from canciones where genero=?";

		try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
			pstmt.setString(1, genero);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String titulo = rs.getString(1);
				String artista = rs.getString(2);
				int duracion = rs.getInt(3);
				System.out.println("Titulo: " + titulo + " artista: " + artista + " duracion: " + duracion);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("NO se ha podido hacer la consulta: " + e.getMessage());
		}
	}

	public static void actualizarDuracionCancion(int idCancion, int nuevaDuracion) {
		String sql = "Update canciones set duracion_segundos=? where id=?";

		try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
			pstmt.setInt(2, idCancion);
			pstmt.setInt(1, nuevaDuracion);

			boolean veri = pstmt.execute();
			if (veri) {
				System.out.println("SE ha podido actualizar la duracion de la cancion con el id: " + idCancion);

			} else
				throw new SQLException();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al actualizar el registro : " + e.getMessage());
		}
	}

}
