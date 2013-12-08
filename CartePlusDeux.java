package LO02_Uno;

public class CartePlusDeux extends Carte {

	public CartePlusDeux(Couleur couleur){
		
		super(20, couleur);
		
	}
	
	public void appliquerEffets(Joueur j){
		
		Manche.getInstance().setJoueurActuel(Manche.getInstance().getJoueurSuivant());
		j.getMain().piocher(2);
		
	}
	
	
}
