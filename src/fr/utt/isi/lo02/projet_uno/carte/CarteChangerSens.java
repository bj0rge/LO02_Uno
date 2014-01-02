package fr.utt.isi.lo02.projet_uno.carte;

import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;
import fr.utt.isi.lo02.projet_uno.manche.Manche;
import LO02_Uno.Partie;

/**
 * <b>CarteChangerSens est la classe repr�sentant une Carte Changement de sens du jeu de UNO.</b>
 * <p>
 * Une CarteChangerSens est caract�ris�e par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte</li>
 * </ul>
 * Une CarteChangerSens peut �tre pos�e sur la table pour �tre jou�e.
 * </p>
 *
 */
public class CarteChangerSens extends Carte {
	
	/**
	 * Constructeur de la CarteChangerSens.
	 * @param couleur
	 * 			La Couleur de la Carte.
	 */
	public CarteChangerSens(Couleur couleur){
		super(20, couleur);
	}
	
	public boolean estJouable(Carte c) {
		// Si la Carte est d�j� jouable, � savoir si elle a la m�me couleur, c'est bon
		boolean retour = super.estJouable(c);
		if ((this.getClass() == c.getClass())) {
			retour = true;
		}
		return retour;
	}

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
	
	public String toString(){
		
		return ("<-> " + this.getCouleur());
		
	}
}