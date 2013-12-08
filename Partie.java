package LO02_Uno;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * <b>Partie est la classe représentant la Partie de UNO. Elle permettra de faire boucler les Manches du jeu.</b>
 * <p>
 * La Partie est une classe unique (singleton), et est caractérisée par :
 * <ul>
 * <li>Une instance statique d'elle-même</li>
 * <li>Le Mode de jeu</li>
 * <li>Une liste des Joueurs de la Partie</li>
 * <li>Le nombre de points à atteindre pour mettre fin à la Partie</li>
 * <li>Le nombre de manches à atteindre pour mettre fin à la Partie</li>
 * <li>Le nombre de manches écoulées lors de la Partie</li>
 * </ul>
 * </p>
 * @see ModeDeJeu
 * @see Joueur
 */
public class Partie 
{
	
	/**
	 * Nombre de cartes à piocher lors du début d'un tour.<br>
	 * NB_CARTES_INITIAL = {@value}
	 */
	public static final int NB_CARTES_INITIAL = 7;
	
	/**
	 * L'instance de la Partie.
	 */
	private static volatile Partie instance = null;
	
	/**
	 * Le Mode de jeu de la Partie. 
	 */
	private ModeDeJeu mode;
	
	/**
	 * La liste des Joueurs qui participent à la Partie.
	 * @see Joueur
	 */
	private ArrayList<Joueur> listeJoueurs;
	
	/**
	 * Le nombre de points à atteindre pour mettre fin à la Partie.
	 */
	private int nb_pts_max;
	
	/**
	 * Le nombre de manches à atteindre pour mettre fin à la Partie.
	 */
	private int nb_manches_max;
	
	/**
	 * Le nombre de manches écoulées depuis le début de la partie.
	 */
	private int manche;
	
	
	
	
	/**
	 * Retourne l'instance du Partie, et la construit si elle n'existe pas.
	 * @return Une instance de Partie, qui correspond au singleton.
	 */
	public final static Partie getInstance() {
		// Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
		// d'éviter un appel coûteux à synchronized (qui est lourd), 
		// une fois que l'instanciation est faite.
		if (Partie.instance == null) {
			// Le mot-clé synchronized sur ce bloc empêche toute instanciation
			// multiple même par différents "threads".
			// Il est TRES important.
			synchronized(Manche.class) {
				if (Partie.instance == null) {
					Partie.instance = new Partie();
					Partie.instance.setMode(ModeDeJeu.STANDARD);
					Partie.instance.setNb_pts_max(500);
					Partie.instance.setManche(1);
					Partie.instance.setListeJoueurs(new ArrayList<Joueur>());
				}
			}
		}
		return Partie.instance;
	}
	
	/**
	 * Ajoute un Joueur à la listeJoueurs.
	 * @param j
	 * 			Joueur à ajouter.
	 * @see Joueur
	 */
	public void ajouterJoueur(Joueur j) {
		this.getListeJoueurs().add(j);
	}
	
	
	
	/**
	 * Incrémente le numéro de la manche en cours.
	 */
	public void incrementerManche() {
		this.setManche(this.getManche() + 1);
	}
	
	/**
	 * Génère la Pioche en ajoutant les Cartes. 
	 * @see Pioche
	 */
	public void genererPioche() {
		for (Couleur couleur : Couleur.values()) {
			for (int val = 1; val <= 9; val++) {
				Pioche.getInstance().getPioche().add(new CarteNumerotee(val, couleur));
				Pioche.getInstance().getPioche().add(new CarteNumerotee(val, couleur));
			}
			Pioche.getInstance().getPioche().add(new CarteNumerotee(0, couleur));
			
			Pioche.getInstance().melanger();
		}
	}
	
	
	
	
	
	
	/**
	 * Retourne le Mode de jeu de la Partie.
	 * @return Le Mode de jeu, sous la forme d'une instance.
	 */
	public ModeDeJeu getMode() {
		return mode;
	}

