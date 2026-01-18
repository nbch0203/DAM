package ejemplo4ConsultaCriterio;

import org.neodatis.odb.*;
import org.neodatis.odb.core.query.*;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.*;

import ejemplo1AlmacenamientoOO.Jugador;

public class ConsultarJugadores {
    public static void main(String[] args) {
        // Abrir la base de datos
        ODB odb = ODBFactory.open("neodatis.test");

        // Consultar jugadores que practiquen voleibol
        IQuery query = new CriteriaQuery(Jugador.class, Where.equal("deporte", "voleibol"));
        query.orderByDesc("nombre,edad");
        
        Objects<Jugador> jugadores = odb.getObjects(query);
        
        System.out.println("Query 1:");

        // Mostrar resultados
        for (Jugador jugador : jugadores) {
            System.out.println("Nombre: " + jugador.getNombre() + ", Ciudad: " + jugador.getCiudad());
        }
        
        //Con CriteriaQuery() se puede usar la interfaz ICriterion para construir el criterio de la consulta
        ICriterion criterio = Where.equal("deporte", "tenis");
        IQuery query2 = new CriteriaQuery(Jugador.class, criterio);
        

        jugadores = odb.getObjects(query2);
        System.out.println("Query 2:");
        
        // Mostrar los datos de los jugadores
        while (jugadores.hasNext()) {
            Jugador jugador = jugadores.next();
            System.out.println("Nombre: " + jugador.getNombre() + ", Deporte: " + jugador.getDeporte());
        }
        // Cerrar la base de datos
        odb.close();
    }
}
