package ejercicio_7;

import java.io.*;
import java.util.*;

class Utensilio implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int unidades;

	public Utensilio(String nombre, int unidades) {
		this.nombre = nombre;
		this.unidades = unidades;
	}

	public String getNombre() {
		return nombre;
	}

	public int getUnidades() {
		return unidades;
	}

	@Override
	public String toString() {
		return "Utensilio: " + nombre + " | Unidades: " + unidades;
	}
}
