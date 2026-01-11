package Ejemplo_Hibernate.Ejemplo_Hibernate;

import jakarta.persistence.*;

//A partir de la versión de hibernate 6 hay que importar jakarta.persistence.* porque javax.persistence.* es para versiones anteriores

@Entity
@Table(name="DEPARTAMENTOS")
public class Departamento {
	@Id // Esto es por ser PRIMARY KEY
	@Column(name="ID_DEP")
	private int idDepartamento; //Mejor utilizar clases envoltorio Integer, Double, etc porque puede que no tengamos valores en bbdd. No es el caso porque es PK
	
	@Column(name="NOMBRE")
	private String nombre;

	public Departamento(){};
	
	public Departamento(String nombre) {
		this.nombre = nombre;
	}
	
	public Departamento(int idDepartamento, String nombre) {
		this.idDepartamento = idDepartamento;
		this.nombre = nombre;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Departamento [idDepartamento=" + idDepartamento + ", nombre=" + nombre + "]";
	}
	
	
	
}
