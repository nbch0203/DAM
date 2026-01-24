package com.example.res_alumnos.controlador;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.res_alumnos.util.Alumno;
@CrossOrigin
@RestController
@RequestMapping("/alumnos")
public class MiControlador {
	@Autowired
	List<Alumno> alumnos;
	
	@Autowired
	HashMap<Integer, Alumno> alumnos_hash;
	
	@GetMapping("/get_alumnos")
	public List<Alumno> get_alumnos() {
	    return alumnos;
	}

	@GetMapping("/get_alumnos_hash")
	public HashMap<Integer, Alumno> get_alumnos_hash() {
	    return alumnos_hash;
	}

	}
	
	@PostMapping("/add_alumno")
	public Integer add_alumno(@RequestBody Alumno alumno) {
		int id_alumno =alumno.getId_alumno();
		if(alumnos_hash.containsKey(id_alumno)) {
			return 1;
		}else {
			alumnos_hash.put(id_alumno, alumno);
			alumnos.add(alumno);
		}
		return 0;
	}
	
	

}
