package Ejercicio_repaso_XML.Repaso_XML;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Trabajador")
@XmlType(propOrder = { "nombre", "apellido", "edad", "dni" })
public class Trabajador {
	private String nombre;
	private String apellido;
	private int edad;
	private DNI dni;

	public Trabajador() {
	} // Constructor vacío necesario

	public Trabajador(String nombre, String apellido, int edad, DNI dni) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public DNI getDni() {
		return dni;
	}

	public void setDni(DNI dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Trabajador [getNombre()=" + getNombre() + ", getApellido()=" + getApellido() + ", getEdad()="
				+ getEdad() + ", getDni()=" + getDni() + "]";
	}

}
