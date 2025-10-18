package practica;

//CLASE PADRE que implementa las dos interfaces creadas
public abstract class Producto implements Vendible,Almacenable{
	
	private String codigo;
	private String nombre;
	private double precio;
	private int stock;
	//CONSTRUCTOR padre
	public Producto(String codigo, String nombre, double precio, int cantidada) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = cantidada;
	}
	//GETTERS Y SETTERS
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int cantidada) {
		this.stock = cantidada;
	}
	//TO STRING usado en las clases hijas para mostrar informacion
	@Override
	public String toString() {
		return "codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock;
	}
	//Metodos abstractos que heredaran de forma obligatoria las clases hijas
	public abstract void calcularDescuento();
	public abstract void mostrarInfo();
	
	//Metodo vender heredado obligatoriamente desde la INTERFAZ Vendible
	public void vender(int cantidad) {
		if(this.stock>=cantidad) {
			this.stock-=cantidad;
		System.out.println("La venta se ha realizado con exito. Stockactual="+getStock());
		System.out.println("------------------------------------------");
		}
		else {
		System.out.println("No hay stock suficiente para realizar la venta. Intoduzca otra cantidad");
		System.out.println("------------------------------------------");
		}
	}
	//Metodo vender heredado obligatoriamente desde la INTERFAZ Almacenable
	public void reabastecer(int cantidad) {
		this.stock+=cantidad;
		System.out.println("El reabastecimiento se ha realizado con exito. Stockactual="+getStock());
		System.out.println("------------------------------------------");
	}
	
	
}
