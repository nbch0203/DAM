package modelo;

import java.io.Serializable;

public class Persona implements Serializable {
	private String nombre, apellido, dni, genero;
	private boolean mayor_edad;

	public Persona(String nombre, String apellido, String dni, String genero, boolean mayor_edad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.genero = genero;
		this.mayor_edad = mayor_edad;
	}

	public Persona() {
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public boolean isMayor_edad() {
		return mayor_edad;
	}

	public void setMayor_edad(boolean mayor_edad) {
		this.mayor_edad = mayor_edad;
	}

	@Override
	public String toString() {
		return "Persona [getNombre()=" + getNombre() + ", getApellido()=" + getApellido() + ", getDni()=" + getDni()
				+ ", getGenero()=" + getGenero() + ", isMayor_edad()=" + isMayor_edad() + "]";
	}

}
