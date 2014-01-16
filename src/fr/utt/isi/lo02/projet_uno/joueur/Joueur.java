package fr.utt.isi.lo02.projet_uno.joueur;

import java.util.Iterator;

import fr.utt.isi.lo02.projet_uno.carte.Carte;
import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;
import fr.utt.isi.lo02.projet_uno.manche.Defausse;
import fr.utt.isi.lo02.projet_uno.manche.Manche;
import fr.utt.isi.lo02.projet_uno.manche.Pioche;
import fr.utt.isi.lo02.projet_uno.partie.Partie;


/**
 * <b>Joueur est la classe repr�sentant un Joueur de UNO.</b>
 * <p>
 * Le Joueur est caract�ris�e par :
 * <ul>
 * <li>Son nom</li>
 * <li>Sa Main</li>
 * <li>Son score actuel</li>
 * <li>Le fait qu'il ait ou non dit "UNO" lors de son dernier tour</li>
 * <li>Le fait qu'il ait ou non d�j� pioch� ce tour</li>
 * <li>Son score</li>
 * </ul>
 * </p>
 * @see Main
 */
public class Joueur {
	
	/**
	 * Le nom du Joueur
	 */
	private String nom;
	
	/**
	 * La Main du Joueur.
	 * @see Main
	 */
	private Main main;
	
	/**
	 * Bool�en caract�risant le fait que le Joueur ait ou non dit "UNO" lors de son dernier tour.
	 */
	private boolean a_dit_uno;
	
	/**
	 * Bool�en caract�risant le fait que le Joueur ait ou non pioch� lors de son tour actuel.
	 */
	private boolean a_pioche;
	
	/**
	 * Le score actuel du Joueur.
	 */
	private int score;

	/**
     * Constructeur du Joueur.
     * <p>
     * A la construction d'un objet Joueur, la Main est cr��e vide, elle sera remplie par
     * la suite. Le score est �galement initialis� � 0, et les variables a_dit_uno et a_pioche � <i>faux</i>.
     * </p>
     * @see Main
     */
	public Joueur(String nom) {
		this.setNom(nom);
		this.setMain(new Main());
		this.setScore(0);
		this.setADitUno(false);
		this.setAPioche(false);
	}

	/**
	 * Permet d'indiquer que le joueur est humain.
	 * @return <i>Vrai</i>
	 */
	public boolean estHumain() {
		return true;
	}

	/**
	 * Permet de pr�senter les options de jeu au joueur actuel et de r�cup�rer sa r�ponse.
	 * @see Joueur#aPioche()
	 */
	public void jouer() {
				
		System.out.println(this + ", que voulez-vous faire ?\n");
		
		boolean fintour = false;
	
		do {
			int ret = -1;
			do {
				// La premi�re option d�pend de s'il a d�j� pioch� ou non
				if (this.aPioche())
					System.out.println("[0] Passer son tour");
				else
					System.out.println("[0] Piocher");
				
				// On lui pr�sente toute les options qu'il a dans la main
				int i = 0;
				Iterator<Carte> it = this.getMain().getCartes().iterator();
				while (it.hasNext()){
					System.out.println("[" + (i+1) + "] Jouer le " + it.next());
					i += 1;
				}
				
				// On r�cup�re ce que le joueur a choisi
				ret = Partie.getInstance().demanderInt();
				
				// Si ce n'est pas dans la liste, on affiche une erreur
				if (ret < 0 || ret > this.getMain().getCartes().size()){
					System.out.println("Valeur incorrecte : veuillez entrer un choix possible.");
				}
				
			}
			// Tant que ret n'est pas dans la liste 
			while (ret < 0 || ret > this.getMain().getCartes().size());
			
			// Si l'option choisie est 0
			if (ret == 0)
				// Si le joueur a d�j� pioch�, cela veut dire que 
				// son choix �tait de finir son tour
				if (this.aPioche()){
					fintour = true;
					this.passerTour();
					System.out.println("\nDerni�re carte jou�e : " + Defausse.getInstance().getDerniereCarteJouee() + ".");
				}
				// Sinon, on le fait piocher
				else {
					System.out.println(" \nVous avez pioch� un " + this.piocher() + ".");
					System.out.println("Derni�re carte jou�e : " + Defausse.getInstance().getDerniereCarteJouee() + ".\n");
				}
			// Sinon, il choisit de poser une carte
			else {
				Carte carte_jouee = this.getMain().getCartes().get(ret - 1);
				System.out.println("\n" + this + " joue la carte " + carte_jouee + ".");
				
				if (carte_jouee.estJouable(Defausse.getInstance().getDerniereCarteJouee())) {
					this.poser(carte_jouee);
				
					fintour = true;
				}
				else {
					System.out.println("La carte " + carte_jouee + " ne peut pas �tre jou�e.\n"
							+ "Pour rappel, la derni�re carte jou�e est un " + Defausse.getInstance().getDerniereCarteJouee() + ".\n");
				}
			}
		}
		while(!fintour);
		
		if (this.getMain().getCartes().size() == 1){
			System.out.println(this + " annonce UNO !\n");
		}
		
	}

