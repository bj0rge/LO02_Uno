package fr.utt.isi.lo02.projet_uno.carte;

import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;
import fr.utt.isi.lo02.projet_uno.manche.Manche;

/**
 * <b>CartePasserTour est la classe représentant une Carte Passe ton tour du jeu de UNO.</b>
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
		// On utilise le constructeur de Carte.
		// Toutes les CartePasserTour valent 20 points et possède une couleur dès le début
		// d'où l'appel de constructeur avec la valeur 20 et une couleur en
		super(20, couleur);
		
	}
	
	public boolean estJouable(Carte c) {
		// On vérifie si c'est la même couleur en utilisant la méthode estJouable de Carte.
		boolean retour = super.estJouable(c);
		
		// On vérifie également si la carte précédente est du même type que CartePasserTour.
		// Si c'est le cas, alors la carte est jouable, quelque soit la couleur de la carte précédente.
		if ((this.getClass() == c.getClass())) {
			retour = true;
		}
		return retour;
	}

	/**
	 * <p>Applique l'effet de CartePasserTour : le joueur suivant passe son tour.</p>
	 * 
	 * @param premier_tour
	 * 			Si <i>Vrai</i> : le premier joueur passe son tour.
	 * @see Manche#passerJoueur()
	 */
	public void appliquerEffets(boolean premier_tour){
		// Si c'est le premier tour, c'est le premier joueur qui passe son tour.
		if (premier_tour) {
			System.out.println(Manche.getInstance().getJoueurActuel() + " passe son tour dès le début !\n");
			Manche.getInstance().passerJoueur();
		}
		else { // Sinon c'est le joueur suivant qui passe son tour.
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