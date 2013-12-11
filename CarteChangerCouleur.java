package LO02_Uno;

public class CarteChangerCouleur extends CarteJoker{

	public CarteChangerCouleur(){
		
		super(null);
		
	}
	
	public void appliquerEffets(boolean premier_tour){
		
		System.out.println("\nJOKER ! Quelle couleur voulez-vous jouer ?\n");
		int i = 0;
		for (Couleur couleur : Couleur.values()){
			System.out.println("[" + i + "] " + couleur);
			i += 1;
		}
		
		switch (Partie.getInstance().demanderInt())
		{
		case 0:
			System.out.println("La couleur du JOKER est ROUGE !");
			this.setCouleur(Couleur.ROUGE);
			break;
		case 1:
			System.out.println("La couleur du JOKER est BLEU !");
			this.setCouleur(Couleur.BLEU);
			break;
		case 2:
			System.out.println("La couleur du JOKER est JAUNE !");
			this.setCouleur(Couleur.JAUNE);
			break;
		case 3:
			System.out.println("La couleur du JOKER est VERT !");
			this.setCouleur(Couleur.VERT);
			break;
		}
		
	}
	
	public String toString(){
		
		StringBuffer sb = new StringBuffer();
		sb.append("Joker");
		if (this.getCouleur() != null){
			sb.append(" " + this.getCouleur());
		}
		
		return sb.toString();
		
	}
	
}