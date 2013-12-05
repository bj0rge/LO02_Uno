package LO02_Uno;

import java.util.ArrayList;


/**
 * <b>JeuDeCartes est la classe repr�sentant le Jeu de UNO. C'est aussi elle qui g�re la partie en cours.</b>
 * <p>
 * Le JeuDeCartes est une classe unique (singleton), et est caract�ris�e par :
 * <ul>
 * <li>Une instance statique d'elle-m�me</li>
 * <li>Le sens actuel du jeu (horaire par d�faut)</li>
 * </ul>
 * </p>
 * @see Defausse
 * @see Pioche
 */
public class JeuDeCartes {

	// Attributs
	
	/**
	 * L'instance du JeuDeCartes.
	 */
	// L'utilisation du mot cl� volatile permet, en Java version 5 et sup�rieur, 
	// d'�viter le cas  o� "Singleton.instance" est non-nul,
	// mais pas encore "r�ellement" instanci�.
	private static volatile JeuDeCartes instance = null;
	
	/**
	 * Le sens du Jeu en cours : horaire � true, anti-horaire � false.
	 */
	private boolean sensHoraire;
	
	/**
	 * Le Joueur actuel, c'est � dire celui qui va jouer son tour.
	 */
	private Joueur joueurActuel;
	

	/**
	 * Constructeur du JeuDeCartes.
	 */
	private JeuDeCartes(){
		super();
	}

	
	
	

	/**
	 * Retourne l'instance du JeuDeCartes, et la construit si elle n'existe pas. Par d�faut, le sens horaire est � true.
	 * @return Une instance de JeuDeCartes, qui correspond au singleton.
	 */
	public final static JeuDeCartes getInstance() {
		// Le "Double-Checked Singleton"/"Singleton doublement v�rifi�" permet 
		// d'�viter un appel co�teux � synchronized (qui est lourd), 
		// une fois que l'instanciation est faite.
		if (JeuDeCartes.instance == null) {
			// Le mot-cl� synchronized sur ce bloc emp�che toute instanciation
			// multiple m�me par diff�rents "threads".
			// Il est TRES important.
			synchronized(JeuDeCartes.class) {
				if (JeuDeCartes.instance == null) {
					JeuDeCartes.instance = new JeuDeCartes();
					JeuDeCartes.instance.setSensHoraire(true);
					JeuDeCartes.instance.setJoueurActuel(Partie.getInstance().getJoueurs().get(0));
					}
				}
			}
		return Defausse.instance;
	}

	
	/**
	 * Inverse le sens de la manche du Jeu en cours.
	 */
	public void changerSens(){
		JeuDeCartes.getInstance().setSensHoraire(!JeuDeCartes.getInstance().isSensHoraire());
	}
	
	/**
	 * Retourne le joueur qui a eu la main au tour de Jeu pr�c�dent.
	 * @return Le Joueur pr�c�dent, sous la forme d'une instance Joueur.
	 * @see Joueur
	 */
	public Joueur getJoueurPrecedent(){
		ArrayList<Joueur> joueurs = Partie.getInstance().getListeJoueurs();
		int index = joueurs.indexOf(getJoueurActuel());
		
		Joueur joueur_precedent;
		if (index == 0)
			joueur_precedent = joueurs.get((joueurs.size())-1);
		else
			joueur_precedent = joueurs.get(index - 1);
		return joueur_precedent;		
	}
	
	/**
	 * Retourne le joueur qui va avoir la main au tour de Jeu suivant.
	 * @return Le Joueur suivant, sous la forme d'une instance Joueur.
	 * @see Joueur
	 */
	public Joueur getJoueurSuivant(){
		ArrayList<Joueur> joueurs = Partie.getInstance().getListeJoueurs();
		int index = joueurs.indexOf(getJoueurActuel());
		
		Joueur joueur_suivant;
		if (index == joueurs.size())
			joueur_suivant = joueurs.get(0);
		else
			joueur_suivant = joueurs.get(index + 1);
		return joueur_suivant;		
	}
	
	
	
	/**
	 * Retourne le sens de d�roulement de la manche du Jeu.
	 * @return <i>true</i> pour le sens horaire, <i>false</i> pour le sens anti-horaire.
	 */
	public boolean isSensHoraire() {
		return sensHoraire;
	}

	/**
	 * Met � jour le sens de d�roulement de la manche du Jeu.
	 * @param sensHoraire
	 * 			<i>true</i> pour horaire, <i>false</i> pour anti-horaire. 
	 */
	public void setSensHoraire(boolean sensHoraire) {
		this.sensHoraire = sensHoraire;
	}

	/**
	 * Retourne le Joueur qui a la main pour le tour actuel de la manche du Jeu.
	 * @return Le Joueur actuel, sous la forme d'une instance Joueur.
	 * @see Joueur
	 */
	public Joueur getJoueurActuel() {
		return joueurActuel;
	}

	/**
	 * Met � jour le joueurActuel.
	 * @param joueurActuel
	 * 			Nouveau Joueur actuel.
	 * @see Joueur
	 */
	public void setJoueurActuel(Joueur joueurActuel) {
		this.joueurActuel = joueurActuel;
	}
	
	
}
