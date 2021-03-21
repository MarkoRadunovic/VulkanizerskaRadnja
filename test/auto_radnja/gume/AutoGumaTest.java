package auto_radnja.gume;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AutoGumaTest {

	AutoGuma g;
	@BeforeEach
	void setUp() throws Exception {
		g = new AutoGuma();
	}

	@AfterEach
	void tearDown() throws Exception {
		g = null;
	}

	@Test
	void testAutoGuma() {
		
		g = new AutoGuma();
		
		assertNotNull(g);
	}

	@Test
	void testAutoGumaStringIntIntInt() {
		
		g = new AutoGuma("Pirelli", 16, 220, 75);
		
		assertNotNull(g);
		assertEquals("Pirelli", g.getMarkaModel());
		assertEquals(16, g.getPrecnik());
		assertEquals(220, g.getSirina());
		assertEquals(75, g.getVisina());
	}

	@Test
	void testSetMarkaModel() {
		g.setMarkaModel("Pirelli");
		
		assertEquals("Pirelli", g.getMarkaModel());
	}
	
	@Test
	void testSetMarkaModelNull() {
	
		assertThrows(java.lang.NullPointerException.class, 
						() -> g.setMarkaModel(null) );
	}
	
	@Test
	void testSetMarkaModelKratakString() {
		assertThrows(java.lang.RuntimeException.class, 
				() -> g.setMarkaModel("P") );
		assertThrows(java.lang.RuntimeException.class, 
				() -> g.setMarkaModel("Pi") );
	}
	
	@Test
	void testSetMarkaModelPrazanString() {
		assertThrows(java.lang.RuntimeException.class, 
				() -> g.setMarkaModel("") );
	}

	@Test
	void testSetPrecnik() {
		g.setPrecnik(14);
		
		assertEquals(14, g.getPrecnik());
	}
	
	@ParameterizedTest
	@CsvSource({
		"-5",
		"10",
		"12",
		"23",
		"26",
		"30",
		"300",
	})
	void testSetPrecnikVanOpsega(int p) {
		
		assertThrows(java.lang.RuntimeException.class, 
						() -> g.setPrecnik(p) );	
	}

	@Test
	void testSetSirina() {
		g.setSirina(220);
		
		assertEquals(220, g.getSirina());
	}
	
	@ParameterizedTest
	@CsvSource({
		"50",
		"134",
		"5",
		"100",
		"-5",
		"356",
		"400",
		"4000",
	})
	void testSetSirinaVanOpsega(int s) {
		
		assertThrows(java.lang.RuntimeException.class, 
						() -> g.setSirina(s) );	
	}

	@Test
	void testSetVisina() {
		g.setVisina(75);
		
		assertEquals(75, g.getVisina());
	}
	
	@ParameterizedTest
	@CsvSource({
		"5",
		"10",
		"-12",
		"24",
		"96",
		"100",
		"150",
		"1000",
	})
	void testSetVisinaVanOpsega(int v) {
		
		assertThrows(java.lang.RuntimeException.class, 
						() -> g.setVisina(v) );	
	}
	
	@ParameterizedTest
	@CsvSource({
		"25",
		"95",
	})
	void testSetVisinaGranice(int v) {
		
		g.setVisina(v);
		assertEquals(v, g.getVisina());
	}

	@Test
	void testToString() {
		
		g.setMarkaModel("Pirelli");
		g.setPrecnik(16);
		g.setSirina(220);
		g.setVisina(75);
		
		String naziv = g.toString();
		
		assertTrue(naziv.contains("Pirelli"));
		assertTrue(naziv.contains("220"));
		assertTrue(naziv.contains("16"));
		assertTrue(naziv.contains("75"));
		
	}

	@ParameterizedTest
	@CsvSource({
		"Pirelli, 16, 220, 75, Pirelli, 16, 220, 75, true", 
		"Pirelli, 16, 220, 75, Dunlop, 16, 220, 75, false", 
		"Pirelli, 16, 220, 75, Pirelli, 17, 220, 75, false", 
		"Pirelli, 16, 220, 75, Pirelli, 16, 250, 75, false", 
		"Pirelli, 16, 220, 75, Pirelli, 16, 220, 85, false", 
		"Pirelli, 17, 220, 75, Pirelli, 17, 250, 75, false", 
		"Pirelli, 17, 220, 75, Pirelli, 17, 220, 85, false", 
		"Pirelli, 16, 220, 75, Pirelli, 16, 250, 75, false", 
		"Pirelli, 16, 220, 75, Pirelli, 16, 220, 85, false", 
		"Pirelli, 16, 220, 75, Pirelli, 17, 250, 85, false", 
	})
	void testEqualsObject(String marka1, int p1, int s1, int v1, String marka2, int p2, int s2, int v2, boolean eq) {
	
		g.setMarkaModel(marka1);
		g.setPrecnik(p1);
		g.setSirina(s1);
		g.setVisina(v1);
		
		AutoGuma g1 = new AutoGuma();
		g1.setMarkaModel(marka2);
		g1.setPrecnik(p2);
		g1.setSirina(s2);
		g1.setVisina(v2);
		
		assertEquals(eq, g.equals(g1));
	}

}
