package modelos;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Generated;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "CLIENTE")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Cliente_seq")
	@SequenceGenerator(name = "Cliente_seq", sequenceName = "Cliente_seq", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@OneToOne(mappedBy = "direccion", cascade = CascadeType.ALL)
	private Direccion direccion;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
	private ArrayList<Pedido> lista;

	public Cliente() {
	}

	public Cliente(Long id, String nombre, Direccion direccion, ArrayList<Pedido> lista) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.lista = lista;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public ArrayList<Pedido> getPedidos() {
		return lista;
	}

	public void setLista(ArrayList<Pedido> lista) {
		this.lista = lista;
	}

	@Override
	public String toString() {
		return "Cliente [getId()=" + getId() + ", getNombre()=" + getNombre() + ", getDireccion()=" + getDireccion()
				+ ", getLista()=" + getPedidos() + "]";
	}

	public void addPedido(Pedido p) {
		// TODO Auto-generated method stub
		ArrayList<Pedido> l = getPedidos();
		if (p != null) {
			l.add(p);
		} else
			throw new IllegalArgumentException("El pedido no puede ser nulo");

	}

	public void removePedido(Pedido p) {
		// TODO Auto-generated method stub
		
	}

}
