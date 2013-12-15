package LO02_Uno;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * <b>Manche est la classe représentant la Manche du jeu de UNO.</b>
 * <p>
 * La Manche est une classe unique (singleton), et est caractérisée par :
 * <ul>
 * <li>Une instance statique d'elle-même</li>
 * <li>Le sens actuel du jeu (horaire par défaut)</li>
 * <li>Le Joueur qui va jouer son tour</li> 
 * </ul>
 * </p>
 */
public class Manche {

	/**
	 * Nombre de cartes à piocher lors du début d'un tour.<br>
	 * NB_CARTES_INITIAL = {@value}
	 */
	public static final int NB_CARTES_INITIAL = 1;
		
	
	/**
	 * L'instance de la Manche.
	 */
	// L'utilisation du mot clé volatile permet, en Java version 5 et supérieur, 
	// d'éviter le cas  où "Singleton.instance" est non-nul,
	// mais pas encore "réellement" instancié.
	private static volatile Manche instance = null;
	
	/**
	 * Le sens du Jeu en cours : horaire à true, anti-horaire à false.
	 */
	private boolean sensHoraire;
	
	/**
	 * Le Joueur qui commence la Manche.
	 */
	private Joueur joueurDebut;


	/**
	 * Le Joueur actuel, c'est à dire celui qui va jouer son tour.
	 */
	private Joueur joueurActuel;
	
	/**
	 * Constructeur de la Manche.
	 */
	private Manche(){
		super();
	}

	/**
		 * Fait dérouler une Manche du début à la fin et retourne un tableau d'entier.
		 * @return Un tableau d'entiers. En index [0], on a l'index du Joueur gagnant dans listeJoueurs, en index [1], le nombre de points gagnés. 
		 */
		public int deroulementManche() {
			
			// On donne pour premier joueur le JoueurDebut
			Manche.getInstance().setJoueurActuel(Manche.getInstance().getJoueurDebut());
			
			// On distribue les cartes à chacun des joueurs
			Manche.getInstance().distribuer();
			
			// On retourne la première carte de la Pioche
			Manche.getInstance().retournerPremiereCarte();
	//		Manche.getInstance().setPremierTour(false);
			
			// Tant que le JoueurActuel a encore au moins une Carte dans la Main
			while(!Manche.getInstance().finManche()) {
				// On fait jouer le JoueurActuel
				Manche.getInstance().jouerTour(Manche.getInstance().getJoueurActuel());
			}
			
			// On récupère l'index du Joueur qui a gagné la partie, ie qui n'a plus de Carte en Main
			int index = 0;
			Iterator<Joueur> it = Partie.getInstance().getListeJoueurs().iterator();
			while (it.hasNext()) {
				Joueur j = (Joueur) it.next();
				if (j.getMain().getCartes().size() == 0) {
					index = Partie.getInstance().getListeJoueurs().indexOf(j); 
				}
			}
			
			// On retourne l'index du Joueur gagnant
			int ret = index;
			return ret;
		}

	/**
	 * Distribue les cartes à chacun des joueurs. A effectuer en début de Manche.
	 */
	public void distribuer() {
		for (int i = 0; i < NB_CARTES_INITIAL; i++) {
			Iterator<Joueur> it = Partie.getInstance().getListeJoueurs().iterator(); // Création d'un itérateur it de listeJoueurs
			while (it.hasNext()){						  // it.hasNext retourne true s'il reste encore des éléments à l'ensemble
				Joueur j = (Joueur) it.next();			 // it.next renvoie l'élément qui a été "sauté", le cast Joueur pour être sur du type
				j.piocher();
			}
		}
	}

	/**
	 * Retourne la première Carte de la Défausse en début de Manche.
	 */
	public void retournerPremiereCarte() {
		// Carte c = Pioche.getInstance().piocher();
		CartePlusDeux c = new CartePlusDeux(Couleur.BLEU);
		
		Defausse.getInstance().defausser(c);
		
		System.out.println("\nLa première carte de la défausse est un " + Defausse.getInstance().getDerniereCarteJouee() + ".");

		c.appliquerEffets(true);
	}

