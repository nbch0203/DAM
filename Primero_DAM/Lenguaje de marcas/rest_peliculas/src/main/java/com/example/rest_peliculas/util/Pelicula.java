package com.example.rest_peliculas.util;

public class Pelicula {
	public int id_pelicula;
	public String titulo;
	public String genero;
	public String autor;
	public int año;
	
	public Pelicula() {
		
	}

	public Pelicula(int id_pelicula, String titulo, String genero, String autor, int año) {
		super();
		this.id_pelicula = id_pelicula;
		this.titulo = titulo;
		this.genero = genero;
		this.autor = autor;
		this.año = año;
	}

	public int getId_pelicula() {
		return id_pelicula;
	}

	public void setId_pelicula(int id_pelicula) {
		this.id_pelicula = id_pelicula;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}
	
	
}
