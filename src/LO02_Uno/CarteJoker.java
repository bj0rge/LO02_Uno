package LO02_Uno;

/**
 * <b>CarteJoker est la classe représentant une Carte du jeu de UNO qui change de couleur.</b>
 *
 */
public abstract class CarteJoker extends Carte {

	public CarteJoker() {
		super(50, null);
	}
	
	public abstract void appliquerEffets(boolean premier_tour);
	
	public abstract String toString();

}
