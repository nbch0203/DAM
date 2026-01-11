package Parte2Ejercicio8;

public class Ejercicio8Class {
	String nombre;
	String NIF;
	String clase;
	boolean caterin;
	boolean bano;
	int maletas;
	String asiento;
	double precio;

	public Ejercicio8Class(String nombre, String NIF) {
		this.nombre = nombre;
		this.NIF = NIF;
	}

	public Ejercicio8Class(String nombre, String NIF, String clase) {
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

	public void setCaterin(boolean caterin) {
		this.caterin = caterin;
	}

	public boolean getCaterin() {
		return this.caterin;
	}

	public void tipoClase() {
		if (clase.equalsIgnoreCase("turista")) {
			precio += 200;
			if (getBano()) {
				precio += 10;
			}
			if (getCaterin()) {
				precio += 20;
			}
			if (getMaletas() < 1) {
				setMaletas(1);
			}
			if (getMaletas() > 1) {
				precio += 50;
			}
			System.out.println("Nombre= " + nombre + " NIF= " + NIF + " clase= " + clase + " caterin= " + caterin
					+ " baño= " + bano + " maletas= " + maletas + " asiento= " + asiento);
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Precio total de = " + precio);
			System.out.println("");
		}
		if (clase.equalsIgnoreCase("VIP")) {
			precio += 200;
			setBano(true);
			setCaterin(true);
			if (getMaletas() < 1) {
				setMaletas(1);
			}
			System.out.println("Nombre= " + nombre + " NIF= " + NIF + " clase= " + clase + " caterin= " + caterin
					+ " baño= " + bano + " maletas= " + maletas + " asiento= " + asiento);
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Precio total de = " + precio);
			System.out.println("");
		}
		if (clase.equalsIgnoreCase("Business")) {
			precio += 200;
				setBano(true);
				setCaterin(true);
			if (getMaletas() < 1) {
				setMaletas(3);
			}
			if (getMaletas() > 3) {
				precio += 50;
			}
			System.out.println("Nombre= " + nombre + " NIF= " + NIF + " clase= " + clase + " caterin= " + caterin
					+ " baño= " + bano + " maletas= " + maletas + " asiento= " + asiento);
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Precio total de = " + precio);
			System.out.println("");
		}
	}

}
