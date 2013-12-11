package LO02_Uno;

public class CartePlusQuatre extends CarteJoker {

	public CartePlusQuatre(){
		
		super(null);
		
	}
	
	public void appliquerEffets(boolean premier_tour){
		
		System.out.println("\n+4 ! Le joueur " + Manche.getInstance().getJoueurSuivant() + "prend 4 cartes et passe son tour.");
		
		for (int i = 0; i < 4; i++) {
			Manche.getInstance().getJoueurSuivant().piocher();
		}
		
		System.out.println("Joueur " + Manche.getInstance().getJoueurActuel() + "! Quelle couleur voulez-vous jouer ?\n");
		
		int i = 0;
		for (Couleur couleur : Couleur.values()){
			System.out.println("[" + i + "] " + couleur);
			i += 1;
		}
		
		switch (Partie.getInstance().demanderInt())
		{
		case 0:
			System.out.println("La couleur du +4 est ROUGE !");
			this.setCouleur(Couleur.ROUGE);
			break;
		case 1:
			System.out.println("La couleur du +4 est BLEU !");
			this.setCouleur(Couleur.BLEU);
			break;
		case 2:
			System.out.println("La couleur du +4 est JAUNE !");
			this.setCouleur(Couleur.JAUNE);
			break;
		case 3:
			System.out.println("La couleur du +4  est VERT !");
			this.setCouleur(Couleur.VERT);
			break;
		}
		
		Manche.getInstance().passerJoueur();
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
