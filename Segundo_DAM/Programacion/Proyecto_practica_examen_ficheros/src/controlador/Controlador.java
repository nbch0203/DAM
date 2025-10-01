package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import interfaz.Interfaz;
import modelo.Alumno;
import modelo.MiObjectOutputStream;

public class Controlador {
	private Alumno modelo;
	private Interfaz vista;

	public Controlador(Alumno modelo, Interfaz vista) {
		this.modelo = modelo;
		this.vista = vista;
		iniciarBotones();
	}

	public Alumno getModelo() {
		return modelo;
	}

	public void setModelo(Alumno modelo) {
		this.modelo = modelo;
	}

	public Interfaz getVista() {
		return vista;
	}

	public void setVista(Interfaz vista) {
		this.vista = vista;
	}

//	public void showMessage(String txt) { // Metodo para mostrar avisos
//		JOptionPane.showMessageDialog(this, txt);
//	}

	public void iniciarBotones() {
		getVista().getGuardartxt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {
					getModelo().guardarTxt(getalumno().toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		getVista().getLeertxt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					getVista().getTextArea().setText(getModelo().getAlumnosTxt());
					getVista().showMessage("Mostrando textarea");
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		getVista().getGuardarObj().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					getModelo().guardarObj(getalumno());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		getVista().getLeerObjt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					getVista().getTextArea().setText(getModelo().mostrarAlumnosDat());
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

	public Alumno getalumno() {
		String nombre = getVista().getNombre().getText();
		int edad = Integer.parseInt(getVista().getEdad().getText());
		Double nota = Double.parseDouble(getVista().getNota().getText());
		Alumno alumno = new Alumno(nombre, edad, nota);
		return alumno;

	}
	
	
	
//	public void guardarTxt(String string) throws IOException {
//		FileWriter fw = new FileWriter(file_txt);
//		BufferedWriter bw = new BufferedWriter(fw);
//		if (!file_txt.exists()) {
//			file_txt.createNewFile();
//		}
//		bw.write(string + "\n");
//		bw.close();
//		fw.close();
//	}
//
//	public void guardarObj(Alumno alumno) throws IOException {
//		FileOutputStream fo = null;
//		ObjectOutputStream os = null;
//
//		if (!file_obj.exists()) {
//			fo = new FileOutputStream(file_obj);
//			os = new ObjectOutputStream(fo);
//		} else {
//			fo = new FileOutputStream(file_obj, true);
//			os = new MiObjectOutputStream(fo);
//		}
//
//		os.writeObject(alumno);
//		os.close();
//
//	}
//
//	public ArrayList<String> getalumnosTxt() throws IOException, ClassNotFoundException { // Lee el fichero de text y
//		// devuelve un array de
//		// string con todo su
//		// contenido
//		ArrayList<String> listaalumnos = new ArrayList<String>();
//
//		if (!file_txt.exists()) { // Evitamos que lance un error si el archivo no existe
//			file_txt.createNewFile();
//		}
//
//		String line;
//		try (FileReader reader = new FileReader(file_txt); BufferedReader buffer = new BufferedReader(reader)) {
//			while ((line = buffer.readLine()) != null) {
//				listaalumnos.add(line);
//			}
//		}
//		return listaalumnos;
//	}
//
//	public ArrayList<String> getAlumnosDat() throws IOException, ClassNotFoundException { // Lee el fichero de objetos y
//																							// devuelve un array de
//																							// string con todo su
//																							// contenido
//		ArrayList<String> lista_alumnos = new ArrayList<String>();
//
//		if (!file_obj.exists()) { // Evitamos que lance un error si el archivo no existe
//			file_obj.createNewFile();
//		}
//
//		try (FileInputStream fi = new FileInputStream(file_obj); ObjectInputStream datos = new ObjectInputStream(fi)) {
//			while (true) {
//				Object aux = datos.readObject();
//				lista_alumnos.add(aux.toString());
//			}
//		} catch (EOFException e) {
//		}
//
//		return lista_alumnos;
//	}
//
//	public String getAlumnosTxt() throws FileNotFoundException, IOException, ClassNotFoundException {
//		String alumnos = "";
//		for (String linea : getalumnosTxt()) {
//			alumnos += linea + "\n";
//		}
//		return alumnos;
//	}
//
//	public String mostrarAlumnosDat() throws ClassNotFoundException, IOException {
//		String Alumnos = "";
//
//		for (String linea : getAlumnosDat()) {
//			Alumnos += linea + "\n";
//		}
//		return Alumnos;
//	}

}
