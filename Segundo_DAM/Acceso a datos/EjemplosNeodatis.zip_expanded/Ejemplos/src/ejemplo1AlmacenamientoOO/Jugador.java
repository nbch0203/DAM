package ejemplo1AlmacenamientoOO;

public class Jugador {
    private String nombre;
    private String deporte;
    private String ciudad;
    private int edad;

    // Constructor
    public Jugador(String nombre, String deporte, String ciudad, int edad) {
        this.nombre = nombre;
        this.deporte = deporte;
        this.ciudad = ciudad;
        this.edad = edad;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

	@Override
	public String toString() {
		return "Jugador [nombre=" + nombre + ", deporte=" + deporte + ", ciudad=" + ciudad + ", edad=" + edad + "]";
	}
    
    
}

