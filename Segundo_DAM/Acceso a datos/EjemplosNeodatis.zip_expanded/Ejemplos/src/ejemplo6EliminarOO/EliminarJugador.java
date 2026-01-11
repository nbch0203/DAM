package ejemplo6EliminarOO;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.core.query.IQuery;


import ejemplo1AlmacenamientoOO.Jugador;

public class EliminarJugador {
    public static void main(String[] args) {
        // Abrir la base de datos
        ODB odb = ODBFactory.open("neodatis.test");

        // Consultar jugador llamado "María"
        IQuery query = new CriteriaQuery(Jugador.class, Where.equal("nombre", "María"));
        Jugador jugador = (Jugador) odb.getObjects(query).getFirst();

        // Eliminar el objeto
        odb.delete(jugador);
        odb.close();
    }
}

