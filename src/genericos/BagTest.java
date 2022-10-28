package genericos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BagTest {

	Bag<String> bolsa;
	
	@BeforeEach
	void setUp() throws Exception {
		bolsa = new Bag<>();
	}

	@DisplayName("Test, es una prueba que sé hacer esa anotación")
	@Test
	void test1() {
		bolsa.add("Test");
		
		assertEquals(1, bolsa.size());
	}
	
	@Test
	void test2() {
		bolsa.add("1", 3);
		
		assertEquals(3, bolsa.size());
	}
	
	@Test
	void test3() {
		int resultado = bolsa.getCount("1");
		
		assertEquals(1, resultado);
	}
	
	@Test
	void test4() {
		bolsa.add("1", 3);
		bolsa.remove("1");
		
		assertEquals(2, bolsa.size());
	}
	
	@Test
	void test5() {
		bolsa.add("1", 3);
		bolsa.remove("1", 4);

		assertEquals(0, bolsa.size());
	}
	
	@Test
	void test6() {
		HashSet<String> setBolsa = bolsa.uniqueSet();
		
		assertEquals(bolsa.uniqueSet(), setBolsa);
	}

}
