package ejemplo7OtrasQuerys;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import ejemplo1AlmacenamientoOO.Jugador;

public class Consultas {

	public static void main(String[] args) {
		 // Abrir la base de datos
        ODB odb = ODBFactory.open("neodatis.test");
        
        //Edad 14
        ICriterion criterio = Where.equal("edad", 14);
        //Empiezan por M
//        ICriterion criterio = Where.like("nombre", "M%");
        //No empiecen por M
//        ICriterion criterio = Where.not(Where.like("nombre", "M%"));
        //Mayor que 14
//      ICriterion criterio = Where.gt("edad", 14);
        //Mayor o igual que 14
//        ICriterion criterio = Where.ge("edad", 14);
        //Menor que 15
//        ICriterion criterio = Where.lt("edad", 15);
        //Menor o igual que 15
//        ICriterion criterio = Where.le("edad", 15);

        
        IQuery query = new CriteriaQuery(Jugador.class, criterio);
        
        //recupera todos los jugadores de la consulta
        Objects<Jugador> jugadores = odb.getObjects(query);
        System.out.println("Query 2:");
        
        // Mostrar los datos de los jugadores
        while (jugadores.hasNext()) {
            Jugador jugador = jugadores.next();
            System.out.println(jugador);
        }
        // Cerrar la base de datos
        odb.close();
	}

}
