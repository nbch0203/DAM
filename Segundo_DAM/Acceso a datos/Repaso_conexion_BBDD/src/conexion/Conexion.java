package conexion;

import java.sql.Connection;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

	private static String bd = "xe";
	private static String login = "C##BIBLIOTECA";
	private static String password = "password";

	// Ruta del servidor, nosotros localhost
	private static String url = "jdbc:oracle:thin:@localhost:1521:" + bd;
	static Connection connection = null;
	private static Statement st;
	private static ResultSet rs;

	public void conectar() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, login, password);
			if (connection != null) {
				System.out.println("Conexion realizada con exito");
			} else {
				System.out.println("Conexion fallida");
			}
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void ejecutarConsulta() throws SQLException {
		int id;
		String nombre, nacionalidad;

		st = connection.createStatement();
		rs = st.executeQuery("select ID_AUTOR,NOMBRE_AUTOR,NACIONALIDAD from AUTORES");

		while (rs.next()) {
			id = rs.getInt("ID_LECTOR");
			nombre = rs.getString("NOMBRE_AUTOR");
			nacionalidad = rs.getString("NACIONALIDAD");
			System.out.println("Id del autor:" + id + "* nombre:" + nombre + "* nacionalidad:" + nacionalidad);

		}
	}

	public void insertarLector(int id, String nombre, String apellido, String email, int multas_pen) {
		String query = "INSERT INTO LECTORES (ID_LECTOR,NOMBRE,APELLIDO,EMAIL,MULTAS_PENDIENTES) VALUES (?,?,?,?,?)";
		try (Connection connection = DriverManager.getConnection(url, login, password);
				PreparedStatement pstm = connection.prepareStatement(query)) {
			pstm.setInt(1, id);
			pstm.setString(2, nombre);
			pstm.setString(3, apellido);
			pstm.setNString(4, email);
			pstm.setInt(5, multas_pen);

			System.out.println("Cliente insertado exitosamente");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cerrar() {
		if (rs != null) {
			try {
				rs.close();
				if (st != null) {
					st.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		System.out.println("Conexion cerrada");
	}

	public void borrarLector(int id_lector) {
		String query = "DELETE FROM LECTORES WHERE ID_LECTOR= ?";
		try (Connection connection = DriverManager.getConnection(url, login, password);
				PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, id_lector);
			int resultado = pstmt.executeUpdate();
			System.out.println("Lector/es borrado con exito " + resultado);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void actualizarCliente(int id_lector, String nuevo_email) {
		String query = "UPDATE LECTORES SET EMAIL = ? WHERE ID_LECTOR = ?";
		try (Connection cn = DriverManager.getConnection(url, login, password);
				PreparedStatement pstm = cn.prepareStatement(query)) {

			// Establecer los parámetros
			pstm.setString(1, nuevo_email);
			pstm.setInt(2, id_lector);

			// Ejecutar la actualización
			int filasActualizadas = pstm.executeUpdate();

			// Opcional: informar del resultado
			if (filasActualizadas > 0) {
				System.out.println("Cliente actualizado correctamente");
			} else {
				System.out.println("No se encontró el cliente con ID: " + id_lector);
			}

		} catch (SQLException e) {
			System.err.println("Error al actualizar cliente: " + e.getMessage());
			e.printStackTrace();
		}
	}
}