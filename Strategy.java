package LO02_Uno;

/**
 * Interface d�finissant la Strat�gie qu'un JoueurIA va d�ployer.
 * @see JoueurIA
 */
public interface Strategy {
	
	public void jouer(Joueur j);
	public void choixCouleur(Joueur j);

}
