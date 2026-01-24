package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorPedidos {
	private Pedido pedido;
	private static File file = new File("pedido.txt");
	
	

	public static File getFile() {
		return file;
	}


	public static void setFile(File file) {
		GestorPedidos.file = file;
	}


	public Pedido getPedido() {
		return pedido;
	}
	

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public void Guardar(Pedido pedido) throws IOException {
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bf = new BufferedWriter(fw);
		if (file.exists()) {
			fw.write(pedido.toString() + "\n");

			bf.close();
			fw.close();

		} else {
			file.createNewFile();
			bf.write(pedido.toString() + "\n");
			fw.close();
			bf.close();
		}

	}

	public String Buscar(String nombre) throws FileNotFoundException, IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String aux = "";
		String linea;
		while ((linea = br.readLine()) != null) {
			if (linea.contains(nombre)) {
				aux = linea;
				break;
			}

		}
		fr.close();
		br.close();
		System.out.println("-------------------");
		return linea;
	}

	public String mostrarTodo() throws FileNotFoundException, IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String aux = "";
		String linea;

		while ((linea = br.readLine()) != null) {
			aux += linea + "\n"; // Agrega la línea leída y un salto de línea
		}

		fr.close();
		br.close();

		// Elimina el último salto de línea si no se desea
		if (!aux.isEmpty()) {
			aux = aux.substring(0, aux.length() - 1);
		}

		return aux;
	}

	public String leer(String texto) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String aux = "";
		String linea;

		while ((linea = br.readLine()) != null) {
			if (linea.contains(texto)) {
				aux += linea + "\n"; // Agrega la línea leída y un salto de línea
			}

		}

		fr.close();
		br.close();

		// Elimina el último salto de línea si no se desea
		if (!aux.isEmpty()) {
			aux = aux.substring(0, aux.length() - 1);
		}

		return aux;

	}

	public void limpiarTxt() throws IOException {
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
	}

}
