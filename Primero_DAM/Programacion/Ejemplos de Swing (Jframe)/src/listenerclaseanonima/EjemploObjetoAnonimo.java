package listenerclaseanonima;

import javax.swing.*;
import java.awt.event.*;

public class EjemploObjetoAnonimo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ejemplo con Objeto Anónimo");
        JButton boton = new JButton("Click me");

        // Implementación anónima del ActionListener
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botón presionado - Objeto Anónimo");
            }
        });

        frame.add(boton);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
