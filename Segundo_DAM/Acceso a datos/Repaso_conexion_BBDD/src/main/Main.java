package main;

import java.sql.SQLException;

import conexion.Conexion;

public class Main {
	public static void main(String[] args) {

		Conexion c = new Conexion();

		c.conectar();

		try {
			c.ejecutarConsulta();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		c.cerrar();

	}

}
