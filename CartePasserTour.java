package LO02_Uno;

public class CartePasserTour extends Carte {

	public CartePasserTour(Couleur couleur){
		
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
			System.out.println("La premi�re page est un " + this + ", " + Manche.getInstance().getJoueurActuel() + " passe son tour !");
			Manche.getInstance().passerJoueur();
		}
		else {
			System.out.println(Manche.getInstance().getJoueurSuivant() + "passe son tour !");
			Manche.getInstance().passerJoueur();
		}
		
	}
	
	public String toString(){
		
		return ("(/) " + this.getCouleur());
		
	}
}