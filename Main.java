package LO02_Uno;

import java.util.ArrayList;

/**
 * <b>Main est la classe repr�sentant la Main d'un Joueur de UNO.</b>
 * <p>
 * La Main est caract�ris�e par :
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
	 * Permet de piocher un certain nombre de Cartes dans la Pioche et de les ajouter � la Main.
	 * @param nb
	 * 			Nombre de Cartes � piocher.
	 * @see Pioche
	 */
	public void piocher(int nb) {
		for (int i = 0; i < nb; i++) {
			this.cartes.add(Pioche.getInstance().piocher());
		}
	}
	
	/**
	 * Permet d'enlever une Carte de la Main et de l'ajouter dans la D�fausse.
	 * @param c
	 * 			Carte � d�fausser.
	 */
	public void defausser(Carte c) {
		this.cartes.remove(c);
		Defausse.getInstance().defausser(c);
	}
	

	/**
	 * Retourne la liste des Cartes contenues dans la Main.
	 * @return La liste des Cartes contenues dans la Main, sous la forme d'une instance d'ArrayList<Carte>.
	 */
	public ArrayList<Carte> getCartes() {
		return cartes;
	}

	/**
	 * Met � jour la Main.
	 * @param cartes
	 * 			Les Cartes qui vont constituer la nouvelle Main, sous la forme d'une instance d'ArrayList<Carte>.
	 */
	public void setCartes(ArrayList<Carte> cartes) {
		this.cartes = cartes;
	}
	
	
	/**
	 * Retourne une repr�sentation de la Main sous forme de String.
	 * @returns Une repr�sentation de la Main sous forme de String. 
	 */
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		for (Carte c : this.getCartes()){
			sb.append(c.toString());
			sb.append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
		
		/*String toto = "";
		for (Carte c : this.getCartes())
			toto += c.toString() + "\n";
		if (toto != "")
			toto = toto.substring(0, toto.length() - 1);
		return toto;*/
	}
	
	
}
