package boletin_2_ejercicio_4;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;

public class Ejercicio_4 {

    private JFrame frame;
    private JComboBox<String> comboImporte;
    private JComboBox<String> comboTipoCuenta;
    private JLabel lblResultado;
    private JLabel lblSaldoCajero;
    private int saldoCajero = 3000;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ejercicio_4 window = new Ejercicio_4();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Ejercicio_4() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("Cajero Automático");

        // Título
        JLabel lblTitulo = new JLabel("CAJERO AUTOMÁTICO");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(110, 20, 250, 30);
        frame.getContentPane().add(lblTitulo);

        // Label Tipo de Cuenta
        JLabel lblTextoCuenta = new JLabel("Tipo de Cuenta:");
        lblTextoCuenta.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTextoCuenta.setBounds(50, 80, 120, 25);
        frame.getContentPane().add(lblTextoCuenta);

        // ComboBox Tipo de Cuenta
        comboTipoCuenta = new JComboBox<String>();
        comboTipoCuenta.setModel(new DefaultComboBoxModel<String>(new String[] {"Caja de Ahorro", "Cuenta Corriente"}));
        comboTipoCuenta.setFont(new Font("Tahoma", Font.PLAIN, 12));
        comboTipoCuenta.setBounds(180, 80, 200, 25);
        frame.getContentPane().add(comboTipoCuenta);

        // Label Importe a Extraer
        JLabel lblTextoImporte = new JLabel("Importe a Extraer:");
        lblTextoImporte.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTextoImporte.setBounds(50, 130, 120, 25);
        frame.getContentPane().add(lblTextoImporte);

        // ComboBox Importe
        comboImporte = new JComboBox<String>();
        comboImporte.setModel(new DefaultComboBoxModel<String>(new String[] {
            "0", "50", "100", "150", "200", "250", "300", "350", "400", "450", "500"
        }));
        comboImporte.setFont(new Font("Tahoma", Font.PLAIN, 12));
        comboImporte.setBounds(180, 130, 200, 25);
        frame.getContentPane().add(comboImporte);

        // Botón Extraer
        JButton btnExtraer = new JButton("Extraer");
        btnExtraer.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnExtraer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                procesarExtraccion();
            }
        });
        btnExtraer.setBounds(140, 208, 150, 40);
        frame.getContentPane().add(btnExtraer);

        // Label Saldo del Cajero
        JLabel lblTextoSaldo = new JLabel("Saldo del Cajero:");
        lblTextoSaldo.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTextoSaldo.setBounds(50, 310, 150, 25);
        frame.getContentPane().add(lblTextoSaldo);

        lblSaldoCajero = new JLabel("3000 €");
        lblSaldoCajero.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblSaldoCajero.setForeground(new Color(0, 128, 0));
        lblSaldoCajero.setBounds(210, 310, 150, 25);
        frame.getContentPane().add(lblSaldoCajero);

        // Label Resultado
        JLabel lblTextoResultado = new JLabel("Resultado:");
        lblTextoResultado.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTextoResultado.setBounds(50, 350, 100, 25);
        frame.getContentPane().add(lblTextoResultado);

        lblResultado = new JLabel("");
        lblResultado.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblResultado.setBounds(160, 350, 250, 25);
        frame.getContentPane().add(lblResultado);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(48, 280, 332, 19);
        frame.getContentPane().add(separator);
    }

    /**
     * Método que procesa la extracción de dinero
     */
    private void procesarExtraccion() {
        // Obtener valores seleccionados
        String tipoCuenta = (String) comboTipoCuenta.getSelectedItem();
        int importe = Integer.parseInt((String) comboImporte.getSelectedItem());
        
        // Verificar si el cajero tiene suficiente dinero
        if (importe > saldoCajero) {
            lblResultado.setText("Fuera de servicio");
            lblResultado.setForeground(Color.RED);
            return;
        }
        
        // Determinar límite según tipo de cuenta
        int limiteExtraccion = 0;
        if (tipoCuenta.equals("Caja de Ahorro")) {
            limiteExtraccion = 200;
        } else if (tipoCuenta.equals("Cuenta Corriente")) {
            limiteExtraccion = 500;
        }
        
        // Verificar si el importe está dentro del límite permitido
        if (importe <= limiteExtraccion) {
            // Extracción correcta
            saldoCajero -= importe;
            lblResultado.setText("Correcto");
            lblResultado.setForeground(new Color(0, 128, 0));
            lblSaldoCajero.setText(saldoCajero + " €");
            
            // Cambiar color si el saldo es bajo
            if (saldoCajero < 500) {
                lblSaldoCajero.setForeground(Color.RED);
            }
        } else {
            // Importe superior al límite
            lblResultado.setText("Incorrecto");
            lblResultado.setForeground(Color.RED);
        }
    }
}