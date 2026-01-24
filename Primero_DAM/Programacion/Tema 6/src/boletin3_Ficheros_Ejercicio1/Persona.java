package boletin3_Ficheros_Ejercicio1;

import java.io.Serializable;

public class Persona implements Serializable {

	private String nif;
	private String Nombre;
	private int Edad;

	public Persona(String nif, String nombre, int edad) {
		this.nif = nif;
		Nombre = nombre;
		Edad = edad;
	}

	public Persona() {
		this.Nombre = null;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public int getEdad() {
		return Edad;
	}

	public void setEdad(int edad) {
		Edad = edad;
	}

	@Override
	public String toString() {
		return "Persona [nif=" + nif + ", Nombre=" + Nombre + ", Edad=" + Edad + "]";
	}

}
