package main;

import java.sql.SQLException;

import conexion.Conexion;

public class Main {
	public static void main(String[] args) {

		Conexion c = new Conexion();

		c.conectar();

		try {
			c.ejecutarConsulta();
			
			c.insertarLector(9, "Nixon", "Cruz", "nixonbcruzh@gmail.com", 2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		c.cerrar();

	}

}
