package fr.utt.isi.lo02.projet_uno.carte;

import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;
import fr.utt.isi.lo02.projet_uno.manche.Manche;
import fr.utt.isi.lo02.projet_uno.partie.Partie;

/**
 * <b>CarteChangerSens est la classe représentant une Carte Changement de sens du jeu de UNO.</b>
 * <p>
 * Une CarteChangerSens est caractérisée par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte</li>
 * </ul>
 * Une CarteChangerSens peut être posée sur la table pour être jouée.
 * </p>
 *
 */
public class CarteChangerSens extends Carte {
	
	/**
	 * Constructeur de la CarteChangerSens.
	 * @param couleur
	 * 			La Couleur de la Carte.
	 * @see Carte#Carte(int, Couleur)
	 */
	public CarteChangerSens(Couleur couleur){
		super(20, couleur);
	}
	
	public boolean estJouable(Carte c) {
		boolean retour = super.estJouable(c);
		if ((this.getClass() == c.getClass())) {
			retour = true;
		}
		return retour;
	}

	/**
	 * <p>Applique l'effet de CarteChangerSens : le jeu change de sens.</p>
	 * 
	 * @param premier_tour
	 * 			<li> Si vrai et qu'il y a plus de 2 joueurs, c'est le joueur à gauche du donneur qui commence.
	 * 			<li> Si faux et qu'il n'y a que 2 joueurs dans la partie, le joueur actuel rejoue.
	 * @see Manche#changerSens()
	 * @see Manche#passerJoueur()
	 */
	public void appliquerEffets(boolean premier_tour){
		
		System.out.println("Le jeu change de sens !\n");
		Manche.getInstance().changerSens();
		
		if (Partie.getInstance().getListeJoueurs().size() > 2){
			if (premier_tour) {
				Manche.getInstance().passerJoueur();
				Manche.getInstance().passerJoueur();
				
				System.out.println("C'est donc " + Manche.getInstance().getJoueurActuel() + " qui commence.");
			}
		} else {
			if (!premier_tour){
				System.out.println(Manche.getInstance().getJoueurActuel() + " peut rejouer.");
				Manche.getInstance().passerJoueur();
			}
		}
	}
	
	/**
	 * Retourne une représentation écrite de l'instance CarteChangerSens.
	 * @return String : "<-> ({@link Couleur})"
	 */
	public String toString(){
		
		return ("<-> " + this.getCouleur());
		
	}
}