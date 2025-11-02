package conexion;

import java.sql.CallableStatement;
import java.sql.Connection;

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
			id = rs.getInt("ID_AUTOR");
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
			// TODO: handle exception
		}

	}

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

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

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

		} catch (

		SQLException e) {
			// TODO: handle exception
		}
	}

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
		}
	}

	public void buscarLectoresConMultas() {
		String query = "Select ID_LECTOR,NOMBRE,MULTAS_PENDIENTES FROM LECTORES where MULTAS_PENDIENTES>0";

		try (Connection cn = DriverManager.getConnection(url, login, password)) {
			PreparedStatement pstm = cn.prepareStatement(query);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String id_lector = rs.getNString("ID_LECTOR");
				String nombre = rs.getNString("NOMBRE");
				int multas = rs.getInt("MULTAS_PENDIENTES");

				System.out.println("ID: " + id_lector + " Nombre: " + nombre + " Multas: " + multas);

//				if (multas > 0) {
//					System.out.println("ID: " + id_lector + " Nombre: " + nombre + " Multas: " + multas);
//				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

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

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	
	public void obtenerMulta(int idLector) {
		
	}
}