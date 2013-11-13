package LO02_Uno;

public class JeuDeCartes {

	// Attributs
	private static JeuDeCartes jeu;
	private Carte[] defausse;
	private Carte[] pioche;
	private boolean sensHoraire;
	private Joueur joueurActuel;
	
	// M�thodes
	private JeuDeCartes(){
		this.sensHoraire = true;
		// A finir
	}
	
	/**
	 * getter du singleton, cr�e le jeu de carte si n'existe pas
	 * @return JeuDeCartes
	 */
	public static JeuDeCartes getJeuDeCartes(){
		if (JeuDeCartes.jeu == null ){
			jeu = new JeuDeCartes();
		}
		return JeuDeCartes.jeu;				
	}
	
}
