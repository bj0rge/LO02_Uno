package LO02_Uno;

public class CarteChangerSens extends Carte {

	public CarteChangerSens(Couleur couleur){
		
		super(20, couleur);
		
	}
	
	public void appliquerEffets(){
		
		System.out.println("Le jeu change de sens !");
		Manche.getInstance().changerSens();
		
	}
	
	public String toString(){
		
		return ("<> " + this.getCouleur());
		
	}

}