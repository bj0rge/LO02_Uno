package LO02_Uno;

/**
 * <b>CartePlusCarte est la classe représentant une Carte +4 du jeu de UNO.</b>
 * <p>
 * Une CartePlusQuatre est caractérisée par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte (peut être nul)</li>
 * </ul>
 * Une CartePlusQuatre peut être posée sur la table pour être jouée.
 * </p>
 *
 */
public class CartePlusQuatre extends CarteJoker {

	/**
	 * Constructeur de la CartePlusQuatre.
	 */
	public CartePlusQuatre(){
		super();
	}
	
	public boolean estJouable(Carte c){
		
		return true;
		
	}
	
	public void appliquerEffets(boolean premier_tour){
		
		if (premier_tour){
			System.out.println("On repioche.");
			Manche.getInstance().retournerPremiereCarte();
		}
		else {
			System.out.println("\n+4 ! " + Manche.getInstance().getJoueurSuivant() + " pioche :\n");
			
			for (int i = 0; i < 4; i++) {
				System.out.println(Manche.getInstance().getJoueurSuivant().piocher());
			}

			System.out.println("et il passe son tour.");
			
			System.out.println(Manche.getInstance().getJoueurActuel() + " ! Quelle couleur voulez-vous jouer ?\n");
			
			int i = 1;
			for (Couleur couleur : Couleur.values()){
				System.out.println("[" + i + "] " + couleur);
				i ++;
			}
			
			switch (Partie.getInstance().demanderInt())
			{
			case 1:
				System.out.println("La couleur du +4 est ROUGE !");
				this.setCouleur(Couleur.ROUGE);
				break;
			case 2:
				System.out.println("La couleur du +4 est BLEU !");
				this.setCouleur(Couleur.BLEU);
				break;
			case 3:
				System.out.println("La couleur du +4 est JAUNE !");
				this.setCouleur(Couleur.JAUNE);
				break;
			case 4:
				System.out.println("La couleur du +4  est VERT !");
				this.setCouleur(Couleur.VERT);
				break;
			}
			
			Manche.getInstance().passerJoueur();
		}
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
