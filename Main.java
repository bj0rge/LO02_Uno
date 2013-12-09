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
	 */
	public Main() {
		this.setCartes(new ArrayList<Carte>());
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
	
	
	/**
	 * Retourne une représentation de la Main sous forme de String.
	 * @returns Une représentation de la Main sous forme de String. 
	 */
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		for (Carte c : this.getCartes()){
			sb.append(c.toString());
			sb.append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
		
	}
	
	
}
