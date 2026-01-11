package boletin_2_ejercicio_1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Ejercicio1 {

    private JFrame frame;
    private JLabel lblPisoActual;
    private JLabel lblDireccion;
    private int pisoActual = 1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ejercicio1 window = new Ejercicio1();
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
    public Ejercicio1() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 536, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("Tablero de Ascensor");

        // Botón Piso 1
        JButton btnPiso1 = new JButton("1");
        btnPiso1.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnPiso1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                procesarPiso(1);
            }
        });
        btnPiso1.setBounds(50, 257, 80, 60);
        frame.getContentPane().add(btnPiso1);

        // Botón Piso 2
        JButton btnPiso2 = new JButton("2");
        btnPiso2.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnPiso2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                procesarPiso(2);
            }
        });
        btnPiso2.setBounds(50, 187, 80, 60);
        frame.getContentPane().add(btnPiso2);

        // Botón Piso 3
        JButton btnPiso3 = new JButton("3");
        btnPiso3.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnPiso3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                procesarPiso(3);
            }
        });
        btnPiso3.setBounds(50, 117, 80, 60);
        frame.getContentPane().add(btnPiso3);

        // Botón Piso 4
        JButton btnPiso4 = new JButton("4");
        btnPiso4.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnPiso4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                procesarPiso(4);
            }
        });
        btnPiso4.setBounds(50, 37, 80, 60);
        frame.getContentPane().add(btnPiso4);

        // Label "Piso:"
        JLabel lblTexto = new JLabel("Piso:");
        lblTexto.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTexto.setBounds(222, 72, 60, 25);
        frame.getContentPane().add(lblTexto);

        // Label que muestra el piso actual
        lblPisoActual = new JLabel("1");
        lblPisoActual.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPisoActual.setBounds(381, 70, 100, 25);
        frame.getContentPane().add(lblPisoActual);

        // Label "Dirección:"
        JLabel lblTextoDireccion = new JLabel("Dirección:");
        lblTextoDireccion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTextoDireccion.setBounds(222, 139, 100, 25);
        frame.getContentPane().add(lblTextoDireccion);

        // Label que muestra la dirección
        lblDireccion = new JLabel("");
        lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblDireccion.setBounds(332, 139, 150, 25);
        frame.getContentPane().add(lblDireccion);
    }

    /**
     * Método que procesa la lógica del ascensor
     */
    private void procesarPiso(int pisoSeleccionado) {
        // Actualizar el piso mostrado
        lblPisoActual.setText(String.valueOf(pisoSeleccionado));
        
        // Determinar la dirección
        if (pisoSeleccionado > pisoActual) {
            lblDireccion.setText("Sube");
        } else if (pisoSeleccionado < pisoActual) {
            lblDireccion.setText("Baja");
        } else {
            lblDireccion.setText("Piso actual");
        }
        
        // Actualizar el piso actual para la próxima selección
        pisoActual = pisoSeleccionado;
    }
}