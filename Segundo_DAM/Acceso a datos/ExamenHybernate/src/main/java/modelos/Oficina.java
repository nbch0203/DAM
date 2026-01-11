package modelos;

import jakarta.persistence.*;

/**
 * Entidad Oficina
 * 
 * Relación OneToOne con Profesor (bidireccional)
 * Oficina es el lado propietario de la relación (@JoinColumn)
 */
@Entity
@Table(name = "OFICINAS")
public class Oficina {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oficina_seq")
    @SequenceGenerator(name = "oficina_seq", sequenceName = "OFICINA_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NUMERO", unique = true, nullable = false, length = 20)
    private String numero;

    @Column(name = "EDIFICIO", nullable = false, length = 50)
    private String edificio;

    @Column(name = "PISO")
    private Integer piso;

    @Column(name = "CAPACIDAD")
    private Integer capacidad;

    // OneToOne: Una oficina pertenece a un profesor
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFESOR_ID", unique = true)
    private Profesor profesor;

    // Constructores
    public Oficina() {
    }

    public Oficina(String numero, String edificio, Integer piso, Integer capacidad) {
        this.numero = numero;
        this.edificio = edificio;
        this.piso = piso;
        this.capacidad = capacidad;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public Integer getPiso() {
        return piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public String getUbicacion() {
        return edificio + " - Piso " + piso + " - Oficina " + numero;
    }

    @Override
    public String toString() {
        return "Oficina{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", edificio='" + edificio + '\'' +
                ", piso=" + piso +
                ", capacidad=" + capacidad +
                '}';
    }
}