package fr.utt.isi.lo02.projet_uno.carte;

import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;

/**
 * <b>CarteJoker est la classe représentant une Carte du jeu de UNO qui change de couleur.</b>
 * <p>C'est une classe fille de la classe abstraite {@link Carte}.</p>
 * <p> C'est la classe abstraite mère de {@link CarteChangerCouleur} et {@link CartePlusQuatre}. </p>
 */
public abstract class CarteJoker extends Carte {
	/**
	 * Constructeur de {@link CarteChangerCouleur} et {@link CartePlusQuatre}.
	 * @see Carte#Carte(int, Couleur)
	 */
	public CarteJoker() {
		super(50, null);
	}
	
	public abstract void appliquerEffets(boolean premier_tour);
	
	public abstract String toString();

}
