package servicios;

import dao.ProfesorDAO;
import modelos.Profesor;
import java.util.List;

/**
 * Servicio para gestión de Profesores
 */
public class ProfesorService {

    private final ProfesorDAO profesorDAO;

    public ProfesorService(ProfesorDAO profesorDAO) {
        this.profesorDAO = profesorDAO;
    }

    public void registrarProfesor(Profesor profesor) {
        validarProfesor(profesor);
        profesorDAO.crear(profesor);
    }

    public Profesor obtenerProfesor(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        return profesorDAO.obtenerPorId(id);
    }

    public Profesor obtenerProfesorConRelaciones(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        return profesorDAO.obtenerPorIdConRelaciones(id);
    }

    public void actualizarProfesor(Profesor profesor) {
        validarProfesor(profesor);
        profesorDAO.actualizar(profesor);
    }

    public void eliminarProfesor(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        profesorDAO.eliminar(id);
    }

    public List<Profesor> obtenerTodosProfesores() {
        return profesorDAO.obtenerTodos();
    }

    public Profesor buscarPorEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email no puede estar vacío");
        }
        return profesorDAO.obtenerPorEmail(email);
    }

    public List<Profesor> buscarPorEspecialidad(String especialidad) {
        return profesorDAO.buscarPorEspecialidad(especialidad);
    }

    private void validarProfesor(Profesor profesor) {
        if (profesor == null) {
            throw new IllegalArgumentException("El profesor no puede ser nulo");
        }
        if (profesor.getNombre() == null || profesor.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del profesor es obligatorio");
        }
        if (profesor.getApellido() == null || profesor.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del profesor es obligatorio");
        }
        if (profesor.getEmail() == null || profesor.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email del profesor es obligatorio");
        }
        if (!profesor.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
    }
}