	/**
	 * Retourne le sens de déroulement de la Manche en cours.
	 * @return <i>true</i> pour le sens horaire, <i>false</i> pour le sens anti-horaire.
	 */
	public boolean isSensHoraire() {
		return sensHoraire;
	}

	/**
	 * Effectuer le déroulement d'un tour d'un Joueur
	 * @param j
	 * 			Joueur qui doit jouer
	 * @see Joueur
	 */
	public void jouerTour(Joueur j) {
		j.raz();
		j.jouer();
	}
	
	/**
	 * Passe le JoueurSuivant en JoueurActuel.
	 */
	public void passerJoueur() {
		this.setJoueurActuel(this.getJoueurSuivant());
	}

	/**
	 * Inverse le sens de la Manche en cours.
	 */
	public void changerSens(){
		Manche.getInstance().setSensHoraire(!Manche.getInstance().isSensHoraire());
	}

	/**
	 * Retourne vrai si un Joueur a posé toutes ses Cartes.
	 * @return <i>true</i> si un Joueur a remporté la Manche
	 */
	private boolean finManche() {
		
		boolean fin_manche = false;
		Iterator<Joueur> it = Partie.getInstance().getListeJoueurs().iterator();
		
		while (it.hasNext()){
			Joueur j = (Joueur) it.next();
			if (j.getMain().getCartes().size() == 0) {
				fin_manche = true;
			}
		}
		return fin_manche;
	}
	
	/**
	 * Compte les points dans la Main de chaque Joueur et les additionne.
	 * @return Le nombre de points contenu dans chaque Main
	 */
	public int compterPoints(Joueur j) {
		int points = 0;
		
		Iterator<Carte> it = j.getMain().getCartes().iterator();
		while (it.hasNext()){
			points += it.next().getPoints();
		}		
			
		return points;
	}
	
	/**
	 * Réinitialise les variables nécessaires au bon fonctionnement d'une manche au commencement de celle-ci
	 */
	public void razManche(){
		
		Manche.getInstance().setSensHoraire(true);
		Manche.getInstance().setJoueurActuel(null);
		
		if (Manche.getInstance().getJoueurDebut() == null) {
			Manche.getInstance().setJoueurDebut(Partie.getInstance().getListeJoueurs().get(0));
		}
		else {
			ArrayList<Joueur> joueurs = Partie.getInstance().getListeJoueurs();
			int index = joueurs.indexOf(Manche.getInstance().getJoueurDebut());
			
			Joueur joueur_suivant;
			if (index == joueurs.size() - 1)
				joueur_suivant = joueurs.get(0);
			else
				joueur_suivant = joueurs.get(index + 1);
			Manche.getInstance().setJoueurDebut(joueur_suivant);
			
		}
		
		ArrayList<Joueur> joueurs = Partie.getInstance().getListeJoueurs();
		
		Iterator<Joueur> itj = joueurs.iterator();
		while (itj.hasNext()){
			Joueur j = itj.next();
			Defausse.getInstance().getDefausse().addAll(j.getMain().getCartes());
			j.getMain().getCartes().clear();			
		}
		
		Defausse.getInstance().setDefausse(Defausse.getInstance().razCouleurJoker(Defausse.getInstance().getDefausse()));
		Pioche.getInstance().getPioche().addAll(Defausse.getInstance().getDefausse());
		
		Pioche.getInstance().melanger();
	}

