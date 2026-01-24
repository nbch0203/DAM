package ejemplo7containsOO;

import org.neodatis.odb.*;
import org.neodatis.odb.core.query.criteria.*;
import java.util.List;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;


public class EjemploWhereCriterios {
    public static void main(String[] args) {
        // Crear algunos objetos Jugador
        Jugador j1 = new Jugador("María", List.of("Real Madrid", "Barcelona"), "Madrid");
        Jugador j2 = new Jugador("Miguel", List.of("Atlético Madrid", "Valencia"), null);
        Jugador j3 = new Jugador("Luis", List.of("Barcelona", "Sevilla"), "Sevilla");
        Jugador j4 = new Jugador("Ana", null, "Bilbao");

        // Abrir la base de datos
        ODB odb = ODBFactory.open("neodatis.test");

        // Almacenar los jugadores
        odb.store(j1);
        odb.store(j2);
        odb.store(j3);
        odb.store(j4);

        // ----------------------
        // 1. Uso de Where.contain
        // ----------------------
        System.out.println("Jugadores que tienen 'Barcelona' en su lista de equipos:");
        ICriterion criterioContain = Where.contain("equipos", "Barcelona");
        Objects<Jugador> jugadoresContain = odb.getObjects(new CriteriaQuery(Jugador.class, criterioContain));
        while (jugadoresContain.hasNext()) {
            Jugador jugador = jugadoresContain.next();
            System.out.println("Nombre: " + jugador.getNombre() + ", Equipos: " + jugador.getEquipos());
        }

        // ----------------------
        // 2. Uso de Where.isNull
        // ----------------------
        System.out.println("\nJugadores cuya ciudad es NULL:");
        ICriterion criterioIsNull = Where.isNull("ciudad");
        Objects<Jugador> jugadoresIsNull = odb.getObjects(new CriteriaQuery(Jugador.class, criterioIsNull));
        while (jugadoresIsNull.hasNext()) {
            Jugador jugador = jugadoresIsNull.next();
            System.out.println("Nombre: " + jugador.getNombre() + ", Ciudad: " + jugador.getCiudad());
        }

        // ----------------------
        // 3. Uso de Where.isNotNull
        // ----------------------
        System.out.println("\nJugadores cuya ciudad NO es NULL:");
        ICriterion criterioIsNotNull = Where.isNotNull("ciudad");
        Objects<Jugador> jugadoresIsNotNull = odb.getObjects(new CriteriaQuery(Jugador.class, criterioIsNotNull));
        while (jugadoresIsNotNull.hasNext()) {
            Jugador jugador = jugadoresIsNotNull.next();
            System.out.println("Nombre: " + jugador.getNombre() + ", Ciudad: " + jugador.getCiudad());
        }

        // Cerrar la base de datos
        odb.close();
    }
}

// Clase Jugador
class Jugador {
    private String nombre;
    private List<String> equipos; // Lista de equipos a los que pertenece
    private String ciudad; // Ciudad asociada al jugador, que puede ser null

    // Constructor
    public Jugador(String nombre, List<String> equipos, String ciudad) {
        this.nombre = nombre;
        this.equipos = equipos;
        this.ciudad = ciudad;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<String> equipos) {
        this.equipos = equipos;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}