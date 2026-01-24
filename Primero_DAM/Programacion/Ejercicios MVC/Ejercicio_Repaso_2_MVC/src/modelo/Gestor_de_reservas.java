package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gestor_de_reservas {
	private Reservas reserva;
	private static File file = new File("reservas.txt");

	public Reservas getReserva() {
		return reserva;
	}

	public void setReserva(Reservas reserva) {
		this.reserva = reserva;
	}

	public void reservar(Reservas reserva) throws IOException {
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bf = new BufferedWriter(fw);
		if (file.exists()) {
			fw.write(reserva.toString() + "\n");

			bf.close();
			fw.close();

		} else {
			file.createNewFile();
			bf.write(reserva.toString() + "\n");
			fw.close();
			bf.close();
		}
	}

	public void cancelar(String nombre) throws IOException {
	    if (!file.exists()) {
	        file.createNewFile();
	    }

	    // Almacenamos las líneas que NO contienen el nombre a eliminar
	    List<String> lineasRestantes = new ArrayList<>();

	    // 1. Leemos el archivo y filtramos las líneas
	    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            if (!linea.contains(nombre)) {
	                lineasRestantes.add(linea);
	            }
	        }
	    }

	    // 2. Sobrescribimos el archivo con las líneas restantes
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
	        for (String linea : lineasRestantes) {
	            bw.write(linea);
	            bw.newLine(); // Añade salto de línea
	        }
	    }

	}
	public void comprobar(String nombre) throws IOException {
		 if (!file.exists()) {
		        file.createNewFile();
		    }

		    // Almacenamos las líneas que NO contienen el nombre a eliminar
		    List<String> lineasRestantes = new ArrayList<>();

		    // 1. Leemos el archivo y filtramos las líneas
		    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		        String linea;
		        while ((linea = br.readLine()) != null) {
		            if (!linea.contains(nombre)) {
		                lineasRestantes.add(linea);
		            }
		        }
		    }
	}
}
