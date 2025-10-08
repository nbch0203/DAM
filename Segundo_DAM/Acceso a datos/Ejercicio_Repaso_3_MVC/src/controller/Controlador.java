package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Cliente;
import model.Gesto_clientes;
import view.Interfaz;

public class Controlador {
	private Gesto_clientes modelo;
	private Interfaz vista;

	public Controlador(Gesto_clientes modelo, Interfaz vista) {
		this.modelo = modelo;
		this.vista = vista;
		Iniciarboton();
	}

	public Gesto_clientes getModelo() {
		return modelo;
	}

	public void setModelo(Gesto_clientes modelo) {
		this.modelo = modelo;
	}

	public Interfaz getVista() {
		return vista;
	}

	public void setVista(Interfaz vista) {
		this.vista = vista;
	}

	public void Iniciarboton() {
		getVista().getBoton_enviar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				guardarTxt();

			}
		});

		getVista().getBoton_objetos().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				guardarObjt();
			}
		});

		getVista().getBoton_mostrar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mostrarTodo();
			}
		});
	}

	public void guardarTxt() {

		Cliente cl = new Cliente(getVista().getTextField_nombre(), getVista().getTextField_correo());
		try {
			getModelo().guardarTxt(cl.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void guardarObjt() {
		Cliente cl = new Cliente(getVista().getTextField_nombre(), getVista().getTextField_correo());

		try {

			FileOutputStream fos = new FileOutputStream("fichero.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(cl);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mostrarTodo() {
		FileInputStream fis;
		try {
			fis = new FileInputStream("fichero.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object aux;
			while (true) {
				aux = ois.readObject();
				getVista().setTextArea(aux.toString());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	public void leerTxt() {
//		Object line;
//		try (FileReader reader = new FileReader(); BufferedReader  = new BufferedReader(reader)) {
//			
//			while ((line = buffer.readLine()) != null) {
//			}
//		}
//
//	}
}
