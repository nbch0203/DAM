package modelos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "LIBRO")
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Libro_seq")
	@SequenceGenerator(name = "Libro_seq", sequenceName = "Libro_seq", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TITULO")
	private String titulo;
	@Column(name = "AUTOR")
	private String autor;

	@OneToOne(mappedBy = "libro", cascade = CascadeType.ALL)
	private DetalleLibro detalle;

	public Libro() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public DetalleLibro getDetalle() {
		return detalle;
	}

	public void setDetalle(DetalleLibro detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return "Libro [getId()=" + getId() + ", getTitulo()=" + getTitulo() + ", getAutor()=" + getAutor() + "]";
	}

}
