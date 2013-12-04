package LO02_Uno;

/**
 * <b>Carte est la classe repr�sentant une carte du jeu de UNO.</b>
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
public class Carte {
	
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
	 * Retourne le nombre de points de la Carte.
	 * @return Le nombre de poins.
	 */
	public int getPoints() {
		return points;
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
	 * Retourne la couleur de la Carte.
	 * @return Une instance de Couleur, qui correspond � la couleur port�e par la Carte.
	 * @see Couleur
	 */
	public Couleur getCouleur() {
		return couleur;
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
	
}
