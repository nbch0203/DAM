// ============================================
// ESTUDIANTE DAO INTERFACE
// ============================================

package dao;

import modelos.Estudiante;
import java.util.List;

/**
 * Interface para operaciones CRUD y consultas de Estudiante
 */
public interface EstudianteDAO {
    void crear(Estudiante estudiante);
    Estudiante obtenerPorId(Long id);
    Estudiante obtenerPorIdConInscripciones(Long id);
    void actualizar(Estudiante estudiante);
    void eliminar(Long id);
    List<Estudiante> obtenerTodos();
    
    // Consultas personalizadas
    Estudiante obtenerPorMatricula(String matricula);
    List<Estudiante> buscarPorNombre(String nombre);
    List<Estudiante> buscarPorCarrera(String carrera);
    List<Estudiante> obtenerEstudiantesPorCurso(Long cursoId);
    Long contarInscripcionesPorEstudiante(Long estudianteId);
    Double obtenerPromedioEstudiante(Long estudianteId);
}
