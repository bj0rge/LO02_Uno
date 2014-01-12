package fr.utt.isi.lo02.projet_uno.strategy;

import java.util.Iterator;

import fr.utt.isi.lo02.projet_uno.carte.Carte;
import fr.utt.isi.lo02.projet_uno.carte.CartePlusQuatre;
import fr.utt.isi.lo02.projet_uno.joueur.JoueurIA;
import fr.utt.isi.lo02.projet_uno.manche.Manche;

/**
 * <b>Strat�gie qu'un JoueurIA va d�ployer.</b>
 * @see JoueurIA
 */
public abstract class Strategy {
	
	/**
	 * Permet d'effectuer un tour de jeu d'un JoueurIA automatiquement,
	 * en s�lectionannt la bonne Carte en fonction de la Strategy de jeu.
	 * @param j
	 * 			Joueur utilisant la Strategy.
	 */
	public abstract void jouer(JoueurIA j);
	
	/**
	 * Modifie la strat�gie du JoueurIA en fonction des cartes qu'il poss�de.
	 * @param j
	 * 		JoueurIA qui est en train de jouer.
	 */
	public void set(JoueurIA j) {
		boolean a_plus_quatre = false;
		Iterator<Carte> it = j.getMain().getCartes().iterator();
		
		// On v�rifie si le Joueur a un +4 dans sa Main 
		while (it.hasNext()) {
			if (it.next() instanceof CartePlusQuatre) {
				a_plus_quatre = true;
			}
		}
		
		Strategy s = null;
		int random = (int)(Math.random() * 5);
		// Si le joueur a un +4 dans son jeu, 1 chance sur 5 de le jouer
		if (a_plus_quatre && random < 1) {
			s = new Bluffer();
		}
		else {
			// Si le Joueur suivant a moins de 3 Cartes dans la main, la strat�gie est aggressive
			if (Manche.getInstance().getJoueurSuivant().getMain().getCartes().size() <= 3) {
				s = new Attaquer();
			}
			// Sinon, on �conomise les Cartes m�chancet�s
			else {
				s = new JouerChiffres();
			}
		}
		
		// On d�finit alors la Strat�gie la plus ad�quate.
		j.setStrategie(s);
	}
	
}
