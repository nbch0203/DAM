package examen;

public class Biblioteca {
//	Cantidad de libros que puedo llegar a prestar
	private static int max_libros = 3;

//	Consulta de la cantidad de libros que tengo ahora mismo prestados
	public static int consultarTotalLibrosPrestados() {
		return Libro.contador;
	}

//	Settear la cantidad de libros maximos que puedo prestar
	public static void setMaximoLibrosPrestados(int max_libros) {
		Biblioteca.max_libros = max_libros;
	}

//	Consultar la cantidad de libros que puedo llegar a prestar	
	public static int getMax_libros() {
		return max_libros;
	}

}
