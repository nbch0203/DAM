package relojDI03;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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

    // Clip para controlar el sonido de la alarma
    private Clip alarmaClip;

    public VentanaReloj() {
        setTitle("Reloj Digital con Alarma");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 260);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== Reloj bonito estilo digital =====
        reloj = new RelojDigitalBean();
        reloj.setHorizontalAlignment(SwingConstants.CENTER);
        reloj.setFont(new Font("Monospaced", Font.BOLD, 25));
        reloj.setOpaque(true);
        reloj.setBackground(new Color(20, 20, 20));
        reloj.setForeground(new Color(0, 255, 128));
        reloj.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 128), 4, true));

        reloj.addAlarmaListener(evt -> {
            reproducirSonido();
            JOptionPane.showMessageDialog(this, evt.getMsg(), "ALARMA",
                    JOptionPane.INFORMATION_MESSAGE);
            detenerSonido(); // Detiene el sonido SOLO al pulsar aceptar
            actualizarAlarmaActiva();
        });

        add(reloj, BorderLayout.NORTH);

        // ===== Panel alarma (con fondo blanco y texto negro) =====
        modeloAlarmas = new DefaultListModel<>();
        listaAlarmas = new JList<>(modeloAlarmas);
        listaAlarmas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaAlarmas.setBackground(Color.WHITE);
        listaAlarmas.setForeground(Color.BLACK);
        listaAlarmas.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        JPanel panelAlarma = new JPanel(new GridLayout(6, 2, 6, 7));
        panelAlarma.setBackground(Color.WHITE);
        panelAlarma.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Alarma"),
                BorderFactory.createEmptyBorder(14, 12, 14, 12)
            )
        );

        JScrollPane scrollAlarmas = new JScrollPane(listaAlarmas);
        scrollAlarmas.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 255, 128), 2, true),
                "Alarmas activas"
            )
        );
        scrollAlarmas.setPreferredSize(new java.awt.Dimension(160, 0));
        scrollAlarmas.setBackground(Color.WHITE);

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
        txtHora.setBackground(Color.WHITE);
        txtHora.setForeground(Color.BLACK);
        txtMinuto = new JTextField("0");
        txtMinuto.setBackground(Color.WHITE);
        txtMinuto.setForeground(Color.BLACK);
        txtMensaje = new JTextField("Despierta");
        txtMensaje.setBackground(Color.WHITE);
        txtMensaje.setForeground(Color.BLACK);
        chkActiva = new JCheckBox("Activa");
        chkActiva.setBackground(Color.WHITE);
        chkActiva.setForeground(Color.BLACK);
        btnAplicar = new JButton("Aplicar alarma");
        btnEliminar = new JButton("Eliminar alarma");

        btnAplicar.setBackground(new Color(240, 240, 240));
        btnEliminar.setBackground(new Color(240, 240, 240));
        btnAplicar.setForeground(Color.BLACK);
        btnEliminar.setForeground(Color.BLACK);

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

        JLabel lblHora = new JLabel("Hora:");
        lblHora.setForeground(Color.BLACK);
        JLabel lblMinuto = new JLabel("Minuto:");
        lblMinuto.setForeground(Color.BLACK);
        JLabel lblMensaje = new JLabel("Mensaje:");
        lblMensaje.setForeground(Color.BLACK);

        panelAlarma.add(lblHora);
        panelAlarma.add(txtHora);
        panelAlarma.add(lblMinuto);
        panelAlarma.add(txtMinuto);
        panelAlarma.add(lblMensaje);
        panelAlarma.add(txtMensaje);
        panelAlarma.add(new JLabel(""));
        panelAlarma.add(chkActiva);
        panelAlarma.add(new JLabel(""));
        panelAlarma.add(btnAplicar);
        panelAlarma.add(new JLabel(""));
        panelAlarma.add(btnEliminar);

        for (Component c : panelAlarma.getComponents()) {
            if (c instanceof JLabel) c.setForeground(Color.BLACK);
        }

        add(panelAlarma, BorderLayout.CENTER);

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
        reloj.setMialarma(new Alarma());
    }

    // --- Cambios aquí: sonar/stop sonar ---
    private void reproducirSonido() {
        detenerSonido(); // Por si hay uno sonando todavía
        try {
            java.net.URL sonidoURL = getClass().getResource("/sonidos/alarma.wav");
            if (sonidoURL == null) {
                System.err.println("No se encontró el archivo de sonido: sonidos/alarma.wav");
                return;
            }
            AudioInputStream audio = AudioSystem.getAudioInputStream(sonidoURL);
            alarmaClip = AudioSystem.getClip();
            alarmaClip.open(audio);
            alarmaClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void detenerSonido() {
        if (alarmaClip != null && alarmaClip.isRunning()) {
            alarmaClip.stop();
            alarmaClip.close();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaReloj().setVisible(true));
    }
}