package masEjercicios2;

import java.util.Comparator;

public class OrdenarNumeros implements Comparator<Object> {
	@Override
	public int compare(Object o1, Object o2) {
		Carta c1 = (Carta) o1;
		Carta c2 = (Carta) o2;
		return c1.getNumero().compareTo(c2.getNumero());
	}
}
