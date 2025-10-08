package boletin_repaso_Ejercicio10;

public class Suites extends Habitaciones implements Reservable,Facturable{
	private int tamaño_metros;

	public Suites(String codigo, String nombre_huesped, String fecha_check_in, String fecha_check_out,
			int tamaño_metros) {
		super(codigo, nombre_huesped, fecha_check_in, fecha_check_out);
		this.tamaño_metros = tamaño_metros;
	}

	public int getTamaño_metros() {
		return tamaño_metros;
	}

	public void setTamaño_metros(int tamaño_metros) {
		this.tamaño_metros = tamaño_metros;
	}

	@Override
	public void generarFactura() {
		
	}

	@Override
	public void reservarse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelarse() {
		// TODO Auto-generated method stub
		
	}
	
	

}
