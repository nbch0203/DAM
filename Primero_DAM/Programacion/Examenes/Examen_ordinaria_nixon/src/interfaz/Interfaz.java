package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class Interfaz extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField usuario, contrasena;
	private JRadioButton Profesor, Alumno;
	private JButton registrar, acceder, contar, buscar;
	private JTextArea textArea;

	public JButton getRegistrar() {
		return registrar;
	}

	public void setRegistrar(JButton registrar) {
		this.registrar = registrar;
	}

	public JButton getAcceder() {
		return acceder;
	}

	public void setAcceder(JButton acceder) {
		this.acceder = acceder;
	}

	public JButton getContar() {
		return contar;
	}

	public void setContar(JButton contar) {
		this.contar = contar;
	}

	public JButton getBuscar() {
		return buscar;
	}

	public void setBuscar(JButton buscar) {
		this.buscar = buscar;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos", TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel.setBounds(24, 25, 270, 121);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(12, 31, 70, 15);
		panel.add(lblNewLabel);

		usuario = new JTextField();
		usuario.setBounds(126, 29, 114, 19);
		panel.add(usuario);
		usuario.setColumns(10);

		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setBounds(12, 71, 108, 15);
		panel.add(lblContrasea);

		contrasena = new JTextField();
		contrasena.setBounds(126, 69, 114, 19);
		panel.add(contrasena);
		contrasena.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Rol", TitledBorder.LEFT,
				TitledBorder.TOP, null, Color.BLUE));
		panel_1.setBounds(326, 25, 252, 121);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		Profesor = new JRadioButton("Profesor");
		Profesor.setBounds(56, 23, 149, 23);
		panel_1.add(Profesor);

		Alumno = new JRadioButton("Alumno");
		Alumno.setBounds(56, 70, 149, 23);
		panel_1.add(Alumno);

		registrar = new JButton("Registrar");
		registrar.setBounds(36, 158, 117, 25);
		contentPane.add(registrar);

		acceder = new JButton("Acceder");
		acceder.setBounds(176, 158, 117, 25);
		contentPane.add(acceder);

		contar = new JButton("Contar");
		contar.setBounds(326, 158, 117, 25);
		contentPane.add(contar);

		buscar = new JButton("Buscar");
		buscar.setBounds(461, 158, 117, 25);
		contentPane.add(buscar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 195, 554, 163);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextField getUsuario() {
		return usuario;
	}

	public void setUsuario(JTextField usuario) {
		this.usuario = usuario;
	}

	public JTextField getContrasena() {
		return contrasena;
	}

	public void setContrasena(JTextField contrasena) {
		this.contrasena = contrasena;
	}

	public JRadioButton getProfesor() {
		return Profesor;
	}

	public void setProfesor(JRadioButton profesor) {
		Profesor = profesor;
	}

	public JRadioButton getAlumno() {
		return Alumno;
	}

	public void setAlumno(JRadioButton alumno) {
		Alumno = alumno;
	}

	public void showmessage(String texto) {
		JOptionPane.showMessageDialog(this, texto);
	}
}
