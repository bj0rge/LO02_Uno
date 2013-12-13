package LO02_Uno;

/**
 * <b>CarteChangerCouleur est la classe repr�sentant une Carte joker du jeu de UNO.</b>
 * <p>
 * Une CarteChangerCouleur est caract�ris�e par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte (peut �tre nul)</li>
 * </ul>
 * Une CarteChangerCouleur peut �tre pos�e sur la table pour �tre jou�e.
 * </p>
 *
 */
public class CarteChangerCouleur extends CarteJoker{

	/**
	 * Constructeur de la CarteChangerCouleur.
	 */
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
			Couleur c = Manche.getInstance().getJoueurActuel().choixCouleur();
			this.setCouleur(c);
			System.out.println("La couleur du Joker est " + c);
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