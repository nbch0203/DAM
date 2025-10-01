package ejemplos;

import javax.swing.*;
import java.awt.event.*;

public class EventoBoton extends JFrame {
	public EventoBoton() {
		JButton boton = new JButton("Haz clic");
		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "¡Botón presionado!");
			}
		});
		add(boton);
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new EventoBoton();
	}
}