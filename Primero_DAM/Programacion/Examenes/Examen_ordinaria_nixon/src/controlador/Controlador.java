package controlador;

import java.awt.dnd.DropTargetEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import interfaz.Interfaz;
import modelo.Usuario;

public class Controlador {
	private Usuario modelo;
	private Interfaz vista;

	public Controlador(Usuario modelo, Interfaz vista) {
		this.modelo = modelo;
		this.vista = vista;
		iniciar();

	}

	public Usuario getModelo() {
		return modelo;
	}

	public void setModelo(Usuario modelo) {
		this.modelo = modelo;
	}

	public Interfaz getVista() {
		return vista;
	}

	public void setVista(Interfaz vista) {
		this.vista = vista;
	}

	public void iniciar() {
		getVista().getRegistrar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					getModelo().guardarTxT(conseguir_datos());
					getModelo().guardarObj(conseguir_datos());

					getVista().showmessage("Usuario registrado con el rol");

				} catch (IOException e1) {
					// TODO Auto-generated catch block
				}
			}
		});

		getVista().getAcceder().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (getModelo().leerTxt(conseguir_datos())) {

						getVista().showmessage("Acceso validado");

					} else {
						getVista().showmessage("No coincide usuario y/o contraseña");
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		getVista().getContar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int profesor = 0;
				int alumno = 0;
				try {
					for (String usu : getModelo().leeUsuarios()) {
						if (usu.contains("Profesor")) {
							profesor++;
						}
						if (usu.contains("Alumno")) {
							alumno++;

						}
					}
					getVista().getTextArea().setText("Profesores: " + profesor + "\n" + "Alumnos: " + alumno);

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

	public Usuario conseguir_datos() {
		Usuario usuario = null;
		String user = getVista().getUsuario().getText();
		String contrasena = getVista().getContrasena().getText();
		String tipo = "";
		if (getVista().getProfesor().isSelected()) {
			tipo = "Profesor";
		} else {
			tipo = "Alumno";
		}
		usuario = new Usuario(user, contrasena, tipo);
		return usuario;

	}
}
