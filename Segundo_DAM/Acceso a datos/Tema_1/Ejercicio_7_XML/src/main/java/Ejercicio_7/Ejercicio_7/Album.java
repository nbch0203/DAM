package Ejercicio_7.Ejercicio_7;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

class Album {
	private int anio;

	private String artista;

	private List<Cancion> canciones = new ArrayList<>();

	private String nombre;

	// Constructores
	public Album() {
	}

	public Album(int anio, String artista, String nombre) {
		this.anio = anio;
		this.artista = artista;
		this.nombre = nombre;
	}

	// Getters
	public int getAnio() {
		return anio;
	}

	public String getArtista() {
		return artista;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Cancion> getCanciones() {
		return canciones;
	}

	public void addCancion(Cancion c) {
		canciones.add(c);
	}
}

@XmlAccessorType(XmlAccessType.FIELD)
class Cancion {
	private String titulo;

	@XmlElement
	private Duracion duracion;

	public Cancion() {
	}

	public Cancion(String titulo, Duracion duracion) {
		this.titulo = titulo;
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "Cancion [titulo=" + titulo + ", duracion=" + duracion + "]";
	}
}

@XmlAccessorType(XmlAccessType.FIELD)
class Duracion {
	@XmlValue
	private int valor;

	@XmlAttribute
	private String extra;

	public Duracion() {
	}

	public Duracion(int valor, String extra) {
		this.valor = valor;
		this.extra = extra;
	}

	@Override
	public String toString() {
		return valor + " (extra: " + extra + ")";
	}
}
