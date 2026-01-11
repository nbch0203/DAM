package practica;

import java.util.Arrays;
import java.util.Objects;

public class Estudiante{
		
	
    private int id;
    private String nombre;
    private double[] calificaciones;
    

    public Estudiante(int id, String nombre, double[] calificaciones) {
        this.id = id;
        this.nombre = nombre;
        this.calificaciones = calificaciones;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double[] getCalificaciones() {
        return calificaciones;
    }

    public double calcularPromedio() {
        double suma = 0;
        for (double calificacion : calificaciones) {
            suma += calificacion;
        }
        return suma / calificaciones.length;
    }
    
    
    

    @Override
    public String toString() {
        StringBuilder calificacionesStr = new StringBuilder();
        for (double calificacion : calificaciones) {
            calificacionesStr.append(calificacion).append(" ");
        }
        return "ID: " + id + ", Nombre: " + nombre + ", Calificaciones: " + calificacionesStr.toString().trim();
    }


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudiante other = (Estudiante) obj;
		return Arrays.equals(calificaciones, other.calificaciones) && id == other.id
				&& Objects.equals(nombre, other.nombre);
	}

}

