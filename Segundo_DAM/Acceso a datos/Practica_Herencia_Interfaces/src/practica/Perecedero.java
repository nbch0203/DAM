package practica;

//Clase hija de Producto
public class Perecedero extends Producto {
	private int dias;

	// Constructor que implementa el constructor de la superclase
	public Perecedero(String codigo, String nombre, double precio, int cantidada, int dias) {
		super(codigo, nombre, precio, cantidada);
		this.dias = dias;
	}

	// GETTERS Y SETTERS
	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	// Metodo heredado desde la super clase para calcular el descuento !!Metodo
	// obligatorio impuesta por la abstraccion
	@Override
	public void calcularDescuento() {
		if (getDias() <= 5) {
			double aux = 0;
			aux = getPrecio() * 0.3;
			setPrecio(getPrecio() - aux);
			System.out.println("El descuento ha sido aplicado porque los dias restantes son " + getDias());
		} else {
			System.out.println("El descuento no ha sido aplicado porque los dias restantes son " + getDias());
		}
	}

	// Metodo heredado desde la super clase para mostrar la informacion !!Metodo
	// obligatorio impuesta por la abstraccion
	@Override
	public void mostrarInfo() {
		System.out.println(super.toString() + ", diasParaVencer=" + getDias());
		System.out.println("----------------------------------");

	}

}
