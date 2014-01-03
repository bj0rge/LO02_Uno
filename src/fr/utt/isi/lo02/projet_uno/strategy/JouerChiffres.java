package fr.utt.isi.lo02.projet_uno.strategy;

import java.util.Iterator;

import fr.utt.isi.lo02.projet_uno.carte.Carte;
import fr.utt.isi.lo02.projet_uno.carte.CarteNumerotee;
import fr.utt.isi.lo02.projet_uno.carte.CartePlusQuatre;
import fr.utt.isi.lo02.projet_uno.joueur.JoueurIA;
import fr.utt.isi.lo02.projet_uno.manche.Defausse;

/**
 * <b>Stratégie déterminant qu'il faut jouer en priorité les Cartes Numérotées à gros chiffre.</b>
 */
public class JouerChiffres extends Strategy {

	
	public void jouer(JoueurIA j) {
		
		
		Carte ca = new CarteNumerotee(-1, null);
		
		// On parcourt la Main du Joueur
		Iterator<Carte> it = j.getMain().getCartes().iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			// Si la Carte c est jouable, et qu'elle est une Carte Numerotée et qu'elle vaut plus de points que ca
			if (c.estJouable(Defausse.getInstance().getDerniereCarteJouee()) 
					&& c.getClass() == ca.getClass()
					&& c.getPoints() > ca.getPoints()) {
				// On passe c dans ca
				ca = c;
			}
		}
			
		// Si aucune Carte Numérotée n'est jouable, alors on joue la première carte jouable différente du +4
		if (ca.getPoints() == -1) {
			it = j.getMain().getCartes().iterator();
			while (it.hasNext() && ca.getPoints() == -1) {
				Carte c = it.next();
				if (c.estJouable(Defausse.getInstance().getDerniereCarteJouee()) 
						&& c.getClass() != (new CartePlusQuatre()).getClass()) {
					ca = c;
				}
			}
		}
		
		// Sinon, c'est que la seule Carte jouable est un +4
		if (ca.getPoints() == -1) {
			it = j.getMain().getCartes().iterator();
			while (it.hasNext() && ca.getPoints() == -1) {
				Carte c = it.next();
				if (c.estJouable(Defausse.getInstance().getDerniereCarteJouee())) {
					ca = c;
				}
			}
		}
			
			
		// On sait maintenant quelle Carte jouer : ca
		System.out.println(j + " pose un " + ca + ".\n");
		j.poser(ca);
	}

}
