package ejemplo3ObjectIdentifier;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;
import org.neodatis.odb.core.oid.OIDFactory;

import ejemplo1AlmacenamientoOO.Jugador;

public class EjemploOID {
    public static void main(String[] args) {
        ODB odb = ODBFactory.open("neodatis.test"); // Abrir BD
        
        OID oid = OIDFactory.buildObjectOID(3); // Obtener objeto con OID 3
        
        // Visualizar los datos del jugador recuperado
        Jugador jug = (Jugador) odb.getObjectFromId(oid);
        System.out.printf("%s, %s, %s, %d %n",
                jug.getNombre(), jug.getDeporte(), jug.getCiudad(), jug.getEdad());
        
        odb.close(); // Cerrar BD
    }
}
