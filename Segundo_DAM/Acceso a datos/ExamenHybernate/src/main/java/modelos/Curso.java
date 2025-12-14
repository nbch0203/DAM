package modelos;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad Curso
 * 
 * Relaciones:
 * - ManyToOne con Profesor (bidireccional)
 * - OneToMany con Inscripcion (bidireccional)
 */
@Entity
@Table(name = "CURSOS")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curso_seq")
    @SequenceGenerator(name = "curso_seq", sequenceName = "CURSO_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODIGO", unique = true, nullable = false, length = 20)
    private String codigo;

    @Column(name = "NOMBRE", nullable = false, length = 150)
    private String nombre;

    @Column(name = "CREDITOS")
    private Integer creditos;

    @Column(name = "CUPO_MAXIMO")
    private Integer cupoMaximo;

    @Column(name = "FECHA_INICIO")
    private LocalDate fechaInicio;

    @Column(name = "FECHA_FIN")
    private LocalDate fechaFin;

    // ManyToOne: Muchos cursos pueden ser dictados por un profesor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFESOR_ID", nullable = false)
    private Profesor profesor;

    // OneToMany: Un curso tiene muchas inscripciones
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones = new ArrayList<>();

    // Constructores
    public Curso() {
    }

    public Curso(String codigo, String nombre, Integer creditos, Integer cupoMaximo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.cupoMaximo = cupoMaximo;
    }

    // Métodos de utilidad
    public void addInscripcion(Inscripcion inscripcion) {
        if (inscripcion != null && !inscripciones.contains(inscripcion)) {
            inscripciones.add(inscripcion);
            inscripcion.setCurso(this);
        }
    }

    public void removeInscripcion(Inscripcion inscripcion) {
        if (inscripcion != null && inscripciones.contains(inscripcion)) {
            inscripciones.remove(inscripcion);
            inscripcion.setCurso(null);
        }
    }

    public boolean tieneCupoDisponible() {
        return inscripciones.size() < cupoMaximo;
    }

    public int getCupoDisponible() {
        return cupoMaximo - inscripciones.size();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Integer getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(Integer cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", creditos=" + creditos +
                ", cupoMaximo=" + cupoMaximo +
                '}';
    }
}