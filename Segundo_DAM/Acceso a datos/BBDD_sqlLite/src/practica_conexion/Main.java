package practica_conexion;

public class Main {
	public static void main(String[] args) {
		Conexion c = new Conexion();
		c.conectar();
//		c.creartablas();
//		c.añadirCancion();
		c.consultarCancionesPorGenero("musica");
		c.desconectar();
	}

}
