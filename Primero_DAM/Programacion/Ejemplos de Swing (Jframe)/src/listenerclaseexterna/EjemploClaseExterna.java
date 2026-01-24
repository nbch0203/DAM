package listenerclaseexterna;

import javax.swing.JButton;
import javax.swing.JFrame;

public class EjemploClaseExterna {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ejemplo con Clase Externa");
        JButton boton = new JButton("Click me");
        
        // Asocia el listener al botón
        boton.addActionListener(new Oyente());

        frame.add(boton);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

