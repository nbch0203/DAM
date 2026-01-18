package ejemplo11funciones;


import java.math.BigDecimal;
import java.math.BigInteger;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

import ejemplo1AlmacenamientoOO.Jugador;



public class EstadisticasJugadores {
    public static void main(String[] args) {
        ODB odb = ODBFactory.open("jugadores.DB");

        // SUMA - Obtiene la suma de las edades
        //select sum(edad) from jugadores
        System.out.println("-------- SUMA --------");
        Values val = odb.getValues(new ValuesCriteriaQuery(Jugador.class).sum("edad"));
        ObjectValues ov = val.nextValues();
        BigDecimal value = (BigDecimal) ov.getByAlias("edad");
        System.out.printf("Suma de edad : %d %n", value.longValue());

        System.out.println("------------------------------------");
        System.out.printf("Suma de edad : %.2f %n", ov.getByAlias("edad"));

        // CUENTA - Obtiene el número de jugadores
        //select count(nombre) from jugadores
        System.out.println("-------- CUENTA --------");
        Values val2 = odb.getValues(new ValuesCriteriaQuery(Jugador.class).count("nombre"));
        ObjectValues ov2 = val2.nextValues();
        BigInteger value2 = (BigInteger) ov2.getByAlias("nombre");
        System.out.printf("Número de jugadores : %d %n", value2.intValue());

        // MEDIA - Obtiene la edad media de los jugadores
        //select avg(edad) from jugadores
        System.out.println("-------- MEDIA --------");
        Values val3 = odb.getValues(new ValuesCriteriaQuery(Jugador.class).avg("edad"));
        ObjectValues ov3 = val3.nextValues();
        BigDecimal value3 = (BigDecimal) ov3.getByAlias("edad");
        System.out.printf("Edad media : %.2f %n", value3.floatValue());
        
        // Otra implementación para visualizar la media de edad
        visualizarmediedad(odb);
        

        // MÁXIMO Y MÍNIMO - Obtiene la edad máxima y mínima
        //select max(edad) edad_max,min(edad) edad_min form jugadores
        System.out.println("-------- MÁXIMO Y MÍNIMO --------");
        Values val4 = odb.getValues(new ValuesCriteriaQuery(Jugador.class).max("edad", "edad_max"));
        ObjectValues ov4 = val4.nextValues();
        BigDecimal maxima = (BigDecimal) ov4.getByAlias("edad_max");

        Values val5 = odb.getValues(new ValuesCriteriaQuery(Jugador.class).min("edad", "edad_min"));
        ObjectValues ov5 = val5.nextValues();
        BigDecimal minima = (BigDecimal) ov5.getByAlias("edad_min");
        System.out.printf("Edad máxima : %d, Edad mínima : %d %n", maxima.intValue(), minima.intValue());


        //GROUP BY
        //select ciudad,count(nombre) from jugador group by ciudad
        System.out.println("-------- GROUP BY --------");

        Values groupby=odb.getValues(new ValuesCriteriaQuery(Jugador.class).field("ciudad").count("nombre").groupBy("ciudad"));

        while(groupby.hasNext()) {
        	ObjectValues objetos=(ObjectValues)groupby.next();
        	System.out.printf("%s, %d%n",objetos.getByAlias("ciudad"),objetos.getByIndex(1));
        }
        
        // Cerrar la base de datos
        odb.close();
    }

    private static void visualizarmediedad(ODB odb) {
        System.out.println("-------- VISUALIZAR MEDIA DE EDAD --------");
        Values val;
        ObjectValues ov;
        try {
            val = odb.getValues(new ValuesCriteriaQuery(Jugador.class).avg("edad"));
            ov = val.nextValues();
            System.out.printf("AVG - La media de edad es: %f %n", ov.getByIndex(0));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }

        Values val2 = odb.getValues(new ValuesCriteriaQuery(Jugador.class).sum("edad").count("edad"));
        ObjectValues ov2 = val2.nextValues();
        float media;
        BigDecimal sumaedad = (BigDecimal) ov2.getByIndex(0);
        BigInteger cuenta = (BigInteger) ov2.getByIndex(1);
        media = sumaedad.floatValue() / cuenta.floatValue();
        System.out.printf("La media de edad es: %.2f Contador = %d Suma = %.2f %n", media, cuenta, sumaedad);
    }
}
