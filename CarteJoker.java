package LO02_Uno;

public class CarteJoker extends Carte {

	public CarteJoker(int points, Couleur couleur) {
		super(points, couleur);

		JeuDeCartes.getInstance().getJoueurActuel.direCouleur();
	}
	
	

}
