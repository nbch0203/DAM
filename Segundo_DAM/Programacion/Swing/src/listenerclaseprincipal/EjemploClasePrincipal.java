package listenerclaseprincipal;

import javax.swing.*;
import java.awt.event.*;

public class EjemploClasePrincipal extends JFrame implements ActionListener {
    private JButton boton;

    public EjemploClasePrincipal() {
        setTitle("Ejemplo con Clase Principal");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boton = new JButton("Click me");
        boton.addActionListener(this); // Se registra la propia clase como oyente
        
        add(boton);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Botón presionado - Clase Principal");
    }

    public static void main(String[] args) {
        new EjemploClasePrincipal();
    }
}
