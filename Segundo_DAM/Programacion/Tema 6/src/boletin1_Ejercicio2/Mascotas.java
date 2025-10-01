package boletin1_Ejercicio2;

public abstract class Mascotas {
	private String nombre;
	private int edad;
	private String estado;
	private String fecha_nacimiento;
	
	
	public Mascotas(String nombre, int edad, String estado, String fecha_nacimiento) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.estado = estado;
		this.fecha_nacimiento = fecha_nacimiento;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}


	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	
	abstract public String muestra();
	
	abstract public void cumpleaños();
	
	abstract public void morir();
	
	abstract public void habla();
	
	
}
