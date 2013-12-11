package LO02_Uno;

/**
 * <b>Carte est la classe repr�sentant une Carte du jeu de UNO.</b>
 * <p>
 * Une Carte est caract�ris�e par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte (peut �tre nul)</li>
 * </ul>
 * Une Carte peut �tre pos�e sur la table pour �tre jou�e.
 * </p>
 *
 */
public abstract class Carte {
	
	/**
	 * Le nombre de points que rapporte la Carte en fin de partie.
	 */
	private int points;
	
	/**
	 * La couleur port�e par la carte. Peut �tre nul pour certaines Cartes sp�ciales.
	 */
	private Couleur couleur;
	
	
	/**
	 * Constructeur de la Carte.
	 * @param points
	 * 			Le nombre de points que rapporte la Carte.
	 * @param couleur
	 * 			La couleur port�e par la Carte. La valeur peut �tre �gale � <i>null</i>.
	 */
	public Carte(int points, Couleur couleur){
		this.setPoints(points);
		this.setCouleur(couleur);
	}
	
	
	
	/**
	 * Retourne la possibilit� de jouer la Carte ou non par rapport � la derni�re pos�e dans la D�fausse.
	 * @return Vrai si la carte est jouable, faux sinon.
	 * @see Defausse
	 */
	public boolean estJouable(Carte c) {
		/* Une Carte sera jouable quoi qu'il arrive si elle a la m�me couleur que celle d'avant, ou si 
		 * la Carte d'avant n'a pas de couleur (Ex: si la premi�re carte retourn�e est une carte noire)
		 */
		return ((this.getCouleur() == c.getCouleur()) || (c.getCouleur() == null));
	}
	
	
	/**
	 * Applique les effets induits par la carte.
	 * @see Defausse
	 */
	public abstract void appliquerEffets(boolean premier_tour);
	

	/**
	 * Retourne le nombre de points de la Carte.
	 * @return Le nombre de poins.
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Retourne la couleur de la Carte.
	 * @return Une instance de Couleur, qui correspond � la couleur port�e par la Carte.
	 * @see Couleur
	 */
	public Couleur getCouleur() {
		return couleur;
	}

	/**
	 * Met � jour le nombre de points de la Carte.
	 * @param points
	 * 			Le nouveau nombre de points.
	 */
	public void setPoints(int points) {
		this.points = points;
	}



	/**
	 * Met � jour la couleur de la Carte.
	 * @param couleur
	 * 			La nouvelle couleur de la Carte.
	 * @see Couleur
	 */
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
	
	
	/**
	 * Retourne une repr�sentation de la Carte sous forme de String.
	 * @returns Une repr�sentation de la Carte sous forme de String. 
	 */
	public abstract String toString();
	
}
