package modelos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

public class Direccion {

	@Id
	@GeneratedValue(generator = "Calle_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "Calle_seq", sequenceName = "Calle_seq", allocationSize = 1)
	private long id;

	@Column(name = "CALLE")
	private String calle;

	@Column(name = "CIUDAD")
	private String ciudad;

	public Direccion() {
	}

	public Direccion(String calle, String ciudad) {
		// TODO Auto-generated constructor stub
		this.calle = calle;
		this.ciudad = ciudad;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "Direccion [getId()=" + getId() + ", getCalle()=" + getCalle() + ", getCiudad()=" + getCiudad() + "]";
	}

}
