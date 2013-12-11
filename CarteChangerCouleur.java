package LO02_Uno;

public class CarteChangerCouleur extends CarteJoker{

	public CarteChangerCouleur() {	
		super();
	}
	
	
	public boolean estJouable(Carte c) {
		return true;
	}
	
	public void appliquerEffets(boolean premier_tour){
		if (premier_tour) {
			System.out.println("\nJOKER ! Jouez ce que vous voulez !\n");
		}
		else {
			
			System.out.println("\nJOKER ! Quelle couleur voulez-vous jouer ?\n");
			int i = 1;
			for (Couleur couleur : Couleur.values()){
				System.out.println("[" + i + "] " + couleur);
				i++;
			}
			
			if (Manche.getInstance().getJoueurActuel().estHumain()) {
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