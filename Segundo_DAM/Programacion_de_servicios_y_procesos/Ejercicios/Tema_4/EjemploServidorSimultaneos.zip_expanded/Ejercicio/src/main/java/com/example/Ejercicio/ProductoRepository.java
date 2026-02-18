package com.example.Ejercicio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	// Esta interfaz ya tiene métodos como save(), findAll(), deleteById(), etc. 
	// Los heredamos y así los podemos utilizar. Hay que indicar sobre qué objetos se van a aplicar y con con clave.
	// Por ello indicamos Producto, Long (JpaRepository<Entidad, ID>)

	// Al añadir esto, Spring implementa automáticamente:
	// SELECT * FROM PRODUCTO WHERE UPPER(MARCA) = UPPER(marca)
	List<Producto> findByMarcaIgnoreCase(String marca);

	// Devuelve un long indicando cuántos registros borró
	long deleteByMarcaIgnoreCase(String marca);
}