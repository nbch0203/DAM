package boletin1_Ejercicio2;

public class Perro extends Mascotas {
	private String raza;
	private int pulgas;

	public Perro(String nombre, int edad, String estado, String fecha_nacimiento, String raza, int pulgas) {
		super(nombre, edad, estado, fecha_nacimiento);
		this.raza = raza;
		this.pulgas = pulgas;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public int getPulgas() {
		return pulgas;
	}

	public void setPulgas(int pulgas) {
		this.pulgas = pulgas;
	}

	@Override
	public String muestra() {
		return raza;
		// TODO Auto-generated method stub

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
