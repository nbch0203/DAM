package dao;

import modelos.Libro;

public interface LibroDAO {

	void creacion(Libro libro);

	void actualizar(Libro libro);

	Libro buscarPorId(Long id);

}
