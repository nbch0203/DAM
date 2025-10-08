package boletin1_Ejercicio2;

public class Loro extends Mascotas{
	private String origen;
	private String habla;
	public Loro(String nombre, int edad, String estado, String fecha_nacimiento, String origen, String habla) {
		super(nombre, edad, estado, fecha_nacimiento);
		this.origen = origen;
		this.habla = habla;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getHabla() {
		return habla;
	}
	public void setHabla(String habla) {
		this.habla = habla;
	}
	
	public String muestra() {
		return "Loro [getOrigen()=" + getOrigen() + ", getHabla()=" + getHabla() + ", getNombre()=" + getNombre()
				+ ", getEdad()=" + getEdad() + ", getEstado()=" + getEstado() + ", getFecha_nacimiento()="
				+ getFecha_nacimiento() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
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
