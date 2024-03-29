package fr.utt.isi.lo02.projet_uno.carte;

import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;

/**
 * <b>CarteNumerotee est la classe repr�sentant une Carte simple du jeu de UNO.</b>
 * <p>
 * Une CarteNum�rot�e est caract�ris�e par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie (soit sa valeur faciale)</li>
 * <li>La couleur qu'elle porte</li>
 * </ul>
 * Une CarteNum�rot�e peut �tre pos�e sur la table pour �tre jou�e.
 * </p>
 *
 */
public class CarteNumerotee extends Carte {

	/**
	 * Le num�ro repr�sent� sur la CarteNumerotee.
	 */
	private int valeur_faciale;
	
	
	/**
	 * Constructeur de CarteNumerotee.
	 * @param points
	 * 			Le nombre de points que rapporte la Carte, qui sera aussi sa valeur faciale.
	 * @param couleur
	 * 			La {@link Couleur} port�e par la Carte.
	 * @see Carte#Carte(int, Couleur)
	 */
	public CarteNumerotee(int points, Couleur couleur) {
		super(points, couleur);
		this.setValeurFaciale(points);
	}

	
	public boolean estJouable(Carte c) {
		// Si la Carte est d�j� jouable, � savoir si elle a la m�me couleur, c'est bon
		boolean retour = super.estJouable(c);
		
		/* Si la Carte c est bien de la classe CarteNumerotee ET si elles ont la m�me valeur faciale
		 * Note : on peut caster sans souci puisque le double & court-circuite la deuxi�me op�ration si la
		 * premi�re renvoie faux
		 */
		if ((this.getClass() == c.getClass()) && (this.getValeurFaciale() == ((CarteNumerotee) c).getValeurFaciale()))
			retour = true;
		
		return retour;
	}
	
	// Inutile mais obligatoire : la m�hode est abstraite pour la Carte M�re, donc une impl�mentation fictive est obligatoire.
	public void appliquerEffets(boolean premier_tour) { }
	
	/**
	 * Retourne la valeur faciale de la CarteNumerotee.
	 * @return La valeur port�e par la Carte.
	 */
	public int getValeurFaciale() {
		return valeur_faciale;
	}

	/**
	 * Met � jour la valeur faciale port�e par la CarteNumerotee
	 * @param valeur_faciale
	 * 			Nouvelle valeur faciale de la Carte.
	 */
	public void setValeurFaciale(int valeur_faciale) {
		this.valeur_faciale = valeur_faciale;
	}

	/**
	 * Retourne la repr�sentation �crite de l'instance CarteNumerotee.
	 * @return String : "(Valeur) ({@link Couleur})"
	 */
	public String toString() {
		return this.getValeurFaciale() + " " + this.getCouleur();
	}
}
