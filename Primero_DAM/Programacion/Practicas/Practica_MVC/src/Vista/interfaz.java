package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout.Group;

public class interfaz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombre;
	private JTextField edad;
	private JTextField peso;
	private JButton cargarobj,cargartxt,Guardarobj,guardarTXT;
	private JTextArea textArea;
	private JComboBox e;
	private JRadioButton hembra,macho;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfaz frame = new interfaz();
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
	public interfaz() {
		setResizable(false);
		setForeground(new Color(0, 0, 0));
		setTitle("Gestor de animales");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nombre = new JTextField();
		nombre.setFont(new Font("Arial", Font.PLAIN, 14));
		nombre.setBounds(162, 10, 242, 19);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		peso = new JTextField();
		peso.setFont(new Font("Arial", Font.PLAIN, 14));
		peso.setBounds(162, 68, 242, 19);
		contentPane.add(peso);
		peso.setColumns(10);
		
		edad = new JTextField();
		edad.setFont(new Font("Arial", Font.PLAIN, 14));
		edad.setBounds(162, 39, 242, 19);
		contentPane.add(edad);
		edad.setColumns(10);
		
		JComboBox especie = new JComboBox();
		especie.setModel(new DefaultComboBoxModel(new String[] {"Perro", "Gato", "Ave", "Conejo"}));
		especie.setToolTipText("");
		especie.setMaximumRowCount(4);
		especie.setFont(new Font("Arial", Font.PLAIN, 14));
		especie.setBounds(162, 97, 242, 21);
		contentPane.add(especie);
		
		JRadioButton macho = new JRadioButton("Macho");
		macho.setFont(new Font("Arial", Font.PLAIN, 14));
		macho.setBounds(162, 138, 103, 21);
		contentPane.add(macho);
		
		
		JRadioButton hembra = new JRadioButton("Hembra");
		hembra.setFont(new Font("Arial", Font.PLAIN, 14));
		hembra.setBounds(285, 138, 103, 21);
		contentPane.add(hembra);

		ButtonGroup group = new ButtonGroup();
		group.add(hembra);
		group.add(macho);
		
		textArea = new JTextArea();
		textArea.setBounds(20, 246, 456, 57);
		contentPane.add(textArea);
		
		guardarTXT = new JButton("GuardarTXT");
		guardarTXT.setFont(new Font("Arial", Font.PLAIN, 14));
		guardarTXT.setBounds(20, 188, 116, 48);
		contentPane.add(guardarTXT);
		
		Guardarobj = new JButton("Guardar OBJ");
		Guardarobj.setFont(new Font("Arial", Font.PLAIN, 14));
		Guardarobj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Guardarobj.setBounds(130, 188, 121, 48);
		contentPane.add(Guardarobj);
		
		cargartxt = new JButton("CargarOBJ");
		cargartxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cargartxt.setFont(new Font("Arial", Font.PLAIN, 14));
		cargartxt.setBounds(360, 188, 116, 48);
		contentPane.add(cargartxt);
		
		cargarobj = new JButton("Cargar TXT");
		cargarobj.setFont(new Font("Arial", Font.PLAIN, 14));
		cargarobj.setBounds(248, 188, 116, 48);
		contentPane.add(cargarobj);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(43, 13, 60, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Edad:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(43, 42, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Peso");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(43, 71, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Especie:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(43, 101, 60, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sexo:");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(43, 142, 45, 13);
		contentPane.add(lblNewLabel_4);
	}

	public JButton getCargarobj() {
		return cargarobj;
	}

	public void setCargarobj(JButton cargarobj) {
		this.cargarobj = cargarobj;
	}

	public JButton getCargartxt() {
		return cargartxt;
	}

	public void setCargartxt(JButton cargartxt) {
		this.cargartxt = cargartxt;
	}

	public JButton getGuardarobj() {
		return Guardarobj;
	}

	public void setGuardarobj(JButton guardarobj) {
		Guardarobj = guardarobj;
	}

	public JButton getGuardarTXT() {
		return guardarTXT;
	}

	public void setGuardarTXT(JButton guardarTXT) {
		this.guardarTXT = guardarTXT;
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

	public JTextField getPeso() {
		return peso;
	}

	public void setPeso(JTextField peso) {
		this.peso = peso;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JComboBox getE() {
		return e;
	}

	public void setE(JComboBox e) {
		this.e = e;
	}
	
	
}
