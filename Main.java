package LO02_Uno;

import java.util.ArrayList;

/**
 * <b>Main est la classe représentant la Main d'un Joueur de UNO.</b>
 * <p>
 * La Main est caractérisée par :
 * <ul>
 * <li>Une liste de Cartes</li>
 * </ul>
 * </p>
 * @see Carte
 */
public class Main {

	/**
	 * Les Cartes contenues dans la Main.
	 * @see Carte
	 */
	private ArrayList<Carte> cartes;
	
	
	/**
	 * Constructeur de la Main.
	 * @param cartes
	 * 			Les Cartes qui vont constituer la Main, sous la forme d'une instance d'ArrayList<Carte>. 
	 */
	public Main(ArrayList<Carte> cartes) {
		this.setCartes(cartes);
	}

	
	/**
	 * Permet de piocher un certain nombre de Cartes dans la Pioche et de les ajouter à la Main.
	 * @param nb
	 * 			Nombre de Cartes à piocher.
	 * @see Pioche
	 */
	public void piocher(int nb) {
		for (int i = 0; i < nb; i++) {
			this.cartes.add(Pioche.getInstance().piocher());
		}
	}
	
	

	/**
	 * Retourne la liste des Cartes contenues dans la Main.
	 * @return La liste des Cartes contenues dans la Main, sous la forme d'une instance d'ArrayList<Carte>.
	 */
	public ArrayList<Carte> getCartes() {
		return cartes;
	}

	/**
	 * Met à jour la Main.
	 * @param cartes
	 * 			Les Cartes qui vont constituer la nouvelle Main, sous la forme d'une instance d'ArrayList<Carte>.
	 */
	public void setCartes(ArrayList<Carte> cartes) {
		this.cartes = cartes;
	}
	
	
}
