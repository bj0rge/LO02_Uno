package fr.utt.isi.lo02.projet_uno.manche;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.isi.lo02.projet_uno.carte.Carte;
import fr.utt.isi.lo02.projet_uno.joueur.Joueur;
import fr.utt.isi.lo02.projet_uno.partie.Partie;


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
	public static final int NB_CARTES_INITIAL = 7;
		
	
	/**
	 * L'instance de la Manche.
	 */
	// L'utilisation du mot clé volatile permet, en Java version 5 et supérieur, 
	// d'éviter le cas  où "Singleton.instance" est non-nul,
	// mais pas encore "réellement" instancié.
	private static volatile Manche instance = null;
	
	/**
	 * Le sens du Jeu en cours : horaire à <i>Vrai</i>, anti-horaire à <i>Faux</i>.
	 */
	private boolean sensHoraire;
	
	/**
	 * Le Joueur qui commence la Manche.
	 * @see Joueur
	 */
	private Joueur joueurDebut;


	/**
	 * Le Joueur actuel, c'est à dire celui qui va jouer son tour.
	 * @see Joueur
	 */
	private Joueur joueurActuel;
	
	/**
	 * Constructeur de la Manche.
	 */
	private Manche(){
		super();
	}

	/**
	 * Fait dérouler une Manche du début à la fin.
	 * @return La position du joueur gagnant, sous forme d'entier.
	 */
	public int deroulementManche() {
	
		// On donne pour premier joueur le JoueurDebut
		Manche.getInstance().setJoueurActuel(Manche.getInstance().getJoueurDebut());
		
		// On distribue les cartes à chacun des joueurs
		Manche.getInstance().distribuer();
		
		// On retourne la première carte de la Pioche
		Manche.getInstance().retournerPremiereCarte();
		
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
	 * Retourne la première Carte de la {@link Defausse} en début de Manche.
	 */
	public void retournerPremiereCarte() {
		
		// On récupérer la première carte de la pioche
		Carte c = Pioche.getInstance().piocher();
		
		// On la pose dans la défausse
		Defausse.getInstance().defausser(c);
		
		System.out.println("\nLa première carte de la défausse est un " + Defausse.getInstance().getDerniereCarteJouee() + ".\n");

		// On applique son effet en précisant que c'est la première carte retournée de la pioche
		c.appliquerEffets(true);
	}

	/**
	 * Retourne le sens de déroulement de la Manche en cours.
	 * @return <i>Vrai</i> pour le sens horaire, <i>Faux</i> pour le sens anti-horaire.
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
		// On affiche la dernière carte posée, via défausse, pour confirmation
		System.out.println("\nLa première carte de la défausse est un " + Defausse.getInstance().getDerniereCarteJouee() + ".\n");
		j.jouer();
	}
	
	/**
	 * Passe le tour du Joueur actuel.
	 */
	public void passerJoueur() {
		this.setJoueurActuel(this.getJoueurSuivant());
	}

	/**
	 * Inverse le sens de la Manche en cours.
	 */
	public void changerSens(){
		// On change le sens en appliquant l'inverse du sens actuel
		Manche.getInstance().setSensHoraire(!Manche.getInstance().isSensHoraire());
	}

	/**
	 * Retourne vrai si un Joueur a posé toutes ses Cartes.
	 * @return <i>Vrai</i> si un Joueur a remporté la Manche, <i>Faux</i> sinon.
	 */
	private boolean finManche() {
		
		boolean fin_manche = false;
		Iterator<Joueur> it = Partie.getInstance().getListeJoueurs().iterator();
		
		// On vérifie s'il y a un joueur avec 0 carte dans la main
		while (it.hasNext()){
			Joueur j = (Joueur) it.next();
			if (j.getMain().getCartes().size() == 0) {
				fin_manche = true;
			}
		}
		return fin_manche;
	}
	
	/**
	 * Compte les points dans la Main d'un Joueur
	 * @return Le nombre de points contenu dans une Main
	 * @see Carte#points
	 */
	public int compterPoints(Joueur j) {
		int points = 0;
		
		// On fait la somme des points de toutes les cartes d'un main d'un joueur j
		Iterator<Carte> it = j.getMain().getCartes().iterator();
		while (it.hasNext()){
			points += it.next().getPoints();
		}		
			
		return points;
	}
	
	/**
	 * Réinitialise les variables nécessaires au bon fonctionnement d'une manche au commencement de celle-ci
	 * @see Manche#joueurActuel
	 * @see Manche#sensHoraire
	 */
	public void razManche(){
		
		// On replace dans le sens horaire et on réinitialise le joueur actuel
		Manche.getInstance().setSensHoraire(true);
		Manche.getInstance().setJoueurActuel(null);
		
		// S'il n'y avait pas de premier joueur, c'est que c'est la première manche, donc le premier joueur à jouer est le premier joueur de la liste
		if (Manche.getInstance().getJoueurDebut() == null) {
			Manche.getInstance().setJoueurDebut(Partie.getInstance().getListeJoueurs().get(0));
		}
		else { // S'il y avait un premier joueur à la manche précédente
			ArrayList<Joueur> joueurs = Partie.getInstance().getListeJoueurs();
			int index = joueurs.indexOf(Manche.getInstance().getJoueurDebut());
			
			// On prend un nouveau premier joueur
			Joueur joueur_suivant;
			// Si on est à la fin de la liste, on prend le premier
			if (index == joueurs.size() - 1)
				joueur_suivant = joueurs.get(0);
			else // Sinon on prend le suivant
				joueur_suivant = joueurs.get(index + 1);
			Manche.getInstance().setJoueurDebut(joueur_suivant);
			
		}
		
		ArrayList<Joueur> joueurs = Partie.getInstance().getListeJoueurs();
		
		// On rassemble les cartes
		Iterator<Joueur> itj = joueurs.iterator();
		while (itj.hasNext()){
			Joueur j = itj.next();
			// On place les cartes d'un joueur dans la défausse
			Defausse.getInstance().getDefausse().addAll(j.getMain().getCartes());
			// On retire les cartes de sa main
			j.getMain().getCartes().clear();			
		}
		
		// On retire les couleurs des CarteJokers (CarteChangerCouleur et CartePlusQuatre)
		Defausse.getInstance().setDefausse(Defausse.getInstance().razCouleurJoker(Defausse.getInstance().getDefausse()));
		
		// On met les cartes de la défausse dans la pioche
		Pioche.getInstance().getPioche().addAll(Defausse.getInstance().getDefausse());
		
		Pioche.getInstance().melanger();
	}

	/**
	 * Retourne l'instance de la Manche, et la construit si elle n'existe pas. Par défaut, le sens horaire est à <i>Vrai</i>.
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
		
		// Le joueur précédent dépend dans quel sens on joue
		Joueur joueur_precedent;
		if (Manche.getInstance().isSensHoraire()) {
			if (index == 0) { // Si le joueur actuel est le premier, le joueur précédent est donc le dernier
				joueur_precedent = joueurs.get((joueurs.size())-1);
			}
			else {
				joueur_precedent = joueurs.get(index - 1);
			}
		}
		else { 
			// Dans le sens anti-horaire, le joueur précédent du dernier joueur est le premier
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
		
		// Le joueur suivant dépend dans quel sens on joue
		Joueur joueur_suivant;
		if (Manche.getInstance().isSensHoraire()) {
			// Dans le sens horaire, si le joueur actuel est le dernier joueur de la liste alors le joueur suivant est le premier de la liste
			if (index == joueurs.size() - 1) {
				joueur_suivant = joueurs.get(0);
			}
			else {
				joueur_suivant = joueurs.get(index + 1);
			}
		}
		else {
			// Dans le sens anti-horaire, le joueur suivant du premier joueur de la liste est le dernier joueur de la liste
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
	 * 			<i>Vrai</i> pour horaire, <i>Faux</i> pour anti-horaire. 
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
