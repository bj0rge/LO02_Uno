package LO02_Uno;

public class CartePlusDeux extends Carte {

	public CartePlusDeux(Couleur couleur){
		
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
			System.out.println("\nLa première carte de la défausse est un " + this + ", pas de bol !\n"
					+ "Le joueur " + (Partie.getInstance().getListeJoueurs().indexOf(Manche.getInstance().getJoueurActuel()) + 1) + " pioche :");
			for (int i = 0; i < 2; i++) {
				System.out.println("Un " + Manche.getInstance().getJoueurActuel().piocher());
			}
			Manche.getInstance().passerJoueur();
	
			System.out.println("et il passe son tour.");
		}
		else {
			System.out.println("Le joueur " + (Partie.getInstance().getListeJoueurs().indexOf(Manche.getInstance().getJoueurSuivant()) + 1) + " pioche :");
			
			for (int i = 0; i < 2; i++) {
				System.out.println("Un " + Manche.getInstance().getJoueurSuivant().piocher());
			}
			Manche.getInstance().passerJoueur();
	
			System.out.println("et il passe son tour.");
		}
	}
	
	public String toString() {
		
		return ("+2 " + this.getCouleur());
		
	}
	
}