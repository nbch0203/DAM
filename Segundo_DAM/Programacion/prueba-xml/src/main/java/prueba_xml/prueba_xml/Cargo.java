package prueba_xml.prueba_xml;

import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlValue;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cargo")
public class Cargo {
	private int nivel;
	private String valor;

	public Cargo() {
	}

	public Cargo(String valor, int nivel) {
		this.valor = valor;
		this.nivel = nivel;
	}

	@XmlAttribute
	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	@XmlValue
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}