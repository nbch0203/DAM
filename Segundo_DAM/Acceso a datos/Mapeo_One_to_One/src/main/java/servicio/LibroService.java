package servicio;

import dao.LibroDAOImpl;
import modelos.Libro;

public class LibroService {
	private final LibroDAOImpl libroDAOImpl;

	public LibroService(LibroDAOImpl libroDAOImpl) {
		this.libroDAOImpl = libroDAOImpl;
	}

	private void verificar(Libro l) throws IllegalAccessException {
		if (l.getTitulo().trim().isEmpty() || l.getTitulo() == null) {
			throw new IllegalArgumentException("El titulo no puede esta vacio");
		}

		if (l.getAutor().trim().isEmpty() || l.getAutor() == null) {
			throw new IllegalAccessException("El autor no puede estar vacio");
		}
	}

	public void registrarLibro(Libro l) throws IllegalAccessException {
		verificar(l);
		libroDAOImpl.creacion(l);

	}

	public void modificarLibro(Libro l) throws IllegalAccessException {
		verificar(l);
		libroDAOImpl.actualizar(l);

	}

	public Libro obtenerLibro(Long id) throws Exception {
		if (id < 0) {
			System.out.println("No existe ese id");
			throw new Exception();
		}
		return libroDAOImpl.buscarPorId(id);
	}

}
