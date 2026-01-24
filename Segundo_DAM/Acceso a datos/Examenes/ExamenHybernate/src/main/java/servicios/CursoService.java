// ============================================
// CURSO SERVICE
// ============================================

package servicios;

import dao.CursoDAO;
import modelos.Curso;
import java.util.List;

/**
 * Servicio para gestión de Cursos
 */
public class CursoService {

    private final CursoDAO cursoDAO;

    public CursoService(CursoDAO cursoDAO) {
        this.cursoDAO = cursoDAO;
    }

    public void registrarCurso(Curso curso) {
        validarCurso(curso);
        cursoDAO.crear(curso);
    }

    public Curso obtenerCurso(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        return cursoDAO.obtenerPorId(id);
    }

    public Curso obtenerCursoConInscripciones(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        return cursoDAO.obtenerPorIdConInscripciones(id);
    }

    public void actualizarCurso(Curso curso) {
        validarCurso(curso);
        cursoDAO.actualizar(curso);
    }

    public void eliminarCurso(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        cursoDAO.eliminar(id);
    }

    public List<Curso> obtenerTodosCursos() {
        return cursoDAO.obtenerTodos();
    }

    public Curso buscarPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código no puede estar vacío");
        }
        return cursoDAO.obtenerPorCodigo(codigo);
    }

    public List<Curso> obtenerCursosDisponibles() {
        return cursoDAO.obtenerCursosConCupoDisponible();
    }

    private void validarCurso(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser nulo");
        }
        if (curso.getCodigo() == null || curso.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("El código del curso es obligatorio");
        }
        if (curso.getNombre() == null || curso.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del curso es obligatorio");
        }
        if (curso.getCreditos() == null || curso.getCreditos() <= 0) {
            throw new IllegalArgumentException("Los créditos deben ser mayor a 0");
        }
        if (curso.getCupoMaximo() == null || curso.getCupoMaximo() <= 0) {
            throw new IllegalArgumentException("El cupo máximo debe ser mayor a 0");
        }
        if (curso.getProfesor() == null) {
            throw new IllegalArgumentException("El curso debe tener un profesor asignado");
        }
    }
}
