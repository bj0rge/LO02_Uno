package fr.utt.isi.lo02.projet_uno.carte;

import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;

/**
 * <b>CarteJoker est la classe représentant une Carte du jeu de UNO qui change de couleur.</b>
 */
public abstract class CarteJoker extends Carte {
	/**
	 * Permet de construire {@link CarteChangerCouleur} et {@link CartePlusQuatre}.
	 * @see Carte#Carte(int, Couleur)
	 */
	public CarteJoker() {
		// On utilise le constructeur de Carte
		// CarteChangerCouleur et CartePlusQuatre valent 50 points et n'ont pas de couleur de base
		// d'où l'appel de constructeur avec 50 en paramètre.
		super(50, null);
	}
	
	public abstract void appliquerEffets(boolean premier_tour);
	
	public abstract String toString();

}
