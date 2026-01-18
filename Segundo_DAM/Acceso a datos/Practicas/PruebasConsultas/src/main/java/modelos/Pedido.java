package modelos;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderNumber")
	private Long numeroPedido;

	@Lob
	@Column(name = "comments")
	private String comentarios;

	@Temporal(TemporalType.DATE)
	@Column(name = "orderDate")
	private Date fechaPedido;

	@Temporal(TemporalType.DATE)
	@Column(name = "requiredDate")
	private Date fechaRequerida;

	@Temporal(TemporalType.DATE)
	@Column(name = "shippedDate")
	private Date fechaVendido;

	@Column(name = "status")
	private String estado;

	@OneToMany(mappedBy = "Pedido")
	private List<DetallesPedido> detallesPedido;

	@ManyToOne
	@JoinColumn(name = "numeroCliente")
	private Cliente cliente;

	public Pedido() {
	}

	public Long getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Date getFechaRequerida() {
		return fechaRequerida;
	}

	public void setFechaRequerida(Date fechaRequerida) {
		this.fechaRequerida = fechaRequerida;
	}

	public Date getFechaVendido() {
		return fechaVendido;
	}

	public void setFechaVendido(Date fechaVendido) {
		this.fechaVendido = fechaVendido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<DetallesPedido> getDetallesPedido() {
		return detallesPedido;
	}

	public void setDetallesPedido(List<DetallesPedido> detallesPedido) {
		this.detallesPedido = detallesPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
