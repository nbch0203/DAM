package conexion;

public class Main {
	public static void main(String[] args) {
		ConexionSQLite c= new ConexionSQLite();
		
		c.conectar();
		
//		c.crearTablas();
//		c.insertarAlumnos();
//		c.insertarCursos();
//		c.insertarMatriculas();
//		c.consultaHaving();
		c.consultaJoin();
		
		c.desconectar();

	}
}
