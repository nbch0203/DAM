package modelo;

public class Reservas {
	private String nombre;
	private String tipo;
	private String metodo_pago;
	private String idioma;
	private double precio;

	public Reservas() {
	}

	public Reservas(String nombre, String tipo, String metodo_pago, String idioma, double precio) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.metodo_pago = metodo_pago;
		this.idioma = idioma;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMetodo_pago() {
		return metodo_pago;
	}

	public void setMetodo_pago(String metodo_pago) {
		this.metodo_pago = metodo_pago;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Reservas de : " + getNombre() + "  Servicio: " + getTipo() + " Forma de pago: " + getMetodo_pago()
				+ " Idioma: " + getIdioma() + " Precio=" + getPrecio();
	}

}