	/**
	 * Fait piocher une Carte et l'ajoute dans la Main. Passe �galement {@link #a_pioche} � <i>Vrai</i>.
	 * @return Carte pioch�e
	 * @see Main
	 */
	public Carte piocher() {
		Carte carte_piochee = Pioche.getInstance().piocher();
		this.getMain().getCartes().add(carte_piochee);
		this.setAPioche(true);
		this.getMain().classer();
		return carte_piochee;
	}

	/**
	 * Pose une Carte, si elle est jouable, applique ses effets, et met fin au tour.
	 * @param carte
	 * 			Carte � poser
	 * @see Carte#estJouable(Carte)
	 * @see Carte#appliquerEffets(boolean)
	 */
	public void poser(Carte carte) {
		// On pose que si on v�rifie d'abord si la carte est jouable
		if (carte.estJouable(Defausse.getInstance().getDerniereCarteJouee())) {
			this.getMain().getCartes().remove(carte); // On retire de la main
			Defausse.getInstance().defausser(carte); // On la pose dans la d�fausse
			carte.appliquerEffets(false); // On applique l'effet en pr�cisant que la premi�re carte retourn�e de la pioche
			this.terminerTour(); // On termine le tour
		}
	}

	/**
	 * Action de dire "UNO" : passe {@link #a_dit_uno} � <i>Vrai</i>
	 */
	public void direUno() {
		this.setADitUno(true);
	}
	
	/**
	 * <p>Faire remarquer � un Joueur qu'il n'a pas dit "UNO". Si c'est le cas, le(s) Joueur(s) en question pioche(nt) 
	 * deux Cartes, si en revanche aucun Joueur n'a oubli� de dire "UNO", c'est celui qui annoncera le "Contre-Uno"
	 * � tort qui piochera deux Cartes.</p> 
	 */
	public void direContreUno() {} // Non impl�ment� � cause de l'interface en ligne de commande
	

	/**
	 * Passer son tour, seulement apr�s avoir pioch�.
	 */
	public void passerTour() {
		if (this.aPioche()) // On passe le tour seulement quand on a pioch�
			this.terminerTour();	
	}
	
	/**
	 * Met fin au tour du Joueur.
	 * @see Manche#passerJoueur()
	 */
	public void terminerTour() {
		Manche.getInstance().passerJoueur(); // Terminer le tour correspond � passer au joueur suivant
	}
	
