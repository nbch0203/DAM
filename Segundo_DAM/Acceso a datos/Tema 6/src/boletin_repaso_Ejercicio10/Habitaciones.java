package boletin_repaso_Ejercicio10;

public abstract class Habitaciones {
	private String codigo;
	private String nombre_huesped;
	private String fecha_check_in;
	private String fecha_check_out;
	
	public Habitaciones(String codigo, String nombre_huesped, String fecha_check_in, String fecha_check_out) {
		this.codigo = codigo;
		this.nombre_huesped = nombre_huesped;
		this.fecha_check_in = fecha_check_in;
		this.fecha_check_out = fecha_check_out;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre_huesped() {
		return nombre_huesped;
	}

	public void setNombre_huesped(String nombre_huesped) {
		this.nombre_huesped = nombre_huesped;
	}

	public String getFecha_check_in() {
		return fecha_check_in;
	}

	public void setFecha_check_in(String fecha_check_in) {
		this.fecha_check_in = fecha_check_in;
	}

	public String getFecha_check_out() {
		return fecha_check_out;
	}

	public void setFecha_check_out(String fecha_check_out) {
		this.fecha_check_out = fecha_check_out;
	}
	
	

}
