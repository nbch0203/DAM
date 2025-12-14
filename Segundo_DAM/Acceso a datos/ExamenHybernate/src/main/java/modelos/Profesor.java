package modelos;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad Profesor
 * 
 * Relaciones:
 * - OneToOne con Oficina (bidireccional)
 * - OneToMany con Curso (bidireccional)
 */
@Entity
@Table(name = "PROFESORES")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profesor_seq")
    @SequenceGenerator(name = "profesor_seq", sequenceName = "PROFESOR_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "APELLIDO", nullable = false, length = 100)
    private String apellido;

    @Column(name = "EMAIL", unique = true, nullable = false, length = 150)
    private String email;

    @Column(name = "ESPECIALIDAD", length = 100)
    private String especialidad;

    // OneToOne: Un profesor tiene una oficina
    @OneToOne(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Oficina oficina;

    // OneToMany: Un profesor puede dictar muchos cursos
    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Curso> cursos = new ArrayList<>();

    // Constructores
    public Profesor() {
    }

    public Profesor(String nombre, String apellido, String email, String especialidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.especialidad = especialidad;
    }

    // Métodos de utilidad para mantener sincronizadas las relaciones bidireccionales
    
    public void setOficina(Oficina oficina) {
        if (this.oficina != null) {
            this.oficina.setProfesor(null);
        }
        this.oficina = oficina;
        if (oficina != null) {
            oficina.setProfesor(this);
        }
    }

    public void addCurso(Curso curso) {
        if (curso != null && !cursos.contains(curso)) {
            cursos.add(curso);
            curso.setProfesor(this);
        }
    }

    public void removeCurso(Curso curso) {
        if (curso != null && cursos.contains(curso)) {
            cursos.remove(curso);
            curso.setProfesor(null);
        }
    }

    // Getters y Setters
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}