	/**
	 * Permet au joueur de choisir une couleur.
	 * @return {@link Couleur}
	 */
	public Couleur choixCouleur() {
		
		System.out.println("\nQuelle couleur voulez-vous lancer " + Manche.getInstance().getJoueurActuel() + " ?\n");
		int i = 1;
		
		// On pr�sente toutes les couleurs possibles
		for (Couleur couleur : Couleur.values()){
			System.out.println("[" + i + "] " + couleur);
			i++;
		}
		
		
		Couleur c = null;
		int val = 0;
		
		// On demande la valeur jusqu'� ce le joueur rentre une valeur possible
		while (val < 1 || val > 4) {
			val = Partie.getInstance().demanderInt();
		}
		
		// On choisit une couleur en fonction de la valeur entr�e
		switch (val)
		{
		case 1:
			c = Couleur.ROUGE;
			break;
		case 2:
			c = Couleur.BLEU;
			break;
		case 3:
			c = Couleur.JAUNE;
			break;
		case 4:
			c = Couleur.VERT;
			break;
		}
		
		return c;
	}
	
	
	/**
	 * Methode permettant de savoir si le Joueur souhaite d�noncer le bluff.
	 * @param j 
	 * 			Joueur qui a jou� le +4
	 * @return <i>Vrai</i> si le Joueur souhaite prendre le risque de d�noncer le bluff,
	 * <i>Faux</i> sinon.
	 */
	public boolean direTuBluffesMartoni(Joueur j) {
		
		// On demande au joueur s'il veut d�noncer un bluff et on recommande tant que la valeur entr�e est diff�rente des valeurs possibles
		System.out.println(this + ", " + j + " joue un +4, voulez-vous d�noncer le bluff ?\n"
				+ "[1] D�noncer le bluff\n"
				+ "[2] Piocher 4 cartes");
		int val = 0;
		while (val < 1 || val > 2) {
			val = Partie.getInstance().demanderInt();
		}
		return val == 1 ? true : false;
	}
	
	
	/**
	 * Ajoute des points au score actuel du Joueur.
	 * @param points
	 * 			Nombre de points � ajouter au score actuel du Joueur.
	 */
	public void ajouterPoints(int points) {
		this.setScore((this.getScore()) + points);
	}

	/**
	 * R�initialise les variables n�cessaires au bon fonctionnement d'un tour au commencement de celui-ci.
	 * @see Joueur#a_dit_uno
	 * @see Joueur#a_pioche
	 */
	public void raz() {
		
		// Au d�but d'un nouveau tour d'un joueur, il n'a pas encore pioch� et il n'a pas encore dit (ou re-dit) UNO
		this.setADitUno(false);
		this.setAPioche(false);
	}

	
	/**
	 * Retourne le {@link #nom} du Joueur.
	 * @return Le nom du Joueur, sous la forme d'un String.
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Retourne la {@link #main} du Joueur.
	 * @return La Main du Joueur, sous la forme d'une instance de Main.
	 */
	public Main getMain() {
		return main;
	}

	/**
	 * Retourne la valeur de {@link #a_dit_uno}.
	 * @return <i>Vrai</i> si le Joueur a dit UNO depuis la fin de son tour pr�c�dent, <i>Faux</i> sinon.
	 */
	public boolean aDitUno() {
		return a_dit_uno;
	}

	/**
	 * Retourne la valeur de {@link #a_pioche}.
	 * @return <i>Vrai</i> si le Joueur a pioch� lors de son tour actuel, <i>Faux</i> sinon.
	 */
	public boolean aPioche() {
		return a_pioche;
	}

	/**
	 * Retourne le {@link #score} du Joueur.
	 * @return Le score du Joueur.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Met � jour le {@link #nom} du Joueur.
	 * @param nom
	 * 			Nouveau nom du Joueur.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Met � jour la {@link #main} du Joueur.
	 * @param main
	 * 			Nouvelle Main � substituer � l'ancienne.
	 */
	public void setMain(Main main) {
		this.main = main;
	}

	/**
	 * Met � jour la valeur de {@link #a_dit_uno}.
	 * @param a_dit_uno
	 * 			Nouvelle valeur de a_dit_uno.
	 */
	public void setADitUno(boolean a_dit_uno) {
		this.a_dit_uno = a_dit_uno;
	}

	/**
	 * Met � jour la valeur de {@link #a_pioche}.
	 * @param a_pioche
	 * 			Nouvelle valeur de a_pioche.
	 */
	public void setAPioche(boolean a_pioche) {
		this.a_pioche = a_pioche;
	}

	/**
	 * Met � jour le {@link #score} du Joueur.
	 * @param score
	 * 			Le nouveau score du joueur.
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Retourne une repr�sentation du Joueur sous forme de String.
	 * @return Une repr�sentation du Joueur sous forme de String. 
	 */
	public String toString() {
		return this.getNom();
	}
}
