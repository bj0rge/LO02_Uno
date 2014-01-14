package fr.utt.isi.lo02.projet_uno.carte;

import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;
import fr.utt.isi.lo02.projet_uno.manche.Manche;
import fr.utt.isi.lo02.projet_uno.joueur.Joueur;

/**
 * <b>CarteChangerCouleur est la classe représentant une Carte joker du jeu de UNO.</b>
 * <p>
 * Une CarteChangerCouleur est caractérisée par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte (peut être nul)</li>
 * </ul>
 * Une CarteChangerCouleur peut être posée sur la table pour être jouée.
 * </p>
 *
 */
public class CarteChangerCouleur extends CarteJoker{

	/**
	 * Constructeur de CarteChangerCouleur.
	 * @see CarteJoker#CarteJoker()
	 */
	public CarteChangerCouleur() {	
		// On utilise le constructeur de CarteJoker.
		super();
	}
	
	/**
	 * Renvoie que CarteChangerCouleur est toujours jouable.
	 * @return <i>Vrai</i>
	 */
	public boolean estJouable(Carte c) {
		// Qu'elle que soit la carte précédente, la CarteChangerCouleur est toujours jouable.
		return true;
	}
	
	/**
	 * <p>Applique l'effet de CarteChangerCouleur : le joueur qui pose cette carte choisit la couleur qu'elle prend.</p>
	 * 
	 * @param premier_tour
	 * 			Si <i>Vrai</i>, le premier joueur peut jouer ce qu'il veut.
	 * @see Joueur#choixCouleur()
	 */
	public void appliquerEffets(boolean premier_tour){
		// Si c'est le premier tour, le premier joueur peut jouer ce qu'il veut.
		if (premier_tour) {
			System.out.println("\nJOKER ! Jouez ce que vous voulez !\n");
		}
		else { // Sinon, c'est le joueur actuel qui choisit la couleur.
			Couleur c = Manche.getInstance().getJoueurActuel().choixCouleur();
			this.setCouleur(c);
			System.out.println("La couleur du Joker est " + c);
		}
	}
	
	/**
	 * Renvoie la représentation écrite de l'instance CarteChangerCouleur.
	 * @return String : "Joker ({@link Couleur})"
	 */
	public String toString(){
		// On utilise un String Buffer pour dissocier le cas où une CarteChangerCouleur possède une couleur du cas où il n'en a pas.
		StringBuffer sb = new StringBuffer();
		sb.append("Joker");
		if (this.getCouleur() != null){ // Si la carte a une couleur alors on la rajoute au StringBuffer.
			sb.append(" " + this.getCouleur());
		}
		
		return sb.toString();
	}
	
}