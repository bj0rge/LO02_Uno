package fr.utt.isi.lo02.projet_uno.carte;

import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;
import fr.utt.isi.lo02.projet_uno.manche.Manche;

/**
 * <b>CartePasserTour est la classe représentant une Carte Passe ton tour du jeu de UNO.</b>
 * <p>C'est une classe fille de la classe abstraite {@link Carte}.</p>
 * <p>
 * Une CartePasserTour est caractérisée par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte</li>
 * </ul>
 * Une CartePasserTour peut être posée sur la table pour être jouée.
 * </p>
 *
 */
public class CartePasserTour extends Carte {
	
	/**
	 * Constructeur de la CartePasserTour.
	 * @param couleur
	 * 			La {@link Couleur} de la Carte.
	 * @see Carte#Carte(int, Couleur)
	 */
	public CartePasserTour(Couleur couleur){
		
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

	/**
	 * <p>Applique l'effet de CartePasserTour : le joueur suivant passe son tour.</p>
	 * 
	 * @param premier_tour
	 * 			<li> Si <i>Vrai</i> : le premier joueur passe son tour.
	 * @see Manche#passerJoueur()
	 */
	public void appliquerEffets(boolean premier_tour){
		if (premier_tour) {
			System.out.println(Manche.getInstance().getJoueurActuel() + " passe son tour dès le début !\n");
			Manche.getInstance().passerJoueur();
		}
		else {
			System.out.println(Manche.getInstance().getJoueurSuivant() + " passe son tour !\n");
			Manche.getInstance().passerJoueur();
		}
		
	}
	
	/**
	 * Retourne une représentation écrite de l'instance CartePasserTour.
	 * @return String : "(/) ({@link Couleur})"
	 */
	public String toString(){
		
		return ("(/) " + this.getCouleur());
		
	}
}