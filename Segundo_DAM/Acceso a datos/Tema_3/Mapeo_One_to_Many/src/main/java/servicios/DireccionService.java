package servicios;

import dao.DireccionDAO;
import dao.DireccionDAOImpl;
import modelo.Direccion;

public class DireccionService {

    private final DireccionDAO dao = new DireccionDAOImpl();

    private void validar(Direccion d) {
        if (d == null)
            throw new IllegalArgumentException("La dirección no puede ser null");

        if (d.getCalle() == null || d.getCalle().trim().equals(""))
            throw new IllegalArgumentException("La calle es obligatoria");

        if (d.getCiudad() == null || d.getCiudad().trim().equals(""))
            throw new IllegalArgumentException("La ciudad es obligatoria");
    }

    public Long crearDireccion(Direccion d) {
        validar(d);
        dao.crear(d);
        return d.getId();
    }

    public Direccion obtenerDireccion(Long id) {
        return dao.obtener(id);
    }

    public void actualizarDireccion(Direccion d) {
        validar(d);
        dao.actualizar(d);
    }

    public void borrarDireccion(Long id) {
        dao.eliminar(id);
    }
}
