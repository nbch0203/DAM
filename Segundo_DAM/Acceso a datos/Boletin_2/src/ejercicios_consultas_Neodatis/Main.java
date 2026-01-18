package ejercicios_consultas_Neodatis;

import datos_necesarios.Jugador;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Or;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class Main {
	private static ODB odb;

	// 0. Consultar la información de todos los jugadores y sus países.

	public static void ejercicio_0() {
		System.out.println("Consultar la información de todos los jugadores y sus países");

		Objects<Jugador> jugadores = odb.getObjects(Jugador.class);

		while (jugadores.hasNext()) {
			System.out.println(jugadores.next());
		}
		odb.close();

	}

	// 1. Consultar jugadores mayores de 20 años y que practiquen fútbol (AND)

	public static void ejercicio_1(String deporte, int edad) {
		System.out.println("\nJugadores que tengan mas de 20 años y jueguen futbol ");
		ICriterion criterio = new And().add(Where.gt("edad", edad)).add(Where.equal("deporte", deporte));

		IQuery query = new CriteriaQuery(Jugador.class, criterio);

		Objects<Jugador> jugadores = odb.getObjects(query);

		while (jugadores.hasNext()) {
			Jugador jugador = jugadores.next();
			System.out.println(jugador.toString());
		}
		odb.close();
	}

	// 2. Consultar jugadores que sean de FRANCIA o jueguen baloncesto (OR)

	public static void ejercicio_2(String pais, String deporte) {

		System.out.println("\nJugadores que sean de FRANCIA o jueguen baloncesto");

		ICriterion criterio = new Or().add(Where.equal("pais.nombrepais", pais)).add(Where.equal("deporte", deporte));

		IQuery query = new CriteriaQuery(Jugador.class, criterio);

		Objects<Jugador> jugadores = odb.getObjects(query);

		while (jugadores.hasNext()) {
			Jugador type = jugadores.next();
			System.out.println(type);
		}
		odb.close();
	}

	// 3. Contar cuántos jugadores hay por cada deporte. (COUNT+GROUP BY)
	public static void ejercicio_3() {
		System.out.println("\nContar cuántos jugadores hay por cada deporte");

//		Objects<Jugador> jugadores = odb.getObjects(Jugador.class);

		Values valores = odb
				.getValues(new ValuesCriteriaQuery(Jugador.class).field("deporte").count("nombre").groupBy("deporte"));

		while (valores.hasNext()) {

			System.out.println(valores.next());
		}

//		HashMap<String, Integer> contador = new HashMap<>();
//
//		// 3. Recorrer todos los jugadores y contar cuántos hay de cada deporte
//		while (jugadores.hasNext()) {
//			Jugador j = jugadores.next();
//			String deporte = j.getDeporte();
//
//			// Si el deporte ya está en el mapa, sumo 1. Si no, lo pongo a 1
//			if (contador.containsKey(deporte)) {
//				int actual = contador.get(deporte);
//				contador.put(deporte, actual + 1);
//			} else {
//				contador.put(deporte, 1);
//			}
//		}
//
//		System.out.println("\nCuántos jugadores hay por cada deporte:");
//		for (String deporte : contador.keySet()) {
//			System.out.println("\n" + deporte + ": " + contador.get(deporte) + " jugadores");
//		}
		odb.close();
	}

	// 4. Calcular la edad promedio de los jugadores

	public static void ejercicio_4() {
		System.out.println("Calcular la edad promedio de los jugadores");

//		Objects<Jugador> jugadores = odb.getObjects(Jugador.class);

		Values values = odb.getValues(new ValuesCriteriaQuery(Jugador.class).avg("edad"));

		System.out.println("La edad media es de : " + values.nextValues().getByIndex(0));

		odb.close();

	}

	// 5. Obtener la edad mínima y máxima de los jugadores

	public static void ejercicio_5() {

		System.out.println("La edad mínima y máxima de los jugadores");

		CriteriaQuery query_maxima = new CriteriaQuery(Jugador.class);

		Values values = odb.getValues(new ValuesCriteriaQuery(query_maxima).max("edad"));

		while (values.hasNext()) {
			System.out.println(values.next());
		}

		values = odb.getValues(new ValuesCriteriaQuery(query_maxima).min("edad"));

		while (values.hasNext()) {
			System.out.println(values.next());
		}

		odb.close();

	}

	// 6. Obtener jugadores que juegan fútbol y tienen entre 18 y 25 años (AND con
	// rango de valores)

	public static void ejercicio_6() {
		System.out.println("Jugadores que juegan fútbol y tienen entre 18 y 25 años");

		ICriterion criterio = new And().add(Where.equal("deporte", "fútbol")).add(Where.gt("edad", 18))
				.add(Where.lt("edad", 25));

		IQuery query = new CriteriaQuery(Jugador.class, criterio);

		Objects<Jugador> jugadores = odb.getObjects(query);

		while (jugadores.hasNext()) {
			System.out.println(jugadores.next());
		}

		odb.close();

	}

	// 7. Obtener jugadores de FRANCIA o ITALIA (OR con múltiples valores)

	public static void ejercicio_7() {
		System.out.println("Obtener jugadores de FRANCIA o ITALIA");

		ICriterion criterio = new Or().add(Where.equal("pais.nombrepais", "FRANCIA"))
				.add(Where.equal("pais.nombrepais", "ITALIA"));
		IQuery query = new CriteriaQuery(Jugador.class, criterio);

		Objects<Jugador> jugadores = odb.getObjects(query);

		while (jugadores.hasNext()) {
			System.out.println(jugadores.next());
		}
		odb.close();
	}

	// 8. Obtener jugadores que juegan baloncesto y no tienen más de 22 años (AND +
	// NOT)

	public static void ejercicio_8() {

		System.out.println("Jugadores que juegan baloncesto y no tienen más de 22 años");

		ICriterion criterio = new And().add(Where.equal("deporte", "baloncesto")).add(Where.not(Where.gt("edad", 22)));

		IQuery query = new CriteriaQuery(Jugador.class, criterio);

		Objects<Jugador> jugadores = odb.getObjects(query);

		while (jugadores.hasNext()) {
			System.out.println(jugadores.next());
		}
		odb.close();
	}

	// 9. Contar jugadores que son de FRANCIA o practican tenis (COUNT + OR)

	public static void ejercicio_9() {

		System.out.println("Jugadores que son de FRANCIA o practican tenis (COUNT + OR)");

		ICriterion criterio = new Or().add(Where.equal("pais.nombrepais", "FRANCIA"))
				.add(Where.equal("deporte", "tenis"));

		Values values = odb.getValues(new ValuesCriteriaQuery(Jugador.class, criterio).count("nombre"));

		while (values.hasNext()) {
			System.out.println(values.next());
		}
		odb.close();
	}

	// 10. Obtener la edad promedio de jugadores que juegan tenis o tienen más de
	// 22 años (AVG + OR)
	public static void ejercicio_10() {
		System.out.println("Obtener la edad promedio de jugadores que juegan tenis o tienen más de 22 años");

		ICriterion criterio = new Or().add(Where.equal("deporte", "tenis")).add(Where.gt("edad", 22));

		Values values = odb.getValues(new ValuesCriteriaQuery(Jugador.class, criterio).avg("edad"));

		while (values.hasNext()) {
			System.out.println(values.next());
		}

	}

	public static void main(String[] args) {
		odb = ODBFactory.open("EQUIPOS.DB");

//		ejercicio_0();
//		ejercicio_1("fútbol", 20);
//		ejercicio_2("FRANCIA", "baloncesto");
//		ejercicio_3();
//		ejercicio_4();
//		ejercicio_5();
//		ejercicio_6();
//		ejercicio_7();
//		ejercicio_8();
//		ejercicio_9();
//		ejercicio_10();

	}
}
