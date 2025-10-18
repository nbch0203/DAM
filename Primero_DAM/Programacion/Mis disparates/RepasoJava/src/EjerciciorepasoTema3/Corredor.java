package EjerciciorepasoTema3;

public class Corredor {
	private String nombre;
	private double velocidad;

	public Corredor(String nombre, double velocidad) {
		this.nombre = nombre;
		this.velocidad = velocidad;
	}

	public Corredor(String nombre) {
		this.nombre = nombre;
		velocidad = 10.00;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public void mejorarVelocidad() {
		velocidad++;
	}

}
