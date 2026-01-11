package ejemplo10byAliasbyPos;


import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

import ejemplo9PaisesJugadores.Jugador;
import ejemplo9PaisesJugadores.Paises;

public class GetByIndex {
    public static void main(String[] args) {
        // Abrir la base de datos Neodatis
        ODB odb = ODBFactory.open("jugadores.DB");

        try {
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
                    .field("pais.nombrepais")); // Navegamos por los atributos de la clase Paises

            // Visualizar los valores recuperados de la consulta
            while (valores.hasNext()) {
                ObjectValues objectValues = (ObjectValues) valores.next();

                // Recuperar los valores por índice
                Object nombre = objectValues.getByIndex(0); // Primer campo: nombre
                Object ciudad = objectValues.getByIndex(1); // Segundo campo: pais.nombrepais

                // Imprimir los resultados con manejo de nulos
                System.out.printf("%s, Ciudad: %s %n",
                        nombre != null ? nombre.toString() : "Nombre no disponible",
                        ciudad != null ? ciudad.toString() : "Ciudad no disponible");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar la base de datos
            if (odb != null) {
                odb.close();
            }
        }
    }
}
