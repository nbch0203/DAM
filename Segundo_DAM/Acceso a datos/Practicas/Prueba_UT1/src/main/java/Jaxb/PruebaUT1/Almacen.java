package Jaxb.PruebaUT1;

import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Almacen {
	@XmlElementWrapper(name = "articulos")
	private List<Articulo> articulos;

	public Almacen() {
	}

	public Almacen(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	public List<Articulo> getArticulo() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
}