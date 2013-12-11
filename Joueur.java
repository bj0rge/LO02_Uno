package LO02_Uno;


/**
 * <b>Joueur est la classe représentant un Joueur de UNO.</b>
 * <p>
 * Le Joueur est caractérisée par :
 * <ul>
 * <li>Sa Main</li>
 * <li>Son score actuel</li>
 * <li>Le fait qu'il ait ou non dit "UNO" lors de son dernier tour</li>
 * <li>Le fait qu'il ait ou non déjà pioché ce tour</li>
 * </ul>
 * </p>
 * @see Main
 */
public class Joueur {
	
	/**
	 * La Main du Joueur.
	 * @see Main
	 */
	private Main main;
	
	/**
	 * Booléen caractérisant le fait que le Joueur ait ou non dit "UNO" lors de son dernier tour.
	 */
	private boolean a_dit_uno;
	
	/**
	 * Booléen caractérisant le fait que le Joueur ait ou non pioché lors de son tour actuel.
	 */
	private boolean a_pioche;
	
	/**
	 * Le score actuel du Joueur.
	 */
	private int score;

	/**
     * Constructeur du Joueur.
     * <p>
     * A la construction d'un objet Joueur, la Main est créée vide, elle sera remplie par
     * la suite. Le score est également initialisé à 0, et les variables a_dit_uno et a_pioche à <i>false</i>.
     * vide.
     * </p>
     */
	public Joueur() {
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
	 * Fait piocher une Carte et l'ajoute dans la Main. Passe également a_pioche à <i>true</i>.
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
	 * 			Carte à poser.
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
	 * Action de dire "UNO" : passe a_dit_uno à <i>true</i>
	 */
	public void direUno() {
		this.setADitUno(true);
	}
	
	/**
	 * Retourne la valeur de a_dit_uno.
	 * @return <i>true</i> si le Joueur a dit UNO depuis la fin de son tour précédent, <i>false</i> sinon.
	 */
	public boolean aDitUno() {
		return a_dit_uno;
	}

	/**
	 * Retourne la valeur de a_pioche.
	 * @return <i>true</i> si le Joueur a pioché lors de son tour actuel, <i>false</i> sinon.
	 */
	public boolean APioche() {
		return a_pioche;
	}

	/**
	 * Faire remarquer à un Joueur qu'il n'a pas dit "UNO". Si c'est le cas, le(s) Joueur(s) en question pioche(nt) 
	 * deux Cartes, si en revanche aucun Joueur n'a oublié de dire "UNO", c'est celui qui annoncera le "Contre-Uno"
	 * à tors qui piochera deux Cartes. 
	 */
	public void direContreUno() {}
	
	
	/**
	 * 
	 */
	// Est-ce que cette méthode a sa place ici ?
	public void direTuBluffesMartoni() {}
	
	/**
	 * 
	 */
	// Est-ce que cette méthode a sa place ici ?
	public void direCouleur(Couleur c) {}

	/**
	 * Passer son tour. A un effet uniquement après avoir pioché, sinon ne fait rien.
	 */
	public void passerTour() {
		if (this.APioche())
			this.terminerTour();	
	}
	
	/**
	 * Met fin au tour du Joueur.
	 */
	public void terminerTour() {
		Manche.getInstance().passerJoueur();
	}
	
	/**
	 * Ajoute des points au score actuel du Joueur.
	 * @param points
	 * 			Nombre de points à ajouter au score actuel du Joueur.
	 */
	public void ajouterPoints(int points) {
		this.setScore((this.getScore()) + points);
	}

	/**
	 * Réinitialise les variables nécessaires au bon fonctionnement d'un tour au commencement de celui-ci
	 */
	public void raz() {
		this.setADitUno(false);
		this.setAPioche(false);
	}

	/**
	 * Retourne la Main du Joueur.
	 * @return La Main du Joueur, sous la forme d'une instance de Main.
	 */
	public Main getMain() {
		return main;
	}

	/**
	 * Retourne le score du Joueur.
	 * @return Le score du Joueur.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Met à jour la Main du Joueur.
	 * @param main
	 * 			Nouvelle Main à substituer à l'ancienne.
	 */
	public void setMain(Main main) {
		this.main = main;
	}

	/**
	 * Met à jour la valeur de a_dit_uno.
	 * @param a_dit_uno
	 * 			Nouvelle valeur de a_dit_uno.
	 */
	public void setADitUno(boolean a_dit_uno) {
		this.a_dit_uno = a_dit_uno;
	}

	/**
	 * Met à jour la valeur de a_pioche.
	 * @param a_pioche
	 * 			Nouvelle valeur de a_pioche.
	 */
	public void setAPioche(boolean a_pioche) {
		this.a_pioche = a_pioche;
	}

	/**
	 * Met à jour le score du Joueur.
	 * @param score
	 * 			Le nouveau score du joueur.
	 */
	public void setScore(int score) {
		this.score = score;
	}
}
