package ejemplo9PaisesJugadores;

public class Jugador {
	private String nombre;
    private String deporte;
    private int edad;
    private Paises pais;

    // Constructor
    public Jugador(String nombre, String deporte, int edad, Paises pais) {
        this.nombre = nombre;
        this.deporte = deporte;
        this.edad = edad;
        this.pais = pais;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", deporte='" + deporte + '\'' +
                ", edad=" + edad +
                ", pais=" + (pais != null ? pais.getNombrepais() : "null") +
                '}';
    }
}