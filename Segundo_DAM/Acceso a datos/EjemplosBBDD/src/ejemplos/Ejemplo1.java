package ejemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejemplo1 {

	private static String bd = "xe";
	private static String login = "C##TALLER";
	private static String password = "password";

	// Ruta del servidor, nosotros localhost
	private static String url = "jdbc:oracle:thin:@localhost:1521:" + bd;
	static Connection connection = null;
	private static Statement st;
	private static ResultSet rs;

	public static void conectar() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, login, password);
			if (connection != null)
				System.out.println("Conexión realizada correctamente");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void cerrar() {
		if (rs != null)
			try {
				rs.close();
				if (st != null)
					st.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println("Conexión cerrada.");
	}

	public static void ejecutarConsulta() throws SQLException {
		int empno;
		String apellido, oficio;
		st = connection.createStatement();
		rs = st.executeQuery("select EMP_NO,APELLIDO,OFICIO from EMPLEADOS");

		while (rs.next()) {
			empno = rs.getInt("emp_no");
			apellido = rs.getString("apellido");
			oficio = rs.getString("oficio");
			System.out.println("empno:" + empno + "*apellido:" + apellido + "*oficio:" + oficio);
		}

	}

	public static void otrasOperaciones() throws SQLException {

		// Consulta a USER_TABLES para verificar si la tabla existe
		String query = "SELECT TABLE_NAME FROM USER_TABLES WHERE TABLE_NAME = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		String tableName = "TABLA1";
		statement.setString(1, tableName.toUpperCase()); // Asegurarse de que el nombre de la tabla esté en mayúsculas

		ResultSet resultSet = statement.executeQuery();

		st = connection.createStatement();
		if (resultSet.next()) {
			// Si existe al menos un registro, la tabla existe
			System.out.println("La tabla " + tableName + " existe.");
			st.executeUpdate("drop table tabla1");
			st.executeUpdate("create table tabla1(c1 int primary key)");
			st.executeUpdate("insert into tabla1 values (10)");
		} else {
			// Si no existe ningún registro, la tabla no existe
			System.out.println("La tabla " + tableName + " no existe.");
			st.executeUpdate("create table tabla1(c1 int primary key)");
			st.executeUpdate("insert into tabla1 values (10)");

		}

		if (resultSet != null)
			resultSet.close();
		if (statement != null)
			statement.close();

	}
	
	public static void ejecutarConsultaPreparada() throws SQLException {
		PreparedStatement ps=connection.prepareStatement("insert into tabla1 values (?)");
		//Los parámetros empiezan a contar desde el 1
		ps.setInt(1, 20);
		ps.executeUpdate();
		ps.setInt(1, 30);
		ps.executeUpdate();
		
		//Recupero todos los datos de la tabla1 y los muestro por pantalla
		ps=connection.prepareStatement("select * from tabla1");
		rs=ps.executeQuery();
		
		while (rs.next()) {
			System.out.println("Valor:"+rs.getInt(1));
		}
		ps.close();
	}
	
	public static void ejecutarConsultaPreparadaBis() throws SQLException {
		PreparedStatement ps=connection.prepareStatement("insert into tabla2 values (default,?,?)");
		//Los parámetros empiezan a contar desde el 1
		ps.setString(1, "Isabel");
		ps.setString(2, "Morera");
		ps.executeUpdate();
		
		ps.setString(1, "Lucía");
		ps.setString(2, "Hernández");
		ps.executeUpdate();
		
		//Recupero todos los datos de la tabla1 y los muestro por pantalla
		ps=connection.prepareStatement("select * from tabla2");
		rs=ps.executeQuery();
		
		while (rs.next()) {
			System.out.println("Nombre:"+rs.getString(1)+" apellido:"+rs.getString(2));
		}
		ps.close();
	}

	public static void main(String[] args) {
		conectar();
		try {
			ejecutarConsulta();
			otrasOperaciones();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cerrar();
		}
	}

}
