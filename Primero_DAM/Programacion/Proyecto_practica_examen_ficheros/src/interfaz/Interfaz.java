package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class Interfaz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombre;
	private JTextField edad;
	private JTextField nota;
	private JTextArea textArea;
	private JButton Guardartxt, GuardarObj, Leertxt, leerObjt;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 81);
		contentPane.add(panel);
		panel.setLayout(null);

		nombre = new JTextField();
		nombre.setBounds(69, 11, 86, 20);
		panel.add(nombre);
		nombre.setColumns(10);

		edad = new JTextField();
		edad.setBounds(255, 11, 86, 20);
		panel.add(edad);
		edad.setColumns(10);

		JLabel Nombre = new JLabel("Nombre:");
		Nombre.setBounds(13, 14, 46, 14);
		panel.add(Nombre);

		nota = new JTextField();
		nota.setBounds(69, 42, 86, 20);
		panel.add(nota);
		nota.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nota:");
		lblNewLabel.setBounds(13, 45, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Edad:");
		lblNewLabel_1.setBounds(189, 14, 46, 14);
		panel.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 149, 414, 101);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		Guardartxt = new JButton("Guardar Txt");
		Guardartxt.setBounds(10, 115, 103, 23);
		contentPane.add(Guardartxt);

		GuardarObj = new JButton("Guardar OBJ");
		GuardarObj.setBounds(123, 115, 100, 23);
		contentPane.add(GuardarObj);

		Leertxt = new JButton("Leer txt");
		Leertxt.setBounds(233, 115, 89, 23);
		contentPane.add(Leertxt);

		leerObjt = new JButton("Leer OBj");
		leerObjt.setBounds(332, 115, 89, 23);
		contentPane.add(leerObjt);
	}

	public JTextField getNombre() {
		return nombre;
	}

	public void setNombre(JTextField nombre) {
		this.nombre = nombre;
	}

	public JTextField getEdad() {
		return edad;
	}

	public void setEdad(JTextField edad) {
		this.edad = edad;
	}

	public JTextField getNota() {
		return nota;
	}

	public void setNota(JTextField nota) {
		this.nota = nota;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JButton getGuardartxt() {
		return Guardartxt;
	}

	public void setGuardartxt(JButton guardartxt) {
		Guardartxt = guardartxt;
	}

	public JButton getGuardarObj() {
		return GuardarObj;
	}

	public void setGuardarObj(JButton guardarObj) {
		GuardarObj = guardarObj;
	}

	public JButton getLeertxt() {
		return Leertxt;
	}

	public void setLeertxt(JButton leertxt) {
		Leertxt = leertxt;
	}

	public JButton getLeerObjt() {
		return leerObjt;
	}

	public void setLeerObjt(JButton leerObjt) {
		this.leerObjt = leerObjt;
	}

	public void showMessage(String txt) { // Metodo para mostrar avisos
		JOptionPane.showMessageDialog(this, txt);
	}

}
