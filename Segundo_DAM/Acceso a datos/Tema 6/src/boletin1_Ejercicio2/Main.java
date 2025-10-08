package boletin1_Ejercicio2;

public class Main {

	public static void main(String[] args) {
		App ap = new App();
		ap.insertarAnimales();
		
		ap.mostrarAnimales();
		
		ap.mostrarAnimal("Codi");
		ap.eliminarAnimales("Codi");
		ap.vaciarInventario();

	}

}
