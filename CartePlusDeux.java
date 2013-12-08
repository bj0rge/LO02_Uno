package LO02_Uno;

public class CartePlusDeux extends Carte {

	public CartePlusDeux(Couleur couleur){ // Constructeur
		
		super(20, couleur);
		
	}
	
	public void appliquerEffets(Joueur j){
		
		Manche.getInstance().setJoueurActuel(Manche.getInstance().getJoueurSuivant()); // Indique à l'unique instance de manche que le joueur actuel est le joueur suivant
		j.getMain().piocher(2); // Fait piocher deux cartes à la main
		
	}
	
	
}
