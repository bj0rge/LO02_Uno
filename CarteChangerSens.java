package LO02_Uno;

/**
 * <b>CarteChangerSens est la classe repr�sentant une Carte Changement de sens du jeu de UNO.</b>
 * <p>
 * Une CarteChangerSens est caract�ris�e par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte</li>
 * </ul>
 * Une CarteChangerSens peut �tre pos�e sur la table pour �tre jou�e.
 * </p>
 *
 */
public class CarteChangerSens extends Carte {
	
	/**
	 * Constructeur de la CarteChangerSens.
	 * @param couleur
	 * 			La Couleur de la Carte.
	 */
	public CarteChangerSens(Couleur couleur){
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
		
		System.out.println("Le jeu change de sens !");
		Manche.getInstance().changerSens();
		if (premier_tour) {
			if (Partie.getInstance().getMode() != ModeDeJeu.DEUX_JOUEURS || 
					(Partie.getInstance().getMode() == ModeDeJeu.CHALLENGE 
						&& Partie.getInstance().getListeJoueurs().size() == 2)){
				Manche.getInstance().passerJoueur();
			} else { 
				Manche.getInstance().passerJoueur();
				Manche.getInstance().passerJoueur();
			}

			System.out.println("C'est donc " + Manche.getInstance().getJoueurActuel() + " qui commence.");

		}
	}
	
	public String toString(){
		
		return ("<> " + this.getCouleur());
		
	}
}