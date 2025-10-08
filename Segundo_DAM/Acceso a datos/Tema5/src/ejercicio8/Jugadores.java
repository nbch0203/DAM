package ejercicio8;

import java.util.Objects;

public class Jugadores {
	String nombre;
	int dorsal;
	public Jugadores(String nombre, int dorsal) {
		this.nombre = nombre;
		this.dorsal = dorsal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDorsal() {
		return dorsal;
	}
	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}
	@Override
	public String toString() {
		return "Jugadores [" + (nombre != null ? "nombre=" + nombre + ", " : "") + "dorsal=" + dorsal + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugadores other = (Jugadores) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
	
}
