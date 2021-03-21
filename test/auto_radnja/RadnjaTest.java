package auto_radnja;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import auto_radnja.gume.AutoGuma;

public abstract class RadnjaTest {
	
	protected Radnja vulkanizerskaRadnja;
	
	@Test
	void testDodajGumu() {
		AutoGuma g = new AutoGuma();
		
		g.setMarkaModel("Pirelli");
		g.setPrecnik(16);
		g.setSirina(220);
		g.setVisina(75);
		
		vulkanizerskaRadnja.dodajGumu(g);
		assertEquals(1, vulkanizerskaRadnja.pronadjiGumu("Pirelli").size());
		assertEquals(g, vulkanizerskaRadnja.pronadjiGumu("Pirelli").get(0));
	}
	
	@Test
	void testDodajGumuNull() {
		
		assertThrows(java.lang.NullPointerException.class, 
						() -> vulkanizerskaRadnja.dodajGumu(null));	
	}
	@Test
	void testDodajGumuDuplikat() {
		AutoGuma g1 = new AutoGuma();
		AutoGuma g2 = new AutoGuma();
		
		g1.setMarkaModel("Pirelli");
		g1.setPrecnik(16);
		g1.setSirina(220);
		g1.setVisina(75);
		
		g2.setMarkaModel("Pirelli");
		g2.setPrecnik(16);
		g2.setSirina(220);
		g2.setVisina(75);
		
		vulkanizerskaRadnja.dodajGumu(g1);
		
		assertThrows(java.lang.RuntimeException.class, 
				() -> vulkanizerskaRadnja.dodajGumu(g2));	
	}

	@Test
	void testPronadjiGumu() {

		AutoGuma g1 = new AutoGuma();
		AutoGuma g2 = new AutoGuma();
		AutoGuma g3 = new AutoGuma();
		
		g1.setMarkaModel("Pirelli");
		g1.setPrecnik(16);
		g1.setSirina(220);
		g1.setVisina(75);
		
		g2.setMarkaModel("Pirelli");
		g2.setPrecnik(16);
		g2.setSirina(225);
		g2.setVisina(78);

		g3.setMarkaModel("Dunlop");
		g3.setPrecnik(16);
		g3.setSirina(220);
		g3.setVisina(75);
		
		vulkanizerskaRadnja.dodajGumu(g1);
		vulkanizerskaRadnja.dodajGumu(g2);
		vulkanizerskaRadnja.dodajGumu(g3);
		
		List<AutoGuma> gume = vulkanizerskaRadnja.pronadjiGumu("Pirelli");
		assertEquals(1, gume.size());
		assertTrue(gume.contains(g1));
		
	}
	
	@Test
	void testPronadjiGumuNull() {
		assertEquals(vulkanizerskaRadnja.pronadjiGumu(null), null);
	}
	
	

}
