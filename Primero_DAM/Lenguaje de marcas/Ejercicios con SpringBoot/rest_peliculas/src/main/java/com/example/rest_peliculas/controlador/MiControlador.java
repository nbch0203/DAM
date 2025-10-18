package com.example.rest_peliculas.controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest_peliculas.util.Pelicula;
@CrossOrigin
@RestController
public class MiControlador {
	@Autowired
	ArrayList<Pelicula> peliculas;

	@GetMapping("/getpeliculas")
	public ArrayList<Pelicula> getpeliculas() {

		return peliculas;
	}

	@PostMapping("/addpelicula")
	public Integer addpelicula(@RequestBody Pelicula p) {
		boolean existe = false;
		for (Pelicula pelicula : peliculas) {
			if (pelicula.getId_pelicula() == p.getId_pelicula()) {
				existe = true;
				break;
			}

		}
		if (existe) {
			return 1;
		}else{
			peliculas.add(p);
			return 0;
		}
	}
}
