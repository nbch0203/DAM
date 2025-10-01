package boletin3_Ficheros_Ejercicio2;

import java.io.Serializable;

public class Persona implements Serializable {
	private String nif;
	private String nombre;
	private int edad;

	public Persona(String nif, String nombre, int edad) {
		this.nif = nif;
		this.nombre = nombre;
		this.edad = edad;
	}

	public Persona() {
		this.nombre = null;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Persona [nif=" + nif + ", nombre=" + nombre + ", edad=" + edad + "]";
	}

}
