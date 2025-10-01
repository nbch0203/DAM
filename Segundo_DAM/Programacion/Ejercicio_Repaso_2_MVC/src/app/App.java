package app;

import controlador.Controlador;
import modelo.Gestor_de_reservas;
import vista.Interfaz;

public class App {
	public static void main(String[] args) {
		Interfaz vista = new Interfaz();
		Gestor_de_reservas modelo = new Gestor_de_reservas();
		new Controlador(modelo, vista);
		vista.setVisible(true);
	}

}
