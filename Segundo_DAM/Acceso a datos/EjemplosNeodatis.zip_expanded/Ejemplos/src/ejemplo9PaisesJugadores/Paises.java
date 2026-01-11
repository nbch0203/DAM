package ejemplo9PaisesJugadores;

public class Paises {
	private int id;
    private String nombrepais;

    // Constructor
    public Paises(int id, String nombrepais) {
        this.id = id;
        this.nombrepais = nombrepais;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrepais() {
        return nombrepais;
    }

    public void setNombrepais(String nombrepais) {
        this.nombrepais = nombrepais;
    }

    @Override
    public String toString() {
        return "Paises{" +
                "id=" + id +
                ", nombrepais='" + nombrepais + '\'' +
                '}';
    }
}
