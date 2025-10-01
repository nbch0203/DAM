package boletin1_Ejercicio2;

public class Gato extends Mascotas {
	private String color;
	private String peloLargo;

	public Gato(String nombre, int edad, String estado, String fecha_nacimiento, String color, String peloLargo) {
		super(nombre, edad, estado, fecha_nacimiento);
		this.color = color;
		this.peloLargo = peloLargo;
	}

	@Override
	public String muestra() {
		return "Gato [getColor()=" + getColor() + ", getPeloLargo()=" + getPeloLargo() + ", getNombre()=" + getNombre()
				+ ", getEdad()=" + getEdad() + ", getEstado()=" + getEstado() + ", getFecha_nacimiento()="
				+ getFecha_nacimiento() + ", getClass()=" + getClass();
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPeloLargo() {
		return peloLargo;
	}

	public void setPeloLargo(String peloLargo) {
		this.peloLargo = peloLargo;
	}

	

	@Override
	public void cumpleaños() {
		// TODO Auto-generated method stub

	}

	@Override
	public void morir() {
		// TODO Auto-generated method stub

	}

	@Override
	public void habla() {
		// TODO Auto-generated method stub

	}

}
