package examen;

public class TestBiblioteca {

	public static void main(String[] args) {
//		Objetos creados a partir de la clase libro
		Libro lib1 = new Libro("Cien años de soledad", "Antonio");
		Libro lib2 = new Libro("El quijote", "Adrian",11);
		Libro lib3 = new Libro("Los 3 cerditos", "Filip");

//		Consulta de los libros prestados ahora mismo

		System.out.println("Cantidad de libros prestados actualmente: " + Biblioteca.consultarTotalLibrosPrestados());
		System.out.println("***********************************");
//		Prestar el libro 1 y saber cuantos libros he prestado

		lib1.prestarLibro();
		System.out.println("********************************************************");
//		Consultar los datos del libro 1
		lib1.consultarLibro();
		System.out.println("********************************************************");
		
//		Consultar los datos del libro 2
		lib2.consultarLibro();
		System.out.println("********************************************************");
	
//		Consultar los datos del libro 3
		lib3.consultarLibro();
		System.out.println("********************************************************");
	
//		Prestar el libro 2 con un mensaje de prioridad
		lib2.prestarLibro("Libro de alta prioridad");
		System.out.println("********************************************************");

//		Devolver el libro 2 
		lib2.devolverLibro();
		System.out.println("********************************************************");

//		Prestar el libro 2
		lib2.prestarLibro();
		System.out.println("***********************************");

//		Prestar el libro 3
		lib3.prestarLibro();
		System.out.println("***********************************");

//		Intento de prestar el libro 3 cuando ya esta prestado		
		lib3.prestarLibro();
		
//		Cambio del maximo de libros que puedes llegar a prestar
		Biblioteca.setMaximoLibrosPrestados(5);
		System.out.println("***********************************");
		
//		Volvemos a intentar prestar el libro 3 con el nuevo maximo de libros prestados
		lib3.prestarLibro();
	}

}
