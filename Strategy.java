package LO02_Uno;

/**
 * Interface définissant la Stratégie qu'un JoueurIA va déployer.
 * @see JoueurIA
 */
public interface Strategy {
	
	/**
	 * Permet d'effectuer un tour de jeu d'un JoueurIA automatiquement,
	 * en sélectionannt la bonne Carte en fonction de la Strategy de jeu.
	 * @param j
	 * 			Joueur utilisant la Strategy.
	 */
	public void jouer(Joueur j);
	
}
