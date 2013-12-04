package LO02_Uno;

import java.util.ArrayList;


/**
 * <b>Defausse est la classe repr�sentant la D�fausse du jeu de UNO.</b>
 * <p>
 * La D�fausse est une classe unique (singleton), et est caract�ris�e par :
 * <ul>
 * <li>Une instance statique d'elle-m�me</li>
 * <li>Une liste de Cartes</li>
 * </ul>
 * </p>
 * @see Carte
 */
public class Defausse {
	
	/**
	 * L'instance de la D�fausse.
	 */
	// L'utilisation du mot cl� volatile permet, en Java version 5 et sup�rieur, 
	// d'�viter le cas  o� "Singleton.instance" est non-nul,
	// mais pas encore "r�ellement" instanci�.
	private static volatile Defausse instance = null;
	
	/**
	 * Les Cartes contenues dans la D�fausse.
	 */
	private ArrayList<Carte> defausse;
	
	
	/**
	 * Constructeur de la D�fausse.
	 */
	private Defausse(){
		super();
	}
	
	
	
	/**
	 * Retourne l'instance de la D�fausse, et la construit si elle n'existe pas.
	 * @return Une instance de Defausse, qui correspond au singleton.
	 */
	public final static Defausse getInstance() {
		// Le "Double-Checked Singleton"/"Singleton doublement v�rifi�" permet 
		// d'�viter un appel co�teux � synchronized (qui est lourd), 
		// une fois que l'instanciation est faite.
		if (Defausse.instance == null) {
			// Le mot-cl� synchronized sur ce bloc emp�che toute instanciation
			// multiple m�me par diff�rents "threads".
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
	 * Ajoute une Carte dans la pile de la D�fausse, elle est donc jou�e et pos�e sur la table. 
	 * @param carte
	 * 			Carte � d�fausser.
	 */
	public void defausser(Carte carte){
		Defausse.getInstance().getDefausse().add(carte);
		
	}
	
	

	
	/**
	 * Retourne la liste des Cartes de la D�fausse.
	 * @return Une liste de Cartes.
	 */
	public ArrayList<Carte> getDefausse() {
		return defausse;
	}
	
	/**
	 * Met � jour la D�fausse.
	 * @param carte
	 * 		Liste des Cartes de la nouvelle D�fausse. 
	 */
	public void setDefausse(ArrayList<Carte> cartes) {
		this.defausse = cartes;
	}
	
	
}
