package ejemplo2RecuperarOO;

import org.neodatis.odb.*;
import org.neodatis.odb.Objects;

import ejemplo1AlmacenamientoOO.Jugador;

public class RecuperarJugadores {
    public static void main(String[] args) {
        // Abrir la base de datos
        ODB odb = ODBFactory.open("neodatis.test");

        // Recuperar todos los jugadores
        Objects<Jugador> jugadores = odb.getObjects(Jugador.class);
        System.out.printf("%d Jugadores: %n", jugadores.size());


        // Mostrar los datos de los jugadores
        while (jugadores.hasNext()) {
            Jugador jugador = jugadores.next();
            System.out.println("Nombre: " + jugador.getNombre() + ", Deporte: " + jugador.getDeporte());
        }

        // Cerrar la base de datos
        odb.close();
    }
}
