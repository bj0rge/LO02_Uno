package LO02_Uno;

public class CarteChangerSens extends Carte {

	public CarteChangerSens(Couleur couleur){ // Constructeur
		
		super(20, couleur);
		
	}
	
	public void appliquerEffets(){
		
		Manche.getInstance().changerSens(); // Change le sens avec la m�thode changerSens de l'unique manche
		
	}
	

}
