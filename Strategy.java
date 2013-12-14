package LO02_Uno;

import java.util.Iterator;

/**
 * Classe d�finissant la Strat�gie qu'un JoueurIA va d�ployer.
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
