package ejercicio2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.basex.api.client.ClientSession;

public class Ejercicio_2 {

	// Configuración de conexión
	private static final String HOST = "localhost";
	private static final int PUERTO = 1984;
	private static final String USUARIO = "admin";
	private static final String PASSWORD = "admin";
	private static final String NOMBRE_BD = "TiendaTech";

	// XML de partida
	private static final String XML_INICIAL = "<inventario>".concat(" <producto id=\"P001\" categoria=\"portatil\">")
			+ " <nombre>HP Pavilion</nombre>" + " <precio moneda=\"EUR\">800</precio>" + " <stock>10</stock>"
			+ " </producto>" + " <producto id=\"P002\" categoria=\"periferico\">" + " <nombre>Raton Logitech</nombre>"
			+ " <precio moneda=\"EUR\">20</precio>" + " <stock>50</stock>" + " </producto>"
			+ " <producto id=\"P003\" categoria=\"monitor\">" + " <nombre>Samsung 24</nombre>"
			+ " <precio moneda=\"EUR\">150</precio>" + " <stock>5</stock>" + " </producto>" + "</inventario>";

	public static void main(String[] args) {
		try (ClientSession sesion = new ClientSession(HOST, PUERTO, USUARIO, PASSWORD)) {

			System.out.println("=== GESTIÓN DE INVENTARIO TIENDA TECH ===\n");

			// A. CONEXIÓN Y LIMPIEZA (CREATE)
			crearBaseDatos(sesion);

			// B. CARGA DE DATOS (ADD)
			cargarDatosIniciales(sesion);

			// C. OPERACIONES CRUD
			System.out.println("\n--- OPERACIONES CRUD ---\n");

			// 1. Insertar nuevo producto
			insertarNuevoProducto(sesion);

			// 2. Actualizar precio
			actualizarPrecio(sesion);

			// 3. Eliminar producto
			eliminarProducto(sesion);

			// D. CONSULTA FINAL (READ)
			mostrarInventarioFinal(sesion);

			System.out.println("\n=== PROCESO COMPLETADO ===");

		} catch (IOException e) {
			System.err.println("Error de conexión: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * A. Crear la base de datos TiendaTech
	 */
	private static void crearBaseDatos(ClientSession sesion) throws IOException {
		System.out.println("1. Creando base de datos '" + NOMBRE_BD + "'...");

		// Eliminar si existe y crear nueva
		sesion.execute("DROP DB " + NOMBRE_BD);
		sesion.execute("CREATE DB " + NOMBRE_BD);

		System.out.println("   ✓ Base de datos creada y abierta correctamente\n");
	}

	/**
	 * B. Cargar datos iniciales desde String usando ByteArrayInputStream
	 */
	private static void cargarDatosIniciales(ClientSession sesion) throws IOException {
		System.out.println("2. Cargando datos iniciales...");

		// Convertir el String XML a ByteArrayInputStream
		ByteArrayInputStream inputStream = new ByteArrayInputStream(XML_INICIAL.getBytes("UTF-8"));

		// Subir el XML con el nombre stock_2024.xml
		sesion.add("stock_2024.xml", inputStream);

		System.out.println("   ✓ Archivo 'stock_2024.xml' cargado correctamente");

		// Mostrar contenido inicial
		System.out.println("\n--- INVENTARIO INICIAL ---");
		String resultado = sesion.execute("XQUERY doc('TiendaTech/stock_2024.xml')//producto");
		System.out.println(resultado);
	}

	/**
	 * C.1. INSERT - Insertar nuevo producto (Teclado Mecánico)
	 */
	private static void insertarNuevoProducto(ClientSession sesion) throws IOException {
		System.out.println("3. Insertando nuevo producto (P004 - Teclado Mecánico)...");

		String xquery = "insert node " + "<producto id='P004' categoria='periferico'>"
				+ "  <nombre>Teclado Mecánico</nombre>" + "  <precio moneda='EUR'>45</precio>" + "  <stock>0</stock>"
				+ "</producto> " + "into doc('TiendaTech/stock_2024.xml')/inventario";

		sesion.execute("XQUERY " + xquery);
		System.out.println("   ✓ Producto P004 insertado correctamente\n");
	}

	/**
	 * C.2. REPLACE VALUE - Actualizar precio del HP Pavilion (P001) a 850
	 */
	private static void actualizarPrecio(ClientSession sesion) throws IOException {
		System.out.println("4. Actualizando precio del producto P001 (HP Pavilion) a 850€...");

		String xquery = "replace value of node doc('TiendaTech/stock_2024.xml')//producto[@id='P001']/precio "
				+ "with '850'";

		sesion.execute("XQUERY " + xquery);
		System.out.println("   ✓ Precio actualizado correctamente\n");
	}

	/**
	 * C.3. DELETE NODE - Eliminar producto P002 (Ratón Logitech)
	 */
	private static void eliminarProducto(ClientSession sesion) throws IOException {
		System.out.println("5. Eliminando producto P002 (Ratón Logitech - descatalogado)...");

		String xquery = "delete node doc('TiendaTech/stock_2024.xml')//producto[@id='P002']";

		sesion.execute("XQUERY " + xquery);
		System.out.println("   ✓ Producto P002 eliminado correctamente\n");
	}

	/**
	 * D. READ - Mostrar inventario final formateado
	 */
	private static void mostrarInventarioFinal(ClientSession sesion) throws IOException {
		System.out.println("\n--- INVENTARIO FINAL ---\n");

		// Opción 1: Mostrar XML completo formateado
		String xmlCompleto = sesion.execute("XQUERY doc('TiendaTech/stock_2024.xml')");
		System.out.println("XML COMPLETO:");
		System.out.println(xmlCompleto);

		System.out.println("\n--- RESUMEN DE PRODUCTOS ---");

		// Opción 2: Mostrar con formato personalizado usando XQuery
		String xqueryFormateado = "for $p in doc('TiendaTech/stock_2024.xml')//producto " + "return concat("
				+ "  'ID: ', $p/@id, ' | ', " + "  'Nombre: ', $p/nombre, ' | ', " + "  'Precio: ', $p/precio, '€ | ', "
				+ "  'Stock: ', $p/stock, ' unidades'" + ")";

		String resultadoFormateado = sesion.execute("XQUERY " + xqueryFormateado);
		System.out.println(resultadoFormateado);
	}
}