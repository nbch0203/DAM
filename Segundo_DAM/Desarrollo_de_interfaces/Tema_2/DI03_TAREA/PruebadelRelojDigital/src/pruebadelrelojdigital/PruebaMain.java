package pruebadelrelojdigital;

import javax.swing.*;
import RelojDigital.RelojDigitalBean; // Ajusta package y clase a lo que generaste

public class PruebaMain {
    public static void main(String[] args) {
        JFrame f = new JFrame("Prueba RelojDigital");
        RelojDigitalBean reloj = new RelojDigitalBean();
        // Ajusta el nombre de clase y métodos según tu bean
        f.add(reloj);
        f.setSize(400, 100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}