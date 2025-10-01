package EjercicioMatricesObjetos;

public class Polvorones{
	private String sabor;
	private int cantidad;
	
	
	public Polvorones(String sabor, int cantidadad) {
		this.sabor = sabor;
		this.cantidad = cantidadad;
	}
	public String getSabor() {
		return sabor;
	}
	public void setSabor(String sabor) {
		this.sabor = sabor;
	}
	public int getCantidadad() {
		return cantidad;
	}
	public void setCantidadad(int cantidadad) {
		this.cantidad = cantidadad;
	}
	
	
}
