package modelos;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad Estudiante
 * 
 * Relación OneToMany con Inscripcion (bidireccional)
 */
@Entity
@Table(name = "ESTUDIANTES")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudiante_seq")
    @SequenceGenerator(name = "estudiante_seq", sequenceName = "ESTUDIANTE_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MATRICULA", unique = true, nullable = false, length = 20)
    private String matricula;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "APELLIDO", nullable = false, length = 100)
    private String apellido;

    @Column(name = "EMAIL", unique = true, nullable = false, length = 150)
    private String email;

    @Column(name = "FECHA_INGRESO")
    private LocalDate fechaIngreso;

    @Column(name = "CARRERA", length = 100)
    private String carrera;

    // OneToMany: Un estudiante puede tener muchas inscripciones
    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones = new ArrayList<>();

    // Constructores
    public Estudiante() {
    }

    public Estudiante(String matricula, String nombre, String apellido, String email, String carrera) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.carrera = carrera;
        this.fechaIngreso = LocalDate.now();
    }

    // Métodos de utilidad
    public void addInscripcion(Inscripcion inscripcion) {
        if (inscripcion != null && !inscripciones.contains(inscripcion)) {
            inscripciones.add(inscripcion);
            inscripcion.setEstudiante(this);
        }
    }

    public void removeInscripcion(Inscripcion inscripcion) {
        if (inscripcion != null && inscripciones.contains(inscripcion)) {
            inscripciones.remove(inscripcion);
            inscripcion.setEstudiante(null);
        }
    }

    public int getTotalCreditos() {
        return inscripciones.stream()
                .map(i -> i.getCurso().getCreditos())
                .reduce(0, Integer::sum);
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", matricula='" + matricula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", carrera='" + carrera + '\'' +
                '}';
    }
}