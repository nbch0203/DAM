package com.example.res_alumnos.configuracion;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.res_alumnos.util.Alumno;
@Configuration
public class MiConfiguracion {
	@Bean
	public List<Alumno> get_alumnos(){
		List<Alumno> alumnos = new ArrayList<Alumno>();
		Alumno a1=new Alumno(1,"ana","Marcas",6.6);
		alumnos.add(a1);
		Alumno a2=new Alumno(2,"juan","Marcas",4.6);
		alumnos.add(a2);
		Alumno a3=new Alumno(3,"pedro","Marcas",8.6);
		alumnos.add(a3);
		Alumno a4=new Alumno(4,"luis","Marcas",5.6);
		alumnos.add(a4);
		Alumno a5=new Alumno(5,"rosa","Marcas",7.6);
		alumnos.add(a5);
		return alumnos;
	}

}
