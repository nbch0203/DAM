package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField numero_serie;
	private JTextField marca;
	private JTextField ano_compra;
	private JButton btn_obj, btn_txt, btn_buscar, btn_mostrar;
	private JCheckBox ssd, RAM, GPU_dedicada, Wifi;
	private JTextArea textArea;
	private JComboBox tipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
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
	public Vista() {
		setFont(new Font("Arial", Font.PLAIN, 14));
		setTitle("Gestión de equipos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(22, 12, 464, 124);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Número de serie:");
		lblNewLabel.setBounds(29, 12, 122, 15);
		panel.add(lblNewLabel);

		numero_serie = new JTextField();
		numero_serie.setBounds(162, 10, 114, 19);
		panel.add(numero_serie);
		numero_serie.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Marca:");
		lblNewLabel_1.setBounds(29, 39, 115, 15);
		panel.add(lblNewLabel_1);

		marca = new JTextField();
		marca.setBounds(162, 41, 114, 19);
		panel.add(marca);
		marca.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Año de compra:");
		lblNewLabel_2.setBounds(29, 65, 115, 15);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Tipo:");
		lblNewLabel_3.setBounds(29, 92, 104, 15);
		panel.add(lblNewLabel_3);

		ano_compra = new JTextField();
		ano_compra.setBounds(162, 63, 114, 19);
		panel.add(ano_compra);
		ano_compra.setColumns(10);

		tipo = new JComboBox();
		tipo.setModel(new DefaultComboBoxModel(new String[] { "", "Portátil", "Sobremesa", "All-in-One" }));
		tipo.setBounds(162, 87, 114, 24);
		panel.add(tipo);

		ssd = new JCheckBox("SSD");
		ssd.setBounds(309, 8, 129, 23);
		panel.add(ssd);

		RAM = new JCheckBox("RAM>8GB");
		RAM.setBounds(309, 35, 129, 23);
		panel.add(RAM);

		GPU_dedicada = new JCheckBox("GPU dedicada");
		GPU_dedicada.setBounds(309, 61, 129, 23);
		panel.add(GPU_dedicada);

		Wifi = new JCheckBox("Wifi integrado");
		Wifi.setBounds(309, 88, 129, 23);
		panel.add(Wifi);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 148, 464, 78);
		scrollPane.setBounds(22, 148, 452, 78);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		scrollPane.setViewportView(textArea);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 238, 474, 47);
		panel_1.setBounds(10, 238, 476, 47);
		contentPane.add(panel_1);

		btn_obj = new JButton("Guardar OBJ");
		btn_obj.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1.add(btn_obj);

		btn_txt = new JButton("Guardar TXT");
		btn_txt.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1.add(btn_txt);

		btn_buscar = new JButton("Buscar TXT");
		btn_buscar.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1.add(btn_buscar);

		btn_mostrar = new JButton("Mostrar OBJ");
		btn_mostrar.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_1.add(btn_mostrar);

	}

	public String getNumero_serie() {
		String aux = numero_serie.getText();
		return aux;
	}

	public void setNumero_serie(JTextField numero_serie) {
		this.numero_serie = numero_serie;
	}

	public String getMarca() {
		String aux = marca.getText();
		return aux;
	}

	public void setMarca(JTextField marca) {
		this.marca = marca;
	}

	public String getAno_compra() {
		String aux = ano_compra.getText();
		return aux;
	}

	public void setAno_compra(JTextField ano_compra) {
		this.ano_compra = ano_compra;
	}

	public JButton getBtn_obj() {
		return btn_obj;
	}

	public void setBtn_obj(JButton btn_obj) {
		this.btn_obj = btn_obj;
	}

	public JButton getBtn_txt() {
		return btn_txt;
	}

	public void setBtn_txt(JButton btn_txt) {
		this.btn_txt = btn_txt;
	}

	public JButton getBtn_buscar() {
		return btn_buscar;
	}

	public void setBtn_buscar(JButton btn_buscar) {
		this.btn_buscar = btn_buscar;
	}

	public JButton getBtn_mostrar() {
		return btn_mostrar;
	}

	public void setBtn_mostrar(JButton btn_mostrar) {
		this.btn_mostrar = btn_mostrar;
	}

	public JCheckBox getSsd() {
		return ssd;
	}

	public void setSsd(JCheckBox ssd) {
		this.ssd = ssd;
	}

	public JCheckBox getRAM() {
		return RAM;
	}

	public void setRAM(JCheckBox rAM) {
		RAM = rAM;
	}

	public JCheckBox getGPU_dedicada() {
		return GPU_dedicada;
	}

	public void setGPU_dedicada(JCheckBox gPU_dedicada) {
		GPU_dedicada = gPU_dedicada;
	}

	public JCheckBox getWifi() {
		return Wifi;
	}

	public void setWifi(JCheckBox wifi) {
		Wifi = wifi;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(String textArea) {
		this.textArea.setText(textArea);
	}

	public String getTipo() {
		String aux = (String) tipo.getSelectedItem();
		return aux;
	}

	public void setTipo(JComboBox tipo) {
		this.tipo = tipo;
	}

}
