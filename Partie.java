package LO02_Uno;

import java.util.ArrayList;


/**
 * <b>Partie est la classe représentant la Partie de UNO. Elle permettra de faire boucler les Manches du jeu.</b>
 * <p>
 * La Partie est une classe unique (singleton), et est caractérisée par :
 * <ul>
 * <li>Une instance statique d'elle-même</li>
 * <li>Le Mode de jeu</li>
 * <li>Une liste des Joueurs de la Partie</li>
 * <li>Le nombre de points à atteindre pour mettre fin à la Partie</li>
 * <li>Le nombre de manches à atteindre pour mettre fin à la Partie</li>
 * <li>Le nombre de manches écoulées lors de la Partie</li>
 * </ul>
 * </p>
 * @see ModeDeJeu
 * @see Joueur
 */
public class Partie 
{
	
	/**
	 * L'instance de la Partie.
	 */
	private static volatile Partie instance = null;
	
	/**
	 * Le Mode de jeu de la Partie. 
	 */
	private ModeDeJeu mode;
	
	/**
	 * La liste des Joueurs qui participent à la Partie.
	 * @see Joueur
	 */
	private ArrayList<Joueur> listeJoueurs;
	
	/**
	 * Le nombre de points à atteindre pour mettre fin à la Partie.
	 */
	private int nb_pts_max;
	
	/**
	 * Le nombre de manches à atteindre pour mettre fin à la Partie.
	 */
	private int nb_manches_max;
	
	/**
	 * Le nombre de manches écoulées depuis le début de la partie.
	 */
	private int manche;
	
	
	
	
	/**
	 * Retourne l'instance du Partie, et la construit si elle n'existe pas.
	 * @return Une instance de Partie, qui correspond au singleton.
	 */
	public final static Partie getInstance() {
		// Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
		// d'éviter un appel coûteux à synchronized (qui est lourd), 
		// une fois que l'instanciation est faite.
		if (Partie.instance == null) {
			// Le mot-clé synchronized sur ce bloc empêche toute instanciation
			// multiple même par différents "threads".
			// Il est TRES important.
			synchronized(Manche.class) {
				if (Partie.instance == null) {
					Partie.instance = new Partie();
					Partie.instance.setMode(ModeDeJeu.STANDARD);
					Partie.instance.setNb_pts_max(500);
					Partie.instance.setManche(0);
					Partie.instance.setListeJoueurs(new ArrayList<Joueur>());
				}
			}
		}
		return Partie.instance;
	}
	
	/**
	 * Retourne le Mode de jeu de la Partie.
	 * @return Le Mode de jeu, sous la forme d'une instance.
	 */
	public ModeDeJeu getMode() {
		return mode;
	}

	/**
	 * Met à jour le Mode de jeu.
	 * @param mode
	 * 			Mode de jeu de la Partie qui va remplacer l'ancien.
	 */
	public void setMode(ModeDeJeu mode) {
		this.mode = mode;
	}


	/**
	 * Retourne le nombre de points à atteindre pour mettre fin à la Partie.
	 * @return Le nombre de points à atteindre pour mettre fin à la Partie.
	 */
	public int getNb_pts_max() {
		return nb_pts_max;
	}

	/**
	 * Met à jour le nombre de points à atteindre pour mettre fin à la Partie.
	 * @param nb_pts_max
	 * 			Le nombre de points à mettre à jour.
	 */
	public void setNb_pts_max(int nb_pts_max) {
		this.nb_pts_max = nb_pts_max;
	}

	/**
	 * Retourne le nombre de manches à atteindre pour mettre fin à la Partie.
	 * @return Le nombre de manches à atteindre pour mettre fin à la Partie.
	 */
	public int getNb_manches_max() {
		return nb_manches_max;
	}

	/**
	 * Met à jour le nombre de manches à atteindre pour mettre fin à la Partie.
	 * @param nb_manches_max
	 * 			Le nombre de manches à mettre à jour.
	 */
	public void setNb_manches_max(int nb_manches_max) {
		this.nb_manches_max = nb_manches_max;
	}

	/**
	 * Retourne le numéro de la manche en cours.
	 * @return Le numéro de la manche en cours.
	 */
	public int getManche() {
		return manche;
	}

	/**
	 * Met à jour le numéro de la manche en cours.
	 * @param manche
	 * 			Le numéro de la manche à mettre à jour.
	 */
	public void setManche(int manche) {
		this.manche = manche;
	}

	/**
	 * Retourne la Liste des Joueurs présents dans la Partie.
	 * @return La liste des Joueurs présents dans la Partie, sous forme d'une instance d'ArrayList<Joueur>.
	 * @see Joueur
	 */
	public ArrayList<Joueur> getListeJoueurs(){
		return this.listeJoueurs;
	}
	
	/**
	 * Met à jour la Liste des Joueurs présents dans la Partie. 
	 * @param listeJoueurs
	 * 			Liste des Joueurs à mettre à jour. 
	 */
	public void setListeJoueurs(ArrayList<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}

	
	
	
//	public static void main(String[] args){
//		
//	}
}
