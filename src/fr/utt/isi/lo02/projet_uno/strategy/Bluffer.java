package fr.utt.isi.lo02.projet_uno.strategy;

import java.util.Iterator;

import fr.utt.isi.lo02.projet_uno.carte.Carte;
import fr.utt.isi.lo02.projet_uno.carte.CartePlusQuatre;
import fr.utt.isi.lo02.projet_uno.joueur.JoueurIA;

/**
 * <b>Strat�gie d�terminant qu'il faut jouer un +4.</b>
 *
 */
public class Bluffer extends Strategy{
	
	/**
	 * Le joueurIA pose une carte +4 s'il en poss�de une.
	 * @param j
	 * 		JoueurIA en train de jouer.
	 */
	public void jouer(JoueurIA j) {
		
		// On parcourt les cartes pour savoir si le joueur IA poss�de une carte +4
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
