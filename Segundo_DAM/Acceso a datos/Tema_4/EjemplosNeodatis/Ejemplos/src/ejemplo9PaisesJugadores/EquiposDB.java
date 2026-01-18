package ejemplo9PaisesJugadores;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Or;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;


public class EquiposDB {

	public static void main(String[] args) {
        ODB odb = ODBFactory.open("EQUIPOS.DB");

        // Crear países
        Paises irlanda = new Paises(1, "IRLANDA");
        Paises francia = new Paises(2, "FRANCIA");
        Paises italia = new Paises(3, "ITALIA");

        // Almacenar países
        odb.store(irlanda);
        odb.store(francia);
        odb.store(italia);

        // Crear jugadores
        Jugador j1 = new Jugador("Ana", "baloncesto", 14, irlanda);
        Jugador j2 = new Jugador("Luis", "fútbol", 14, francia);
        Jugador j3 = new Jugador("Mario", "tenis", 14, italia);
        Jugador j4 = new Jugador("Clara", "fútbol", 15, francia);

        // Almacenar jugadores
        odb.store(j1);
        odb.store(j2);
        odb.store(j3);
        odb.store(j4);

        odb.close();

        // Visualizar jugadores de 14 años de IRLANDA, FRANCIA, ITALIA
        visualizarJugadores14();

        // Actualizar edades de jugadores de un país
        actualizarEdades("FRANCIA");

        // Visualizar jugadores de un país y deporte
        visualizarJugadoresPorPaisYDeporte("FRANCIA", "fútbol");

        // Borrar un país de la base de datos
//        borrarPais("ITALIA");
    }

    // Método para visualizar jugadores de 14 años de IRLANDA, FRANCIA e ITALIA
    public static void visualizarJugadores14() {
        ODB odb = ODBFactory.open("EQUIPOS.DB");
        ICriterion criterio = new Or()
                .add(Where.equal("pais.nombrepais", "IRLANDA"))
                .add(Where.equal("pais.nombrepais", "FRANCIA"))
                .add(Where.equal("pais.nombrepais", "ITALIA"))
                .add(Where.equal("edad", 14));
        Objects<Jugador> jugadores = odb.getObjects(new CriteriaQuery(Jugador.class, criterio));

        System.out.println("\nJugadores de 14 años de IRLANDA, FRANCIA e ITALIA:");
        while (jugadores.hasNext()) {
            System.out.println(jugadores.next());
        }
        odb.close();
    }

    // Método para actualizar edades de jugadores de un país
    public static void actualizarEdades(String nombrePais) {
        ODB odb = ODBFactory.open("EQUIPOS.DB");
        ICriterion criterio = Where.equal("pais.nombrepais", nombrePais);
        Objects<Jugador> jugadores = odb.getObjects(new CriteriaQuery(Jugador.class, criterio));

        if (jugadores.isEmpty()) {
            System.out.println("\nNo hay jugadores del país " + nombrePais + ".");
        } else {
            while (jugadores.hasNext()) {
                Jugador jugador = jugadores.next();
                jugador.setEdad(jugador.getEdad() + 2);
                odb.store(jugador);
            }
            System.out.println("\nLas edades de los jugadores de " + nombrePais + " se han actualizado.");
        }
        odb.close();
    }

    // Método para visualizar jugadores de un país y deporte
    public static void visualizarJugadoresPorPaisYDeporte(String nombrePais, String deporte) {
        ODB odb = ODBFactory.open("EQUIPOS.DB");
        ICriterion criterio = new And()
                .add(Where.equal("pais.nombrepais", nombrePais))
                .add(Where.equal("deporte", deporte));
        Objects<Jugador> jugadores = odb.getObjects(new CriteriaQuery(Jugador.class, criterio));

        System.out.println("\nJugadores de " + nombrePais + " que practican " + deporte + ":");
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores.");
        } else {
            while (jugadores.hasNext()) {
                System.out.println(jugadores.next());
            }
        }
        odb.close();
    }

    // Método para borrar un país de la base de datos
    public static void borrarPais(String nombrePais) {
        ODB odb = ODBFactory.open("EQUIPOS.DB");
        ICriterion criterioPais = Where.equal("nombrepais", nombrePais);
        Objects<Paises> paises = odb.getObjects(new CriteriaQuery(Paises.class, criterioPais));

        if (paises.isEmpty()) {
            System.out.println("\nEl país " + nombrePais + " no existe en la base de datos.");
        } else {
            Paises pais = paises.getFirst();
            ICriterion criterioJugadores = Where.equal("pais.nombrepais", nombrePais);
            Objects<Jugador> jugadores = odb.getObjects(new CriteriaQuery(Jugador.class, criterioJugadores));

            if (!jugadores.isEmpty()) {
                System.out.println("\nEl país tiene jugadores asignados. Asignando null a sus países...");
                while (jugadores.hasNext()) {
                    Jugador jugador = jugadores.next();
                    jugador.setPais(null);
                    odb.store(jugador);
                }
            }
            odb.delete(pais);
            System.out.println("\nEl país " + nombrePais + " ha sido eliminado de la base de datos.");
        }
        odb.close();
    }
}
