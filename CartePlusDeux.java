package LO02_Uno;

public class CartePlusDeux extends Carte {

	public CartePlusDeux(Couleur couleur){
		
		super(20, couleur);
		
	}
	
	public void appliquerEffets(){
		
		System.out.println("Le joueur " + Manche.getInstance().getJoueurActuel() + "a posé un +2 !");
		System.out.println("Le joueur " + Manche.getInstance().getJoueurSuivant() + "prend deux cartes et passe son tour.");
		
		for (int i = 0; i < 2; i++) {
			Manche.getInstance().getJoueurSuivant().piocher();
		}
		Manche.getInstance().passerJoueur();
		
	}
	
	public String toString() {
		
		return ("+2 " + this.getCouleur());
		
	}
	
}