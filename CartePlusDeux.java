package LO02_Uno;

public class CartePlusDeux extends Carte {

	public CartePlusDeux(Couleur couleur){
		
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
			System.out.println("\nLa premi�re carte de la d�fausse est un " + this + ", pas de bol !\n"
					+ Manche.getInstance().getJoueurActuel() + " pioche :");
			for (int i = 0; i < 2; i++) {
				System.out.println("Un " + Manche.getInstance().getJoueurActuel().piocher());
			}
			Manche.getInstance().passerJoueur();
	
			System.out.println("et il passe son tour.");
		}
		else {
			System.out.println(Manche.getInstance().getJoueurSuivant() + " pioche :");
			
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