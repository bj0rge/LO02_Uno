package fr.utt.isi.lo02.projet_uno.manche;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.isi.lo02.projet_uno.carte.Carte;
import fr.utt.isi.lo02.projet_uno.carte.CarteJoker;


/**
 * <b>Defausse est la classe repr�sentant la d�fausse du jeu de UNO.</b>
 * <p>
 * La d�fausse est une classe unique (singleton), et est caract�ris�e par :
 * <ul>
 * <li>Une instance statique d'elle-m�me</li>
 * <li>Une liste de Cartes</li>
 * </ul>
 * </p>
 * @see Carte
 */
public class Defausse {
	
	/**
	 * L'instance de la {@link Defausse}.
	 */
	// L'utilisation du mot cl� volatile permet, en Java version 5 et sup�rieur, 
	// d'�viter le cas  o� "Singleton.instance" est non-nul,
	// mais pas encore "r�ellement" instanci�.
	private static volatile Defausse instance = null;
	
	/**
	 * Les Cartes contenues dans la {@link Defausse}.
	 * @see Carte
	 */
	private ArrayList<Carte> defausse;
	
	
	
	/**
	 * Constructeur de la d�fausse.
	 */
	private Defausse(){
		super();
	}	
	
	/**
	 * Retourne l'instance de la Defausse, et la construit si elle n'existe pas.
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
	 * Ajoute une Carte dans la pile de la d�fausse, elle est donc jou�e et pos�e sur la table. 
	 * @param carte
	 * 			Carte � d�fausser.
	 */
	public void defausser(Carte carte){
		this.getDefausse().add(carte);
	}

	/**
	 * Permet de retourner "physiquement" l'ancienne d�fausse afin de constituer la nouvelle Pioche.
	 * @return La future Pioche, sous la forme d'une instance d'une {@link ArrayList}.
	 * @see Pioche
	 */
	public ArrayList<Carte> retournerDefausse() {
		// On r�cup�re la derni�re Carte jou�e
		Carte carte_jouee = this.getDerniereCarteJouee();
		
		// On l'enl�ve de la d�fausse actuelle
		this.getDefausse().remove(this.getIndexCartePosee());
		
		// On stocke la d�fausse en remettant la couleur des joker � null.
		ArrayList<Carte> nouvelle_pioche = Defausse.getInstance().razCouleurJoker(this.getDefausse());
		
		// On met � jour la nouvelle d�fausse
		ArrayList<Carte> nouvelle_defausse = new ArrayList<Carte>();
		nouvelle_defausse.add(carte_jouee);
		this.setDefausse(nouvelle_defausse);
		
		// On retourne finalement ce qui sera la nouvelle Pioche non m�lang�e
		return nouvelle_pioche;
	}
	
	/**
	 * R�initialise la couleur des CarteJoker.
	 * @param liste_carte
	 * 			Liste des Cartes � r�initialiser
	 * @return La liste de Cartes avec les Jokers remis � z�ro, sous forme d'une instance de Cartes {@link ArrayList}.
	 */
	public ArrayList<Carte> razCouleurJoker(ArrayList<Carte> liste_carte){	
		// On repasse la couleur des cartes +4 et changer couleur � null
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
	 * Retourne la liste des Cartes de la Defausse.
	 * @return Une liste de Cartes.
	 */
	public ArrayList<Carte> getDefausse() {
		return defausse;
	}
	
	/**
	 * Retourne l'index de la derni�re Carte pos�e.
	 * @return L'index de la derni�re Carte pos�e.
	 */
	public int getIndexCartePosee() {
		return (this.getDefausse().size() -1);
	}

	/**
	 * Retourne la derni�re Carte pos�e sur le haut de la Defausse.
	 * @return La derni�re Carte pos�e sur le haut de la Defausse, sous la forme d'une instance de Carte.
	 */
	public Carte getDerniereCarteJouee() {
		return this.getDefausse().get(this.getIndexCartePosee());
	}

	/**
	 * Retourne l'avant derni�re Carte pos�e sur le haut de la Defausse.
	 * @return L'avant derni�re Carte pos�e sur le haut de la Defausse, sous la forme d'une instance de Carte.
	 */
	public Carte getAvantDerniereCarteJouee() {
		return this.getDefausse().get(this.getIndexCartePosee() -1);
	}

	/**
	 * Met � jour la Defausse.
	 * @param cartes
	 * 		Liste des Cartes de la nouvelle Defausse. 
	 */
	public void setDefausse(ArrayList<Carte> cartes) {
		this.defausse = cartes;
	}
}
