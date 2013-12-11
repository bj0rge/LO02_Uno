package LO02_Uno;

public class CarteChangerSens extends Carte {

	public CarteChangerSens(Couleur couleur){
		
		super(20, couleur);
		
	}
	
	public void appliquerEffets(boolean premier_tour){
		
		System.out.println("La première carte de la défausse est un " + this);
		System.out.println("Le jeu change de sens !");
		Manche.getInstance().changerSens();
		if (premier_tour) {
			Manche.getInstance().passerJoueur();
			Manche.getInstance().passerJoueur();
			System.out.println("C'est donc le joueur " + (Partie.getInstance().getListeJoueurs().indexOf((Manche.getInstance().getJoueurActuel())) + 1) + " qui commence.");
		}
	}
	
	public String toString(){
		
		return ("<> " + this.getCouleur());
		
	}
	
	public boolean estJouable(Carte c) {
		// Si la Carte est déjà jouable, à savoir si elle a la même couleur, c'est bon
		boolean retour = super.estJouable(c);
		if ((this.getClass() == c.getClass())) {
			retour = true;
		}
		return retour;
	}

}