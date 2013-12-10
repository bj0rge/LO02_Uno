package LO02_Uno;

public class CarteChangerCouleur extends CarteJoker{

	public CarteChangerCouleur(Couleur couleur){
		
		super(null);
		
	}
	
	public void appliquerEffets(){
		
		System.out.println("JOKER ! Quelle couleur voulez-vous jouer ?");
		int i = 1;
		for (Couleur couleur : Couleur.values()){
			System.out.println("[" + i + "] " + couleur);
			i += 1;
		}
		
		switch (Partie.getInstance().demanderInt())
		{
		case 1:
			System.out.println("La couleur du JOKER est ROUGE !");
			this.setCouleur(Couleur.ROUGE);
			break;
		case 2:
			System.out.println("La couleur du JOKER est BLEU !");
			this.setCouleur(Couleur.BLEU);
			break;
		case 3:
			System.out.println("La couleur du JOKER est JAUNE !");
			this.setCouleur(Couleur.JAUNE);
			break;
		case 4:
			System.out.println("La couleur du JOKER est VERT !");
			this.setCouleur(Couleur.VERT);
			break;
		
		}
		
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