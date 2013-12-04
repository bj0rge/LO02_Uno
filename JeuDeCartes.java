package LO02_Uno;

public class JeuDeCartes {

	// Attributs
	private static JeuDeCartes jeu;
	private Defausse defausse;
	private Pioche pioche;
	private boolean sensHoraire;
	private Joueur joueurActuel;
	private Joueur[] joueurs;
	
	// Méthodes
	private JeuDeCartes(){
		this.sensHoraire = true;
		// A finir
	}
	
	/**
	 * getter du singleton, crée le jeu de carte si n'existe pas
	 * @return JeuDeCartes
	 */
	public static JeuDeCartes getJeuDeCartes(){
		if (JeuDeCartes.jeu == null ){
			jeu = new JeuDeCartes();
		}
		return JeuDeCartes.jeu;				
	}
	
}
