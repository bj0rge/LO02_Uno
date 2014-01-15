package fr.utt.isi.lo02.projet_uno.carte;

import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;
import fr.utt.isi.lo02.projet_uno.manche.Manche;
<<<<<<< HEAD:src/fr/utt/isi/lo02/projet_uno/carte/CartePlusDeux.java
import fr.utt.isi.lo02.projet_uno.joueur.Joueur;
=======
>>>>>>> interface_graphique2:src/fr/utt/isi/lo02/projet_uno/carte/CartePlusDeux.java

/**
 * <b>CartePlusDeux est la classe repr�sentant une Carte +2 du jeu de UNO.</b>
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
		// On utilise le constructeur de Carte.
		// Toutes les CartePlusDeux valent 20 points et poss�de une couleur d�s le d�but
		// d'o� l'appel de constructeur avec la valeur 20 et une couleur en param�tre.
		super(20, couleur);
		
	}
	
	public boolean estJouable(Carte c) {
		// On v�rifie si c'est la m�me couleur en utilisant la m�thode estJouable de Carte.
		boolean retour = super.estJouable(c);
		
		// On v�rifie �galement si la carte pr�c�dente est du m�me type que CartePlusDeux.
		// Si c'est le cas, alors la carte est jouable, quelque soit la couleur de la carte pr�c�dente.
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
		
		// Si c'est le premier tour, c'est le premier joueur qui pioche.
		if (premier_tour) {
			
			// S'il est humain, on fait piocher et on affiche les cartes qu'il a pioch�.
			if (Manche.getInstance().getJoueurActuel().estHumain()) {
				// String Buffer permet d'afficher les deux cartes pioch�es sur une m�me ligne.
				StringBuffer sb = new StringBuffer();
				sb.append(Manche.getInstance().getJoueurActuel());
				sb.append(" pioche : ");
				for (int i = 0; i < 2; i++) {
					sb.append(Manche.getInstance().getJoueurActuel().piocher());
					sb.append(" et ");
				}
				
				// On retire les 3 derniers caract�res du String Buffer, qui correspondent � "et "
				sb.delete((sb.length() - 3), sb.length());
				System.out.println(sb);
			}
			else { // Si ce n'est pas un joueur humain, on fait piocher et on indique juste qu'il a pioch� 2 cartes.
				System.out.println(Manche.getInstance().getJoueurActuel() + " pioche 2 cartes");
				for (int i = 0; i < 2; i++) {
					Manche.getInstance().getJoueurActuel().piocher();
				}
			}
			Manche.getInstance().passerJoueur();
	
			System.out.println("et il passe son tour.");
		}
		else { // Si ce n'est pas le premier tour, c'est la m�me chose qu'au-dessus mais avec le joueur suivant.
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