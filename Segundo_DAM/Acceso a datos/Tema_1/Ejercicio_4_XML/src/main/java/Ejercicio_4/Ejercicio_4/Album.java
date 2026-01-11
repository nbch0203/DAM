package Ejercicio_4.Ejercicio_4;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.sun.xml.txw2.annotation.XmlAttribute;

@XmlRootElement(name = "Album")
@XmlType(propOrder = { "artista", "titulo", "duracion" })
public class Album {

	private String titulo;
	private String artista;
	private Duracion duracion;

	public Album() {
	}

	@XmlElement(name = "titulo")
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public Duracion getDuracion() {
		return duracion;
	}

	public void setDuracion(Duracion duracion) {
		this.duracion = duracion;
	}

}
