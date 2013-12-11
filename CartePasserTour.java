package LO02_Uno;

public class CartePasserTour extends Carte {

	public CartePasserTour(Couleur couleur){
		
		super(20, couleur);
		
	}
	
	public void appliquerEffets(boolean premier_tour){
		if (premier_tour) {
			System.out.println("La première page est un " + this + ", le joueur " + (Partie.getInstance().getListeJoueurs().indexOf(Manche.getInstance().getJoueurActuel()) + 1) + " passe son tour !");
			Manche.getInstance().passerJoueur();
		}
		else {
			System.out.println("Le joueur " + Manche.getInstance().getJoueurSuivant() + "passe son tour !");
			Manche.getInstance().passerJoueur();
		}
		
	}
	
	
	public boolean estJouable(Carte c) {
		// Si la Carte est déjà jouable, à savoir si elle a la même couleur, c'est bon
		boolean retour = super.estJouable(c);
		if ((this.getClass() == c.getClass())) {
			retour = true;
		}
		return retour;
	}
	
	public String toString(){
		
		return ("(/) " + this.getCouleur());
		
	}
	
}