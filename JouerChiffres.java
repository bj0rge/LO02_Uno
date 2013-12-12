package LO02_Uno;

import java.util.Iterator;

/**
 * Classe déterminant la Stratégie de jouer en priorité les Cartes Numérotées à gros chiffre.
 */
public class JouerChiffres implements Strategy {

	
	public void jouer(Joueur j) {
		
		
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
		j.poser(ca);
		System.out.println(j + " pose un " + ca + ".\n");
	}

}
