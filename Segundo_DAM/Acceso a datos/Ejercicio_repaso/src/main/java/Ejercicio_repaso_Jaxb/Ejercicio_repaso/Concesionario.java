package Ejercicio_repaso_Jaxb.Ejercicio_repaso;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Concesionario")
@XmlType(propOrder = { "nombre", "vehiculos", "ubicacion" })
public class Concesionario {
	private ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

	private String nombre;
	private String ubicacion;

	public Concesionario() {
	}

	@XmlAttribute(name = "nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlAttribute(name = "ubicacion")
	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@XmlElement(name = "Vehiculo")
	public ArrayList<Vehiculo> getvehiculos() {
		return vehiculos;
	}

	public void setvehiculos(ArrayList<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

}
