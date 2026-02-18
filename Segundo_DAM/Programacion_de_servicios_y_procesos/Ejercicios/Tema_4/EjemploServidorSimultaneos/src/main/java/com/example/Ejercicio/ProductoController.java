package com.example.Ejercicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	//Cuando arranque la app, Spring Boot va a crear una "clase oculata" en memoria con lo que tiene la interfaz 
	//se le llama Proxy dinámico que se creo automáticamente. Es esa clase la que ejecuta los métodos CRUD heredados.
	@Autowired // Inyectamos el repositorio
	private ProductoRepository productoRepository;


	/*
	 * Puedo tener varios métodos para un mismo verbo HHTP.
	 * Para que Spring Boot sepa a qué método de Java llamar cuando llega una
	 * petición, se fija en dos cosas: 1. El verbo HTTP (GET, POST, PUT, DELETE). 
	 * 2. La ruta (URL) específica. 
	 * Por tanto, si el verbo es el mismo, la ruta debe ser diferente.
	 */
	

	// ===================================================================
    // 1. OPERACIONES INDIVIDUALES (POR ID -> PATH VARIABLE)
    // ===================================================================
    // El ID identifica un recurso único, por eso usamos PathVariable

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
    	
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto datos) {
        Optional<Producto> resultado = productoRepository.findById(id);
        if (resultado.isPresent()) {
            Producto p = resultado.get();
            if (datos.getPrecio() > 0) p.setPrecio(datos.getPrecio());
            if (datos.getStock() >= 0) p.setStock(datos.getStock());
            return ResponseEntity.ok(productoRepository.save(p));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return ResponseEntity.ok("Producto eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("No se encontró el producto.");
        }
    }
    
    // POST solo hay uno (Crear)
    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto nuevo) {
        return new ResponseEntity<>(productoRepository.save(nuevo), HttpStatus.CREATED);
    }

    // ===================================================================
    // 2. OPERACIONES MASIVAS / FILTROS (POR MARCA -> REQUEST PARAM)
    // ===================================================================
    // Para que sea más REST "puro" usamos Query Params (?marca=...) 
    // porque estamos operando sobre una lista filtrada.

    // GET /api/productos?marca=Samsung
    @GetMapping
    public ResponseEntity<List<Producto>> listar(@RequestParam(required = false) String marca) {
        List<Producto> productos;
        if (marca != null && !marca.isEmpty()) {
            productos = productoRepository.findByMarcaIgnoreCase(marca);
        } else {
            productos = productoRepository.findAll();
        }
        return ResponseEntity.ok(productos);
    }

    // PUT /api/productos?marca=Samsung
    // Nótese que quitamos "/marca" de la ruta. Ahora es la raíz con parámetro.
    @PutMapping
    public ResponseEntity<?> actualizarMasivo(@RequestParam(required = true) String marca, 
                                              @RequestBody Producto datos) {
        // IMPORTANTE: Ponemos required=true para no actualizar TODA la base de datos por error
        
        List<Producto> productos = productoRepository.findByMarcaIgnoreCase(marca);

        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("No existen productos de la marca: " + marca);
        }

        for (Producto p : productos) {
            if (datos.getPrecio() > 0) p.setPrecio(datos.getPrecio());
            if (datos.getStock() >= 0) p.setStock(datos.getStock());
        }

        return ResponseEntity.ok(productoRepository.saveAll(productos));
    }

    // DELETE /api/productos?marca=Samsung
    //Para que se borren todos o ninguna petición sea haga commit (rollback) usamos @Transactional para que se haga en una sesión todo
    @DeleteMapping
    @Transactional
    public ResponseEntity<String> borrarMasivo(@RequestParam(required = true) String marca) {
        // IMPORTANTE: required=true es vital aquí por seguridad
        
        long borrados = productoRepository.deleteByMarcaIgnoreCase(marca);

        if (borrados > 0) {
            return ResponseEntity.ok("Se han eliminado " + borrados + " productos.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("No se encontraron productos de esa marca.");
        }
    }

}