package practica;

//Clase hija de Producto
public class Electronico extends Producto {
	private int garantia;

	// Constructor que implementa el constructor de la superclase
	public Electronico(String codigo, String nombre, double precio, int cantidada, int garantia) {
		super(codigo, nombre, precio, cantidada);
		this.garantia = garantia;
	}

	// GETTERS Y SETTER
	public int getGarantia() {
		return garantia;
	}

	public void setGarantia(int garantia) {
		this.garantia = garantia;
	}

	// Metodo heredado desde la super clase para calcular el descuento !!Metodo
	// obligatorio impuesta por la abstraccion
	@Override
	public void calcularDescuento() {
		if (this.garantia > 12) {
			double aux = 0;
			aux = getPrecio() * 0.1;
			setPrecio(getPrecio() - aux);
			System.out.println("El descuento ha sido aplicado porque la garantia es de " + getGarantia());
		} else {
			System.out.println("No se ha aplicado el descuento porque la garantia es de " + getGarantia());
		}
	}

	// Metodo heredado desde la super clase para mostrar la informacion !!Metodo
	// obligatorio impuesta por la abstraccion
	@Override
	public void mostrarInfo() {
		System.out.println(super.toString() + ", garantia=" + getGarantia());
		System.out.println("----------------------------------");

	}

}
