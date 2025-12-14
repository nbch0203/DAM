package dao;

import modelos.Curso;
import java.util.List;

/**
 * Interface para operaciones CRUD y consultas de Curso
 */
public interface CursoDAO {
    void crear(Curso curso);
    Curso obtenerPorId(Long id);
    Curso obtenerPorIdConInscripciones(Long id);
    void actualizar(Curso curso);
    void eliminar(Long id);
    List<Curso> obtenerTodos();
    
    // Consultas personalizadas
    Curso obtenerPorCodigo(String codigo);
    List<Curso> buscarPorNombre(String nombre);
    List<Curso> obtenerCursosConCupoDisponible();
    List<Curso> obtenerCursosPorProfesor(Long profesorId);
    Long contarEstudiantesPorCurso(Long cursoId);
    Double obtenerPromedioNotasCurso(Long cursoId);
}