package clases;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "PERSONAS")
public class Persona {
	@Id
	@SequenceGenerator( name="")
	@GeneratedValue()
	@Column(name = "DNI")
	private String dni;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "EDAD")
	private int edad;
	@Column(name = "SEXO")
	private String sexo;

	public Persona() {
	}

	public Persona(String dni, String nombre, int edad, String sexo) {
		this.dni = dni;
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Persona [getDni()=" + getDni() + ", getNombre()=" + getNombre() + ", getEdad()=" + getEdad()
				+ ", getSexo()=" + getSexo() + "]";
	}

}
