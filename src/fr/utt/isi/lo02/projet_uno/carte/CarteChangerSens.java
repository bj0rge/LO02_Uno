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
	 * 			La {@link Couleur} de la Carte.
	 * @see Carte#Carte(int, Couleur)
	 */
	public CarteChangerSens(Couleur couleur){
		// On utilise le constructeur de Carte.
		// Toutes les CarteChangerSens valent 20 points et possède une couleur dès le début
		// d'où l'appel de constructeur avec la valeur 20 et une couleur en paramètre.
		super(20, couleur);
	}
	
	public boolean estJouable(Carte c) {
		// On vérifie si c'est la même couleur en utilisant la méthode estJouable de Carte.
		boolean retour = super.estJouable(c);
		
		// On vérifie également si la carte précédente est du même type que CarteChangerSens.
		// Si c'est le cas, alors la carte est jouable, quelque soit la couleur de la carte précédente.
		if ((this.getClass() == c.getClass())) {
			retour = true;
		}
		return retour;
	}

	/**
	 * <p>Applique l'effet de CarteChangerSens : le jeu change de sens.</p>
	 * 
	 * @param premier_tour
	 * 			<li> Si <i>Vrai</i> et qu'il y a plus de 2 joueurs, c'est le joueur à gauche du donneur qui commence.
	 * 			<li> Si <i>Faux</i> et qu'il n'y a que 2 joueurs dans la partie, le joueur actuel rejoue.
	 * @see Manche#changerSens()
	 * @see Manche#passerJoueur()
	 */
	public void appliquerEffets(boolean premier_tour){
		
		// On change de sens.
		System.out.println("Le jeu change de sens !\n");
		Manche.getInstance().changerSens();
		
		// Lorsqu'il y a plus de 2 joueurs (soit le mode Standard, Equipe ou Challenge avec plus de 2 joueurs restants)
		if (Partie.getInstance().getListeJoueurs().size() > 2){
			// Si c'est le premier tour, c'est au joueur à gauche du donneur, soit deux fois à gauche du premier joueur.
			if (premier_tour) {
				// On passe le premier joueur à gauche du premier joueur, qui est en théorie le donneur.
				Manche.getInstance().passerJoueur();
				// On passe le donneur pour arriver au joueur à sa gauche.
				Manche.getInstance().passerJoueur();
				
				System.out.println("C'est donc " + Manche.getInstance().getJoueurActuel() + " qui commence.");
			}
		} else { // S'il ne reste que 2 joueurs (soit le mode 2 joueurs ou Challenge avec 2 joueurs restants)
			// Si ce n'est pas le premier tour, alors le joueur actuel rejoue.
			if (!premier_tour){
				System.out.println(Manche.getInstance().getJoueurActuel() + " peut rejouer.");
				// On passe le joueur suivant, pour que le joueur actuel puisse rejouer.
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