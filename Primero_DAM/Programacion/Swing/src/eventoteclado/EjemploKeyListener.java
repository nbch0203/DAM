package eventoteclado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EjemploKeyListener extends JFrame implements KeyListener {
    private JTextArea areaTexto;

    public EjemploKeyListener() {
        setTitle("Ejemplo KeyListener");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        areaTexto = new JTextArea(5, 20);
        areaTexto.addKeyListener(this); // Se registra el listener

        add(new JScrollPane(areaTexto));
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Tecla tipeada: " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Tecla presionada: " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Tecla liberada: " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    public static void main(String[] args) {
        new EjemploKeyListener();
    }
}
