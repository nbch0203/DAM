package main;

import java.sql.SQLException;

import conexion.Conexion;

public class Main {
	public static void main(String[] args) {

		Conexion c = new Conexion();

		c.conectar();

		try {
//			c.borrarLector(2001);
////			c.actualizarCliente(2001, "nixonbcruzh@gmail.com");
//			c.buscarLibrosPorAnio(1981);
			c.registrarNuevoAutorPS(5, "a", "peruano");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		c.cerrar();

	}

}
