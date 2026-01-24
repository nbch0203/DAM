package conexion;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Conexion {
	private static String bd = "DBNixon";
	private static String login = "admin";
	private static String password = "Villaverde1.0";
	private static String url = "jdbc:mysql://dbinterfacesnixon.cn0swo6cw91x.us-east-1.rds.amazonaws.com:3306/" + bd;
	private Connection connection = null;
	private Statement st;
	private ResultSet rs;

	/**
	 * Establece la conexión con la base de datos
	 * @return true si la conexión fue exitosa, false en caso contrario
	 */
	public boolean conectar() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, login, password);
			if (connection != null) {
				System.out.println("Conexion realizada con exito");
				return true;
			} else {
				System.out.println("Conexion fallida");
				return false;
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Driver MySQL no encontrado");
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			System.err.println("Error al conectar con la base de datos");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Inserta una nueva reserva completa en la base de datos
	 * @param nombre Nombre del cliente
	 * @param correo Correo electrónico del cliente
	 * @param telefono Teléfono del cliente
	 * @param fecha Fecha del evento
	 * @param numeroPersonas Número de asistentes
	 * @param tipoEvento Tipo de evento (Banquete, Jornada, Congreso)
	 * @param tipoCocina Tipo de servicio de cocina seleccionado
	 * @param dias Número de días (para congresos)
	 * @param necesitaHabitacion Si necesitan habitación (para congresos)
	 * @return true si la inserción fue exitosa, false en caso contrario
	 */
	public boolean insertarReserva(String nombre, String correo, String telefono, Date fecha, 
								   int numeroPersonas, String tipoEvento, String tipoCocina, 
								   int dias, boolean necesitaHabitacion) {
		// Primero insertamos el cliente
		int idCliente = insertarClienteYObtenerID(nombre, correo, telefono);
		
		if (idCliente == -1) {
			System.err.println("Error: No se pudo insertar el cliente");
			return false;
		}
		
		// Luego insertamos la reserva
		String queryReserva = "INSERT INTO reservas (id_cliente, fecha_evento, num_personas, tipo_evento, " +
							  "tipo_cocina, num_dias, necesita_habitacion, fecha_reserva) " +
							  "VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
		
		try (PreparedStatement pstm = connection.prepareStatement(queryReserva)) {
			pstm.setInt(1, idCliente);
			pstm.setTimestamp(2, new Timestamp(fecha.getTime()));
			pstm.setInt(3, numeroPersonas);
			pstm.setString(4, tipoEvento);
			pstm.setString(5, tipoCocina);
			pstm.setInt(6, dias);
			pstm.setBoolean(7, necesitaHabitacion);
			
			int filasAfectadas = pstm.executeUpdate();
			System.out.println("Reserva insertada exitosamente. Filas afectadas: " + filasAfectadas);
			return true;
		} catch (SQLException e) {
			System.err.println("Error al insertar reserva");
			e.printStackTrace();
			return false;
		}
	}

	

	/**
	 * Inserta un cliente y devuelve su ID generado
	 * @param nombre Nombre del cliente
	 * @param correo Correo electrónico
	 * @param telefono Teléfono
	 * @return ID del cliente insertado, o -1 si hubo error
	 */
	private int insertarClienteYObtenerID(String nombre, String correo, String telefono) {
		String query = "INSERT INTO clientes (nombre, correo, telefono, fecha_registro) VALUES (?, ?, ?, NOW())";
		try (PreparedStatement pstm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			pstm.setString(1, nombre);
			pstm.setString(2, correo);
			pstm.setString(3, telefono);

			int filasAfectadas = pstm.executeUpdate();
			
			if (filasAfectadas > 0) {
				ResultSet generatedKeys = pstm.getGeneratedKeys();
				if (generatedKeys.next()) {
					int id = generatedKeys.getInt(1);
					System.out.println("Cliente insertado exitosamente con ID: " + id);
					return id;
				}
			}
			return -1;
		} catch (SQLException e) {
			System.err.println("Error al insertar cliente");
			e.printStackTrace();
			return -1;
		}
	}



	/**
	 * Cierra la conexión con la base de datos y libera recursos
	 */
	public void cerrar() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (connection != null) {
				connection.close();
			}
			System.out.println("Conexion cerrada");
		} catch (SQLException e) {
			System.err.println("Error al cerrar la conexion");
			e.printStackTrace();
		}
	}


	/**
	 * Verifica si la conexión está activa
	 * @return true si está conectado, false en caso contrario
	 */
	public boolean estaConectado() {
		try {
			return connection != null && !connection.isClosed();
		} catch (SQLException e) {
			return false;
		}
	}
}