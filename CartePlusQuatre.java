package LO02_Uno;

public class CartePlusQuatre extends CarteJoker {

	public CartePlusQuatre(Couleur couleur){
		
		super(null);
		
	}
	
	public void appliquerEffets(){
		
		
		
	}
	
	public String toString(){
		
		StringBuffer sb = new StringBuffer();
		sb.append("+4");
		if (this.getCouleur() != null){
			sb.append(" " + this.getCouleur());
		}
		
		return sb.toString();
		
	}
	
}
