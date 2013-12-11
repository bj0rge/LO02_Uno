package LO02_Uno;

public abstract class CarteJoker extends Carte {

	public CarteJoker() {
		super(50, null);
	}
	
	public abstract void appliquerEffets(boolean premier_tour);
	
	public abstract String toString();

}
