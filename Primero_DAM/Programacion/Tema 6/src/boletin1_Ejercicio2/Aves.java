package boletin1_Ejercicio2;

public abstract class Aves extends Mascotas {
	String pico;
	String vuela;

	public Aves(String nombre, int edad, String estado, String fecha_nacimiento, String pico, String vuela) {
		super(nombre, edad, estado, fecha_nacimiento);
		this.pico = pico;
		this.vuela = vuela;
	}

	abstract public void volar();

}
