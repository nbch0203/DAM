package modelos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "DETALLE_LIBRO")
public class DetalleLibro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalle_seq")
	@SequenceGenerator(name = "detalle_seq", sequenceName = "detalle_seq", allocationSize = 1)

	@Column(name = "ID")
	private Long id;

	@Column(name = "SIPNOSIS_LARGA")
	private String sipnosis;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "LIBRO_ID", unique = true, nullable = false)
	private Libro libro;

	public DetalleLibro() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSipnosis() {
		return sipnosis;
	}

	public void setSipnosis(String sipnosis) {
		this.sipnosis = sipnosis;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	@Override
	public String toString() {
		return "DetalleLibro [getId()=" + getId() + ", getSipnosis()=" + getSipnosis() + ", getLibro()=" + getLibro()
				+ "]";
	}

}
