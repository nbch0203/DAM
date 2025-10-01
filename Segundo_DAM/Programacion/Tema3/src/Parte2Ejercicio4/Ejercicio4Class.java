package Parte2Ejercicio4;
import java.math.*;
public class Ejercicio4Class {

	// Atributos

	String Corigen;
	String Cdestino;
	int codigo;
	int codigo2;
	double importeviaje_persona;
	int numero_adultos;
	int numero_ninos;
	double valorale = (int) Math.random() * 3 + 1;
	// Constructor

	public Ejercicio4Class(String Corigen, String Cdestino,int codigo, double importeviaje,int numero_adultos,int numero_ninos) {
		this.Corigen = Corigen;
		this.Cdestino = Cdestino;
		this.codigo=codigo;
		this.importeviaje_persona = importeviaje;
		this.numero_adultos=numero_adultos;
		this.numero_ninos=numero_ninos;
		codigo2=Corigen.charAt(0)+Cdestino.charAt(0)+codigo;
		
		
	}
}
