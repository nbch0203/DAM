package model;

import java.io.Serializable;

public class Cliente implements Serializable{
	private String nombre;
	private String correo;

	public Cliente(String nombre, String correo) {
		this.nombre = nombre;
		this.correo = correo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		return "Nombre: " + getNombre() + "\n" + "Correo=" + getCorreo();
	}

}
