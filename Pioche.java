package LO02_Uno;

import java.util.ArrayList;
import java.util.Collections;


/**
 * <b>Pioche est la classe représentant la Pioche du jeu de UNO.</b>
 * <p>
 * La Pioche est une classe unique (singleton), et est caractérisée par :
 * <ul>
 * <li>Une instance statique d'elle-même</li>
 * <li>Une liste de Cartes</li>
 * </ul>
 * </p>
 * @see Carte
 */
public class Pioche {

	/**
	 * L'instance de la Pioche.
	 */
	// L'utilisation du mot clé volatile permet, en Java version 5 et supérieur, 
	// d'éviter le cas  où "Singleton.instance" est non-nul,
	// mais pas encore "réellement" instancié.
	private static volatile Pioche instance = null;
	
	/**
	 * Les Cartes contenues dans la Pioche.
	 * @see Carte
	 */
	private ArrayList<Carte> pioche;
	
	
	
	/**
	 * Constructeur de la Pioche.
	 */
	private Pioche(){
		super();
	}
	
	
	
	
	/**
	 * Action de piocher une Carte dans la Pioche.<br>
	 * Plus simplement : supprime la première Carte de la Pioche et la renvoie. Retourne la Défausse pour 
	 * en faire la nouvelle Pioche si besoin, en conservant la dernière Carte de la Défausse.  
	 * @return La Carte piochée.
	 * @see Carte
	 */
	public Carte piocher() {
		Carte carte_piochee = this.getPioche().get(0);
		this.getPioche().remove(0);
		if (this.getPioche().size() == 0)
			retournerDefausse();
		return carte_piochee;
	}
	
	
	/**
	 * Permet de retourner la Défausse. Utilisée uniquement après que la dernière Carte de la Pioche a été piochée.
	 */
	private void retournerDefausse() {
		ArrayList<Carte> nouvelle_pioche = Defausse.getInstance().retournerDefausse();
		this.setPioche(nouvelle_pioche);
		this.melanger();
	}
	
	
	/**
	 * Permet de mélanger la Pioche.
	 */
	public void melanger() {
		Collections.shuffle(getPioche());
	}
	
	
	

	/**
	 * Retourne l'instance de la Pioche, et la construit si elle n'existe pas.
	 * @return Une instance de Pioche, qui correspond au singleton.
	 */
	public final static Pioche getInstance() {
		// Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
		// d'éviter un appel coûteux à synchronized (qui est lourd), 
		// une fois que l'instanciation est faite.
		if (Pioche.instance == null) {
			// Le mot-clé synchronized sur ce bloc empêche toute instanciation
			// multiple même par différents "threads".
			// Il est TRES important.
			synchronized(Pioche.class) {
				if (Pioche.instance == null) {
					Pioche.instance = new Pioche();
					Pioche.getInstance().setPioche(new ArrayList<Carte>());
					}
				}
			}
		return Pioche.instance;
	}

	/**
	 * Retourne la liste des Cartes de la Pioche.
	 * @return Une liste de Cartes.
	 */
	public ArrayList<Carte> getPioche() {
		return pioche;
	}

	/**
	 * Met à jour la Pioche.
	 * @param carte
	 * 		Liste des Cartes de la nouvelle Pioche. 
	 */
	public void setPioche(ArrayList<Carte> pioche) {
		this.pioche = pioche;
	}
	
	
	
	
}
