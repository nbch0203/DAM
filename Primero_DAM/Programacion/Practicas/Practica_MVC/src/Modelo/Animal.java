package Modelo;

import java.io.Serializable;

public class Animal implements Serializable {
	private String nombre;
	private String especie;
	private int edad;
	private transient double peso;
	private String sexo;
	

	public Animal(String nombre, String especie, int edad, double peso, String sexo) {
		this.nombre = nombre;
		this.especie = especie;
		this.edad = edad;
		this.peso = peso;
		this.sexo = sexo;
	}

	public Animal() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return nombre + " - " + especie + " - " + edad + " años - " + peso + "kg - " + sexo;
	}
		
		
	}
	
	
