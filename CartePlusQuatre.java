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

			if (Manche.getInstance().getJoueurSuivant().estHumain()) {
				System.out.println(Manche.getInstance().getJoueurSuivant() + " pioche :");
				for (int i = 0; i < 4; i++) {
					System.out.println("Un " + Manche.getInstance().getJoueurSuivant().piocher());
				}
			}
			else {
				System.out.println(Manche.getInstance().getJoueurSuivant() + " pioche 4 cartes.");
				for (int i = 0; i < 4; i++) {
					Manche.getInstance().getJoueurSuivant().piocher();
				}
			}
			
			Couleur c = Manche.getInstance().getJoueurActuel().choixCouleur();
			System.out.println("La couleur du +4 est " + c + " !");
			this.setCouleur(c);
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
