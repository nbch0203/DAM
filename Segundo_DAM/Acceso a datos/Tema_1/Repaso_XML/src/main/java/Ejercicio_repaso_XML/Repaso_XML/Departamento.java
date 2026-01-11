package Ejercicio_repaso_XML.Repaso_XML;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement

@XmlType(propOrder = { "nombre", "presupuesto", "piso", "trabajadores" })
public class Departamento {
	private String nombre;
	private double presupuesto;
	private int piso;

	@XmlElementWrapper(name = "Trabajadores")
	@XmlElement(name = "Trabajador")
	private List<Trabajador> trabajadores;

	public Departamento() {
	} // Añade esto

	public Departamento(String nombre, double presupuesto, int piso, List<Trabajador> trabajador) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.piso = piso;
		this.trabajadores = trabajador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public List<Trabajador> getTrabajado() {
		return trabajadores;
	}

	public void setTrabajador(List<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}
}