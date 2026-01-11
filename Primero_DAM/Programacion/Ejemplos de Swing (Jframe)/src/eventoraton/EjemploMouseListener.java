package eventoraton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EjemploMouseListener extends JFrame implements MouseListener {
    private JLabel etiqueta;

    public EjemploMouseListener() {
        setTitle("Ejemplo MouseListener");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        etiqueta = new JLabel("Pasa el mouse por aquí");
        etiqueta.addMouseListener(this); // Se registra el listener

        add(etiqueta);
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        etiqueta.setText("Mouse clicado");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        etiqueta.setText("Mouse entró");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        etiqueta.setText("Mouse salió");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        etiqueta.setText("Mouse presionado");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        etiqueta.setText("Mouse liberado");
    }

    public static void main(String[] args) {
        new EjemploMouseListener();
    }
}
