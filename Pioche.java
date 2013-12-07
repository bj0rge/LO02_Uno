package LO02_Uno;

import java.util.ArrayList;
import java.util.Collections;


/**
 * <b>Pioche est la classe repr�sentant la Pioche du jeu de UNO.</b>
 * <p>
 * La Pioche est une classe unique (singleton), et est caract�ris�e par :
 * <ul>
 * <li>Une instance statique d'elle-m�me</li>
 * <li>Une liste de Cartes</li>
 * </ul>
 * </p>
 * @see Carte
 */
public class Pioche {

	/**
	 * L'instance de la Pioche.
	 */
	// L'utilisation du mot cl� volatile permet, en Java version 5 et sup�rieur, 
	// d'�viter le cas  o� "Singleton.instance" est non-nul,
	// mais pas encore "r�ellement" instanci�.
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
	 * Plus simplement : supprime la premi�re Carte de la Pioche et la renvoie. Retourne la D�fausse pour 
	 * en faire la nouvelle Pioche si besoin, en conservant la derni�re Carte de la D�fausse.  
	 * @return La Carte pioch�e.
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
	 * Permet de retourner la D�fausse. Utilis�e uniquement apr�s que la derni�re Carte de la Pioche a �t� pioch�e.
	 */
	private void retournerDefausse() {
		ArrayList<Carte> nouvelle_pioche = Defausse.getInstance().retournerDefausse();
		this.setPioche(nouvelle_pioche);
		this.melanger();
	}
	
	
	/**
	 * Permet de m�langer la Pioche.
	 */
	public void melanger() {
		Collections.shuffle(getPioche());
	}
	
	
	

	/**
	 * Retourne l'instance de la Pioche, et la construit si elle n'existe pas.
	 * @return Une instance de Pioche, qui correspond au singleton.
	 */
	public final static Pioche getInstance() {
		// Le "Double-Checked Singleton"/"Singleton doublement v�rifi�" permet 
		// d'�viter un appel co�teux � synchronized (qui est lourd), 
		// une fois que l'instanciation est faite.
		if (Pioche.instance == null) {
			// Le mot-cl� synchronized sur ce bloc emp�che toute instanciation
			// multiple m�me par diff�rents "threads".
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
	 * Met � jour la Pioche.
	 * @param carte
	 * 		Liste des Cartes de la nouvelle Pioche. 
	 */
	public void setPioche(ArrayList<Carte> pioche) {
		this.pioche = pioche;
	}
	
	
	
	
}
