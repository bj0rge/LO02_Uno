package fr.utt.isi.lo02.projet_uno.carte;

import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;
import fr.utt.isi.lo02.projet_uno.manche.Manche;
import fr.utt.isi.lo02.projet_uno.joueur.Joueur;

/**
 * <b>CartePlusDeux est la classe repr�sentant une Carte +2 du jeu de UNO.</b>
 * <p>C'est une classe fille de la classe abstraite {@link Carte}.</p>
 * <p>
 * Une CartePlusDeux est caract�ris�e par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte </li>
 * </ul>
 * Une CartePlusDeux peut �tre pos�e sur la table pour �tre jou�e.
 * </p>
 *
 */
public class CartePlusDeux extends Carte {

	/**
	 * Constructeur de la CartePlusDeux.
	 * @param couleur
	 * 			La {@link Couleur} de la Carte.
	 * @see Carte#Carte(int, Couleur)
	 */
	public CartePlusDeux(Couleur couleur){
		
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

	/**
	 * Applique les effets de CartePlusDeux : le joueur suivant pioche deux cartes.
	 * @param premier_tour
	 * 			Si <i>Vrai</i>, c'est le joueur actuel qui pioche deux cartes.
	 * @see Joueur#piocher()
	 */
	public void appliquerEffets(boolean premier_tour){
		if (premier_tour) {
			
			if (Manche.getInstance().getJoueurActuel().estHumain()) {
				StringBuffer sb = new StringBuffer();
				sb.append(Manche.getInstance().getJoueurActuel());
				sb.append(" pioche : ");
				for (int i = 0; i < 2; i++) {
					sb.append(Manche.getInstance().getJoueurActuel().piocher());
					sb.append(" et ");
				}
				sb.delete((sb.length() - 3), sb.length());
				System.out.println(sb);
			}
			else {
				System.out.println(Manche.getInstance().getJoueurActuel() + " pioche 2 cartes");
				for (int i = 0; i < 2; i++) {
					Manche.getInstance().getJoueurActuel().piocher();
				}
			}
			Manche.getInstance().passerJoueur();
	
			System.out.println("et il passe son tour.");
		}
		else {
			if (Manche.getInstance().getJoueurSuivant().estHumain()) {
				StringBuffer sb = new StringBuffer();
				sb.append(Manche.getInstance().getJoueurSuivant());
				sb.append(" pioche : ");
				for (int i = 0; i < 2; i++) {
					sb.append(Manche.getInstance().getJoueurSuivant().piocher());
					sb.append(" et ");
				}
				sb.delete((sb.length() - 3), sb.length());
				System.out.println(sb);
				
			}
			else {
				System.out.println(Manche.getInstance().getJoueurSuivant() + " pioche 2 cartes.");
				for (int i = 0; i < 2; i++) {
					Manche.getInstance().getJoueurSuivant().piocher();
				}
			}
			Manche.getInstance().passerJoueur();
	
			System.out.println("et il passe son tour.");
		}
	}
	
	 /** Retourne la repr�sentation �crite de l'instance CartePlusDeux.
	 * @return String : "+2 ({@link Couleur})"
	 */
	public String toString() {
		
		return ("+2 " + this.getCouleur());
		
	}
	
}