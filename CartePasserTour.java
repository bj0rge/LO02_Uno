package LO02_Uno;

/**
 * <b>CartePasserTour est la classe représentant une Carte Passe ton tour du jeu de UNO.</b>
 * <p>
 * Une CartePasserTour est caractérisée par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte</li>
 * </ul>
 * Une CartePasserTour peut être posée sur la table pour être jouée.
 * </p>
 *
 */
public class CartePasserTour extends Carte {
	
	/**
	 * Constructeur de la CartePasserTour.
	 * @param couleur
	 * 			La Couleur de la Carte.
	 */
	public CartePasserTour(Couleur couleur){
		
		super(20, couleur);
		
	}
	
	public boolean estJouable(Carte c) {
		// Si la Carte est déjà jouable, à savoir si elle a la même couleur, c'est bon
		boolean retour = super.estJouable(c);
		if ((this.getClass() == c.getClass())) {
			retour = true;
		}
		return retour;
	}

	public void appliquerEffets(boolean premier_tour){
		if (premier_tour) {
			System.out.println("La première page est un " + this + ", " + Manche.getInstance().getJoueurActuel() + " passe son tour !");
			Manche.getInstance().passerJoueur();
		}
		else {
			System.out.println(Manche.getInstance().getJoueurSuivant() + " passe son tour !");
			Manche.getInstance().passerJoueur();
		}
		
	}
	
	public String toString(){
		
		return ("(/) " + this.getCouleur());
		
	}
}