package boletin5_Ejercicio6;

import boletin7_Ejercicio8.EdadNoValidaException;

public class Gato {
	private String nombre;
	private int edad;
	
	public Gato(String nombre, int edad) {
		if(nombre.length() >3) {
			this.nombre = nombre;
			}else
			{
				try {
					throw new NombreNoException(nombre);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		if (edad>0) {
			this.edad = edad;
		} else {
			try {
				throw new EdadNoValidaException(edad);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	public String getNombre() {
		
		return nombre;
	}
	public void setNombre(String nombre) {
		if(nombre.length() >3) {
		this.nombre = nombre;
		}else
		{
			try {
				throw new NombreNoException(nombre);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		if (edad>0) {
			this.edad = edad;
		} else {
			try {
				throw new EdadNoValidaException(edad);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public String Imprimir() {
		return "nombre=" + nombre + ", edad=" + edad + "]";
	}
	
	
}
