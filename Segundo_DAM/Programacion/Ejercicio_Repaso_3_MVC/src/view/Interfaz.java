package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Interfaz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_nombre;
	private JTextField textField_correo;
	private JButton boton_enviar, boton_objetos;
	private JCheckBox checkbox;
	private JTextArea textArea;
	private JButton boton_mostrar;

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
		setTitle("Formulario Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 67);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel Nombre = new JLabel("Nombre:");
		Nombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Nombre.setBounds(39, 8, 81, 14);
		panel.add(Nombre);

		textField_nombre = new JTextField();
		textField_nombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_nombre.setBounds(130, 5, 169, 20);
		panel.add(textField_nombre);
		textField_nombre.setColumns(10);

		JLabel Correo = new JLabel("Correo:");
		Correo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Correo.setBounds(39, 42, 81, 14);
		panel.add(Correo);

		textField_correo = new JTextField();
		textField_correo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_correo.setBounds(130, 36, 169, 20);
		panel.add(textField_correo);
		textField_correo.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 89, 414, 67);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		boton_enviar = new JButton("Enviar");
		boton_enviar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		boton_enviar.setBounds(147, 33, 109, 23);
		panel_1.add(boton_enviar);

		checkbox = new JCheckBox("Incluir desayuno");
		checkbox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		checkbox.setBounds(137, 7, 144, 23);
		panel_1.add(checkbox);

		boton_objetos = new JButton("New button");
		boton_objetos.setBounds(266, 36, 85, 21);
		panel_1.add(boton_objetos);

		boton_mostrar = new JButton("New button");
		boton_mostrar.setBounds(32, 36, 85, 21);
		panel_1.add(boton_mostrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 170, 414, 80);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}

	public String getTextField_nombre() {
		String aux = textField_nombre.getText();
		return aux;
	}

	public void setTextField_nombre(JTextField textField_nombre) {
		this.textField_nombre = textField_nombre;
	}

	public String getTextField_correo() {
		String aux = textField_correo.getText();
		return aux;
	}

	public void setTextField_correo(JTextField textField_correo) {
		this.textField_correo = textField_correo;
	}

	public JButton getBoton_enviar() {
		return boton_enviar;
	}

	public void setBoton_enviar(JButton boton_enviar) {
		this.boton_enviar = boton_enviar;
	}

	public JCheckBox getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(JCheckBox checkbox) {
		this.checkbox = checkbox;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(String textArea) {
		this.textArea.setText(textArea);
	}

	public JButton getBoton_objetos() {
		return boton_objetos;
	}

	public void setBoton_objetos(JButton boton_objetos) {
		this.boton_objetos = boton_objetos;
	}

	public JButton getBoton_mostrar() {
		return boton_mostrar;
	}

	public void setBoton_mostrar(JButton boton_mostrar) {
		this.boton_mostrar = boton_mostrar;
	}

}
