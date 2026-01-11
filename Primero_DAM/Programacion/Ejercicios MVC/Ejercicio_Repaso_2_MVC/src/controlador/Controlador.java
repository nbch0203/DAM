package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;

import modelo.Gestor_de_reservas;
import modelo.Reservas;
import vista.Interfaz;

public class Controlador {
	private Gestor_de_reservas modelo;
	private Interfaz vista;

	public Controlador(Gestor_de_reservas modelo, Interfaz vista) {
		this.modelo = modelo;
		this.vista = vista;
		iniciartodo();
	}

	public Gestor_de_reservas getModelo() {
		return modelo;
	}

	public void setModelo(Gestor_de_reservas modelo) {
		this.modelo = modelo;
	}

	public Interfaz getVista() {
		return vista;
	}

	public void setVista(Interfaz vista) {
		this.vista = vista;
	}

	public void iniciartodo() {
		getVista().getReservar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservar();

			}
		});
		
		getVista().getCancelar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelar();
				
			}
		});
	}
	
	public void cancelar() {
		String nombre= getVista().getTextField();
		
		try {
			getModelo().cancelar(nombre);
			getVista().setTextArea("Cancelando la reserva ......");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void reservar() {

		Reservas rs = new Reservas();
		String nombre = getVista().getTextField();

		rs.setNombre(nombre);

		if (getVista().getCastellano().isSelected()) {
			rs.setIdioma("Castellano");
		} else {
			rs.setIdioma("Ingles");
		}
		if (getVista().getTarjeta().isSelected()) {
			rs.setMetodo_pago("Tarjeta");
		} else {
			rs.setMetodo_pago("Efectivo");
		}
		JComboBox<String> comboBox = getVista().getComboBox();
		String opcionseleccionada = (String) comboBox.getSelectedItem();

		rs.setTipo(opcionseleccionada);

		try {
			getModelo().reservar(rs);
			getVista().setTextArea("Guardando la reserva.....");
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
