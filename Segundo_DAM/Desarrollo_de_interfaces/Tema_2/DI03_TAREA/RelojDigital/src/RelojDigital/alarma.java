/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RelojDigital;

    /*
     * Clase auxiliar que me permite gestionar alarmas.
     *
     * Atributos:
     *
     * hora, minuto y mensaje de la alarma y si está activa.
     *
     * Métodos:
     *
     * getters y setters de los atributos.
     * constructor vacío que crea una alarma vacía e inactiva.
     * constructor con parámetros que crea una alarma activa.
     * coincide devuelve true cuando la hora y minuto que se pasa como parámetro
     *     coincide con la hora y minuto de la alarma.
     *
     */
    public class alarma{

        private int hora;
        private int minuto;
        private String mensaje;
        private boolean activa;

     /**
     * Devuelve si la alarma está activa
     *
     */

        public boolean isActiva() {
            return activa;
        }
     /**
     * Establece el valor de activa
     *
     * @param activa indica si la alarma está activa
     */
        public void setActiva(boolean activa) {
            this.activa = activa;
        }
     /**
     * Devuelve el valor de la hora de la alarma
     *
     */
        public int getHora() {
            return hora;
        }
     /**
     * Establece el valor de hora
     *
     * @param hora valor de la hora de la alarma
     */
        public void setHora(int hora) {
            this.hora = hora;
        }
     /**
     * Devuelve el valor del mensaje de la alarma
     *
     */

        public String getMensaje() {
            return mensaje;
        }

     /**
     * Establece el valor del mensaje
     *
     * @param mensaje valor del mensaje que se mostrará cuando salte la alarma
     */
        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
     /**
     * Devuelve el valor de los minutos de la alarma
     *
     */

        public int getMinuto() {
            return minuto;
        }

     /**
     * Establece el valor de minuto
     *
     * @param minuto valor de los minutos de la alarma
     */
        public void setMinuto(int minuto) {
            this.minuto = minuto;
        }

       


      /**
      * Constructor
      *Crea una alarma activa con los valores que se pasan como parámetro
      *
      * @param h hora de la alarma
      * @param m minutos de la alarma
      * @param msg mensaje a mostrar cuando salte la alarma
      */
        public alarma(int h, int m, java.lang.String msg)
        {
            hora = h;
            minuto = m;
            mensaje = msg;
            activa = true;
        }


    /**
     * Contructor.
     * Crea una alarma vacía e inactiva.
     *
     */
        public alarma()
        {
            hora = 0;
            minuto = 0;
            mensaje = "";
            activa = false;
        }

    /**
     * Compara un valor de hora y minutos con el de la alarma
     *
     * @param h hora a comparar
     * @param m minutos a comparar
     */
        public boolean coincide(int h, int m)
        {
            return (hora == h && minuto == m)?true:false;
        }
    }//fin clase auxiliar