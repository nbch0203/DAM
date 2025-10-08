package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Pedido implements Serializable{
	private String nombre;
	private ArrayList<Producto> articulos;
	
	public Pedido(String nombre, ArrayList<Producto> articulos) {
		this.nombre = nombre;
		this.articulos = articulos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Producto> getArticulos() {
		return articulos;
	}

	public void setArticulos(ArrayList<Producto> articulos) {
		this.articulos = articulos;
	}

	@Override
	public String toString() {
		return "Nombre=" + nombre + ", articulos=" + articulos.toString() ;
	}
	
	
		
}
