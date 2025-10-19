package Ejercicio_repaso_XML.Repaso_XML;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "DNI")
@XmlType(propOrder = { "nacionalidad", "id", "fechacaducidad" })
public class DNI {
	private String nacionalidad; // Cambiar a minúscula para consistencia
	private String id;
	private String fechacaducidad;

	public DNI() {
	} // Constructor vacío necesario

	public DNI(String nacionalidad, String id, String fechacaducidad) {
		this.nacionalidad = nacionalidad;
		this.id = id;
		this.fechacaducidad = fechacaducidad;
	}

	public String getnacionalidad() {
		return nacionalidad;
	}

	public void setnacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	public String getFechacaducidad() {
		return fechacaducidad;
	}

	public void setFechacaducidad(String fechacaducidad) {
		this.fechacaducidad = fechacaducidad;
	}

}
