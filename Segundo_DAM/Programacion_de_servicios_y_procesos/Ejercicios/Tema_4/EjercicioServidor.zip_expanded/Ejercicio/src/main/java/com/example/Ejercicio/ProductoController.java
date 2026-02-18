package com.example.Ejercicio;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private List<Producto> inventario = new ArrayList<>();
    private long contadorId = 1;

    public ProductoController() {
        inventario.add(new Producto(contadorId++, "Ratón Gaming", "Logitech", 45.50, 10));
        inventario.add(new Producto(contadorId++, "Monitor 24", "Samsung", 120.00, 5));
        inventario.add(new Producto(contadorId++, "Teclado Mecánico", "Corsair", 89.99, 8));
    }

    // 1. GET (Listar todos o filtrar por marca)
    @GetMapping
    public List<Producto> listar(@RequestParam(required = false) String marca) {
        // Si la marca es nula (no la han enviado), devolvemos todo
        if (marca == null) {
            return inventario;
        }

        // Si hay marca, creamos una lista nueva solo con los coincidentes
        List<Producto> filtrados = new ArrayList<>();
        for (Producto p : inventario) {
            // equalsIgnoreCase para que da igual mayúsculas o minúsculas
            if (p.getMarca().equalsIgnoreCase(marca)) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }

    // 2. POST (Crear)
    @PostMapping
    public Producto crear(@RequestBody Producto nuevo) {
        nuevo.setId(contadorId++);
        inventario.add(nuevo);
        return nuevo;
    }

    // 3. PUT (Actualizar)
    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto datos) {
        // Recorremos la lista buscando el ID
        for (Producto p : inventario) {
            if (p.getId().equals(id)) {
                // Solo actualizamos precio y stock
                p.setPrecio(datos.getPrecio());
                p.setStock(datos.getStock());
                return p;
            }
        }
        return null; // Si termina el bucle y no lo encuentra
    }

    // 4. DELETE (Borrar) - VERSIÓN SIN LAMBDAS
    @DeleteMapping("/{id}")
    public String borrar(@PathVariable Long id) {
        // Usamos un bucle for normal con índice 'i'
        // Es necesario para poder borrar sin causar errores en la lista
        for (int i = 0; i < inventario.size(); i++) {
            Producto p = inventario.get(i);
            
            if (p.getId().equals(id)) {
                inventario.remove(i); // Borramos la posición i
                return "Producto eliminado correctamente.";
            }
        }
        return "No se encontró el producto.";
    }
}