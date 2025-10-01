package boletin_repaso_Ejercicio10;

public class Estandar extends Habitaciones implements Reservable,Facturable{
	private int numero_camas;
	private double precio;

	public Estandar(String codigo, String nombre_huesped, String fecha_check_in, String fecha_check_out,
			int numero_camas) {
		super(codigo, nombre_huesped, fecha_check_in, fecha_check_out);
		this.numero_camas = numero_camas;
	}

	public int getNumero_camas() {
		return numero_camas;
	}

	public void setNumero_camas(int numero_camas) {
		this.numero_camas = numero_camas;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public void generarFactura() {
		double total;
		
		total=precio*3;
		System.out.println("El precio de la habitacion serian : "+total);
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
