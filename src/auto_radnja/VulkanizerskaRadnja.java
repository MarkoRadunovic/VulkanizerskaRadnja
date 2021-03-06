package auto_radnja;

import java.util.LinkedList;

import auto_radnja.gume.AutoGuma;

/**
 * Klasa koja predstavlja vulkanizersku radnju i implementira interfejs Radnja.
 * 
 * @author Marko Radunovic
 * @version 0.6
 *
 */
public class VulkanizerskaRadnja implements Radnja {

	
	/**
	 * Lista sa svim gumama u vulkanizerskoj radnji.
	 */
	private LinkedList<AutoGuma> gume = new LinkedList<AutoGuma>();
	
	
	@Override
	public void dodajGumu(AutoGuma a) {
		
		if (a == null)
			throw new NullPointerException("Guma ne sme biti null");
		
		if (gume.contains(a))
			throw new RuntimeException("Guma vec postoji");
		
		gume.addFirst(a);
	}
	
	
	@Override
	public LinkedList<AutoGuma> pronadjiGumu(String markaModel) {
		
		if (markaModel == null)
			return null;
		
		AutoGuma g = new AutoGuma();
		g.setMarkaModel(markaModel);
		g.setPrecnik(16);
		g.setSirina(220);
		g.setVisina(75);
		
		LinkedList<AutoGuma> novaLista = new LinkedList<AutoGuma>();
		
		for(int i=0; i<gume.size(); i++) {
		
			if (gume.get(i).equals(g))
				novaLista.add(gume.get(i));
		}
		return novaLista;
	}

}
