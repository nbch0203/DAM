package app;

import java.sql.Date;

import conexion_bbdd.Conexion;

public class App {

	public static void main(String[] args) {
		Conexion cn = new Conexion();
		cn.conectar();
		try {
			cn.insertarCliente(7, "Nixon", "Calle calle", "611254628", Date.valueOf("2003-04-02"));
			cn.ejecutarConsulta();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			cn.cerrar();
		}

	}

}
