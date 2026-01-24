package conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

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

		/*
		 * Ejercicio 1: Conexión y Cierre
		 */

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

	/*
	 * Ejercicio 2: SELECT Básico
	 */

	public void ejecutarConsulta() throws SQLException {
		int id;
		String nombre, nacionalidad;

		st = connection.createStatement();
		rs = st.executeQuery("select ID_AUTOR,NOMBRE_AUTOR,NACIONALIDAD from AUTORES");

		while (rs.next()) {
			id = rs.getInt("ID_AUTOR");
			nombre = rs.getString("NOMBRE_AUTOR");
			nacionalidad = rs.getString("NACIONALIDAD");
			System.out.println("Id del autor:" + id + "* nombre:" + nombre + "* nacionalidad:" + nacionalidad);

		}
	}

	/*
	 * Ejercicio 3: INSERT
	 */

	public void insertarLector(int id, String nombre, String apellido, String email, double multas_pen) {
		String query = "INSERT INTO LECTORES (ID_LECTOR,NOMBRE,APELLIDO,EMAIL,MULTAS_PENDIENTES) VALUES (?,?,?,?,?)";
		try (Connection connection = DriverManager.getConnection(url, login, password);
				PreparedStatement pstm = connection.prepareStatement(query)) {
			pstm.setInt(1, id);
			pstm.setString(2, nombre);
			pstm.setString(3, apellido);
			pstm.setNString(4, email);
			pstm.setDouble(5, multas_pen);

			pstm.execute();

			System.out.println("Cliente insertado exitosamente");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Ejercicio 4: UPDATE
	 */

	public void actualizarCliente(int id_lector, String nuevo_email) {
		String query = "UPDATE LECTORES SET email = ? WHERE id_lector = ?";
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

	/*
	 * Ejercicio 5: DELETE y Filas Afectadas
	 */

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

	/*
	 * Ejercicio 6: SELECT Parametrizado
	 */

	public void buscarLibrosPorAnio(int anio) {
		int isbn, anio_publi, num_copias_total, num_copias_dis, id_autor;
		String titulo;

		String query = "Select ISBN,TITULO,ANIO_PUBLICACION,NUM_COPIAS_TOTALES,NUM_COPIAS_DISP,ID_AUTOR from LIBROS WHERE ANIO_PUBLICACION= ?";

		try (Connection cn = DriverManager.getConnection(url, login, password)) {
			PreparedStatement pstm = cn.prepareStatement(query);

			pstm.setInt(1, anio);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				isbn = rs.getInt("ISBN");
				titulo = rs.getString("TITULO");
				anio_publi = rs.getInt("ANIO_PUBLICACION");
				num_copias_total = rs.getInt("NUM_COPIAS_TOTALES");
				num_copias_dis = rs.getInt("NUM_COPIAS_DISP");
				id_autor = rs.getInt("ID_AUTOR");

				System.out.println("ISBN: " + isbn + " titulo: " + titulo + " año de publicacion: " + anio_publi
						+ " numero de copias totales: " + num_copias_total + " numero de copias disponibles "
						+ num_copias_dis + " id autor: " + id_autor);

			}

		} catch (SQLException e) {
			// TODO: handle
			e.printStackTrace();
		}

	}

	/*
	 * Ejercicio 7: INSERT Parametrizado
	 */

	public void registrarNuevoAutorPS(int id, String nombre, String nacionalidad) {
		String query = "INSERT INTO AUTORES (ID_AUTOR,NOMBRE_AUTOR,NACIONALIDAD) VALUES (?,?,?)";

		try (Connection cn = DriverManager.getConnection(url, login, password)) {
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, id);
			pstm.setString(2, nombre);
			pstm.setString(3, nacionalidad);

			int verificar = pstm.executeUpdate();
			if (verificar > 0) {
				System.out.println("Insertado un nuevo autor");
			} else
				System.out.println("No se ha podido insertar");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/*
	 * Ejercicio 8: UPDATE Parametrizado
	 */

	public void cambiarCopiasDisponibles(String isbn, int nuevasCopias) {
		String query = "UPDATE LIBROS SET NUM_COPIAS_DISP=? WHERE ISBN=?";
		try (Connection cn = DriverManager.getConnection(url, login, password)) {
			PreparedStatement pstm = cn.prepareStatement(query);

			pstm.setInt(1, nuevasCopias);
			pstm.setString(2, isbn);

			boolean confirmacion = pstm.execute();

			if (confirmacion) {

				System.out.println("Se ha actualizado la cantidad de copias a : " + nuevasCopias);
			} else {
				System.out.println("No se ha podido actualizar el libro con el ISBN : " + isbn);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/*
	 * Ejercicio 9: Consulta con JOIN
	 */

	public void listarLibrosDeAutor(String nombreAutor) {
		String query = "SELECT L.TITULO FROM LIBROS L JOIN AUTORES ON L.ID_AUTOR=A.ID_AUTOR WHERE ID_AUTOR=? ";

		try (Connection cn = DriverManager.getConnection(url, login, password)) {
			PreparedStatement pstm = cn.prepareStatement(query);

			pstm.setString(1, nombreAutor);

			boolean confirmar = pstm.execute();

			if (confirmar) {
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) {
					String titulo = rs.getString("TITULO");

					System.out.println("El titulo del libro es : " + titulo + "\n");
				}
			} else {
				System.out.println("No se ha podido ejecutar la query");

			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/*
	 * Ejercicio 10: Ejercicio Desafío (SELECT con WHERE)
	 */

	public void buscarLectoresConMultas() {
		String query = "Select ID_LECTOR,NOMBRE,MULTAS_PENDIENTES FROM LECTORES where MULTAS_PENDIENTES>0";

		try (Connection cn = DriverManager.getConnection(url, login, password)) {
			PreparedStatement pstm = cn.prepareStatement(query);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String id_lector = rs.getNString("ID_LECTOR");
				String nombre = rs.getNString("NOMBRE");
				double multas = rs.getDouble("MULTAS_PENDIENTES");

				System.out.println("ID: " + id_lector + " Nombre: " + nombre + " Multas: " + multas);

			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/*
	 * Ejercicio 11: CallableStatement (Parámetro OUT)
	 */

	public void procesarDevolucion(int idPrestamo) {
		String call = "EXEC REGISTRAR_DEVOLUCION(?,?)";
		try (Connection cn = DriverManager.getConnection(url, login, password)) {
			CallableStatement cls = cn.prepareCall(call);

			/*
			 * Decimos el id del prestamo y como es la entrada del procedimiento lo
			 * colocamos como primero
			 */
			cls.setInt(1, idPrestamo);

			// Parametro de salida (donde y de que tipo)

			cls.registerOutParameter(2, Types.VARCHAR);
			// Ejecutamos
			cls.execute();

			// Obtenemos el resultado
			String titulo = cls.getString("TITULO");

			System.out.println("Devolucion exitosa");
			System.out.println("Se ha devuelto el libro: " + titulo);

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/*
	 * Ejercicio 12: CallableStatement (Llamada a Función)
	 */

	public void obtenerMulta(int idLector) throws SQLException {
		Connection cn = null;
		String call = "{? = call CALCULAR_MULTA_LECTOR(?)}";

		try {
			cn = DriverManager.getConnection(url, login, password);
			cn.setAutoCommit(false);

			CallableStatement cls = cn.prepareCall(call);

			cls.setInt(2, idLector);
			cls.registerOutParameter(1, Types.NUMERIC);

			cls.execute();

			cn.commit();

			double cantidad = cls.getDouble(1);

			System.out.println("La multa del lector con id :" + idLector + " es de : " + cantidad + " €");

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("no se ha podido");
			cn.rollback();
		}

	}

	/*
	 * Ejercicio 13: Transacciones (COMMIT) Tarda mucho en cargar y no termina
	 * revisar
	 * 
	 * mi intento
	 */
//	public void registrarPrestamoSeguro(int idLector, String isbn) throws SQLException {
//		Connection cn = null;
//		String insert = "insert into prestamos (ID_PRESTAMO,ID_LECTOR,ISBN,FECHA_LIMITE) values(?,?,?,?)";
//		String update = "update prestamos set Fecha_limite=? where id_prestamo=4";
//		try {
//			cn = DriverManager.getConnection(url, login, password);
//			PreparedStatement pstm = cn.prepareStatement(insert);
//
//			/*
//			 * Para usar date hay que crear un objeto de ese tipo y setearle un long en este
//			 * caso la fecha del systema
//			 */
//			Date date = new Date(System.currentTimeMillis());
//			pstm.setInt(1, 4);
//			pstm.setInt(2, idLector);
//			pstm.setString(3, isbn);
//			pstm.setDate(4, date);
//
//			int verificacion = pstm.executeUpdate();
//
//			if (verificacion > 0) {
//				pstm.close();
//				System.out.println("Se ha creado un nuevo prestamo al lector con id: " + idLector);
//				System.out.println("El ISBN del libro es : " + isbn);
//
//				PreparedStatement pstm2 = cn.prepareStatement(update);
//
//				pstm2.setDate(1, date);
//
//				int veri = pstm2.executeUpdate();
//
//				if (veri > 0) {
//					pstm2.close();
//
//					System.out.println("Updateado");
//				} else
//					throw new SQLException();
//
//			} else
//				throw new SQLException();
//
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}

	private static void registrarPrestamoSeguro(int id, String isbn) throws SQLException {
		try {
			connection.setAutoCommit(false);
			String sql = "UPDATE prestamos SET isbn = ? WHERE id_prestamo = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, sql);
			ps.setInt(2, id);
			ps.executeUpdate();

			sql = "INSERT INTO prestamos(id_prestamo, isbn) VALUES(?, ?)";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, sql);
			ps.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
		}
	}

	/*
	 * Ejercicio 14: Transacciones (ROLLBACK)
	 */
	public void transaccionesRollback(int idLector, String isbn) throws SQLException {
		Connection cn = null;
		String insert = "insert into prestamos (ID_PRESTAMO,ID_LECTOR,ISBN,FECHA_LIMITE) values(?,?,?,?)";
		String update = "update prestamos set Fecha_limite=? where id_prestamo=4";
		try {
			cn = DriverManager.getConnection(url, login, password);
			cn.setAutoCommit(false);
			PreparedStatement pstm = cn.prepareStatement(insert);

			/*
			 * Para usar date hay que crear un objeto de ese tipo y setearle un long en este
			 * caso la fecha del systema
			 */
			Date date = new Date(System.currentTimeMillis());
			pstm.setInt(1, 4);
			pstm.setInt(2, idLector);
			pstm.setString(3, isbn);
			pstm.setDate(4, date);

			int verificacion = pstm.executeUpdate();

			if (verificacion > 0) {
				pstm.close();
				System.out.println("Se ha creado un nuevo prestamo al lector con id: " + idLector);
				System.out.println("El ISBN del libro es : " + isbn);

				PreparedStatement pstm2 = cn.prepareStatement(update);

				pstm2.setDate(1, date);

				int veri = pstm2.executeUpdate();

				if (veri > 0) {
					pstm2.close();
					cn.commit();
					System.out.println("Updateado");
				} else
					throw new SQLException();

			} else
				throw new SQLException();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			cn.rollback();
		}
	}

	/*
	 * Ejercicio 15: CallableStatement (Parámetro IN/OUT)
	 */

	public void aplicarMulta(int idLector, double montoAdicional) {
		Connection cn = null;
		String call = "call ACTUALIZAR_MULTA_INOUT(?,?)";
//		String query = "Select MULTAS_PENDIENTES from lectores where id_lector=?";
//		Double multa = null;
		try {
			cn = DriverManager.getConnection(url, login, password);
			CallableStatement cls = cn.prepareCall(call);
//			PreparedStatement pstm = cn.prepareStatement(query);

//			pstm.setInt(1, idLector);
			// seteo de entrada el parametro 1
			cls.setInt(1, idLector);

			// cambio a salida el parametro 2
			cls.registerOutParameter(2, Types.DOUBLE);

			// seteo de entrada el parametro 2
			cls.setDouble(2, montoAdicional);

//			ResultSet rs = pstm.executeQuery();
//
//			while (rs.next()) {
//				multa = rs.getDouble("MULTAS_PENDIENTES");
//			}

			cls.execute();

			double monto = cls.getDouble(2);

			System.out.println("Se le ha sumado una cantidad de : " + monto);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}