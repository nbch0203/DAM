package com.example.Ejercicio; // ⚠️ Cambia esto si tu paquete se llama diferente


import jakarta.persistence.*;

@Entity
public class Producto {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String marca;
    private double precio;
    private int stock;

    public Producto() {}

    public Producto(Long id, String nombre, String marca, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}