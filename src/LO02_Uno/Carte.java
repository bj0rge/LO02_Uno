package LO02_Uno;

/**
 * <b>Carte est la classe représentant une Carte du jeu de UNO.</b>
 * <p>
 * Une Carte est caractérisée par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte (peut être nul)</li>
 * </ul>
 * Une Carte peut être posée sur la table pour être jouée.
 * </p>
 *
 */
public abstract class Carte {
	
	/**
	 * Le nombre de points que rapporte la Carte en fin de partie.
	 */
	private int points;
	
	/**
	 * La couleur portée par la carte. Peut être nul pour certaines Cartes spéciales.
	 */
	private Couleur couleur;
	
	
	/**
	 * Constructeur de la Carte.
	 * @param points
	 * 			Le nombre de points que rapporte la Carte.
	 * @param couleur
	 * 			La couleur portée par la Carte. La valeur peut être égale à <i>null</i>.
	 */
	public Carte(int points, Couleur couleur){
		this.setPoints(points);
		this.setCouleur(couleur);
	}
	
	
	
	/**
	 * Retourne la possibilité de jouer la Carte ou non par rapport à la dernière posée dans la Défausse.
	 * @return Vrai si la carte est jouable, faux sinon.
	 * @see Defausse
	 */
	public boolean estJouable(Carte c) {
		/* Une Carte sera jouable quoi qu'il arrive si elle a la même couleur que celle d'avant, ou si 
		 * la Carte d'avant n'a pas de couleur (Ex: si la première carte retournée est une carte noire)
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
	 * @return Une instance de Couleur, qui correspond à la couleur portée par la Carte.
	 * @see Couleur
	 */
	public Couleur getCouleur() {
		return couleur;
	}

	/**
	 * Met à jour le nombre de points de la Carte.
	 * @param points
	 * 			Le nouveau nombre de points.
	 */
	public void setPoints(int points) {
		this.points = points;
	}



	/**
	 * Met à jour la couleur de la Carte.
	 * @param couleur
	 * 			La nouvelle couleur de la Carte.
	 * @see Couleur
	 */
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
	
	
	/**
	 * Retourne une représentation de la Carte sous forme de String.
	 * @returns Une représentation de la Carte sous forme de String. 
	 */
	public abstract String toString();
	
}
