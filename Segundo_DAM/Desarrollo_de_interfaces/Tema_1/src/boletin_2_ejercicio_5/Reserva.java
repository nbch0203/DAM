package boletin_2_ejercicio_5;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextPane;

public class Reserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelContacto;
	private JTextField textFieldNombre;
	private JTextField textFieldNumero;
	private JTextField textFieldNumeroPersonas;
	private JPanel panelTipoEvento;
	private SpinnerDateModel dateModel;
	private JSpinner spinnerFecha;
	private ButtonGroup btnGroup;
	private JRadioButton rdbtnBanquete, rdbtnJornada, rdbtnCongreso; 
	private JLabel lblCocina;
	private String[] opcionesCocina = {"Buffet libre", "Carta", "Pedir cita con el chef", "No precisa"};
	private JComboBox<String> comboBoxCocina;
	private JPanel panelReserva;
	private JLabel lblDias;
	private SpinnerNumberModel modelSpinerDias;
	private JSpinner spinnerDias;
	private JButton btnAceptar, btnCancelar; 
	private JCheckBox chckbxNecesitaranHabitacion;
	private JTextPane textPane;
	private Object valorInicialFecha;
	private Object valorInicialDias;
	
	public Reserva() {
		setTitle("Reserva");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JLabel lblReservasDelSalon = new JLabel("RESERVAS DEL SALÓN HAVANA");
		lblReservasDelSalon.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblReservasDelSalon.setBounds(28, 23, 361, 41);
		contentPane.add(lblReservasDelSalon);
		
		panelContacto = new JPanel();
		panelContacto.setBorder(new TitledBorder(null, "Datos de contacto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelContacto.setBounds(28, 72, 361, 84);
		panelContacto.setLayout(null);
		contentPane.add(panelContacto);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 23, 55, 15);
		panelContacto.add(lblNombre);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(12, 50, 62, 15);
		panelContacto.add(lblTelefono);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setToolTipText("Introduce el nombre completo de la persona que realiza la reserva");
		textFieldNombre.setBounds(85, 21, 264, 19);
		panelContacto.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setToolTipText("Introduce un teléfono de contacto de 9 dígitos");
		textFieldNumero.setColumns(10);
		textFieldNumero.setBounds(85, 48, 133, 19);
		
		// Únicamente permitimos introducir números
		textFieldNumero.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        // Solo permite dígitos y limita a 9 caracteres
		        if (!Character.isDigit(c) || textFieldNumero.getText().length() >= 9) {
		            e.consume();
		        }
		    }
		});
		panelContacto.add(textFieldNumero);
		
		panelReserva = new JPanel();
		panelReserva.setLayout(null);
		panelReserva.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Datos de la reserva", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelReserva.setBounds(28, 179, 394, 268);
		contentPane.add(panelReserva);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(12, 23, 55, 15);
		panelReserva.add(lblFecha);
		
		JLabel lblNumeroDePersonas = new JLabel("Número de personas");
		lblNumeroDePersonas.setBounds(197, 23, 148, 15);
		panelReserva.add(lblNumeroDePersonas);
		
		textFieldNumeroPersonas = new JTextField();
		textFieldNumeroPersonas.setToolTipText("Indica el número de asistentes al evento");
		textFieldNumeroPersonas.setColumns(10);
		textFieldNumeroPersonas.setBounds(197, 44, 102, 19);
		
		
		// Únicamente permitimos introducir números
		textFieldNumeroPersonas.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        // Solo permite dígitos
		        if (!Character.isDigit(c)) {
		            e.consume();
		        }
		    }
		});
		panelReserva.add(textFieldNumeroPersonas);
		
		// Configurar spinner de fecha (mínimo el día siguiente)
		LocalDate localTomorrow = LocalDate.now().plusDays(1);
		Date tomorrow = Date.from(localTomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
		dateModel = new SpinnerDateModel(tomorrow, tomorrow, null, Calendar.DAY_OF_MONTH);
		spinnerFecha = new JSpinner(dateModel);
		spinnerFecha.setToolTipText("Selecciona la fecha del evento (mínimo mañana)");
		spinnerFecha.setBounds(12, 44, 167, 19);
		panelReserva.add(spinnerFecha);

		// Establecemos formato de fecha
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy");
		spinnerFecha.setEditor(dateEditor);
		valorInicialFecha = spinnerFecha.getValue();
		
		panelTipoEvento = new JPanel();
		panelTipoEvento.setLayout(null);
		panelTipoEvento.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Tipo de evento", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelTipoEvento.setBounds(12, 88, 133, 106);
		panelReserva.add(panelTipoEvento);
		
		rdbtnBanquete = new JRadioButton("Banquete");
		rdbtnBanquete.setToolTipText("Evento de un solo día para celebración");
		rdbtnBanquete.setBounds(8, 22, 94, 23);
		rdbtnBanquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desactivar();
			}
		});
		panelTipoEvento.add(rdbtnBanquete);
		
		rdbtnJornada = new JRadioButton("Jornada");
		rdbtnJornada.setToolTipText("Evento de un solo día profesional");
		rdbtnJornada.setBounds(8, 49, 94, 23);
		rdbtnJornada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desactivar();
			}
		});
		panelTipoEvento.add(rdbtnJornada);
		
		rdbtnCongreso = new JRadioButton("Congreso");
		rdbtnCongreso.setToolTipText("Evento de varios días con posibilidad de alojamiento");
		rdbtnCongreso.setBounds(8, 76, 93, 23);
		rdbtnCongreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activar();
			}
		});
		panelTipoEvento.add(rdbtnCongreso);
		
		btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnBanquete);
		btnGroup.add(rdbtnCongreso);
		btnGroup.add(rdbtnJornada);
		
		lblCocina = new JLabel("Cocina");
		lblCocina.setBounds(163, 100, 47, 15);
		panelReserva.add(lblCocina);
		
		comboBoxCocina = new JComboBox<String>(opcionesCocina);
		comboBoxCocina.setToolTipText("Selecciona el tipo de servicio de cocina");
		comboBoxCocina.setBounds(221, 95, 148, 24);
		panelReserva.add(comboBoxCocina);
		
		lblDias = new JLabel("Días");
		lblDias.setBounds(163, 167, 31, 15);
		panelReserva.add(lblDias);
		
		modelSpinerDias = new SpinnerNumberModel(0, 0, 100, 1);
		spinnerDias = new JSpinner(modelSpinerDias);
		spinnerDias.setToolTipText("Número de días que durará el congreso");
		spinnerDias.setBounds(221, 165, 55, 19);
		panelReserva.add(spinnerDias);
		valorInicialDias = spinnerDias.getValue();
		
		chckbxNecesitaranHabitacion = new JCheckBox("Necesitarán habitación");
		chckbxNecesitaranHabitacion.setToolTipText("Marca si los asistentes necesitarán alojamiento");
		chckbxNecesitaranHabitacion.setBounds(12, 224, 190, 23);
		panelReserva.add(chckbxNecesitaranHabitacion);
		
		// Botones de acción
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setToolTipText("Validar y enviar la reserva");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comprobarCampos();
			}
		});
		btnAceptar.setBounds(86, 469, 117, 25);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Limpiar todos los campos del formulario");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
		btnCancelar.setBounds(250, 469, 117, 25);
		contentPane.add(btnCancelar);
		
		// Panel de texto para mensajes
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(28, 508, 394, 62);
		contentPane.add(textPane);
		
		// Desactivar inicialmente los campos de congreso
		desactivar();
		setVisible(true);
	}

	/**
	 * Limpia todos los campos del formulario y los deja en su estado inicial
	 */
	protected void limpiarCampos() {
		textFieldNombre.setText("");
		textFieldNumero.setText("");
		textFieldNumeroPersonas.setText("");
		btnGroup.clearSelection();
		chckbxNecesitaranHabitacion.setSelected(false);
		spinnerDias.setValue(valorInicialDias);
		spinnerFecha.setValue(valorInicialFecha);
		comboBoxCocina.setSelectedIndex(0);
		textPane.setText("");
		desactivar();
	}

	/**
	 * Comprueba que todos los campos obligatorios estén correctamente cumplimentados
	 */
	protected void comprobarCampos() {
		String motivoError = "";
		boolean error = false;
		
		// Comprobamos si ha introducido nombre
		if(textFieldNombre.getText().trim().isEmpty()) {
			motivoError += "Debes introducir un nombre. ";
			error = true;
		}
		
		// Comprobamos si ha introducido teléfono
		if(textFieldNumero.getText().isEmpty()) {
			motivoError += "Debes introducir un teléfono. ";
			error = true;
		} else if (!textFieldNumero.getText().matches("\\d{9}")) {
			motivoError += "Debes introducir un teléfono VÁLIDO de 9 dígitos. ";
			error = true;
		}
		
		// Comprobamos si ha introducido número de personas
		if(textFieldNumeroPersonas.getText().isEmpty()) {
			motivoError += "Debes indicar el número de asistentes. ";
			error = true;
		} else if (Integer.parseInt(textFieldNumeroPersonas.getText()) <= 0) {
			motivoError += "El número de asistentes debe ser mayor que 0. ";
			error = true;
		}
		
		// Comprobamos si ha seleccionado tipo de evento
		if (!rdbtnBanquete.isSelected() && !rdbtnCongreso.isSelected() && !rdbtnJornada.isSelected()) {
			motivoError += "Debes seleccionar el tipo de evento. ";
			error = true;
		} else if (rdbtnCongreso.isSelected() && Integer.parseInt(spinnerDias.getValue().toString()) <= 0) {
			motivoError += "Debes indicar el número de días del congreso. ";
			error = true;
		}
		
		// Objetos necesarios para manipular el color del resultado mostrado en el textpane
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet color = new SimpleAttributeSet();
		
		if (error) {
			textPane.setText("ERROR: " + motivoError);
			StyleConstants.setForeground(color, Color.RED);
		} else {
			textPane.setText("✓ RESERVA ENVIADA CORRECTAMENTE");
			StyleConstants.setForeground(color, new Color(0, 128, 0));
		}
		doc.setCharacterAttributes(0, doc.getLength(), color, false);
	}

	/**
	 * Desactiva los campos específicos de congreso (días y habitaciones)
	 */
	public void desactivar() {
		lblDias.setEnabled(false);
		spinnerDias.setEnabled(false);
		chckbxNecesitaranHabitacion.setEnabled(false);
	}
	
	/**
	 * Activa los campos específicos de congreso (días y habitaciones)
	 */
	public void activar() {
		if (rdbtnCongreso.isSelected()) {
			if (!lblDias.isEnabled()) {
				lblDias.setEnabled(true);
			}
			if (!spinnerDias.isEnabled()) {
				spinnerDias.setEnabled(true);
			}
			if (!chckbxNecesitaranHabitacion.isEnabled()) {
				chckbxNecesitaranHabitacion.setEnabled(true);
			}
		}
	}

	public static void main(String[] args) {
		Reserva reserva = new Reserva();
	}
}