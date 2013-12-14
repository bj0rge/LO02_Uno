package LO02_Uno;

import java.util.Iterator;

public class Bluffer extends Strategy{
	
	public void jouer(JoueurIA j) {
		Iterator<Carte> it = j.getMain().getCartes().iterator();
		Carte c = null;
		while (it.hasNext()) {
			Carte ca = it.next();
			if (ca instanceof CartePlusQuatre) {
				c = ca;
			}
		}
		j.poser(c);		
	}

}
