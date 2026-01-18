// ============================================
// ESTUDIANTE SERVICE
// ============================================

package servicios;

import dao.EstudianteDAO;
import modelos.Estudiante;
import java.util.List;

/**
 * Servicio para gestión de Estudiantes
 */
public class EstudianteService {

    private final EstudianteDAO estudianteDAO;

    public EstudianteService(EstudianteDAO estudianteDAO) {
        this.estudianteDAO = estudianteDAO;
    }

    public void registrarEstudiante(Estudiante estudiante) {
        validarEstudiante(estudiante);
        estudianteDAO.crear(estudiante);
    }

    public Estudiante obtenerEstudiante(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        return estudianteDAO.obtenerPorId(id);
    }

    public Estudiante obtenerEstudianteConInscripciones(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        return estudianteDAO.obtenerPorIdConInscripciones(id);
    }

    public void actualizarEstudiante(Estudiante estudiante) {
        validarEstudiante(estudiante);
        estudianteDAO.actualizar(estudiante);
    }

    public void eliminarEstudiante(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        estudianteDAO.eliminar(id);
    }

    public List<Estudiante> obtenerTodosEstudiantes() {
        return estudianteDAO.obtenerTodos();
    }

    public Estudiante buscarPorMatricula(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Matrícula no puede estar vacía");
        }
        return estudianteDAO.obtenerPorMatricula(matricula);
    }

    public List<Estudiante> buscarPorCarrera(String carrera) {
        return estudianteDAO.buscarPorCarrera(carrera);
    }

    private void validarEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo");
        }
        if (estudiante.getMatricula() == null || estudiante.getMatricula().trim().isEmpty()) {
            throw new IllegalArgumentException("La matrícula del estudiante es obligatoria");
        }
        if (estudiante.getNombre() == null || estudiante.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estudiante es obligatorio");
        }
        if (estudiante.getApellido() == null || estudiante.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del estudiante es obligatorio");
        }
        if (estudiante.getEmail() == null || estudiante.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email del estudiante es obligatorio");
        }
        if (!estudiante.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
    }
}