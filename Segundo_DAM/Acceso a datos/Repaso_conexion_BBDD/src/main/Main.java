package main;

import java.sql.SQLException;

import conexion.Conexion;

public class Main {
	public static void main(String[] args) {

		Conexion c = new Conexion();

		c.conectar();

		//			c.registrarPrestamoSeguro(1, "9788497592208");
		c.aplicarMulta(2001, 2.5);

		c.cerrar();

	}

}
