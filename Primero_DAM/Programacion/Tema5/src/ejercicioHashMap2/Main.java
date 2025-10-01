package ejercicioHashMap2;

import java.util.HashMap;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		HashMap<Integer, String>Mapa=new HashMap<Integer, String>();
		
		Mapa.put(1, "Red");
		Mapa.put(2, "Green");
		Mapa.put(3, "Black");
		Mapa.put(4, "White");
		Mapa.put(5, "Blue");

		Iterator<Integer> it= Mapa.keySet().iterator();
		while(it.hasNext()) {
			int key =it.next();
			System.out.println("Clave :" +key+" Value : " +Mapa.get(key)+"\n");
		}
		
		System.out.println("Color : "+Mapa.values());
		
		System.out.println("**************************************************************");
		
		HashMap<Integer, String>mapa=new HashMap<Integer, String>();
		
		mapa.put(6, "Pink");
		mapa.put(7, "Orange");
		mapa.put(8, "Purple");
		
		Mapa.putAll(mapa);
		
		Iterator<Integer> ite=Mapa.keySet().iterator();
		
		System.out.println("Clave :"+Mapa.keySet()+" Valor : "+Mapa.values());
		
		System.out.println(mapa.keySet()+" "+ mapa.values() );
		
		mapa.clear();
		
		System.out.println(mapa.keySet()+ " " + mapa.values());
		
		HashMap<String, Integer> mp =new HashMap<String, Integer>();
		
		mp.put("Red",1 );
		mp.put("Green", 2);
		mp.put("Black", 3);
		mp.put("White", 4);
		mp.put("Blue", 5);
		
//		System.out.println("El color "+mp.+ " existe en la posición: "+mp.containsValue("Green"));
		
		
		Mapa.containsKey("Green");
	}

}