	/**
	 * Met à jour le Mode de jeu.
	 * @param mode
	 * 			Mode de jeu de la Partie qui va remplacer l'ancien.
	 */
	public void setMode(ModeDeJeu mode) {
		this.mode = mode;
	}


	/**
	 * Retourne le nombre de points à atteindre pour mettre fin à la Partie.
	 * @return Le nombre de points à atteindre pour mettre fin à la Partie.
	 */
	public int getNb_pts_max() {
		return nb_pts_max;
	}

	/**
	 * Met à jour le nombre de points à atteindre pour mettre fin à la Partie.
	 * @param nb_pts_max
	 * 			Le nombre de points à mettre à jour.
	 */
	public void setNb_pts_max(int nb_pts_max) {
		this.nb_pts_max = nb_pts_max;
	}

	/**
	 * Retourne le nombre de manches à atteindre pour mettre fin à la Partie.
	 * @return Le nombre de manches à atteindre pour mettre fin à la Partie.
	 */
	public int getNb_manches_max() {
		return nb_manches_max;
	}

	/**
	 * Met à jour le nombre de manches à atteindre pour mettre fin à la Partie.
	 * @param nb_manches_max
	 * 			Le nombre de manches à mettre à jour.
	 */
	public void setNb_manches_max(int nb_manches_max) {
		this.nb_manches_max = nb_manches_max;
	}

	/**
	 * Retourne le numéro de la manche en cours.
	 * @return Le numéro de la manche en cours.
	 */
	public int getManche() {
		return manche;
	}

	/**
	 * Met à jour le numéro de la manche en cours.
	 * @param manche
	 * 			Le numéro de la manche à mettre à jour.
	 */
	public void setManche(int manche) {
		this.manche = manche;
	}

	/**
	 * Retourne la Liste des Joueurs présents dans la Partie.
	 * @return La liste des Joueurs présents dans la Partie, sous forme d'une instance d'ArrayList<Joueur>.
	 * @see Joueur
	 */
	public ArrayList<Joueur> getListeJoueurs(){
		return this.listeJoueurs;
	}
	
	/**
	 * Retourne un Joueur en fonction de sa position dans liste_joueurs 
	 * @param position
	 * 			La position du Joueur que l'on veut récupérer.
	 * @return Le Joueur demandé.
	 */
	public Joueur getJoueur(int position) {
		return this.getListeJoueurs().get(position);
	}
	
	/**
	 * Met à jour la Liste des Joueurs présents dans la Partie. 
	 * @param listeJoueurs
	 * 			Liste des Joueurs à mettre à jour. 
	 */
	public void setListeJoueurs(ArrayList<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}

	
	
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		// Création de la partie. Pas nécessaire, mais je trouve ça plus joli.
		Partie.getInstance();
		
		// On construit la pioche. Faut pas s'appeler Einstein pour le comprendre =)
		Partie.getInstance().genererPioche();
		System.out.println("\nPioche générée.\n");
		
		// Ajout de 4 joueurs humains.
		for (int i = 0; i<4; i++){
			Partie.getInstance().ajouterJoueur(new Joueur());
			System.out.println("Génération du joueur " + i);
		}
		
		System.out.println("\nLa pioche contient :");
		System.out.println(Pioche.getInstance().getPioche());
		
		// Probablement à faire dans une méthode distribuer() de Manche
		// Penser à rappatrier la constante
		for (int i = 0; i < NB_CARTES_INITIAL; i++) {
			for (Joueur j : Partie.getInstance().getListeJoueurs())
				j.piocher();
		}
		System.out.println("\nLes joueurs ont pioché.\n");
		
		// Irait dans la même méthode susnommée
		Carte c = Pioche.getInstance().piocher();
		Defausse.getInstance().defausser(c);
		c.appliquerEffets();
		
