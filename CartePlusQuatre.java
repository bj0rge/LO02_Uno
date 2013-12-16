package LO02_Uno;

import java.util.Iterator;

/**
 * <b>CartePlusCarte est la classe repr�sentant une Carte +4 du jeu de UNO.</b>
 * <p>
 * Une CartePlusQuatre est caract�ris�e par les informations suivantes :
 * <ul>
 * <li>Le nombre de points qu'elle rapporte en fin de partie</li>
 * <li>La couleur qu'elle porte (peut �tre nul)</li>
 * </ul>
 * Une CartePlusQuatre peut �tre pos�e sur la table pour �tre jou�e.
 * </p>
 *
 */
public class CartePlusQuatre extends CarteJoker {

	/**
	 * Constructeur de la CartePlusQuatre.
	 */
	public CartePlusQuatre(){
		super();
	}
	
	public boolean estJouable(Carte c){
		return true;
	}
	
	public void appliquerEffets(boolean premier_tour){
		// Si c'est le premier tour, on repioche
		if (premier_tour){
			System.out.println("On repioche.");
			Manche.getInstance().retournerPremiereCarte();
		}
		// Sinon, on applique l'effet
		else {
			Joueur j1 = Manche.getInstance().getJoueurActuel();
			Joueur j2 =  Manche.getInstance().getJoueurSuivant();
			Joueur piocheur = null;
			int nb_cartes = 0;
			
			boolean dit_bluff = j2.direTuBluffesMartoni(j1);	// On v�rifie si le Joueur 2 d�nonce le bluff 
			
			// S'il d�nonce le bluff
			if (dit_bluff) {
				System.out.println(j2 + " pense que " + j1 + " bluffe.");
				nb_cartes = 6;	// Quel que soit le piocheur, il r�cup�rera 6 Cartes suppl�mentaires
				Carte c = Defausse.getInstance().getAvantDerniereCarteJouee();
				Iterator<Carte> it = j2.getMain().getCartes().iterator();
				boolean peut_jouer = false;
				
				// On v�rifie si j1 pouvait jouer autre chose
				while (it.hasNext()) {
					Carte ca = it.next();
					if (ca.estJouable(c) ) {
						// Si ca n'est pas un +4, alors il pouvait jouer
						peut_jouer = (ca.getClass() == new CartePlusQuatre().getClass()) ? peut_jouer : true;
					}
				}
				// Si j1 pouvait jouer autre chose, il a bluff�, c'est lui qui pioche
				if (peut_jouer) {
					System.out.println(j1 + " a bluff�, il pouvait jouer autre chose.");
					piocheur = j1;
				}
				// Sinon, c'est le d�nonceur qui pioche
				else {
					System.out.println(j1 + " n'a pas bluff�.");
					piocheur = j2;
				}
			}
			
			// Si j2 ne prend pas le risque de d�noncer le bluff
			else {
				piocheur = j2;	// C'est lui qui pioche
				nb_cartes = 4;	// et il pioche 4 cartes
			}
			
			// Enfin, on fait piocher
			if (piocheur.estHumain()) {
				StringBuffer sb = new StringBuffer();
				sb.append(Manche.getInstance().getJoueurSuivant());
				sb.append(" pioche : ");
				for (int i = 0; i < nb_cartes; i++) {
					sb.append(Manche.getInstance().getJoueurSuivant().piocher());
					sb.append(", ");
				}
				sb.delete((sb.length() - 2), sb.length());
				System.out.println(sb);
			}
			else {
				System.out.println(piocheur + " pioche " + nb_cartes + " cartes.");
				for (int i = 0; i < nb_cartes; i++) {
					piocheur.piocher();
				}
			}
			
			Couleur c = j1.choixCouleur();	// On change la couleur
			System.out.println("La couleur du +4 est " + c + " !");
			this.setCouleur(c);
			// Et si c'est j2 qui r�cup�re des Cartes, il passe son tour en prime !
			if (piocheur == j2) {
				Manche.getInstance().passerJoueur();
			}
		}
	}
	
	public String toString(){
		
		StringBuffer sb = new StringBuffer();
		sb.append("+4");
		if (this.getCouleur() != null){
			sb.append(" " + this.getCouleur());
		}
		
		return sb.toString();
		
	}
	
}
