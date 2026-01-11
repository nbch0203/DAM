package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.SwingConstants;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Interfaz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JRadioButton Efectivo, Tarjeta;
	private JButton Reservar, Cancelar, Modificar;
	private JCheckBox Castellano, Ingles;
	private JComboBox<String> comboBox;
	private JTextArea textArea;

	

	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBox = comboBox;
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
		setTitle("Rerserva de tours");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 133, 414, 33);
		contentPane.add(panel_1);

		Reservar = new JButton("Reservar");
		panel_1.add(Reservar);

		Cancelar = new JButton("Cancelar");
		panel_1.add(Cancelar);

		Modificar = new JButton("Modificar");
		panel_1.add(Modificar);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 111);
		contentPane.add(panel);

		JLabel Nombre = new JLabel("Nombre:");
		Nombre.setBounds(21, 11, 46, 14);

		textField = new JTextField();
		textField.setBounds(77, 8, 86, 20);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(21, 36, 46, 14);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(21, 52, 142, 22);
		comboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "", "Free Tour", "Leyendas nocturnas", "Leyendas vivientes", "Tour express" }));

		Castellano = new JCheckBox("Castellano");
		Castellano.setBounds(202, 7, 97, 23);

		Ingles = new JCheckBox("Ingles");
		Ingles.setBounds(311, 7, 97, 23);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(202, 36, 202, 64);
		panel_2.setLayout(null);

		Tarjeta = new JRadioButton("Tarjeta");
		Tarjeta.setBounds(20, 33, 84, 23);
		panel_2.add(Tarjeta);

		Efectivo = new JRadioButton("Efectivo");
		Efectivo.setBounds(106, 33, 76, 23);
		panel_2.add(Efectivo);

		JLabel lblNewLabel_2 = new JLabel("Forma de pago");
		lblNewLabel_2.setLabelFor(panel_2);
		lblNewLabel_2.setBounds(31, 11, 100, 14);
		panel_2.add(lblNewLabel_2);
		panel_2.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { Tarjeta, Efectivo }));
		panel.setLayout(null);
		panel.add(Nombre);
		panel.add(textField);
		panel.add(lblNewLabel_1);
		panel.add(comboBox);
		panel.add(Castellano);
		panel.add(Ingles);
		panel.add(panel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 177, 414, 76);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setBackground(Color.PINK);
		textArea.setText("Pulsa un botón para ver resultados");
		textArea.setForeground(Color.BLACK);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
	}
	public JButton getCancelar() {
		return Cancelar;
	}

	public void setCancelar(JButton cancelar) {
		Cancelar = cancelar;
	}

	public JButton getModificar() {
		return Modificar;
	}

	public void setModificar(JButton modificar) {
		Modificar = modificar;
	}

	

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(String string) {
		this.textArea.setText(string);
	}

	public String getTextField() {
		String aux = textField.getText();
		return aux;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JRadioButton getEfectivo() {
		return Efectivo;
	}

	public void setEfectivo(JRadioButton efectivo) {
		Efectivo = efectivo;
	}

	public JRadioButton getTarjeta() {
		return Tarjeta;
	}

	public void setTarjeta(JRadioButton tarjeta) {
		Tarjeta = tarjeta;
	}

	public JButton getReservar() {
		return Reservar;
	}

	public void setReservar(JButton reservar) {
		Reservar = reservar;
	}

	public JCheckBox getCastellano() {
		return Castellano;
	}

	public void setCastellano(JCheckBox castellano) {
		Castellano = castellano;
	}

	public JCheckBox getIngles() {
		return Ingles;
	}

	public void setIngles(JCheckBox ingles) {
		Ingles = ingles;
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}
}
