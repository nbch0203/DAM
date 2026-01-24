package listenerclaseexterna;

import javax.swing.*;
import java.awt.event.*;

// Clase oyente que implementa ActionListener
class Oyente implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Botón presionado - Clase Externa");
    }
}

