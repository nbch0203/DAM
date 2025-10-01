package app;

import java.io.File;
import java.io.IOException;

import controlador.Controlador;
import interfaz.Interfaz;
import modelo.Usuario;

public class App {
	public static void main(String[] args) {

		Usuario modelo = new Usuario();
		Interfaz vista = new Interfaz();
		new Controlador(modelo, vista);
		vista.setVisible(true);
	}
}
