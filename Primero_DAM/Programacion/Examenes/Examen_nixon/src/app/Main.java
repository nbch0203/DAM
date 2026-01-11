package app;

import java.io.File;
import java.io.IOException;

import controlador.Controlador;
import modelo.Equipo;
import vista.Vista;

public class Main {
	public static void main(String[] args) {

//		Equipo modelo = new Equipo();
//		Vista vista = new Vista();
//		Controlador controlador = new Controlador(vista, modelo);
//		File file = new File("equipos.txt");
//		File file2 = new File("equipos.dat");
//		try {
//			file.createNewFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			file2.createNewFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Equipo modelo = new Equipo();
		Vista vista = new Vista();
		new Controlador(vista, modelo);
		vista.setVisible(true);
	}
}
