package ejemplo8ExpresionesLogicas;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Or;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;


public class EjemploCriteriosLogica {
	 public static void main(String[] args) {
	        // Crear jugadores
	        Jugador j1 = new Jugador("María", "Madrid", 14);
	        Jugador j2 = new Jugador("Miguel", "Madrid", 15);
	        Jugador j3 = new Jugador("Luis", "Barcelona", 15);
	        Jugador j4 = new Jugador("Mario", "Madrid", 15);
	        Jugador j5 = new Jugador("Ana", "Valencia", 14);

	        // Abrir base de datos
	        ODB odb = ODBFactory.open("neodatis.test");

	        // Almacenar jugadores
	        odb.store(j1);
	        odb.store(j2);
	        odb.store(j3);
	        odb.store(j4);
	        odb.store(j5);

	        // -------------------------------
	        // 1. Jugadores de 15 años y de Madrid
	        // -------------------------------
	        System.out.println("Jugadores de 15 años de Madrid:");
	        ICriterion criterioAnd = new And()
	                .add(Where.equal("ciudad", "Madrid"))
	                .add(Where.equal("edad", 15));
	        Objects<Jugador> jugadoresAnd = odb.getObjects(new CriteriaQuery(Jugador.class, criterioAnd));
	        while (jugadoresAnd.hasNext()) {
	            Jugador jugador = jugadoresAnd.next();
	            System.out.println(jugador);
	        }

	        // -------------------------------
	        // 2. Jugadores de 15 años o de Madrid
	        // -------------------------------
	        System.out.println("\nJugadores de 15 años o de Madrid:");
	        ICriterion criterioOr = new Or()
	                .add(Where.equal("ciudad", "Madrid"))
	                .add(Where.equal("edad", 15));
	        Objects<Jugador> jugadoresOr = odb.getObjects(new CriteriaQuery(Jugador.class, criterioOr));
	        while (jugadoresOr.hasNext()) {
	            Jugador jugador = jugadoresOr.next();
	            System.out.println(jugador);
	        }

	        // -------------------------------
	        // 3. Jugadores que no empiezan por M
	        // -------------------------------
	        System.out.println("\nJugadores que no empiezan por M:");
	        ICriterion criterioNot = Where.not(Where.like("nombre", "M%"));
	        Objects<Jugador> jugadoresNot = odb.getObjects(new CriteriaQuery(Jugador.class, criterioNot));
	        while (jugadoresNot.hasNext()) {
	            Jugador jugador = jugadoresNot.next();
	            System.out.println(jugador);
	        }

	        // Cerrar base de datos
	        odb.close();
	    }
	}

	// Clase Jugador
	class Jugador {
	    private String nombre;
	    private String ciudad;
	    private int edad;

	    // Constructor
	    public Jugador(String nombre, String ciudad, int edad) {
	        this.nombre = nombre;
	        this.ciudad = ciudad;
	        this.edad = edad;
	    }

	    // Getters y Setters
	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getCiudad() {
	        return ciudad;
	    }

	    public void setCiudad(String ciudad) {
	        this.ciudad = ciudad;
	    }

	    public int getEdad() {
	        return edad;
	    }

	    public void setEdad(int edad) {
	        this.edad = edad;
	    }

	    // Método toString para mostrar resultados
	    @Override
	    public String toString() {
	        return "Jugador{" +
	                "nombre='" + nombre + '\'' +
	                ", ciudad='" + ciudad + '\'' +
	                ", edad=" + edad +
	                '}';
	    }
	}
