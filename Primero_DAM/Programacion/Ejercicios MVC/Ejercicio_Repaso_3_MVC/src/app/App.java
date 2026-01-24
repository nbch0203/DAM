package app;

import model.Gesto_clientes;
import view.Interfaz;

public class App {

	public static void main(String[] args) {
		Interfaz vista = new Interfaz();
		Gesto_clientes modelo = new Gesto_clientes();
		new controller.Controlador(modelo, vista);
		vista.setVisible(true);

	}

}
