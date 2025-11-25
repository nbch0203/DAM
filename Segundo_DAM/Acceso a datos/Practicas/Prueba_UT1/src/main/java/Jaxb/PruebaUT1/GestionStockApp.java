package Jaxb.PruebaUT1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class GestionStockApp {

	// --- Rutas de Ficheros ---
	private static final String RUTA_CSV = "recursos/stock_catalogo.csv";
	private static final String RUTA_PRECIOS_BIN = "recursos/precios.dat";
	private static final String RUTA_STOCK_OBJ = "recursos/stock.dat";
	private static final String RUTA_XML = "recursos/catalogo.xml";
	private static final String RUTA_RESUMEN = "recursos/resumen.txt";

	public static void main(String[] args) {
		// 1. Datos de prueba
		List<Articulo> stockInicial = new ArrayList<>();
		stockInicial.add(new Articulo("P001", "Monitor Curvo 27", 15, 299.99));
		stockInicial.add(new Articulo("P002", "Teclado Mecánico", 0, 75.50)); // Precio a modificar en Tarea 5
		stockInicial.add(new Articulo("P003", "Disco SSD 1TB", 30, 89.90));
		stockInicial.add(new Articulo("P004", "Webcam 4K", 5, 120.00));

		// Crear directorio de recursos si no existe
		new File("recursos").mkdirs();

		System.out.println("--- INICIANDO FLUJO DE PRUEBA COMPLETO ---\n");

		// --- I. Ficheros de Texto ---
		try {
			exportarA_CSV(stockInicial, RUTA_CSV);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Tarea 1
		System.out.println("[T.2] Artículos con stock 0: " + contarLineasNoVacias(RUTA_CSV) + "\n"); // Tarea 2

		// --- II. Ficheros Binarios Primitivos y Aleatorio ---
		guardarID_Precio(stockInicial, RUTA_PRECIOS_BIN); // Tarea 3 (Ahora con RandomAccessFile)

		System.out.println("--- [T.4] VERIFICACIÓN BINARIO (ANTES DE MODIFICAR) ---");
		leerFicheroPreciosBinario(RUTA_PRECIOS_BIN); // Tarea 4 (Con RandomAccessFile)

		modificarPrecioPorPosicion(RUTA_PRECIOS_BIN, 2, 85.00); // Tarea 5
		System.out.println("\n[T.5] Precio del artículo 2 modificado a 85.00€ con RandomAccessFile.");

		System.out.println("--- [T.4] VERIFICACIÓN BINARIO (DESPUÉS DE MODIFICAR) ---");
		leerFicheroPreciosBinario(RUTA_PRECIOS_BIN); // Tarea 4 (Verificación pos-modificación)

		// --- III. Serialización y JAXB ---
		System.out.println("\n--- INICIANDO SERIALIZACIÓN (T.6) ---");
		// Primero, creamos el fichero
		guardarSerializado(stockInicial, RUTA_STOCK_OBJ); // Tarea 6
		// Luego, añadimos un nuevo artículo
		List<Articulo> nuevoArticulo = new ArrayList<>();
		nuevoArticulo.add(new Articulo("P005", "Ratón Inalámbrico", 12, 25.00));
		guardarSerializado(nuevoArticulo, RUTA_STOCK_OBJ);

		System.out.println("--- [T.7] VERIFICACIÓN DE OBJETOS SERIALIZADOS ---");
		leerFicheroObjetos(RUTA_STOCK_OBJ); // Tarea 7

//		convertirBinarioA_XML(RUTA_STOCK_OBJ, RUTA_XML); // Tarea 8

//		generarResumen(RUTA_XML, RUTA_RESUMEN); // Tarea 9
		System.out.println("[T.9] Reporte final generado en: " + RUTA_RESUMEN);
		System.out.println("\n--- FIN DEL EXAMEN ---");
	}

	// =========================================================================
	// I. FICHEROS DE TEXTO
	// =========================================================================

	// Tarea 1: Exportación a Fichero de Texto (CSV)
	public static void exportarA_CSV(List<Articulo> lista, String ruta) throws IOException {
		File file = new File(ruta);

		if (!file.exists()) {
			file.createNewFile();
			FileWriter fw = new FileWriter(file);
			BufferedWriter bfw = new BufferedWriter(fw);
			bfw.write("CODIGO;NOMBRE;STOCK;PRECIO");

		} else if (file.exists()) {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bfw = new BufferedWriter(fw);

			for (Articulo articulo : lista) {
				bfw.write("/n" + articulo.toString());
			}
			bfw.close();
		}

	}

	// Tarea 2: Recuperación de Texto y Documentación (Contar Stock 0)
	public static int contarLineasNoVacias(String rutaFichero) {
		int contador = 0;
		File file = new File(rutaFichero);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			String linea;
			while ((linea = bfr.readLine()) != null) {
				if (linea.contains("stock=0")) {
					contador++;
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return contador;

	}

	// =========================================================================
	// II. FICHEROS BINARIOS PRIMITIVOS Y ACCESO ALEATORIO (TODO RandomAccessFile)
	// =========================================================================

	// Tarea 3: Almacenamiento de Datos Primitivos
	public static void guardarID_Precio(List<Articulo> lista, String rutaFichero) {
		final int codigo = 10;
		final int precio = 8;
		int posicion = 0;
		final int tammnio_articulo = (2 * codigo) + precio;

		File file = new File(rutaFichero);

		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			raf.seek(raf.length());
			for (Articulo articulo : lista) {
				raf.writeUTF(articulo.getCodigo());
				raf.writeDouble(articulo.getPrecio());
				raf.seek(posicion + tammnio_articulo);
				posicion++;
			}

		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// Tarea 4: Verificación de Fichero Binario
	public static void leerFicheroPreciosBinario(String rutaFichero) {
		File file = new File(rutaFichero);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		final int codigo = 10;
		final int precio = 8;
		final int tammnio_articulo = (2 * codigo) + precio;
		try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
			raf.seek(0);
			for (int i = 0; i < raf.length(); i++) {
				StringBuilder sb = new StringBuilder(raf.readUTF());
//				String sb = raf.readUTF();
				double pre = raf.readDouble();
				System.out.printf("Codigo " + sb + " Precio: " + pre + "\n");
				raf.seek(i + tammnio_articulo);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}

	}

	// Tarea 5: Modificación con Posicionamiento Aleatorio
	public static void modificarPrecioPorPosicion(String rutaFichero, int posicion, double nuevoPrecio) {
		File file = new File(rutaFichero);
		final int codigo = 10 * 2;
		final int precio = 8;
		final int tammnio_articulo = codigo + precio;

		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			raf.seek(posicion * tammnio_articulo);
			raf.writeDouble(nuevoPrecio);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// =========================================================================
	// III. SERIALIZACIÓN DE OBJETOS Y CONVERSIÓN JAXB
	// =========================================================================

	// Tarea 6: Almacenamiento y Añadido de Objetos
	public static void guardarSerializado(List<Articulo> articulos, String ruta) {
		File file = new File(ruta);
		FileOutputStream fos;
		ObjectOutputStream oos;
		if (!file.exists()) {

			try {
				file.createNewFile();
				fos = new FileOutputStream(file, true);
				oos = new ObjectOutputStream(fos);

				for (Articulo articulo : articulos) {
					oos.writeObject(articulo);
				}
				oos.close();

			} catch (IOException e) {
				// TODO: handle exception
			}

		} else {
			try {
				fos = new FileOutputStream(file, true);
				oos = new ObjectStreamAppender(fos);

				for (Articulo articulo : articulos) {
					oos.writeObject(articulo);
				}
				oos.close();

			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

	// Tarea 7: Verificación de Serialización
	public static void leerFicheroObjetos(String rutaFichero) {
		File file = new File(rutaFichero);

		try {
			FileInputStream fis = new FileInputStream(file);
			try (ObjectInputStream ois = new ObjectInputStream(fis)) {
				while (ois.readObject() != null) {

					Articulo articulo = (Articulo) ois.readObject();
					System.out.println(articulo.toString());
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Método auxiliar para Tarea 8 (reutiliza lógica de Tarea 7)
	private static List<Articulo> leerTodosLosObjetos(String rutaFichero) {

		File file = new File(rutaFichero);
		ArrayList<Articulo> lista = new ArrayList<Articulo>();

		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			for (int i = 0; i < lista.size(); i++) {
				Articulo articulo = (Articulo) ois.readObject();
				lista.add(articulo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;

	}

	// Tarea 8: Conversión Objeto Serializado a XML
	public static void convertirBinarioA_XML(String rutaBinario, String rutaXML) {
		ArrayList<Articulo> lista = (ArrayList<Articulo>) leerTodosLosObjetos(rutaBinario);
		File file = new File(rutaXML);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			JAXBContext contexto = JAXBContext.newInstance(Almacen.class, Articulo.class);
			Marshaller marshaller = contexto.createMarshaller();

			for (Articulo articulo : lista) {
				marshaller.marshal(articulo, file);
			}

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Tarea 9: Conversión XML a Reporte Final
	public static void generarResumen(String rutaXML, String rutaSalida) {
		double precio_promedio = 0.0;
		ArrayList<Articulo> lista = (ArrayList<Articulo>) leerTodosLosObjetos(rutaXML);
		File file = new File(rutaSalida);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			JAXBContext contexto = JAXBContext.newInstance(Almacen.class, Articulo.class);
			Unmarshaller unmarshal = contexto.createUnmarshaller();

			for (int i = 0; i < lista.size(); i++) {
				Articulo articulo = (Articulo) unmarshal.unmarshal(file);
			}

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}