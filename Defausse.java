package LO02_Uno;

import java.util.ArrayList;
import java.util.Iterator;


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
	 * @see Carte
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
					Defausse.getInstance().setDefausse(new ArrayList<Carte>());
					}
				}
			}
		return Defausse.instance;
	}
	
	/**
	 * Réinitialise la couleur des CarteJoker
	 * @param liste_carte
	 * 			Liste des Cartes à réinitialiser
	 * @return La liste de Cartes avec les Jokers remis à zéro, sous forme d'une instance de Cartes ArrayList.
	 */
	public ArrayList<Carte> razCouleurJoker(ArrayList<Carte> liste_carte){	
		// On repasse la couleur des cartes +4 et changer couleur à null
		// Faudrait pas que le joueur en pioche une et ne puisse pas choisir la couleur =) 
		Iterator<Carte> it = liste_carte.iterator();
		while (it.hasNext()){
			Carte c = it.next();
			if (c instanceof CarteJoker) {
				c.setCouleur(null);
			}
		}
		return liste_carte;
	}
	
	/**
	 * Ajoute une Carte dans la pile de la Défausse, elle est donc jouée et posée sur la table. 
	 * @param carte
	 * 			Carte à défausser.
	 */
	public void defausser(Carte carte){
		this.getDefausse().add(carte);
	}
	
	/**
	 * Retourne l'index de la dernière Carte posée.
	 * @return L'index de la dernière Carte posée.
	 */
	public int getIndexCartePosee() {
		return (this.getDefausse().size() -1);
	}
	
	/**
	 * Permet de retourner "physiquement" l'ancienne Défausse afin de constituer la nouvelle Pioche.
	 * @return La future Pioche, sous la forme d'une instance d'une ArrayList<Carte>
	 * @see Pioche
	 */
	public ArrayList<Carte> retournerDefausse() {
		// On récupère la dernière Carte jouée
		Carte carte_jouee = this.getDerniereCarteJouee();
		
		// On l'enlève de la Défausse actuelle
		this.getDefausse().remove(this.getIndexCartePosee());
		
		// On stocke la défausse.
		ArrayList<Carte> nouvelle_pioche = this.getDefausse();
		
		// On met à jour la nouvelle Défausse
		ArrayList<Carte> nouvelle_defausse = new ArrayList<Carte>();
		nouvelle_defausse.add(carte_jouee);
		this.setDefausse(nouvelle_defausse);
		
		// On retourne finalement ce qui sera la nouvelle Pioche non mélangée
		return nouvelle_pioche;
	}
	
	
	/**
	 * Retourne la dernière Carte posée sur le haut de la Défausse.
	 * @return La dernière Carte posée sur le haut de la Défausse, sous la forme d'une instance de Carte.
	 */
	public Carte getDerniereCarteJouee() {
		return this.getDefausse().get(this.getIndexCartePosee());
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
