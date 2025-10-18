package EjerciciorepasoTema3;

public class Agenda {
	public static final int max_cont = 3;
	private int posicion;

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public boolean altaContacto(Contacto contacto, int posicion) {
		if (posicion > 0 || posicion < 4) {
			switch (posicion) {
			case 1:
				if(contacto.getNombre().equals(null)) {
					contacto.setNombre(contacto.getNombre());
					
				}
			}
			return false;
		}
		return false;
	}
	
	public boolean bajaContacto(int posicion) {
		switch(posicion) {
		case 1: 
			
		}
		return false;
	}
}
