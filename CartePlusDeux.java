package LO02_Uno;

public class CartePlusDeux extends Carte {

	public CartePlusDeux(Couleur couleur){
		
		super(20, couleur);
		
	}
	
	public void appliquerEffets(){
		
		System.out.println("Le joueur " + (Partie.getInstance().getListeJoueurs().indexOf(Manche.getInstance().getJoueurSuivant()) + 1) + " pioche :");
		
		for (int i = 0; i < 2; i++) {
			System.out.println("Un " + Manche.getInstance().getJoueurSuivant().piocher());
		}
		Manche.getInstance().passerJoueur();

		System.out.println("et il passe son tour.");
	}
	
	public boolean estJouable(Carte c) {
		// Si la Carte est déjà jouable, à savoir si elle a la même couleur, c'est bon
				boolean retour = super.estJouable(c);
				if ((this.getClass() == c.getClass())) {
					retour = true;
				}
				return retour;
	}
	
	
	public String toString() {
		
		return ("+2 " + this.getCouleur());
		
	}
	
}