package Parte2Ejercicio3;

public class Ejercicio3class {
	String nombre, procedencia;
	double kilos;
	double precio;
	double precio_por_kilo;

	static double beneficio;

	public Ejercicio3class(String nombre, String procedencia, double kilos, double precio, double precio_por_kilo) {
		this.nombre = nombre;
		this.procedencia = procedencia;
		this.kilos = kilos;
		this.precio = precio;
		this.precio_por_kilo = precio_por_kilo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getProcedencia() {
		return procedencia;
	}

	public double getKilos() {
		return kilos;
	}

	public double getPrecio() {
		return precio;
	}

	public double getPrecioKilo() {
		return precio_por_kilo;
	}

	public int rebajar(double rebaja) {
		int resultado = -1;
		if (this.precio < (this.precio_por_kilo - rebaja)) {
			precio_por_kilo -= rebaja;
			resultado = 0;

		}
		return resultado;

	}

	public double vender(int kilosvender) {
		double resultado = -1;
		if (kilos >= kilosvender) {
			kilos -= kilosvender;
			resultado = kilosvender * kilos;
			beneficio += resultado;

		}
		return resultado;
	}

	public String mismaprocedencia(String procedencia,String procedencia2) {
		if (procedencia.equals(procedencia2)) {
			System.out.println("Son del mismo pais");
		}
		return procedencia;
		
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + ", procedencia=" + procedencia + ", kilos=" + kilos + ", precio="
				+ precio + ", precio_por_kilo=" + precio_por_kilo;
	}
}