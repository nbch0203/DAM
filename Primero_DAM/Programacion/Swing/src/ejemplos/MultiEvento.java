package ejemplos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MultiEvento extends JFrame implements ActionListener {
	private JButton btnRojo, btnVerde, btnAzul;

	public MultiEvento() {
		setTitle("Ejemplo de múltiples eventos");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		btnRojo = new JButton("Rojo");
		btnVerde = new JButton("Verde");
		btnAzul = new JButton("Azul");

		btnRojo.addActionListener(this);
		btnVerde.addActionListener(this);
		btnAzul.addActionListener(this);

		add(btnRojo);
		add(btnVerde);
		add(btnAzul);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRojo) {
			getContentPane().setBackground(Color.RED);
		} else if (e.getSource() == btnVerde) {
			getContentPane().setBackground(Color.GREEN);
		} else if (e.getSource() == btnAzul) {
			getContentPane().setBackground(Color.BLUE);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MultiEvento().setVisible(true));
	}
}