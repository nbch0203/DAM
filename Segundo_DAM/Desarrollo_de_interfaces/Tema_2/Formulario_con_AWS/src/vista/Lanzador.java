package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Lanzador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnRealizarUnaReserva;

	
	public static void main(String[] args) {
		Lanzador app = new Lanzador();
	}

	public Lanzador() {
		setTitle("Menú");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		btnRealizarUnaReserva = new JButton("Realizar una reserva");
		btnRealizarUnaReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Vista();
				setVisible(false);
			}
		});
		btnRealizarUnaReserva.setBounds(143, 96, 181, 25);
		contentPane.add(btnRealizarUnaReserva);
		
		setVisible(true);
	}
}