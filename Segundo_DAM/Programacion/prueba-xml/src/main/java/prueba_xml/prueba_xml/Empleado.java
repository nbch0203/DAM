package prueba_xml.prueba_xml;

import com.sun.xml.txw2.annotation.XmlElement;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "empleado")
@XmlType(propOrder = { "dni", "nombre", "edad", "cargo" })
public class Empleado {
	private String dni;
	private String nombre;
	private int edad;
	private Cargo cargo;

	// Constructores
	public Empleado() {
	}

	public Empleado(String dni, String nombre, int edad, Cargo cargo) {
		this.dni = dni;
		this.nombre = nombre;
		this.edad = edad;
		this.cargo = cargo;
	}

	public Empleado(String dni, String nombre, int edad) {
		this.dni = dni;
		this.nombre = nombre;
		this.edad = edad;
	}

	// Getters y Setters con anotaciones JAXB
	@XmlElement

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@XmlElement
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElement
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@XmlElement
	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		if (cargo != null) {
			return nombre + " con DNI " + dni + " y " + edad + " años - Cargo: " + cargo.getValor();
		}
		return nombre + " con DNI " + dni + " y " + edad + " años";
	}
}