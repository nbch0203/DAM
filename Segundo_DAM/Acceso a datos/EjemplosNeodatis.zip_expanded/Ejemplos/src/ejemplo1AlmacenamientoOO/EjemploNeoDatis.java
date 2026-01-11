package ejemplo1AlmacenamientoOO;

import org.neodatis.odb.*;

public class EjemploNeoDatis {
    public static void main(String[] args) {
    	// Crear instancias para almacenar en BD
        Jugador j1 = new Jugador("María", "voleibol", "Madrid", 14);
        Jugador j2 = new Jugador("Miguel", "tenis", "Madrid", 15);
        Jugador j3 = new Jugador("Mario", "baloncesto", "Guadalajara", 15);
        Jugador j4 = new Jugador("Alicia", "tenis", "Madrid", 14);
        

        // Abrir la base de datos
        ODB odb = ODBFactory.open("neodatis.test");

        // Almacenamos objetos
        odb.store(j1);
        odb.store(j2);
        odb.store(j3);
        odb.store(j4);

        // Cerrar la base de datos
        odb.close();
    }
}

