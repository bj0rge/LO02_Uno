package LO02_Uno;

/**
 * Interface définissant la Stratégie qu'un JoueurIA va déployer.
 * @see JoueurIA
 */
public interface Strategy {
	
	public void jouer(Joueur j);
	public void choixCouleur(Joueur j);

}
