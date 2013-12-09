package LO02_Uno;

public class CartePlusQuatre extends CarteJoker {

	public CartePlusQuatre(Couleur couleur){
		
		super(couleur);
		
	}
	
	public void appliquerEffets(){
		
		
		
	}
	
	public String toString(){
		
		return ("+4 " + this.getCouleur());
		
	}
	
	
}
