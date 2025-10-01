package Parte3Ejercicio3;

import Parte3Ejercicio2.Ejercicio2Class;

public class Ejercicio3Class {
	private String nombre, telefono;
	private static int numempleados;

	public Ejercicio3Class(String nombre, String telefono) {
		this.setNombre(nombre);
		this.setTelefono(telefono);
		numempleados++;
	}

	public Ejercicio3Class(Ejercicio3Class otroempleado) {
		this(otroempleado.getNombre(), otroempleado.getTelefono());
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Ejercicio2Class) {
			Ejercicio2Class d = (Ejercicio2Class) obj;
			if (this.nombre.equals(d.getNombre()) && this.telefono.equals(getTelefono())) {
				return (true);
			}

		}
		return false;
	}

	public static int getNumempleados() {
		return numempleados;
	}

	public static void setNumempleados(int numempleados) {
		Ejercicio3Class.numempleados = numempleados;
	}

}
