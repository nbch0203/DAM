package Parte2Ejercicio1;

public class Ejercicio1Class {
	private String nombre;
	private int telefono;
	private static int numempleado;
	
	public Ejercicio1Class(String nombre,int telefono) {
		this.nombre=nombre;
		this.telefono=telefono;
		numempleado++;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public static int getnumepleado() {
		return numempleado;
	}
}

