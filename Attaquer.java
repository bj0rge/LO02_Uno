package LO02_Uno;

import java.util.Iterator;

public class Attaquer extends Strategy {

	/**
	 * Retourne le niveau de méchanceté d'une Carte afin de savoir
	 * s'il faut la jouer plutôt qu'une autre.
	 * @param c 
	 * 			La Carte à analyser.
	 * @return Le niveau de méchanceté.
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
	
	public void jouer(Joueur j) {
		/* On cherche à savoir si le Joueur possède une Carte méchanceté,
		 * et si oui, on garde en mémoire celle qui apporte le plus grand
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
		
		// Si la Carte la moins favorable n'est pas une simple carte numérotée, on la joue
		if (nv > 0) {
			System.out.println(j + " pose un " + c + ".\n");
			j.poser(c);
		}
		// Sinon, on joue en posant un chiffre.
		else {
			((JoueurIA) j).setStrategie(new JouerChiffres());
			j.getStrategie().jouer(j);
		}
	}
}
