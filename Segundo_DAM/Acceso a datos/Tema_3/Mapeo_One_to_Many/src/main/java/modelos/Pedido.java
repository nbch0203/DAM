package modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PEDIDO")
public class Pedido {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private Cliente cliente;

	public Pedido() {
	}

	public Pedido(String nextLine) {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Pedido [getId()=" + getId() + ", getDescripcion()=" + getDescripcion() + ", getCliente()="
				+ getCliente() + "]";
	}

}
