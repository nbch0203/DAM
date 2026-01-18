package ejemplo10byAliasbyPos;


import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

import ejemplo9PaisesJugadores.Jugador;
import ejemplo9PaisesJugadores.Paises;

public class GetByAlias {
    public static void main(String[] args) {
        // Abrir la base de datos Neodatis
        ODB odb = ODBFactory.open("jugadores.DB");
        

        // Crear países
        Paises espania = new Paises(1, "España");
        Paises mexico = new Paises(2, "México");

        // Crear jugadores
        Jugador jugador1 = new Jugador("Juan", "Fútbol", 25, espania);
        Jugador jugador2 = new Jugador("Pedro", "Béisbol", 30, mexico);

        // Guardar datos en la base de datos
        odb.store(jugador1);
        odb.store(jugador2);


        System.out.println("Datos inicializados correctamente.");
    

        // Consulta sobre el nombre y el país (ciudad) de todos los jugadores
        Values valores = odb.getValues(new ValuesCriteriaQuery(Jugador.class)
                .field("nombre")
                .field("pais.nombrepais")); //navegamos por los atributos de la clase Paises

        // Visualizar los valores recuperados de la consulta
        while (valores.hasNext()) {
            ObjectValues objectValues = (ObjectValues) valores.next();
            System.out.printf("%s, Ciudad: %s %n",
                    objectValues.getByAlias("nombre"),  // Obtiene el nombre del jugador
                    objectValues.getByAlias("pais.nombrepais")); // Obtiene el nombre del país
        }

        // Cerrar la base de datos
        odb.close();
    }
}
