package LO02_Uno;

/**
 * <b>CartePasserTour est la classe repr�sentant une Carte Passe ton tour du jeu de UNO.</b>
 * <p>
 * Une CartePasserTour est caract�ris�e par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte</li>
 * </ul>
 * Une CartePasserTour peut �tre pos�e sur la table pour �tre jou�e.
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
		// Si la Carte est d�j� jouable, � savoir si elle a la m�me couleur, c'est bon
		boolean retour = super.estJouable(c);
		if ((this.getClass() == c.getClass())) {
			retour = true;
		}
		return retour;
	}

	public void appliquerEffets(boolean premier_tour){
		if (premier_tour) {
			System.out.println("La premi�re page est un " + this + ", " + Manche.getInstance().getJoueurActuel() + " passe son tour !");
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