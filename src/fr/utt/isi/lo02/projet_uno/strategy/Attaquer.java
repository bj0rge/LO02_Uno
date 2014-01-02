package fr.utt.isi.lo02.projet_uno.strategy;

import java.util.Iterator;

import fr.utt.isi.lo02.projet_uno.carte.Carte;
import fr.utt.isi.lo02.projet_uno.carte.CarteChangerCouleur;
import fr.utt.isi.lo02.projet_uno.carte.CarteChangerSens;
import fr.utt.isi.lo02.projet_uno.carte.CarteNumerotee;
import fr.utt.isi.lo02.projet_uno.carte.CartePasserTour;
import fr.utt.isi.lo02.projet_uno.carte.CartePlusDeux;
import fr.utt.isi.lo02.projet_uno.carte.CartePlusQuatre;
import fr.utt.isi.lo02.projet_uno.joueur.JoueurIA;
import fr.utt.isi.lo02.projet_uno.manche.Defausse;

/**
 * <b>Strat�gie d�terminant qu'il faut jouer en priorit� des Cartes "m�chancet�".</b>
 *
 */
public class Attaquer extends Strategy {

	/**
	 * Retourne le niveau de m�chancet� d'une Carte afin de savoir
	 * s'il faut la jouer plut�t qu'une autre.
	 * @param c 
	 * 			La Carte � analyser.
	 * @return Le niveau de m�chancet�.
	 */
	private int niveauMechancete(Carte c) {
		int nv = -1;

		if (c.getClass() == new CarteNumerotee(0, null).getClass()) {
			nv = 0;
		}
		else if (c.getClass() == new CarteChangerCouleur().getClass()) {
			nv = 1;
		}
		else if (c.getClass() == new CarteChangerSens(null).getClass()) {
			nv = 2;
		}
		else if (c.getClass() == new CartePasserTour(null).getClass()) {
			nv = 3;
		}
		else if (c.getClass() == new CartePlusDeux(null).getClass()) {
			nv = 4;
		}
		else if (c.getClass() == new CartePlusQuatre().getClass()) {
			nv = 5;
		}
		return nv;
	}
	
	public void jouer(JoueurIA j) {
		/* On cherche � savoir si le Joueur poss�de une Carte m�chancet�,
		 * et si oui, on garde en m�moire celle qui apporte le plus grand
		 * malus au Joueur suivant.
		 */
		int nv = -1;
		Carte c = null;
		Iterator<Carte> it = j.getMain().getCartes().iterator();
		while (it.hasNext()) {
			Carte ca = it.next();
			if (this.niveauMechancete(ca) > nv && ca.estJouable(Defausse.getInstance().getDerniereCarteJouee())) {
				nv = this.niveauMechancete(ca);
				c = ca;
			}
		}
		
		// Si la Carte la moins favorable n'est pas une simple carte num�rot�e, on la joue
		if (nv > 0) {
			System.out.println(j + " pose un " + c + ".\n");
			j.poser(c);
		}
		// Sinon, on joue en posant un chiffre.
		else {
			j.setStrategie(new JouerChiffres());
			j.getStrategie().jouer(j);
		}
	}
}
