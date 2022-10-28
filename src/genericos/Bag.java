package genericos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Bag<T> {
	private ArrayList<T> bolsa;

	/**
	 * @param bolsa constructor
	 */
	public Bag() {
		this.bolsa = new ArrayList<>();
	}

	/**
	 * 
	 * @param elemento
	 * @return booleano para indicar si se añadió
	 */
	public boolean add(T elemento) {
		return this.bolsa.add(elemento);
	}
	
	public void add(T elemento, int veces) {
		for (int i = 0; i < veces; i++) {
			this.bolsa.add(elemento);
		}
	}
	
	public int getCount(T elemento) {
			int cuenta = 0;
			ArrayList<T> bolsa2 = new ArrayList<>();
			bolsa2.addAll(this.bolsa);
			
			while (bolsa2.indexOf(elemento) >= 0) {
				bolsa2.remove(elemento);
				cuenta++;
			}
		
		return cuenta;
	}
	
	public boolean remove(T elemento) {
		return this.bolsa.remove(elemento);
	}
	
	public void remove(T elemento, int veces) {
		for (int i = 0; i < veces; i++) {
			this.bolsa.remove(elemento);
		}
	}
	
	public int size() {
		return this.bolsa.size();
	}
	
	public HashSet<T> uniqueSet() {
		HashSet<T> setBolsa = new HashSet<>();
		
		setBolsa.addAll(this.bolsa);
		
		return setBolsa;
	}

	@Override
	public String toString() {
		return "Bag [bolsa=" + bolsa + "]";
	}
	
}