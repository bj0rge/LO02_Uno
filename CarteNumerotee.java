package LO02_Uno;

/**
 * <b>CarteNumerotee est la classe représentant une Carte simple du jeu de UNO.</b>
 * <p>
 * Une CarteNumérotée est caractérisée par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte (peut être nul)</li>
 * <li>Sa valeur faciale</li>
 * </ul>
 * Une CarteNumérotée peut être posée sur la table pour être jouée.
 * </p>
 *
 */
public class CarteNumerotee extends Carte {

	/**
	 * Le numéro représenté sur la CareNumérotée.
	 */
	private int valeur_faciale;
	
	
	/**
	 * Constructeur de la Carte.
	 * @param points
	 * 			Le nombre de points que rapporte la Carte, qui sera aussi sa valeur faciale.
	 * @param couleur
	 * 			La couleur portée par la Carte. La valeur peut être égale à <i>null</i>.
	 */
	public CarteNumerotee(int points, Couleur couleur) {
		super(points, couleur);
		this.setValeurFaciale(points);
	}

	
	public boolean estJouable(Carte c) {
		// Si la Carte est déjà jouable, à savoir si elle a la même couleur, c'est bon
		boolean retour = super.estJouable(c);
		
		/* Si la Carte c est bien de la classe CarteNumerotee ET si elles ont la même valeur faciale
		 * Note : on peut caster sans souci puisque le double & court-circuite la deuxième opération si la
		 * première renvoie faux
		 */
		if ((this.getClass() == c.getClass()) && (this.getValeurFaciale() == ((CarteNumerotee) c).getValeurFaciale()))
			retour = true;
		
		return retour;
	}


	
	public String toString() {
		return this.getValeurFaciale() + " " + this.getCouleur();
	}
	
	public void appliquerEffets(boolean premier_tour) { } // Inutile mais obligatoire : la méhode est abstraite pour la Carte Mère

	
	
	
	/**
	 * Retourne la valeur faciale de la CarteNumerotee.
	 * @return La valeur portée par la Carte.
	 */
	public int getValeurFaciale() {
		return valeur_faciale;
	}

	/**
	 * Met à jour la valeur faciale portée par la CarteNumerotee
	 * @param valeur_faciale
	 * 			Nouvelle valeur faciale de la Carte.
	 */
	public void setValeurFaciale(int valeur_faciale) {
		this.valeur_faciale = valeur_faciale;
	}


}
