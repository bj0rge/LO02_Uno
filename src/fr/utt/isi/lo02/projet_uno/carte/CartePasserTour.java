package fr.utt.isi.lo02.projet_uno.carte;

import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;
import fr.utt.isi.lo02.projet_uno.manche.Manche;

/**
 * <b>CartePasserTour est la classe repr�sentant une Carte Passe ton tour du jeu de UNO.</b>
 * <p>
 * Une CartePasserTour est caract�ris�e par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte</li>
 * </ul>
 * Une CartePasserTour peut �tre pos�e sur la table pour �tre jou�e.
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
		// Toutes les CartePasserTour valent 20 points et poss�de une couleur d�s le d�but
		// d'o� l'appel de constructeur avec la valeur 20 et une couleur en
		super(20, couleur);
		
	}
	
	public boolean estJouable(Carte c) {
		// On v�rifie si c'est la m�me couleur en utilisant la m�thode estJouable de Carte.
		boolean retour = super.estJouable(c);
		
		// On v�rifie �galement si la carte pr�c�dente est du m�me type que CartePasserTour.
		// Si c'est le cas, alors la carte est jouable, quelque soit la couleur de la carte pr�c�dente.
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
			System.out.println(Manche.getInstance().getJoueurActuel() + " passe son tour d�s le d�but !\n");
			Manche.getInstance().passerJoueur();
		}
		else { // Sinon c'est le joueur suivant qui passe son tour.
			System.out.println(Manche.getInstance().getJoueurSuivant() + " passe son tour !\n");
			Manche.getInstance().passerJoueur();
		}
		
	}
	
	/**
	 * Retourne une repr�sentation �crite de l'instance CartePasserTour.
	 * @return String : "(/) ({@link Couleur})"
	 */
	public String toString(){
		
		return ("(/) " + this.getCouleur());
		
	}
}