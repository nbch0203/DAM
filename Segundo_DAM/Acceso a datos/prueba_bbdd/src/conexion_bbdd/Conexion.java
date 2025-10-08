package conexion_bbdd;

import java.sql.Connection;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

	private static String bd = "xe";
	private static String login = "C##TALLER";
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
		int empno;
		String nombre, direccion, telefono;
		Date fecha;

		st = connection.createStatement();
		rs = st.executeQuery("select NCLIENTE,NOMBRE,DIRECCION,TELEFONO,FECHA_ALTA from CLIENTES_TALLER");

		while (rs.next()) {
			empno = rs.getInt("NCLIENTE");
			nombre = rs.getString("NOMBRE");
			direccion = rs.getString("DIRECCION");
			telefono = rs.getString("TELEFONO");
			fecha = rs.getDate("FECHA_ALTA");
			System.out.println("empno:" + empno + "*nombre:" + nombre + "*direccion:" + direccion + "*telefono: "
					+ telefono + "*fecha_alta: " + fecha);

		}
	}

	public void insertarCliente(int nCliente, String nombre, String direccion, String telefono, Date fecha_alta) {
		String query = "INSERT INTO Clientes_taller (ncliente,nombre,direccion,telefono,fecha_alta) VALUES (?,?,?,?,?)";
		try (Connection connection = DriverManager.getConnection(url, login, password);
				PreparedStatement pstm = connection.prepareStatement(query)) {
			pstm.setInt(1, nCliente);
			pstm.setString(2, nombre);
			pstm.setString(3, direccion);
			pstm.setNString(4, telefono);
			pstm.setDate(5, fecha_alta);

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

	public void borrarCliente(int nCliente) {
		String query = "DELETE FROM clientes_taller WHERE ncliente= ?";
		try (Connection connection = DriverManager.getConnection(url, login, password);
				PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, nCliente);
			int resultado = pstmt.executeUpdate();
			System.out.println("Cliente/s borrado con exito" + resultado);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void actualizarCliente(int ncliente, String nuevaDir) {
		String query = ("UPDATE");
		try (Connection cn = DriverManager.getConnection(url, login, password);
				PreparedStatement pstm = cn.prepareStatement(query)) {

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
