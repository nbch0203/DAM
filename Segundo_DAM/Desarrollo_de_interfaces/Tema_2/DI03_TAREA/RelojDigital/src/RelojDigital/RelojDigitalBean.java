/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RelojDigital;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Calendar;
import java.util.EventListener;
import javax.swing.*;

/**
 *
 * @author usuario
 */
public class RelojDigitalBean extends JLabel  implements ActionListener, Serializable {

    //modo representa el modo en que se muestra el reloj.
    //se establece por defecto a true que significa que se va usar el modo 24 horas.
    protected boolean modo24;
    private Timer t;
    private Calendar calendario;
    private String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
    private String[] minutos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09","10", "11", "12", "13", "14", "15", "16", "17", "18", "19","20", "21", "22", "23", "24", "25", "26", "27", "28", "29","30", "31", "32", "33", "34", "35", "36", "37", "38", "39","40", "41", "42", "43", "44", "45", "46", "47", "48", "49","50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
    private String[] segundos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09","10", "11", "12", "13", "14", "15", "16", "17", "18", "19","20", "21", "22", "23", "24", "25", "26", "27", "28", "29","30", "31", "32", "33", "34", "35", "36", "37", "38", "39","40", "41", "42", "43", "44", "45", "46", "47", "48", "49","50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
    private String[] AMPM = {"AM", "PM"};

    private AlarmaListener receptor;

    
    /**
     * Get the value of modo
     *
     * @return the value of modo
     */
    public boolean isModo24() {
        return modo24;
    }

    /**
     * Set the value of modo
     *
     * @param modo new value of modo
     */
    public void setModo24(boolean modo24) {
        this.modo24 = modo24;
    }


    protected alarma mialarma;

    public alarma getMialarma() {
        alarma a = new alarma();
        a.setActiva(mialarma.isActiva());
        a.setHora(mialarma.getHora());
        a.setMinuto(mialarma.getMinuto());
        a.setMensaje(mialarma.getMensaje());
        return a;
    }

    public void setMialarma(alarma mialarma) {
        this.mialarma = mialarma;
    }





/**
 *
 * CONSTRUCTOR DEL RELOJ DIGITAL
 *
 */
    public RelojDigitalBean() {
        //por defeto el modo es a 24 horas
        modo24 = true;
        t = new Timer (1000, this);
        t.start();

        calendario = Calendar.getInstance();

        String h = horas[calendario.get(Calendar.HOUR_OF_DAY)];
        String m = minutos[calendario.get(Calendar.MINUTE)];
        String s = segundos[calendario.get(Calendar.SECOND)];
        String AM_PM = AMPM[calendario.get(Calendar.AM_PM)];

        setText(h + ":" + m + ":" + s + " " + AM_PM);

        mialarma = new alarma();

    }


    /**
 *
 * @author usuario
     * Código que genera el EventObject que permite la
     * construcción de un evento cuando llega la hora de una alarma activa.
 */
public class AlarmaEvent extends java.util.EventObject
{
    String msg;
    // constructor
    public AlarmaEvent(Object source, String msg)
    {
        super(source);
        this.msg = msg;
    }

    public String getMsg()
    {
        return msg;
    }
}

//Define la interfaz para el nuevo tipo de evento
public interface AlarmaListener extends EventListener
{
    void SuenaAlarma(AlarmaEvent ev);
}





    //Implementamos el Timer para que cambie la hora cada segundo
    public void actionPerformed(ActionEvent e)
    {
        String h;
        String m;
        String s;
        String AM_PM;
        calendario = Calendar.getInstance();

        if (isModo24())
        {
            h = horas[calendario.get(Calendar.HOUR_OF_DAY)];
            m = minutos[calendario.get(Calendar.MINUTE)];
            s = segundos[calendario.get(Calendar.SECOND)];
            AM_PM = AMPM[calendario.get(Calendar.AM_PM)];
            setText(h + ":" + m + ":" + s + " " + AM_PM);
        }
        else
        {
            h = horas[calendario.get(Calendar.HOUR)];
            m = minutos[calendario.get(Calendar.MINUTE)];
            s = segundos[calendario.get(Calendar.SECOND)];
            setText(h + ":" + m + ":" + s);
        }
        repaint();

        /**
         *
         * Gestion de las alarmas, si coincide se lanza el evento
         *
         */
        if(mialarma.isActiva())
        {
            if(mialarma.coincide(Integer.parseInt(h), Integer.parseInt(m)) )
                receptor.SuenaAlarma( new AlarmaEvent(this, mialarma.getMensaje()) );
        }
    }



    /**
     * Añade un oyente para el evento AlarmaEvent
     *
     * @param receptor
     */
    public void addAlarmaListener(AlarmaListener receptor)
    {
        this.receptor = receptor;
    }

    /**
     * Elimina un oyente del evento AlarmaEvent
     *
     * @param receptor
     */
    public void removeAlarmaListener(AlarmaListener receptor)
    {
        this.receptor=null;
    }

}
