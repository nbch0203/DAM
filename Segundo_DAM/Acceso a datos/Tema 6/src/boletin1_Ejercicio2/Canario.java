package boletin1_Ejercicio2;

public class Canario extends Mascotas {
	private String color;
	private String canta;

	public Canario(String nombre, int edad, String estado, String fecha_nacimiento, String color, String canta) {
		super(nombre, edad, estado, fecha_nacimiento);
		this.color = color;
		this.canta = canta;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCanta() {
		return canta;
	}

	public void setCanta(String canta) {
		this.canta = canta;
	}

	@Override
	public String muestra() {
		return canta;
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
