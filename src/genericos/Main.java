package genericos;

import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		Bag<String> bolsa = new Bag<>();
		
		bolsa.add("Test");
		bolsa.add("1", 3);
		System.out.println(bolsa.getCount("1"));
		bolsa.remove("1");
		bolsa.remove("1", 4);
		System.out.println(bolsa.size());
		HashSet<String> setBolsa =  bolsa.uniqueSet();
		System.out.println(setBolsa);
		System.out.println(bolsa.toString());
		
	}

}
