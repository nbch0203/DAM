package examen_nixon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Proyecto implements Comparable<Object>  {
	
    private int id;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private String estado;
    private double presupuesto;

    public Proyecto(int id, String nombre, String fechaInicio, String fechaFin, String estado, double presupuesto) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.presupuesto = presupuesto;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public double getPresupuesto() {
        return presupuesto;
    }
    
    public void setNombre(String nombre) {
    	this.nombre= nombre;
    }
    
    public void setFechaInicio(String fechaini) {
    	this.fechaInicio=fechaini;
    }
    
    public void setFechaFin(String fechafin) {
    	this.fechaFin=fechafin;
    }
    public void setEstado(String est) {
    	this.estado=est;
    }
    public void setPresupuesto(double presu) {
    	this.presupuesto=presu;
    }

  
	@Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Fecha Inicio: " + fechaInicio + ", Fecha Fin: " + fechaFin + ", Estado: " + estado + ", Presupuesto: " + presupuesto;
    }

	
	
	

	public int compareTo(Object o1,Object o2) {
		Proyecto c1 = (Proyecto) o1;
		Proyecto c2 = (Proyecto) o2;
		return c1.getNombre().compareTo(c2.getNombre());
	}

	@Override
	public int hashCode() {
		return Objects.hash(estado, fechaFin, fechaInicio, id, nombre, presupuesto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proyecto other = (Proyecto) obj;
		return Double.doubleToLongBits(presupuesto) == Double.doubleToLongBits(other.presupuesto);
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}



