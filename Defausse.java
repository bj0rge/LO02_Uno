package LO02_Uno;

import java.util.ArrayList;


/**
 * <b>Defausse est la classe représentant la Défausse du jeu de UNO.</b>
 * <p>
 * La Défausse est une classe unique (singleton), et est caractérisée par :
 * <ul>
 * <li>Une instance statique d'elle-même</li>
 * <li>Une liste de Cartes</li>
 * </ul>
 * </p>
 * @see Carte
 */
public class Defausse {
	
	/**
	 * L'instance de la Défausse.
	 */
	// L'utilisation du mot clé volatile permet, en Java version 5 et supérieur, 
	// d'éviter le cas  où "Singleton.instance" est non-nul,
	// mais pas encore "réellement" instancié.
	private static volatile Defausse instance = null;
	
	/**
	 * Les Cartes contenues dans la Défausse.
	 */
	private ArrayList<Carte> defausse;
	
	
	/**
	 * Constructeur de la Défausse.
	 */
	private Defausse(){
		super();
	}
	
	
	
	/**
	 * Retourne l'instance de la Défausse, et la construit si elle n'existe pas.
	 * @return Une instance de Defausse, qui correspond au singleton.
	 */
	public final static Defausse getInstance() {
		// Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
		// d'éviter un appel coûteux à synchronized (qui est lourd), 
		// une fois que l'instanciation est faite.
		if (Defausse.instance == null) {
			// Le mot-clé synchronized sur ce bloc empêche toute instanciation
			// multiple même par différents "threads".
			// Il est TRES important.
			synchronized(Defausse.class) {
				if (Defausse.instance == null) {
					Defausse.instance = new Defausse();
					}
				}
			}
		return Defausse.instance;
	}
	
	/**
	 * Ajoute une Carte dans la pile de la Défausse, elle est donc jouée et posée sur la table. 
	 * @param carte
	 * 			Carte à défausser.
	 */
	public void defausser(Carte carte){
		Defausse.getInstance().getDefausse().add(carte);
		
	}
	
	

	
	/**
	 * Retourne la liste des Cartes de la Défausse.
	 * @return Une liste de Cartes.
	 */
	public ArrayList<Carte> getDefausse() {
		return defausse;
	}
	
	/**
	 * Met à jour la Défausse.
	 * @param carte
	 * 		Liste des Cartes de la nouvelle Défausse. 
	 */
	public void setDefausse(ArrayList<Carte> cartes) {
		this.defausse = cartes;
	}
	
	
}
