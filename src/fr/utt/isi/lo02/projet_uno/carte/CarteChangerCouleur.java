package fr.utt.isi.lo02.projet_uno.carte;

import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;
import fr.utt.isi.lo02.projet_uno.manche.Manche;
import fr.utt.isi.lo02.projet_uno.joueur.Joueur;

/**
 * <b>CarteChangerCouleur est la classe repr�sentant une Carte joker du jeu de UNO.</b>
 * <p>
 * Une CarteChangerCouleur est caract�ris�e par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte (peut �tre nul)</li>
 * </ul>
 * Une CarteChangerCouleur peut �tre pos�e sur la table pour �tre jou�e.
 * </p>
 *
 */
public class CarteChangerCouleur extends CarteJoker{

	/**
	 * Constructeur de CarteChangerCouleur.
	 * @see CarteJoker#CarteJoker()
	 */
	public CarteChangerCouleur() {	
		super();
	}
	
	/**
	 * Renvoie que CarteChangerCouleur est toujours jouable.
	 * @return Vrai
	 */
	public boolean estJouable(Carte c) {
		return true;
	}
	
	/**
	 * <p>Applique l'effet de CarteChangerCouleur : le joueur qui pose cette carte choisit la couleur qu'elle prend.</p>
	 * 
	 * @param premier_tour
	 * 			Si vrai, le premier joueur peut jouer ce qu'il veut.
	 * @see Joueur#choixCouleur()
	 */
	public void appliquerEffets(boolean premier_tour){
		if (premier_tour) {
			System.out.println("\nJOKER ! Jouez ce que vous voulez !\n");
		}
		else {
			Couleur c = Manche.getInstance().getJoueurActuel().choixCouleur();
			this.setCouleur(c);
			System.out.println("La couleur du Joker est " + c);
		}
	}
	
	/**
	 * Renvoie la repr�sentation �crite de l'instance CarteChangerCouleur.
	 * @return String : "Joker ({@link Couleur})"
	 */
	public String toString(){
		
		StringBuffer sb = new StringBuffer();
		sb.append("Joker");
		if (this.getCouleur() != null){
			sb.append(" " + this.getCouleur());
		}
		
		return sb.toString();
	}
	
}