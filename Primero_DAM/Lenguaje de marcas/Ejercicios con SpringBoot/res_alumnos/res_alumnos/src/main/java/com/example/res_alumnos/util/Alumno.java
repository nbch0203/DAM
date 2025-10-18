package com.example.res_alumnos.util;

public class Alumno {
public int id_alumno;
public String nombre;
public String asignatura;
public double nota;
public Alumno(int id_alumno, String nombre, String asignatura, double nota) {
	this.id_alumno = id_alumno;
	this.nombre = nombre;
	this.asignatura = asignatura;
	this.nota = nota;
}
public Alumno() {
	
}
public int getId_alumno() {
	return id_alumno;
}
public void setId_alumno(int id_alumno) {
	this.id_alumno = id_alumno;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getAsignatura() {
	return asignatura;
}
public void setAsignatura(String asignatura) {
	this.asignatura = asignatura;
}
public double getNota() {
	return nota;
}
public void setNota(double nota) {
	this.nota = nota;
}





}
