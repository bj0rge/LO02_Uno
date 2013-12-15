package LO02_Uno;

/**
 * <b>CartePlusDeux est la classe représentant une Carte +2 du jeu de UNO.</b>
 * <p>
 * Une CartePlusDeux est caractérisée par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte </li>
 * </ul>
 * Une CartePlusDeux peut être posée sur la table pour être jouée.
 * </p>
 *
 */
public class CartePlusDeux extends Carte {

	/**
	 * Constructeur de la CartePlusDeux.
	 * @param couleur
	 * 			La Couleur de la Carte.
	 */
	public CartePlusDeux(Couleur couleur){
		
		super(20, couleur);
		
	}
	
	public boolean estJouable(Carte c) {
		// Si la Carte est déjà jouable, à savoir si elle a la même couleur, c'est bon
		boolean retour = super.estJouable(c);
		if ((this.getClass() == c.getClass())) {
			retour = true;
		}
		return retour;
	}

	public void appliquerEffets(boolean premier_tour){
		if (premier_tour) {
			
			if (Manche.getInstance().getJoueurActuel().estHumain()) {
				StringBuffer sb = new StringBuffer();
				sb.append(Manche.getInstance().getJoueurActuel());
				sb.append(" pioche : ");
				for (int i = 0; i < 2; i++) {
					sb.append(Manche.getInstance().getJoueurActuel().piocher());
					sb.append(" et ");
				}
				sb.delete((sb.length() - 3), sb.length());
				System.out.println(sb);
			}
			else {
				System.out.println(Manche.getInstance().getJoueurActuel() + " pioche 2 cartes");
				for (int i = 0; i < 2; i++) {
					Manche.getInstance().getJoueurActuel().piocher();
				}
			}
			Manche.getInstance().passerJoueur();
	
			System.out.println("et il passe son tour.");
		}
		else {
			if (Manche.getInstance().getJoueurSuivant().estHumain()) {
				 // System.out.println(Manche.getInstance().getJoueurSuivant() + " pioche :");
				StringBuffer sb = new StringBuffer();
				sb.append(Manche.getInstance().getJoueurSuivant());
				sb.append(" pioche : ");
				for (int i = 0; i < 2; i++) {
					sb.append(Manche.getInstance().getJoueurSuivant().piocher());
					sb.append(" et ");
				}
				sb.delete((sb.length() - 3), sb.length());
				System.out.println(sb);
				
			}
			else {
				System.out.println(Manche.getInstance().getJoueurSuivant() + " pioche 2 cartes.");
				for (int i = 0; i < 2; i++) {
					Manche.getInstance().getJoueurSuivant().piocher();
				}
			}
			Manche.getInstance().passerJoueur();
	
			System.out.println("et il passe son tour.");
		}
	}
	
	public String toString() {
		
		return ("+2 " + this.getCouleur());
		
	}
	
}