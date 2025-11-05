package vista;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextPane;

import conexion.Conexion;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelContacto;
	private JTextField textFieldNombre;
	private JTextField textFieldNumero;
	private JTextField textFieldCorreo;
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
	
	// Objeto de conexión a la base de datos
	private Conexion conexionBD;
	
	public Vista() {
		// Inicializar y establecer conexión a la base de datos
		conexionBD = new Conexion();
		boolean conectado = conexionBD.conectar();
		
		if (!conectado) {
			System.err.println("ADVERTENCIA: No se pudo establecer conexión con la base de datos");
		}
		
		inicializarComponentes();
		
		// Cerrar conexión al cerrar la ventana
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (conexionBD != null) {
					conexionBD.cerrar();
				}
			}
		});
		
		desactivar();
		setVisible(true);
	}

	/**
	 * Inicializa todos los componentes de la interfaz gráfica
	 */
	private void inicializarComponentes() {
		setTitle("Reserva");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JLabel lblReservasDelSalon = new JLabel("RESERVAS DEL SALÓN HAVANA");
		lblReservasDelSalon.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblReservasDelSalon.setBounds(28, 23, 361, 41);
		contentPane.add(lblReservasDelSalon);
		
		crearPanelContacto();
		crearPanelReserva();
		crearBotones();
		crearTextoPaneResultado();
	}

	/**
	 * Crea el panel de datos de contacto
	 */
	private void crearPanelContacto() {
		panelContacto = new JPanel();
		panelContacto.setBorder(new TitledBorder(null, "Datos de contacto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelContacto.setBounds(28, 72, 361, 115);
		panelContacto.setLayout(null);
		contentPane.add(panelContacto);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 23, 55, 15);
		panelContacto.add(lblNombre);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(12, 50, 55, 15);
		panelContacto.add(lblCorreo);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(12, 77, 62, 15);
		panelContacto.add(lblTelefono);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setToolTipText("Introduce el nombre completo de la persona que realiza la reserva");
		textFieldNombre.setBounds(85, 21, 264, 19);
		panelContacto.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setToolTipText("Introduce el correo electrónico");
		textFieldCorreo.setBounds(85, 48, 264, 19);
		panelContacto.add(textFieldCorreo);
		textFieldCorreo.setColumns(10);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setToolTipText("Introduce un teléfono de contacto de 9 dígitos");
		textFieldNumero.setColumns(10);
		textFieldNumero.setBounds(85, 75, 133, 19);
		
		// Únicamente permitimos introducir números
		textFieldNumero.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!Character.isDigit(c) || textFieldNumero.getText().length() >= 9) {
		            e.consume();
		        }
		    }
		});
		panelContacto.add(textFieldNumero);
	}

	/**
	 * Crea el panel de datos de la reserva
	 */
	private void crearPanelReserva() {
		panelReserva = new JPanel();
		panelReserva.setLayout(null);
		panelReserva.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Datos de la reserva", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelReserva.setBounds(28, 210, 394, 268);
		contentPane.add(panelReserva);
		
		// Fecha
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(12, 23, 55, 15);
		panelReserva.add(lblFecha);
		
		LocalDate localTomorrow = LocalDate.now().plusDays(1);
		Date tomorrow = Date.from(localTomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
		dateModel = new SpinnerDateModel(tomorrow, tomorrow, null, Calendar.DAY_OF_MONTH);
		spinnerFecha = new JSpinner(dateModel);
		spinnerFecha.setToolTipText("Selecciona la fecha del evento (mínimo mañana)");
		spinnerFecha.setBounds(12, 44, 167, 19);
		panelReserva.add(spinnerFecha);

		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy");
		spinnerFecha.setEditor(dateEditor);
		valorInicialFecha = spinnerFecha.getValue();
		
		// Número de personas
		JLabel lblNumeroDePersonas = new JLabel("Número de personas");
		lblNumeroDePersonas.setBounds(197, 23, 148, 15);
		panelReserva.add(lblNumeroDePersonas);
		
		textFieldNumeroPersonas = new JTextField();
		textFieldNumeroPersonas.setToolTipText("Indica el número de asistentes al evento");
		textFieldNumeroPersonas.setColumns(10);
		textFieldNumeroPersonas.setBounds(197, 44, 102, 19);
		
		textFieldNumeroPersonas.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!Character.isDigit(c)) {
		            e.consume();
		        }
		    }
		});
		panelReserva.add(textFieldNumeroPersonas);
		
		// Panel tipo de evento
		crearPanelTipoEvento();
		
		// Cocina
		lblCocina = new JLabel("Cocina");
		lblCocina.setBounds(163, 100, 47, 15);
		panelReserva.add(lblCocina);
		
		comboBoxCocina = new JComboBox<String>(opcionesCocina);
		comboBoxCocina.setToolTipText("Selecciona el tipo de servicio de cocina");
		comboBoxCocina.setBounds(221, 95, 148, 24);
		panelReserva.add(comboBoxCocina);
		
		// Días
		lblDias = new JLabel("Días");
		lblDias.setBounds(163, 167, 31, 15);
		panelReserva.add(lblDias);
		
		modelSpinerDias = new SpinnerNumberModel(0, 0, 100, 1);
		spinnerDias = new JSpinner(modelSpinerDias);
		spinnerDias.setToolTipText("Número de días que durará el congreso");
		spinnerDias.setBounds(221, 165, 55, 19);
		panelReserva.add(spinnerDias);
		valorInicialDias = spinnerDias.getValue();
		
		// Checkbox habitación
		chckbxNecesitaranHabitacion = new JCheckBox("Necesitarán habitación");
		chckbxNecesitaranHabitacion.setToolTipText("Marca si los asistentes necesitarán alojamiento");
		chckbxNecesitaranHabitacion.setBounds(12, 224, 190, 23);
		panelReserva.add(chckbxNecesitaranHabitacion);
	}

	/**
	 * Crea el panel de tipo de evento
	 */
	private void crearPanelTipoEvento() {
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
	}

	/**
	 * Crea los botones Aceptar y Cancelar
	 */
	private void crearBotones() {
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setToolTipText("Validar y enviar la reserva");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				procesarReserva();
			}
		});
		btnAceptar.setBounds(86, 500, 117, 25);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Limpiar todos los campos del formulario");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
		btnCancelar.setBounds(250, 500, 117, 25);
		contentPane.add(btnCancelar);
	}

	/**
	 * Crea el panel de texto para mostrar resultados
	 */
	private void crearTextoPaneResultado() {
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(28, 539, 394, 85);
		contentPane.add(textPane);
	}

	/**
	 * Limpia todos los campos del formulario
	 */
	protected void limpiarCampos() {
		textFieldNombre.setText("");
		textFieldCorreo.setText("");
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
	 * Procesa la reserva: valida los campos y envía los datos a la base de datos
	 */
	protected void procesarReserva() {
		String motivoError = validarCampos();
		
		if (!motivoError.isEmpty()) {
			mostrarError(motivoError);
			return;
		}
		
		// Verificar conexión a la base de datos
		if (!conexionBD.estaConectado()) {
			mostrarError("No hay conexión con la base de datos");
			return;
		}
		
		// Obtener datos del formulario
		String nombre = textFieldNombre.getText().trim();
		String correo = textFieldCorreo.getText().trim();
		String telefono = textFieldNumero.getText().trim();
		Date fechaEvento = (Date) spinnerFecha.getValue();
		int numeroPersonas = Integer.parseInt(textFieldNumeroPersonas.getText());
		String tipoEvento = obtenerTipoEventoSeleccionado();
		String tipoCocina = (String) comboBoxCocina.getSelectedItem();
		int dias = (Integer) spinnerDias.getValue();
		boolean necesitaHabitacion = chckbxNecesitaranHabitacion.isSelected();
		
		// Enviar datos a la base de datos a través de Conexion
		boolean insertado = conexionBD.insertarReserva(
			nombre, correo, telefono, fechaEvento, 
			numeroPersonas, tipoEvento, tipoCocina, 
			dias, necesitaHabitacion
		);
		
		if (insertado) {
			mostrarExito("✓ RESERVA ENVIADA CORRECTAMENTE\n✓ CLIENTE REGISTRADO EN LA BASE DE DATOS");
			limpiarCampos();
		} else {
			mostrarError("No se pudo registrar la reserva en la base de datos");
		}
	}

	/**
	 * Valida todos los campos del formulario
	 * @return String con el mensaje de error, o vacío si no hay errores
	 */
	private String validarCampos() {
		StringBuilder motivoError = new StringBuilder();
		
		if(textFieldNombre.getText().trim().isEmpty()) {
			motivoError.append("Debes introducir un nombre. ");
		}
		
		if(textFieldCorreo.getText().trim().isEmpty()) {
			motivoError.append("Debes introducir un correo. ");
		} else if (!textFieldCorreo.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			motivoError.append("Debes introducir un correo VÁLIDO. ");
		}
		
		if(textFieldNumero.getText().isEmpty()) {
			motivoError.append("Debes introducir un teléfono. ");
		} else if (!textFieldNumero.getText().matches("\\d{9}")) {
			motivoError.append("Debes introducir un teléfono VÁLIDO de 9 dígitos. ");
		}
		
		if(textFieldNumeroPersonas.getText().isEmpty()) {
			motivoError.append("Debes indicar el número de asistentes. ");
		} else if (Integer.parseInt(textFieldNumeroPersonas.getText()) <= 0) {
			motivoError.append("El número de asistentes debe ser mayor que 0. ");
		}
		
		if (!rdbtnBanquete.isSelected() && !rdbtnCongreso.isSelected() && !rdbtnJornada.isSelected()) {
			motivoError.append("Debes seleccionar el tipo de evento. ");
		} else if (rdbtnCongreso.isSelected() && Integer.parseInt(spinnerDias.getValue().toString()) <= 0) {
			motivoError.append("Debes indicar el número de días del congreso. ");
		}
		
		return motivoError.toString();
	}

	/**
	 * Obtiene el tipo de evento seleccionado
	 * @return String con el tipo de evento
	 */
	private String obtenerTipoEventoSeleccionado() {
		if (rdbtnBanquete.isSelected()) return "Banquete";
		if (rdbtnJornada.isSelected()) return "Jornada";
		if (rdbtnCongreso.isSelected()) return "Congreso";
		return "";
	}

	/**
	 * Muestra un mensaje de error en el textPane
	 */
	private void mostrarError(String mensaje) {
		textPane.setText("ERROR: " + mensaje);
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet color = new SimpleAttributeSet();
		StyleConstants.setForeground(color, Color.RED);
		doc.setCharacterAttributes(0, doc.getLength(), color, false);
	}

	/**
	 * Muestra un mensaje de éxito en el textPane
	 */
	private void mostrarExito(String mensaje) {
		textPane.setText(mensaje);
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet color = new SimpleAttributeSet();
		StyleConstants.setForeground(color, new Color(0, 128, 0));
		doc.setCharacterAttributes(0, doc.getLength(), color, false);
	}

	/**
	 * Desactiva los campos específicos de congreso
	 */
	public void desactivar() {
		lblDias.setEnabled(false);
		spinnerDias.setEnabled(false);
		chckbxNecesitaranHabitacion.setEnabled(false);
	}
	
	/**
	 * Activa los campos específicos de congreso
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
}