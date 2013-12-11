package LO02_Uno;

/**
 * <b>CartePlusDeux est la classe repr�sentant une Carte +2 du jeu de UNO.</b>
 * <p>
 * Une CartePlusDeux est caract�ris�e par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte </li>
 * </ul>
 * Une CartePlusDeux peut �tre pos�e sur la table pour �tre jou�e.
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
		// Si la Carte est d�j� jouable, � savoir si elle a la m�me couleur, c'est bon
		boolean retour = super.estJouable(c);
		if ((this.getClass() == c.getClass())) {
			retour = true;
		}
		return retour;
	}

	public void appliquerEffets(boolean premier_tour){
		if (premier_tour) {
			System.out.println("\nLa premi�re carte de la d�fausse est un " + this + ", pas de bol !\n"
					+ Manche.getInstance().getJoueurActuel() + " pioche :");
			for (int i = 0; i < 2; i++) {
				System.out.println("Un " + Manche.getInstance().getJoueurActuel().piocher());
			}
			Manche.getInstance().passerJoueur();
	
			System.out.println("et il passe son tour.");
		}
		else {
			System.out.println(Manche.getInstance().getJoueurSuivant() + " pioche :");
			
			for (int i = 0; i < 2; i++) {
				System.out.println("Un " + Manche.getInstance().getJoueurSuivant().piocher());
			}
			Manche.getInstance().passerJoueur();
	
			System.out.println("et il passe son tour.");
		}
	}
	
	public String toString() {
		
		return ("+2 " + this.getCouleur());
		
	}
	
}