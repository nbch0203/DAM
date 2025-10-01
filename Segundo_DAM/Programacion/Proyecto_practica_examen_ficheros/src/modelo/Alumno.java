package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Alumno implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static File file_txt = new File("fichero.txt");
	private static File file_obj = new File("fichero.dat");
	private String nombre;
	private int edad;
	private Double nota;

	public Alumno(String nombre, int edad, Double nota) {
		this.nombre = nombre;
		this.edad = edad;
		this.setNota(nota);
	}

	public Alumno() {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Alumno [ getNombre()=" + getNombre() + ", getEdad()=" + getEdad() + ", getNotas()=" + getNota() + "]";
	}

	public static File getFile_obj() {
		return file_obj;
	}

	public static void setFile_obj(File file_obj) {
		Alumno.file_obj = file_obj;
	}

	public static File getFile_txt() {
		return file_txt;
	}

	public static void setFile_txt(File file_txt) {
		Alumno.file_txt = file_txt;
	}

	public void guardarTxt(String string) throws IOException {
		FileWriter fw = null;
		BufferedWriter bw = null;

		if (!file_txt.exists()) {
			fw = new FileWriter(file_txt);
			bw = new BufferedWriter(fw);
		} else {
			fw = new FileWriter(file_txt, true);
			bw = new BufferedWriter(fw);
		}

		bw.write(string + "\n");
		bw.close();
		fw.close();
		
	}

	public void guardarObj(Alumno alumno) throws IOException {
		FileOutputStream fo = null;
		ObjectOutputStream os = null;

		if (!file_obj.exists()) {
			fo = new FileOutputStream(file_obj);
			os = new ObjectOutputStream(fo);
		} else {
			fo = new FileOutputStream(file_obj, true);
			os = new MiObjectOutputStream(fo);
		}

		os.writeObject(alumno);
		os.close();

	}

	public ArrayList<String> getalumnosTxt() throws IOException, ClassNotFoundException { // Lee el fichero de text y
		// devuelve un array de
		// string con todo su
		// contenido
		ArrayList<String> listaalumnos = new ArrayList<String>();

		if (!file_txt.exists()) { // Evitamos que lance un error si el archivo no existe
			file_txt.createNewFile();
		}

		String line;
		try (FileReader reader = new FileReader(file_txt); BufferedReader buffer = new BufferedReader(reader)) {
			while ((line = buffer.readLine()) != null) {
				listaalumnos.add(line);
			}
		}
		return listaalumnos;
	}

	public ArrayList<String> getAlumnosDat() throws IOException, ClassNotFoundException { // Lee el fichero de objetos y
																							// devuelve un array de
																							// string con todo su
																							// contenido
		ArrayList<String> lista_alumnos = new ArrayList<String>();

		if (!file_obj.exists()) { // Evitamos que lance un error si el archivo no existe
			file_obj.createNewFile();
		}

		try (FileInputStream fi = new FileInputStream(file_obj); ObjectInputStream datos = new ObjectInputStream(fi)) {
			while (true) {
				Object aux = datos.readObject();
				lista_alumnos.add(aux.toString());
			}
		} catch (EOFException e) {
		}

		return lista_alumnos;
	}

	public String getAlumnosTxt() throws FileNotFoundException, IOException, ClassNotFoundException {
		String alumnos = "";
		for (String linea : getalumnosTxt()) {
			alumnos += linea + "\n";
		}
		return alumnos;
	}

	public String mostrarAlumnosDat() throws ClassNotFoundException, IOException {
		String Alumnos = "";

		for (String linea : getAlumnosDat()) {
			Alumnos += linea + "\n";
		}
		return Alumnos;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

}
