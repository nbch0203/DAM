
import controlador.Controlador;
import interfaz.Interfaz;
import modelo.Alumno;

public class App {

	public static void main(String[] args) {
		Alumno modelo = new Alumno();
		Interfaz vista = new Interfaz();
		new Controlador(modelo, vista);
		vista.setVisible(true);
	}

}
