package ejercicios_repaso;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.ResultSet;

import org.basex.api.client.ClientSession;

public class Repaso {
	static final String HOST = "localhost";
	private static final int PUERTO = 1984;
	private static final String USUARIO = "admin";
	private static final String PASSWORD = "admin";
	private static final String NOMBRE_BD = "Concesionario";

	private static final String XML_CONCESIONARIO = "<concesionario>"
			.concat(" <coche id=\"C01\" combustible=\"gasolina\">").concat(" <marca>Toyota</marca>")
			.concat(" <modelo>Corolla</modelo>").concat(" <precio moneda=\"EUR\">22000</precio>")
			.concat(" <km>15000</km>").concat(" <extras>").concat(" <extra>GPS</extra>")
			.concat(" <extra>Cámara trasera</extra>").concat(" </extras>").concat(" </coche>")
			.concat(" <coche id=\"C02\" combustible=\"diesel\">").concat(" <marca>Ford</marca>")
			.concat(" <modelo>Focus</modelo>").concat(" <precio moneda=\"EUR\">18000</precio>")
			.concat(" <km>85000</km>").concat(" <extras>").concat(" <extra>Llantas aleación</extra>")
			.concat(" </extras>").concat(" </coche>").concat(" <coche id=\"C03\" combustible=\"hibrido\">")
			.concat(" <marca>Toyota</marca>").concat(" <modelo>Yaris</modelo>")
			.concat(" <precio moneda=\"EUR\">19500</precio>").concat(" <km>5000</km>").concat(" <extras/>")
			.concat(" </coche>").concat(" <coche id=\"C04\" combustible=\"electrico\">").concat(" <marca>Tesla</marca>")
			.concat(" <modelo>Model 3</modelo>").concat(" <precio moneda=\"USD\">35000</precio>").concat(" <km>0</km>")
			.concat(" <extras>").concat(" <extra>Piloto automático</extra>")
			.concat(" <extra>Asientos calefactables</extra>").concat(" <extra>Techo solar</extra>").concat(" </extras>")
			.concat(" </coche>").concat("</concesionario>");

	private static void crearBaseDatos(ClientSession sesion) throws IOException {
		System.out.println("Creando la base de datos: " + NOMBRE_BD);

		sesion.execute("DROP DB " + NOMBRE_BD);
		sesion.execute("CREATE DB " + NOMBRE_BD);
	}

	private static void cargarDatos(ClientSession sesion) throws IOException {
		System.out.println("Cargando los datos iniciales....");

		ByteArrayInputStream inputStream = new ByteArrayInputStream(XML_CONCESIONARIO.getBytes("UTF-8"));

		sesion.add("concesionario.xml", inputStream);

		System.out.println("Archivo concesionario.xml cargado");

	}

	private static void ejercicio_1(ClientSession sesion) throws IOException {
		System.out.println("Obteniendo las rutas y texto.....");

		System.out.println("Los modelos de los coches del concesionario son: ");
		String resultado = sesion.execute(
				"XQUERY for $x in doc('Concesionario/concesionario.xml')//concesionario return $x//modelo/text()");
		System.out.println(resultado);
	}

	private static void ejercicio_2(ClientSession sesion) throws IOException {
		System.out.println("Obtener todos los coches cuyo combustible sea gasolina: ");

		String combustible = sesion.execute("XQUERY for $x in doc('Concesionario/concesionario.xml')//coche "
				+ "where $x/@combustible = 'gasolina' and $x/precio>20000 and $x/marca='Toyota' return $x");

		System.out.println(combustible);
	}

	private static void ejercicio_3(ClientSession sesion) throws IOException {
		System.out.println("Ejercicio 3");

		String resultado = sesion.execute("XQUERY for $x in doc ('Concesionario/concesionario.xml')//coche"
				+ " where $x/modelo='Focus' return $x/@id/");

		System.out.println(resultado);
	}

	private static void ejercicio_8() {
		String a = "sum (doc('Concesionario/concesionario.xml')//precio)";
		String ab = "count(doc('Concesionario/concesionario.xml')//coche)";

		String b = "for $x in doc('Concesionario/concesionario.xml')//coche\n" + "where $x//extra='Techo solar'\n"
				+ "return $x";

		String c = "for $x in doc('Concesionario/concesionario.xml')//coche\n"
				+ "\n"
				+ "where count($x//extra) >2\n"
				+ "return $x";
		
		String d="for $x in doc('Concesionario/concesionario.xml')//coche\n"
				+ "where $x/@id='C01'\n"
				+ "return $x//extra[1]/text()";
	}

	public static void main(String[] args) {

		try (ClientSession sesion = new ClientSession(HOST, PUERTO, USUARIO, PASSWORD)) {
//			crearBaseDatos(sesion);
//			cargarDatos(sesion);
//			ejercicio_1(sesion);
//			ejercicio_2(sesion);
			ejercicio_3(sesion);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
