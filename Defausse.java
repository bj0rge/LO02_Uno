package LO02_Uno;

import java.util.ArrayList;
import java.util.Collections;


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
	 * @see Carte
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
					Defausse.getInstance().setDefausse(new ArrayList<Carte>());
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
		this.getDefausse().add(carte);
	}
	
	/**
	 * Retourne l'index de la derni�re Carte pos�e.
	 * @return L'index de la derni�re Carte pos�e.
	 */
	public int getIndexCartePosee() {
		return (this.getDefausse().size() -1);
	}
	
	/**
	 * Permet de retourner "physiquement" l'ancienne D�fausse afin de constituer la nouvelle Pioche.
	 * @return La future Pioche, sous la forme d'une instance d'une ArrayList<Carte>
	 * @see Pioche
	 */
	public ArrayList<Carte> retournerDefausse() {
		// On r�cup�re la derni�re Carte jou�e
		Carte carte_jouee = this.getDefausse().get(this.getIndexCartePosee());
		// On l'enl�ve de la D�fausse actuelle
		this.getDefausse().remove(this.getIndexCartePosee());
		// On stocke la d�fausse, qu'on m�lange.
		ArrayList<Carte> nouvelle_pioche = this.getDefausse();
		Collections.shuffle(nouvelle_pioche);
		// On met � jour la nouvelle D�fausse
		ArrayList<Carte> nouvelle_defausse = new ArrayList<Carte>();
		nouvelle_defausse.add(carte_jouee);
		this.setDefausse(nouvelle_defausse);
		
		// On retourne finalement ce qui sera la nouvelle Pioche
		return nouvelle_pioche;
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
