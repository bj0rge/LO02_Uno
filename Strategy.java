package LO02_Uno;

/**
 * Interface d�finissant la Strat�gie qu'un JoueurIA va d�ployer.
 * @see JoueurIA
 */
public interface Strategy {
	
	/**
	 * Permet d'effectuer un tour de jeu d'un JoueurIA automatiquement,
	 * en s�lectionannt la bonne Carte en fonction de la Strategy de jeu.
	 * @param j
	 * 			Joueur utilisant la Strategy.
	 */
	public void jouer(Joueur j);
	
}
