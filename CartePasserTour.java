package LO02_Uno;

public class CartePasserTour extends Carte {

	public CartePasserTour(Couleur couleur){
		
		super(20, couleur);
		
	}
	
	public void appliquerEffets(boolean premier_tour){
		
		System.out.println("Le joueur " + Manche.getInstance().getJoueurSuivant() + "passe son tour !");
		Manche.getInstance().passerJoueur();
		
	}
	
	public String toString(){
		
		return ("(/) " + this.getCouleur());
		
	}
	
}