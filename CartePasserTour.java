package LO02_Uno;

public class CartePasserTour extends Carte {

	public CartePasserTour(Couleur couleur){ // Constructeur
		
		super(20, couleur);
		
	}
	
	public void appliquerEffets(){
		
		Manche.getInstance().setJoueurActuel(Manche.getInstance().getJoueurSuivant()); // Indique à l'unique instance de manche que le joueur actuel est le joueur suivant
		
	}
	
	
}
