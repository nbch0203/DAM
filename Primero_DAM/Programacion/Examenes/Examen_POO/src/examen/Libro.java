package examen;

public class Libro {
//	Atributos de la clase libro

	private String titulo;
	private String autor;
	private int paginas;
	static int contador = 0;

//	Constructor del objeto libro

	public Libro(String titulo, String autor) {
		this.titulo = titulo;
		this.autor = autor;

	}

//	Segundo constructor del objeto libro añadiendo las paginas del libro

	public Libro(String titulo,String autor,int paginas) {
		this(titulo,autor);
		this.paginas=paginas;
	}

//	Getters y setters de los atributos

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

//	Metodo para saber la informacion de los libros

	public void consultarLibro() {
		System.out.println("Datos del libro 1: " + getTitulo() + " tiene un total de : " + getPaginas() + " cuyo autor es: " + getAutor());
	}

//	Metodo para prestar un libro y mostrar cual en caso de si se ha realizado el prestamo o no
	public boolean prestarLibro() {
		if (contador < Biblioteca.getMax_libros()) {
			contador++;
			System.out.println("Libro prestado: " + getTitulo());
			System.out.println("Total de libros prestados actualmente: " + contador);
			return true;
		} else {
			System.out.println(
					"No se pudo prestar el libro: " + getTitulo() + ". Ya está prestado o se alcanzó el límite.");
			return false;
		}
	}

//	Metodo para prestamo de libro con un mensaje de prioridad
	public boolean prestarLibro(String mensaje) {
		if (prestarLibro()) {
			System.out.println(mensaje);
			return true;
		}
		return false;

	}

//	Método para devolver un libro mostrando el titulo y la cantidad de libros prestado ahora mismo	
	public void devolverLibro() {
		contador--;
		System.out.println("Libro devuelto: " + getTitulo());
		System.out.println("Total de libros prestados actualmente: " + contador);
	}

}
