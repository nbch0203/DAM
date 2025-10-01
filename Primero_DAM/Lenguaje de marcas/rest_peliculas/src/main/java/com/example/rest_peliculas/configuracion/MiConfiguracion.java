package com.example.rest_peliculas.configuracion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.rest_peliculas.util.Pelicula;

@Configuration
public class MiConfiguracion {
	
	  @Bean
	  public ArrayList<Pelicula> getpeliculas(){
			
			 ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
			
			 peliculas.add(new Pelicula(1, "Sombras del Ayer", "drama", "Alejandro González Iñárritu", 2015));
			 peliculas.add(new Pelicula(2, "Horizonte de Acero", "ciencia ficción", "Denis Villeneuve", 2021));
			 peliculas.add(new Pelicula(3, "Ecos del Pasado", "thriller", "David Fincher", 2014));
			 peliculas.add(new Pelicula(4, "El Guardián del Tiempo", "aventura", "Steven Spielberg", 2012));
			 peliculas.add(new Pelicula(5, "La Sinfonía del Silencio", "musical", "Baz Luhrmann", 2018));
			 peliculas.add(new Pelicula(6, "Entre Luces y Sombras", "romance", "Sofia Coppola", 2019));
			 peliculas.add(new Pelicula(7, "Código Infinito", "acción", "James Cameron", 2020));
			 peliculas.add(new Pelicula(8, "La Risa Eterna", "comedia", "Taika Waititi", 2016));
			 peliculas.add(new Pelicula(9, "Misterios del Vacío", "misterio", "Christopher Nolan", 2023));
			 peliculas.add(new Pelicula(10, "El Viaje de los Sentidos", "fantasía", "Guillermo del Toro", 2017));


			 return peliculas;
		}

}