package ejemplos;

import javax.swing.*;

public class MiVentana extends JFrame {
	public MiVentana() {
		setTitle("Mi primera ventana");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MiVentana();
	}
}
