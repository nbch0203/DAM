package gestor;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import oracle.sql.DATE;

public class GestorBD {

	private Connection conn = null;
	private static ResultSet rs;
	private static Statement st;

	// Sustituye estos con tus datos de Oracle
	private final String bd = "xe";
	private final String URL_CONEXION = "jdbc:oracle:thin:@localhost:1521:" + bd;
	private final String USUARIO = "C##MINITIENDA";
	private final String PASSWORD = "password";

	/**
	 * Criterio d: Establece la conexión con la base de datos Oracle. Criterio c:
	 * Usa el driver específico de Oracle.
	 */
	public void conectar() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD);
			System.out.println("Conexión establecida con Oracle.");

		} catch (SQLException | ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("No se ha podido conectar: " + e.getMessage());
			System.err.println("ERROR: No se pudo conectar a la BD Oracle.");

		}

	}

	/**
	 * Criterio i: Cierra la conexión.
	 */
	public void desconectar() {
		if (rs != null) {
			try {
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

				System.out.println("\nDesconexión exitosa.");

			} catch (SQLException e) {
				// TODO: handle exception
				System.err.println("ERROR al cerrar conexión: " + e.getMessage());

			}

		}

	}

	/**
	 * Tarea A - Criterio k: Consulta stock usando un Procedimiento Almacenado.
	 * Criterio g: Gestión del parámetro de salida.
	 */
	public void consultarStock(int productoId) {
		String sql = "call CONSULTAR_STOCK(?,?)";
		System.out.println("\n--- Tarea 1: Consultar Stock (ID: " + productoId + ") ---");

		try (CallableStatement cls = conn.prepareCall(sql)) {

			cls.setInt(1, productoId);
			cls.registerOutParameter(2, Types.NUMERIC);

			cls.execute();
			int stockActual = cls.getInt(2);

			System.out.println("Stock actual del Producto " + productoId + ": " + stockActual + " unidades.");

		} catch (SQLException e) {
			System.err.println("ERROR al consultar stock: " + e.getMessage());
		}

	}

	/**
	 * Tarea B - Criterio f, j: Procesa un pedido completo y su log de manera
	 * atómica.
	 */
