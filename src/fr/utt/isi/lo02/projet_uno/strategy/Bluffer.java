package fr.utt.isi.lo02.projet_uno.strategy;

import java.util.Iterator;

import fr.utt.isi.lo02.projet_uno.carte.Carte;
import fr.utt.isi.lo02.projet_uno.carte.CartePlusQuatre;
import fr.utt.isi.lo02.projet_uno.joueur.JoueurIA;

/**
 * <b>Stratégie déterminant qu'il faut jouer un +4.</b>
 *
 */
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
