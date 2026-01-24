package relojDI03;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import RelojDigital.Alarma;
import RelojDigital.RelojDigitalBean;

public class VentanaReloj extends JFrame {

	private DefaultListModel<Alarma> modeloAlarmas;
	private JList<Alarma> listaAlarmas;
    private RelojDigitalBean reloj;
    private JTextField txtHora;
    private JTextField txtMinuto;
    private JTextField txtMensaje;
    private JCheckBox chkActiva;
    private JButton btnAplicar;
    private JButton btnEliminar;


    public VentanaReloj() {
        setTitle("Reloj Digital con Alarma");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== Reloj =====
        reloj = new RelojDigitalBean();
        reloj.setHorizontalAlignment(SwingConstants.CENTER);
        reloj.setFont(new Font("Arial", Font.BOLD, 28));

        reloj.addAlarmaListener(evt -> {
        	reproducirSonido();
            JOptionPane.showMessageDialog(this, evt.getMsg(), "ALARMA",
                    JOptionPane.INFORMATION_MESSAGE);
            actualizarAlarmaActiva(); 
        });

        add(reloj, BorderLayout.NORTH);

        // ===== Panel alarma =====
        modeloAlarmas = new DefaultListModel<>();
        listaAlarmas = new JList<>(modeloAlarmas);
        listaAlarmas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel panelAlarma = new JPanel(new GridLayout(6, 2, 5, 5));
        panelAlarma.setBorder(BorderFactory.createTitledBorder("Alarma"));
        
        JScrollPane scrollAlarmas = new JScrollPane(listaAlarmas);
        scrollAlarmas.setBorder(
            BorderFactory.createTitledBorder("Alarmas activas")
        );
        scrollAlarmas.setPreferredSize(new java.awt.Dimension(150, 0));
        add(scrollAlarmas, BorderLayout.EAST);
        listaAlarmas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Alarma a = listaAlarmas.getSelectedValue();
                if (a != null) {
                    txtHora.setText(String.valueOf(a.getHora()));
                    txtMinuto.setText(String.valueOf(a.getMinuto()));
                    txtMensaje.setText(a.getMensaje());
                    chkActiva.setSelected(a.isActiva());
                }
            }
        });


        txtHora = new JTextField("0");
        txtMinuto = new JTextField("0");
        txtMensaje = new JTextField("Despierta");
        chkActiva = new JCheckBox("Activa");
        btnAplicar = new JButton("Aplicar alarma");
        btnEliminar = new JButton("Eliminar alarma");
        btnEliminar.addActionListener(e -> {
            int idx = listaAlarmas.getSelectedIndex();
            if (idx >= 0) {
                modeloAlarmas.remove(idx);
                actualizarAlarmaActiva();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Selecciona una alarma",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            }
        });


        panelAlarma.add(new JLabel("Hora:"));
        panelAlarma.add(txtHora);
        panelAlarma.add(new JLabel("Minuto:"));
        panelAlarma.add(txtMinuto);
        panelAlarma.add(new JLabel("Mensaje:"));
        panelAlarma.add(txtMensaje);
        panelAlarma.add(new JLabel(""));
        panelAlarma.add(chkActiva);
        panelAlarma.add(new JLabel(""));
        panelAlarma.add(btnAplicar);
        panelAlarma.add(new JLabel(""));
        panelAlarma.add(btnEliminar);

        add(panelAlarma, BorderLayout.CENTER);

        // ===== Acción botón =====
        btnAplicar.addActionListener(e -> aplicarAlarma());
    }

    private void aplicarAlarma() {
        try {
            int h = Integer.parseInt(txtHora.getText());
            int m = Integer.parseInt(txtMinuto.getText());

            Alarma alarma = new Alarma(h, m, txtMensaje.getText());
            alarma.setActiva(chkActiva.isSelected());

            int idx = listaAlarmas.getSelectedIndex();
            if (idx >= 0)
                modeloAlarmas.set(idx, alarma);
            else
                modeloAlarmas.addElement(alarma);

            // La activa en el reloj (por ahora la primera activa)
            reloj.setMialarma(alarma);
            actualizarAlarmaActiva();


        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Hora o minuto incorrectos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarAlarmaActiva() {
        for (int i = 0; i < modeloAlarmas.size(); i++) {
            Alarma a = modeloAlarmas.get(i);
            if (a.isActiva()) {
                reloj.setMialarma(a);
                return;
            }
        }
        reloj.setMialarma(new Alarma()); // ninguna activa
    }

    private void reproducirSonido() {
        try {
            javax.sound.sampled.AudioInputStream audio =
                javax.sound.sampled.AudioSystem.getAudioInputStream(
                    getClass().getResource("/sonidos/alarma.wav")
                );
            javax.sound.sampled.Clip clip =
                javax.sound.sampled.AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaReloj().setVisible(true));
    }
}