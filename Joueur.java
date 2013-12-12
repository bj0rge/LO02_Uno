package LO02_Uno;

import java.util.Iterator;


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
     * la suite. Le score est �galement initialis� � 0, et les variables a_dit_uno et a_pioche � <i>false</i>.
     * vide.
     * </p>
     */
	public Joueur(String nom) {
		this.setNom(nom);
		this.setMain(new Main());
		this.setScore(0);
		this.setADitUno(false);
		this.setAPioche(false);
	}

	/**
	 * Retourne la nature du Joueur.
	 * @return <i>true</i> si le Joueur est humain, <i>false</i> sinon.
	 */
	public boolean estHumain() {
		return true;
	}

	/**
	 * Fait piocher une Carte et l'ajoute dans la Main. Passe �galement a_pioche � <i>true</i>.
	 */
	public Carte piocher() {
		Carte carte_piochee = Pioche.getInstance().piocher();
		this.getMain().getCartes().add(carte_piochee);
		this.setAPioche(true);
		return carte_piochee;
	}

	/**
	 * Poser une Carte, si elle est jouable, appliquer ses effets, et mettre fin au tour.
	 * @param carte
	 * 			Carte � poser.
	 */
	public void poser(Carte carte) {
		if (carte.estJouable(Defausse.getInstance().getDerniereCarteJouee())) {
			this.getMain().getCartes().remove(carte);
			Defausse.getInstance().defausser(carte);
			carte.appliquerEffets(false);
			this.terminerTour();
		}
	}

	/**
	 * Action de dire "UNO" : passe a_dit_uno � <i>true</i>
	 */
	public void direUno() {
		this.setADitUno(true);
	}
	
	/**
	 * Faire remarquer � un Joueur qu'il n'a pas dit "UNO". Si c'est le cas, le(s) Joueur(s) en question pioche(nt) 
	 * deux Cartes, si en revanche aucun Joueur n'a oubli� de dire "UNO", c'est celui qui annoncera le "Contre-Uno"
	 * � tors qui piochera deux Cartes. 
	 */
	public void direContreUno() {}
	
	
	/**
	 * 
	 */
	// Est-ce que cette m�thode a sa place ici ?
	public void direTuBluffesMartoni() {}
	
	/**
	 * 
	 */
	// Est-ce que cette m�thode a sa place ici ?
	public void direCouleur(Couleur c) {}

	/**
	 * Passer son tour. A un effet uniquement apr�s avoir pioch�, sinon ne fait rien.
	 */
	public void passerTour() {
		if (this.aPioche())
			this.terminerTour();	
	}
	
	/**
	 * Met fin au tour du Joueur.
	 */
	public void terminerTour() {
		Manche.getInstance().passerJoueur();
	}
	
	public void jouer() {
		
		System.out.println("\n" + this + ", que voulez-vous faire ?\n");
		
		boolean fintour = false;
	
		do {
			int ret = -1;
			do {
				if (this.aPioche())
					System.out.println("[0] Passer son tour");
				else
					System.out.println("[0] Piocher");
				
				int i = 0;
				Iterator<Carte> it = this.getMain().getCartes().iterator();
				while (it.hasNext()){
					System.out.println("[" + (i+1) + "] Jouer le " + it.next());
					i += 1;
				}
				
				ret = Partie.getInstance().demanderInt();
				
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
					System.out.println("La carte " + carte_jouee + " a �t� d�fauss�e de votre main.\n");
					
					// syso de la derni�re carte pos�e, via d�fausse, pour confirmation
					System.out.println("La premi�re carte de la d�fausse est maintenant un " + Defausse.getInstance().getDerniereCarteJouee() + ".");
				
					fintour = true;
				}
				else {
					System.out.println("La carte " + carte_jouee + " ne peut pas �tre jou�e.\n"
							+ "Pour rappel, la derni�re carte jou�e est un " + Defausse.getInstance().getDerniereCarteJouee() + ".\n");
				}
			}
		}
		while(!fintour);
	}
	
	/**
	 * Retourne la Couleur � jouer au prochain tour en fonction des Cartes dans la main
	 */
	public Couleur choixCouleur() {
		System.out.println("\nQuelle couleur voulez-vous lancer " + Manche.getInstance().getJoueurActuel() + " ?\n");
		int i = 1;
		
		for (Couleur couleur : Couleur.values()){
			System.out.println("[" + i + "] " + couleur);
			i++;
		}
		
		
		Couleur c = null;
		int val = 0;
		while (val < 1 || val > 4) {
			Partie.getInstance().demanderInt();
		}
		
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
	 * Ajoute des points au score actuel du Joueur.
	 * @param points
	 * 			Nombre de points � ajouter au score actuel du Joueur.
	 */
	public void ajouterPoints(int points) {
		this.setScore((this.getScore()) + points);
	}

	/**
	 * R�initialise les variables n�cessaires au bon fonctionnement d'un tour au commencement de celui-ci
	 */
	public void raz() {
		this.setADitUno(false);
		this.setAPioche(false);
	}

	
	/**
	 * Retourne le nom du Joueur.
	 * @return Le nom du Joueur, sous la forme d'une String.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Retourne la Main du Joueur.
	 * @return La Main du Joueur, sous la forme d'une instance de Main.
	 */
	public Main getMain() {
		return main;
	}

	/**
	 * Retourne la valeur de a_dit_uno.
	 * @return <i>true</i> si le Joueur a dit UNO depuis la fin de son tour pr�c�dent, <i>false</i> sinon.
	 */
	public boolean aDitUno() {
		return a_dit_uno;
	}

	/**
	 * Retourne la valeur de a_pioche.
	 * @return <i>true</i> si le Joueur a pioch� lors de son tour actuel, <i>false</i> sinon.
	 */
	public boolean aPioche() {
		return a_pioche;
	}

	/**
	 * Retourne le score du Joueur.
	 * @return Le score du Joueur.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Met � jour le nom du Joueur.
	 * @param nom
	 * 			Nouveau nom du Joueur.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Met � jour la Main du Joueur.
	 * @param main
	 * 			Nouvelle Main � substituer � l'ancienne.
	 */
	public void setMain(Main main) {
		this.main = main;
	}

	/**
	 * Met � jour la valeur de a_dit_uno.
	 * @param a_dit_uno
	 * 			Nouvelle valeur de a_dit_uno.
	 */
	public void setADitUno(boolean a_dit_uno) {
		this.a_dit_uno = a_dit_uno;
	}

	/**
	 * Met � jour la valeur de a_pioche.
	 * @param a_pioche
	 * 			Nouvelle valeur de a_pioche.
	 */
	public void setAPioche(boolean a_pioche) {
		this.a_pioche = a_pioche;
	}

	/**
	 * Met � jour le score du Joueur.
	 * @param score
	 * 			Le nouveau score du joueur.
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Retourne une repr�sentation du Joueur sous forme de String.
	 * @returns Une repr�sentation du Joueur sous forme de String. 
	 */
	public String toString() {
		return this.getNom();
	}
}