//	public void procesarPedido(int productoId, int cantidad) {
//		String sql_update = "Update PRODUCTOS set stock=? where id=?";
//		String sql_insert = "insert into log_pedidos values (11,2,300,sysdate)";
//
//		try {
//			conn.setAutoCommit(false);
//
//			System.out.println("\n--- Tarea 2: Procesar Pedido (Prod: " + productoId + ", Cant: " + cantidad + ") ---");
//
//			try (PreparedStatement pstm = conn.prepareStatement(sql_update)) {
//				System.out.println("    [Transacción Iniciada]");
//				st = conn.createStatement();
//
//				pstm.setInt(1, cantidad);
//				pstm.setInt(2, productoId);
//
//				int veri_update = pstm.executeUpdate();
//				int veri_insert = st.executeUpdate(sql_insert);
//
//				// Verificacion de que todo se ha realizado correctamente
//				// y hacemos un commit
//
//				if (veri_insert > 0 && veri_update > 0) {
//					System.out.println("Pedido procesado y STOCK actualizado.");
//					conn.commit();
//				}
//
//			} catch (SQLException e) {
//				// TODO: handle exception
//
//				System.err.println("ERROR procesando pedido. Detalle: " + "El producto no existe o ID incorrecto.");
//				conn.rollback();
//				System.out.println("Cambios deshechos.");
//
//				System.err.println("ERROR al deshacer cambios: " + e.getMessage());
//
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}

	/**
	 * Tarea B - Criterio f, j: Procesa un pedido completo y su log de manera
	 * atómica.
	 */
	public void procesarPedido(int productoId, int cantidad) {
	    String sql_update = "UPDATE productos SET stock = stock - ? WHERE id = ?";
	    String sql_insert = "INSERT INTO log_pedidos (id, producto_id, cantidad_solicitada, fecha) " +
                "VALUES (log_pedidos_seq.NEXTVAL, ?, ?, SYSDATE)";

	    try {
	        conn.setAutoCommit(false);

	        System.out.println("\n--- Tarea 2: Procesar Pedido (Prod: " + productoId + ", Cant: " + cantidad + ") ---");
	        System.out.println("    [Transacción Iniciada]");

	        try (PreparedStatement pstmUpdate = conn.prepareStatement(sql_update);
	             PreparedStatement pstmInsert = conn.prepareStatement(sql_insert)) {

	            // Actualizar stock
	            pstmUpdate.setInt(1, cantidad);
	            pstmUpdate.setInt(2, productoId);
	            int filasActualizadas = pstmUpdate.executeUpdate();

	            // Insertar en log
	            pstmInsert.setInt(1, productoId);
	            pstmInsert.setInt(2, cantidad);
	            int filasInsertadas = pstmInsert.executeUpdate();

	            // Verificar que ambas operaciones fueron exitosas
	            if (filasActualizadas > 0 && filasInsertadas > 0) {
	                conn.commit();
	                System.out.println("    ✓ Pedido procesado y STOCK actualizado.");
	                System.out.println("    ✓ Registro insertado en log_pedidos.");
	                System.out.println("    [Transacción COMMIT]");
	            } else {
	                conn.rollback();
	                System.out.println("    ✗ No se pudo procesar el pedido (producto no existe).");
	                System.out.println("    [Transacción ROLLBACK]");
	            }

	        } catch (SQLException e) {
	            conn.rollback();
	            System.err.println("    ✗ ERROR procesando pedido: " + e.getMessage());
	            System.out.println("    [Transacción ROLLBACK]");
	        } finally {
	            conn.setAutoCommit(true);
	        }

	    } catch (SQLException e) {
	        System.err.println("ERROR en la transacción: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	/**
	 * Tarea D - Criterio h, g: Consulta 1
	 */
	public void listarPedidosDetallados() {

		String sql = "Select p.nombre,cantidad_solicitada " + "from productos p " + "join log_pedidos "
				+ "on producto_id=p.id " + "order by fecha desc";

		System.out.println("\n--- Tarea 4: Consulta con JOIN (Pedidos Detallados) ---");
		System.out.println("    ---------------------------------------------------------");

		try (Statement stm = conn.createStatement()) {
			String nombre;
			int cantidad;

			rs = stm.executeQuery(sql);
			while (rs.next()) {

				nombre = rs.getString("nombre");
				cantidad = rs.getInt("cantidad_solicitada");
				System.out.printf("    Producto: %-20s | Cantidad: %-5d\n", nombre, cantidad);
				System.out.println("    ---------------------------------------------------------");

			}

		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println("ERROR al listar pedidos detallados: " + e.getMessage());

		}

	}

	/**
	 * Tarea E - Criterio h, g: Consulta 2
	 */
	public void productosAltaDemanda(int limite) {
		String sql = "Select p.NOMBRE,sum(CANTIDAD_SOLICITADA) as demanda_total "
				+ "from PRODUCTOS p join LOG_PEDIDOS lp "
				+ "on p.id=lp.PRODUCTO_ID "
				+ "group by p.NOMBRE "
				+ "having sum(lp.CANTIDAD_SOLICITADA) >?";
		
		System.out.println("\n--- Tarea 5: Consulta con HAVING (Demanda > " + limite + ") ---");

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			
			System.out.println("    Productos con demanda total superior a " + limite + " unidades:");
			
			pstm.setInt(1, limite);

			rs = pstm.executeQuery();
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				int demanda = rs.getInt("demanda_total");
				System.out.printf("    - Producto: %-20s | Demanda Total: %d\n", nombre, demanda);

			}

		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println("ERROR en consulta 2: " + e.getMessage());

		}

	}

	/**
	 * Criterio i: Método auxiliar para cerrar recursos de forma segura.
	 */
//	private void cerrar() {
//
//		System.err.println("Error al cerrar recurso: " + e.getMessage());
//
//	}

	public Connection getConn() {
		return conn;
	}

}