	/**
	 * Retourne l'instance de la Manche, et la construit si elle n'existe pas. Par défaut, le sens horaire est à true.
	 * @return Une instance de Manche, qui correspond au singleton.
	 */
	public final static Manche getInstance() {
		// Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
		// d'éviter un appel coûteux à synchronized (qui est lourd), 
		// une fois que l'instanciation est faite.
		if (Manche.instance == null) {
			// Le mot-clé synchronized sur ce bloc empêche toute instanciation
			// multiple même par différents "threads".
			// Il est TRES important.
			synchronized(Manche.class) {
				if (Manche.instance == null) {
					Manche.instance = new Manche();
					Manche.instance.setSensHoraire(true);
					Manche.instance.setJoueurActuel(Partie.getInstance().getListeJoueurs().get(0));
					Manche.instance.setJoueurDebut(null);
					}
				}
			}
		return Manche.instance;
	}

	/**
	 * Retourne le Joueur qui commence la Manche en cours.
	 * @return Le Joueur qui commence la Manche, sous la forme d'une instance Joueur.
	 * @see Joueur
	 */
	public Joueur getJoueurDebut() {
		return joueurDebut;
	}

	/**
	 * Retourne le Joueur qui a la main pour le tour actuel de la Manche en cours.
	 * @return Le Joueur actuel, sous la forme d'une instance Joueur.
	 * @see Joueur
	 */
	public Joueur getJoueurActuel() {
		return joueurActuel;
	}

	/**
	 * Retourne le Joueur qui a eu la main au tour de jeu précédent dans la Manche.
	 * @return Le Joueur précédent, sous la forme d'une instance Joueur.
	 * @see Joueur
	 */
	public Joueur getJoueurPrecedent(){
		ArrayList<Joueur> joueurs = Partie.getInstance().getListeJoueurs();
		int index = joueurs.indexOf(getJoueurActuel());
		
		Joueur joueur_precedent;
		if (Manche.getInstance().isSensHoraire()) {
			if (index == 0) {
				joueur_precedent = joueurs.get((joueurs.size())-1);
			}
			else {
				joueur_precedent = joueurs.get(index - 1);
			}
		}
		else {
			if (index == joueurs.size() - 1) {
				joueur_precedent = joueurs.get(0);
			}
			else {
				joueur_precedent = joueurs.get(index + 1);
			}
		}
		return joueur_precedent;		
	}

	/**
	 * Retourne le Joueur qui va avoir la main au tour de jeu suivant.
	 * @return Le Joueur suivant, sous la forme d'une instance Joueur.
	 * @see Joueur
	 */
	public Joueur getJoueurSuivant(){
		ArrayList<Joueur> joueurs = Partie.getInstance().getListeJoueurs();
		int index = joueurs.indexOf(getJoueurActuel());
		
		Joueur joueur_suivant;
		if (Manche.getInstance().isSensHoraire()) {
			if (index == joueurs.size() - 1) {
				joueur_suivant = joueurs.get(0);
			}
			else {
				joueur_suivant = joueurs.get(index + 1);
			}
		}
		else {
			if (index == 0) {
				joueur_suivant = joueurs.get((joueurs.size()-1));
			}
			else  {
				joueur_suivant = joueurs.get(index-1);
			}
		}
		return joueur_suivant;
	}

	/**
	 * Met à jour le sens de déroulement de la Manche en cours.
	 * @param sensHoraire
	 * 			<i>true</i> pour horaire, <i>false</i> pour anti-horaire. 
	 */
	public void setSensHoraire(boolean sensHoraire) {
		this.sensHoraire = sensHoraire;
	}

	/**
	 * Met à jour le joueurDebut.
	 * @param joueurDebut
	 * 			Nouveau Joueur qui commence la Manche.
	 * @see Joueur
	 */
	public void setJoueurDebut(Joueur joueurDebut) {
		this.joueurDebut = joueurDebut;
	}

	/**
	 * Met à jour le joueurActuel.
	 * @param joueurActuel
	 * 			Nouveau Joueur actuel.
	 * @see Joueur
	 */
	public void setJoueurActuel(Joueur joueurActuel) {
		this.joueurActuel = joueurActuel;
	}
}
