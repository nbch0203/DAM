package ejemplo12funciones;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

import ejemplo9PaisesJugadores.*;

public class Vistas {

	public static void main(String[] args) {
//		JugadoresPaises();
		nombreCiudad("ITALIA", 14);
	}

//obtener el nombre y la ciudad de los jugadores cuyo pais es ESPAÑA y edad igual a 15
	private static void nombreCiudad(String pais, int edad) {
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		Values valores = odb.getValues(new ValuesCriteriaQuery(Jugador.class,
				new And().add(Where.equal("pais.nombrepais", pais)).add(Where.equal("edad", edad))).field("nombre")
				.field("deporte"));

		while (valores.hasNext()) {
			ObjectValues objectValues = (ObjectValues) valores.next();
			System.out.printf("Nombre: %s,deporte: %s %n", objectValues.getByIndex(0), objectValues.getByIndex(1));
		}

	}

	/*
	 * obtener el nombre, edad y país del jugador; en SQL sería como realizar la
	 * siguiente consulta: SELECT nombre, edad, nombrepais FROM Jugadores j, Paises
	 * p WHERE j.id = p.d;
	 */
	private static void JugadoresPaises() {
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		Values valores = odb.getValues(
				new ValuesCriteriaQuery(Jugador.class).field("nombre").field("edad").field("pais.nombrepais"));

		while (valores.hasNext()) {
			ObjectValues objectValues = (ObjectValues) valores.next();
			System.out.printf("Nombre: %s, Edad: %s, Pais: %s %n", objectValues.getByAlias("nombre"), // O usa
																										// getByIndex(0)
					objectValues.getByIndex(1), objectValues.getByIndex(2));
		}

		odb.close();
	}

}
