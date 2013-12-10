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
	public static final int NB_CARTES_INITIAL = 7;
		
	
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
					}
				}
			}
		return Manche.instance;
	}

	
	/**
	 * Inverse le sens de la Manche en cours.
	 */
	public void changerSens(){
		Manche.getInstance().setSensHoraire(!Manche.getInstance().isSensHoraire());
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
		if (index == 0)
			joueur_precedent = joueurs.get((joueurs.size())-1);
		else
			joueur_precedent = joueurs.get(index - 1);
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
		if (index == joueurs.size())
			joueur_suivant = joueurs.get(0);
		else
			joueur_suivant = joueurs.get(index + 1);
		return joueur_suivant;		
	}
	
	/**
	 * Passe le JoueurSuivant en JoueurActuel.
	 */
	public void passerJoueur() {
		this.setJoueurActuel(this.getJoueurSuivant());
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
		Carte c = Pioche.getInstance().piocher();
		Defausse.getInstance().defausser(c);
		c.appliquerEffets();
		
		System.out.println("La première carte de la défausse est un " + Defausse.getInstance().getDerniereCarteJouee() + ".");
	}
	
	
	/**
	 * Effectuer le déroulement d'un tour d'un Joueur
	 * @param j
	 * 			Joueur qui doit jouer
	 * @see Joueur
	 */
	public void jouerTour(Joueur j) {
		j.raz();
		
		System.out.println("\nJoueur " + (Partie.getInstance().getListeJoueurs().indexOf(j) + 1) + ", que voulez-vous faire ?\n");
		
		boolean fintour = false;
		
		do {
			int ret = -1;
			do {
				if (j.APioche())
					System.out.println("[0] Passer son tour");
				else
					System.out.println("[0] Piocher");
				
				int i = 0;
				Iterator<Carte> it = j.getMain().getCartes().iterator();
				while (it.hasNext()){
					System.out.println("[" + (i+1) + "] Jouer le " + it.next());
					i += 1;
				}
				
				ret = Partie.getInstance().demanderInt();
			}
			// Tant que ret n'est pas dans la liste 
			while (ret < 0 || ret > j.getMain().getCartes().size());
			
			// Si l'option choisie est 0
			if (ret == 0)
				// Si le joueur a déjà pioché, cela veut dire que 
				// son choix était de finir son tour
				if (j.APioche())
					fintour = true;
				// Sinon, on le fait piocher
				else {
					System.out.println("Vous avez pioché un " + j.piocher());
				}
			// Sinon, il choisit de poser une carte
			else {
				Carte carte_jouee = j.getMain().getCartes().get(ret - 1);
				System.out.println("\nVous jouez la carte " + carte_jouee + ".");
				
				if (carte_jouee.estJouable(Defausse.getInstance().getDerniereCarteJouee())) {
					j.poser(carte_jouee);
					System.out.println("La carte " + carte_jouee + " a été défaussée.");
					
					// syso de la dernière carte posée, via défausse, pour confirmation
					System.out.println("La première carte de la défausse est maintenant un " + Defausse.getInstance().getDerniereCarteJouee());
				
					fintour = true;
				}
				else {
					System.out.println("La carte " + carte_jouee + " ne peut pas être jouée.\n"
							+ "Pour rappel, la dernière carte jouée est un " + Defausse.getInstance().getDerniereCarteJouee());
				}
			}
		}
		while(!fintour);		

		// Et on passe au joueur suivant !
		Manche.getInstance().passerJoueur();
	}
	
	
	
	
	
	
	/**
	 * Retourne le sens de déroulement de la Manche en cours.
	 * @return <i>true</i> pour le sens horaire, <i>false</i> pour le sens anti-horaire.
	 */
	public boolean isSensHoraire() {
		return sensHoraire;
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
	 * Retourne le Joueur qui a la main pour le tour actuel de la Manche en cours.
	 * @return Le Joueur actuel, sous la forme d'une instance Joueur.
	 * @see Joueur
	 */
	public Joueur getJoueurActuel() {
		return joueurActuel;
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
