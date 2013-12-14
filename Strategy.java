package LO02_Uno;

import java.util.Iterator;

/**
 * Classe définissant la Stratégie qu'un JoueurIA va déployer.
 * @see JoueurIA
 */
public abstract class Strategy {
	
	/**
	 * Permet d'effectuer un tour de jeu d'un JoueurIA automatiquement,
	 * en sélectionannt la bonne Carte en fonction de la Strategy de jeu.
	 * @param j
	 * 			Joueur utilisant la Strategy.
	 */
	public abstract void jouer(JoueurIA j);
	
	public void set(JoueurIA j) {
		boolean a_plus_quatre = false;
		Iterator<Carte> it = j.getMain().getCartes().iterator();
		
		// On vérifie si le Joueur a un +4 dans sa Main 
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
			// Si le Joueur suivant a moins de 3 Cartes dans la main, la stratégie est aggressive
			if (Manche.getInstance().getJoueurSuivant().getMain().getCartes().size() <= 3) {
				s = new Attaquer();
			}
			// Sinon, on économise les Cartes méchancetés
			else {
				s = new JouerChiffres();
			}
		}
		
		// On définit alors la Stratégie la plus adéquate.
		j.setStrategie(s);
	}
	
}
