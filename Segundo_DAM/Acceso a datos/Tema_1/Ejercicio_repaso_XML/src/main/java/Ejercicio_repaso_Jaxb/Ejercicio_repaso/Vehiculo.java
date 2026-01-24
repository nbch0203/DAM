package Ejercicio_repaso_Jaxb.Ejercicio_repaso;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="Vehiculo")
@XmlType(propOrder={"precio","ano","marca","modelo","vendedor"})
public class Vehiculo {
	private int ano;
	private String marca;
	private String vendedor;
	private String modelo;
	private double precio;

	public Vehiculo() {
	}
	
	@XmlAttribute(name="ano")
	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	@XmlAttribute(name="modelo")
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@XmlAttribute(name="marca")
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	@XmlAttribute(name="vendedor")
	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	
	@XmlAttribute(name="precio")
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Vehiculo [getAno()=" + getAno() + ", getMarca()=" + getMarca() + ", getVendedor()=" + getVendedor()
				+ ", getPrecio()=" + getPrecio() + "]";
	}

}
