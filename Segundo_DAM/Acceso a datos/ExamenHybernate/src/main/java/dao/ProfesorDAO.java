package dao;

import java.util.List;
import modelos.*;

/**
 * Interface para operaciones CRUD y consultas de Profesor
 */
public interface ProfesorDAO {
    
    // CRUD básico
    void crear(Profesor profesor);
    Profesor obtenerPorId(Long id);
    Profesor obtenerPorIdConRelaciones(Long id);
    void actualizar(Profesor profesor);
    void eliminar(Long id);
    List<Profesor> obtenerTodos();
    
    // Consultas personalizadas
    Profesor obtenerPorEmail(String email);
    List<Profesor> buscarPorEspecialidad(String especialidad);
    List<Profesor> buscarPorNombre(String nombre);
    List<Profesor> obtenerProfesoresConOficina();
    List<Profesor> obtenerProfesoresSinOficina();
    Long contarCursosPorProfesor(Long profesorId);
    List<Profesor> obtenerProfesoresConMasDe(int numeroCursos);
}