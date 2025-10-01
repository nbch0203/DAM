package Parte3Ejercicio1;

public class Ejercicio1Class {
	private String nombre, telefono;
	private static int numempleados;

	public Ejercicio1Class(String nombre,String telefono) {
		this.nombre=nombre;
		this.telefono=telefono;
		numempleados++;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public static int getNumempleados() {
		return numempleados;
	}

	public static void setNumempleados(int numempleados) {
		Ejercicio1Class.numempleados = numempleados;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