		System.out.println("La première carte de la défausse est un " + c + ".");
		
		
		
		System.out.println("\nVoici les mains des joueurs :");
		for (Joueur j : Partie.getInstance().getListeJoueurs())
			System.out.println(j.getMain().getCartes());
		
		
		
		
		Joueur j = Partie.getInstance().getJoueur(0);
		
		/*
		 * Début gestion du tour. Faudra en faire une fonction (Manche probablement).
		 * ET SURTOUT, SECURISER LE NEXTINT() : vérifier que c'est bien un int,
		 * et vérifier qu'il est compris dans ce qu'on attend de lui.
		 */
		j.raz();
		System.out.println("\nJoueur 1, voici votre main.");
		
		for (int i = 0; i < j.getMain().getCartes().size(); i++) {
			System.out.println(j.getMain().getCartes().get(i));
		}
		
		System.out.println("\n[1] Jouer ?\n[2] Piocher ?");
		int ret = sc.nextInt(); 
		
		if (ret == 1){
			System.out.println("Que jouer ?");
			for (int i = 0; i < j.getMain().getCartes().size(); i++) {
				System.out.println("[" + (i+1) + "] " + j.getMain().getCartes().get(i));
			}
			ret = sc.nextInt();
			Carte carte_jouee = j.getMain().getCartes().get(ret - 1);
			System.out.println("\nVous jouez la carte " + carte_jouee + ".");

			// Gérer si la carte n'est pas jouable. 
			/*
			 * Attention !
			 * Tant qu'il n'y a pas de vérification, il FAUT que la carte posée soit
			 * jouable. Peut-être utiliser un retour de fonction finalement ?
			 * Et un do{}while();
			 */
			j.poser(carte_jouee);
			System.out.println("La carte " + carte_jouee + " a été défaussée.");

			// syso de la dernière carte posée, via défausse, pour confirmation
			System.out.println(Defausse.getInstance().getDerniereCarteJouee());
		}
		else if (ret == 2){
			j.piocher();
			
			// Trouver un moyen de retourner la carte piochée, à l'heure actuelle la fonction ne le permet pas
			System.out.println("\nJoueur 1, voici votre main.");
			
			for (int i = 0; i < j.getMain().getCartes().size(); i++) {
				System.out.println(j.getMain().getCartes().get(i));
			}
			
			/*
			 * De même qu'au dessus : il faut sécuriser le nextInt() en s'assurant qu'il s'agit bien 
			 * d'un int et non d'autre chose (String...), et qu'il est bien compris entre 1 et 2
			 */
			System.out.println("\nJouer [1] ? Passer [2] ?");
			ret = sc.nextInt(); 
			
			
			if (ret == 1){
				System.out.println("Que jouer ?");
				for (int i = 0; i < j.getMain().getCartes().size(); i++) {
					System.out.println("[" + (i+1) + "] " + j.getMain().getCartes().get(i));
				}
				ret = sc.nextInt();
				Carte carte_jouee = j.getMain().getCartes().get(ret - 1);
				System.out.println("\nVous jouez la carte " + carte_jouee);
				
				
				// Gérer si aucune carte n'est jouable. 
				/*
				 * Attention !
				 * Tant qu'il n'y a pas de vérification, il FAUT que la carte posée soit
				 * jouable. Peut-être utiliser un retour de fonction finalement ?
				 * Et un do{}while();
				 */
				j.poser(carte_jouee);
				System.out.println("La carte " + carte_jouee + " a été défaussée.");
				
				// syso de la dernière carte posée, via défausse, pour confirmation
				System.out.println(Defausse.getInstance().getDefausse().get(Defausse.getInstance().getIndexCartePosee()));
			}
		}
		

		// Et on passe au joueur suivant !
		Manche.getInstance().passerJoueur();
		
		/*
		 * Fin de la gestion du tour.
		 */
		
		

		
		sc.close();
		
	}
}
