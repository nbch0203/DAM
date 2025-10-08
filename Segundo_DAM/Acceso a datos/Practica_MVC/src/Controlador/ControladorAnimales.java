package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelo.Animal;
import Modelo.GestorAnimales;
import Vista.interfaz;

public class ControladorAnimales {
	private Animal modelo;
	private interfaz vista;
	
	GestorAnimales ga= new GestorAnimales();
	
	public ControladorAnimales(Animal modelo, interfaz vista) {
		this.modelo = modelo;
		this.vista = vista;
	}
	
	public Animal getModelo() {
		return modelo;
	}
	public void setModelo(Animal modelo) {
		this.modelo = modelo;
	}
	public interfaz getVista() {
		return vista;
	}
	public void setVista(interfaz vista) {
		this.vista = vista;
	}
	
	public void guardarInformacion() {
		
	}
	
}

