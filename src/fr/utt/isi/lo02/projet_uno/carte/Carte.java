package fr.utt.isi.lo02.projet_uno.carte;

import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;
import fr.utt.isi.lo02.projet_uno.manche.Defausse;
import fr.utt.isi.lo02.projet_uno.manche.Pioche;

/**
 * <b>Carte est la classe repr�sentant une carte du jeu de UNO.</b>
 * <p> C'est une classe abstraite m�re de tous les cartes du jeu.
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
	 * La {@link Couleur} port�e par la carte. Peut �tre nul pour des Cartes Joker.
	 */
	private Couleur couleur;
	
	
	/**
	 * Constructeur de la Carte.
	 * @param points
	 * 			Le nombre de points que rapporte la Carte.
	 * @param couleur
	 * 			La {@link Couleur} port�e par la Carte. La valeur peut �tre �gale � <i>null</i>.
	 */
	public Carte(int points, Couleur couleur){
		this.setPoints(points);
		this.setCouleur(couleur);
	}
	
	
	
	/**
	 * Retourne la possibilit� ou non de jouer la Carte par rapport � la derni�re pos�e dans la d�fausse.
	 * @param c
	 * 		La carte que le joueur veut jouer
	 * @return <i>Vrai</i> si jouable, <i>Faux</i> sinon
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
	 * @param premier_tour
	 * 			Les effets d'une carte sont l�g�rement modifi�es si elle est retourn�e de la pioche dans la d�fausse au premier tour.
	 * @see Pioche
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
	 * @return Une instance de {@link Couleur}, qui correspond � la couleur port�e par la Carte.
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
	 * 			La nouvelle {@link Couleur} de la Carte.
	 */
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
	
	
	/**
	 * Retourne une repr�sentation de la Carte sous forme de String.
	 * @return Une repr�sentation de la Carte sous forme de String. 
	 */
	public abstract String toString();
	
}
