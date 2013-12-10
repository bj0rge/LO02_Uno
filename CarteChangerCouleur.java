package LO02_Uno;

public class CarteChangerCouleur extends CarteJoker{

	public CarteChangerCouleur(Couleur couleur){
		
		super(null);
		
	}
	
	public void appliquerEffets(){
		
		
		
	}
	
	public String toString(){
		
		StringBuffer sb = new StringBuffer();
		sb.append("J");
		if (this.getCouleur() != null){
			sb.append(" " + this.getCouleur());
		}
		
		return sb.toString();
		
	}
	
}