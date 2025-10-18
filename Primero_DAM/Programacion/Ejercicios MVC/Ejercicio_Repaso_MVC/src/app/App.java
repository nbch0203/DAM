package app;

import java.io.IOException;
import java.util.ArrayList;

import controlador.Controlador;
import modelo.*;
import vista.Interfaz;

public class App {
	public static void main(String[] args) {
		Interfaz vista = new Interfaz();
		GestorPedidos gp = new GestorPedidos();
		new Controlador(vista, gp);
		vista.setVisible(true);
	}
}
