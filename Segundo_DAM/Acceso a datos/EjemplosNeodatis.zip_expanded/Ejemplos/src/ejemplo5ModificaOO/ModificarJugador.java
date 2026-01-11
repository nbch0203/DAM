package ejemplo5ModificaOO;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;


import ejemplo1AlmacenamientoOO.Jugador;

public class ModificarJugador {
    public static void main(String[] args) {
        // Abrir la base de datos
        ODB odb = ODBFactory.open("neodatis.test");

        // Consultar jugador llamado "María"
        CriteriaQuery query = new CriteriaQuery(Jugador.class, Where.equal("nombre", "María"));
        //getFirst() obtiene solamente el primer jugador encontrado en la consulta
        Jugador jugador = (Jugador) odb.getObjects(query).getFirst();

        System.out.println(jugador);
        // Modificar su deporte
        jugador.setDeporte("tenis");

        // Guardar cambios
        odb.store(jugador);
        odb.commit();//valida los cambios
        odb.close();//cierro bbdd
    }
}
