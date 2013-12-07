package LO02_Uno;

import java.util.ArrayList;


/**
 * <b>Manche est la classe repr�sentant la Manche du jeu de UNO.</b>
 * <p>
 * La Manche est une classe unique (singleton), et est caract�ris�e par :
 * <ul>
 * <li>Une instance statique d'elle-m�me</li>
 * <li>Le sens actuel du jeu (horaire par d�faut)</li>
 * <li>Le Joueur qui va jouer son tour</li> 
 * </ul>
 * </p>
 */
public class Manche {

	
	/**
	 * L'instance de la Manche.
	 */
	// L'utilisation du mot cl� volatile permet, en Java version 5 et sup�rieur, 
	// d'�viter le cas  o� "Singleton.instance" est non-nul,
	// mais pas encore "r�ellement" instanci�.
	private static volatile Manche instance = null;
	
	/**
	 * Le sens du Jeu en cours : horaire � true, anti-horaire � false.
	 */
	private boolean sensHoraire;
	
	/**
	 * Le Joueur actuel, c'est � dire celui qui va jouer son tour.
	 */
	private Joueur joueurActuel;
	

	/**
	 * Constructeur de la Manche.
	 */
	private Manche(){
		super();
	}

	
	
	

	/**
	 * Retourne l'instance de la Manche, et la construit si elle n'existe pas. Par d�faut, le sens horaire est � true.
	 * @return Une instance de Manche, qui correspond au singleton.
	 */
	public final static Manche getInstance() {
		// Le "Double-Checked Singleton"/"Singleton doublement v�rifi�" permet 
		// d'�viter un appel co�teux � synchronized (qui est lourd), 
		// une fois que l'instanciation est faite.
		if (Manche.instance == null) {
			// Le mot-cl� synchronized sur ce bloc emp�che toute instanciation
			// multiple m�me par diff�rents "threads".
			// Il est TRES important.
			synchronized(Manche.class) {
				if (Manche.instance == null) {
					Manche.instance = new Manche();
					Manche.instance.setSensHoraire(true);
					Manche.instance.setJoueurActuel(Partie.getInstance().getListeJoueurs().get(0));
					}
				}
			}
		return Manche.instance;
	}

	
	/**
	 * Inverse le sens de la Manche en cours.
	 */
	public void changerSens(){
		Manche.getInstance().setSensHoraire(!Manche.getInstance().isSensHoraire());
	}
	
	/**
	 * Retourne le Joueur qui a eu la main au tour de jeu pr�c�dent dans la Manche.
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
	 * Retourne le Joueur qui va avoir la main au tour de jeu suivant.
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
	 * Passe le JoueurSuivant en JoueurActuel.
	 */
	public void passerJoueur() {
		this.setJoueurActuel(this.getJoueurSuivant());
	}
	
	
	
	/**
	 * Retourne le sens de d�roulement de la Manche en cours.
	 * @return <i>true</i> pour le sens horaire, <i>false</i> pour le sens anti-horaire.
	 */
	public boolean isSensHoraire() {
		return sensHoraire;
	}

	/**
	 * Met � jour le sens de d�roulement de la Manche en cours.
	 * @param sensHoraire
	 * 			<i>true</i> pour horaire, <i>false</i> pour anti-horaire. 
	 */
	public void setSensHoraire(boolean sensHoraire) {
		this.sensHoraire = sensHoraire;
	}

	/**
	 * Retourne le Joueur qui a la main pour le tour actuel de la Manche en cours.
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
