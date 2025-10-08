package Parte2Ejercicio7;

public class Ejercicio7Class {
	String nombre;
	String NIF;
	String clase;
	boolean caterin;
	boolean bano;
	int maletas;
	String asiento;

	public Ejercicio7Class(String nombre, String NIF) {
		this.nombre = nombre;
		this.NIF = NIF;
	}

	public Ejercicio7Class(String nombre, String NIF, String clase) {
		this(nombre, NIF);
		this.clase = clase;
	}

	public void setMaletas(int maletas) {
		this.maletas = maletas;
	}

	public int getMaletas() {
		return this.maletas;
	}

	public void setfacturar(String asiento) {
		this.asiento = asiento;
	}

	public String getfacturar() {
		return this.asiento;
	}

	void setClase(String clase) {
		this.clase = clase;
	}

	public String getClase() {
		return this.clase;
	}

	public void setBano(boolean bano) {
		this.bano = bano;
	}

	public boolean getBano() {
		return this.bano;
	}

	@Override
	public String toString() {
		return "Nombre=" + nombre + ", NIF=" + NIF + ", clase=" + clase + ", caterin=" + caterin + ", bano=" + bano
				+ ", maletas=" + maletas + ", asiento=" + asiento;
	}

}
