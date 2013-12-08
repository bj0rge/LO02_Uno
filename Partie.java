package LO02_Uno;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * <b>Partie est la classe repr�sentant la Partie de UNO. Elle permettra de faire boucler les Manches du jeu.</b>
 * <p>
 * La Partie est une classe unique (singleton), et est caract�ris�e par :
 * <ul>
 * <li>Une instance statique d'elle-m�me</li>
 * <li>Le Mode de jeu</li>
 * <li>Une liste des Joueurs de la Partie</li>
 * <li>Le nombre de points � atteindre pour mettre fin � la Partie</li>
 * <li>Le nombre de manches � atteindre pour mettre fin � la Partie</li>
 * <li>Le nombre de manches �coul�es lors de la Partie</li>
 * </ul>
 * </p>
 * @see ModeDeJeu
 * @see Joueur
 */
public class Partie 
{
	
	/**
	 * Nombre de cartes � piocher lors du d�but d'un tour.<br>
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
	 * La liste des Joueurs qui participent � la Partie.
	 * @see Joueur
	 */
	private ArrayList<Joueur> listeJoueurs;
	
	/**
	 * Le nombre de points � atteindre pour mettre fin � la Partie.
	 */
	private int nb_pts_max;
	
	/**
	 * Le nombre de manches � atteindre pour mettre fin � la Partie.
	 */
	private int nb_manches_max;
	
	/**
	 * Le nombre de manches �coul�es depuis le d�but de la partie.
	 */
	private int manche;
	
	
	
	
	/**
	 * Retourne l'instance du Partie, et la construit si elle n'existe pas.
	 * @return Une instance de Partie, qui correspond au singleton.
	 */
	public final static Partie getInstance() {
		// Le "Double-Checked Singleton"/"Singleton doublement v�rifi�" permet 
		// d'�viter un appel co�teux � synchronized (qui est lourd), 
		// une fois que l'instanciation est faite.
		if (Partie.instance == null) {
			// Le mot-cl� synchronized sur ce bloc emp�che toute instanciation
			// multiple m�me par diff�rents "threads".
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
	 * Ajoute un Joueur � la listeJoueurs.
	 * @param j
	 * 			Joueur � ajouter.
	 * @see Joueur
	 */
	public void ajouterJoueur(Joueur j) {
		this.getListeJoueurs().add(j);
	}
	
	
	
	/**
	 * Incr�mente le num�ro de la manche en cours.
	 */
	public void incrementerManche() {
		this.setManche(this.getManche() + 1);
	}
	
	/**
	 * G�n�re la Pioche en ajoutant les Cartes. 
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
	 * Met � jour le Mode de jeu.
	 * @param mode
	 * 			Mode de jeu de la Partie qui va remplacer l'ancien.
	 */
	public void setMode(ModeDeJeu mode) {
		this.mode = mode;
	}


	/**
	 * Retourne le nombre de points � atteindre pour mettre fin � la Partie.
	 * @return Le nombre de points � atteindre pour mettre fin � la Partie.
	 */
	public int getNb_pts_max() {
		return nb_pts_max;
	}

	/**
	 * Met � jour le nombre de points � atteindre pour mettre fin � la Partie.
	 * @param nb_pts_max
	 * 			Le nombre de points � mettre � jour.
	 */
	public void setNb_pts_max(int nb_pts_max) {
		this.nb_pts_max = nb_pts_max;
	}

	/**
	 * Retourne le nombre de manches � atteindre pour mettre fin � la Partie.
	 * @return Le nombre de manches � atteindre pour mettre fin � la Partie.
	 */
	public int getNb_manches_max() {
		return nb_manches_max;
	}

	/**
	 * Met � jour le nombre de manches � atteindre pour mettre fin � la Partie.
	 * @param nb_manches_max
	 * 			Le nombre de manches � mettre � jour.
	 */
	public void setNb_manches_max(int nb_manches_max) {
		this.nb_manches_max = nb_manches_max;
	}

	/**
	 * Retourne le num�ro de la manche en cours.
	 * @return Le num�ro de la manche en cours.
	 */
	public int getManche() {
		return manche;
	}

	/**
	 * Met � jour le num�ro de la manche en cours.
	 * @param manche
	 * 			Le num�ro de la manche � mettre � jour.
	 */
	public void setManche(int manche) {
		this.manche = manche;
	}

	/**
	 * Retourne la Liste des Joueurs pr�sents dans la Partie.
	 * @return La liste des Joueurs pr�sents dans la Partie, sous forme d'une instance d'ArrayList<Joueur>.
	 * @see Joueur
	 */
	public ArrayList<Joueur> getListeJoueurs(){
		return this.listeJoueurs;
	}
	
	/**
	 * Retourne un Joueur en fonction de sa position dans liste_joueurs 
	 * @param position
	 * 			La position du Joueur que l'on veut r�cup�rer.
	 * @return Le Joueur demand�.
	 */
	public Joueur getJoueur(int position) {
		return this.getListeJoueurs().get(position);
	}
	
	/**
	 * Met � jour la Liste des Joueurs pr�sents dans la Partie. 
	 * @param listeJoueurs
	 * 			Liste des Joueurs � mettre � jour. 
	 */
	public void setListeJoueurs(ArrayList<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}

	
	
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		// Cr�ation de la partie. Pas n�cessaire, mais je trouve �a plus joli.
		Partie.getInstance();
		
		// On construit la pioche. Faut pas s'appeler Einstein pour le comprendre =)
		Partie.getInstance().genererPioche();
		System.out.println("\nPioche g�n�r�e.\n");
		
		// Ajout de 4 joueurs humains.
		for (int i = 0; i<4; i++){
			Partie.getInstance().ajouterJoueur(new Joueur());
			System.out.println("G�n�ration du joueur " + i);
		}
		
		System.out.println("\nLa pioche contient :");
		System.out.println(Pioche.getInstance().getPioche());
		
		// Probablement � faire dans une m�thode distribuer() de Manche
		// Penser � rappatrier la constante
		for (int i = 0; i < NB_CARTES_INITIAL; i++) {
			for (Joueur j : Partie.getInstance().getListeJoueurs())
				j.piocher();
		}
		System.out.println("\nLes joueurs ont pioch�.\n");
		
		// Irait dans la m�me m�thode susnomm�e
		Carte c = Pioche.getInstance().piocher();
		Defausse.getInstance().defausser(c);
		c.appliquerEffets();
		
		System.out.println("La premi�re carte de la d�fausse est un " + c + ".");
		
		
		
		System.out.println("\nVoici les mains des joueurs :");
		for (Joueur j : Partie.getInstance().getListeJoueurs())
			System.out.println(j.getMain().getCartes());
		
		
		
		
		Joueur j = Partie.getInstance().getJoueur(0);
		
		/*
		 * D�but gestion du tour. Faudra en faire une fonction (Manche probablement).
		 * ET SURTOUT, SECURISER LE NEXTINT() : v�rifier que c'est bien un int,
		 * et v�rifier qu'il est compris dans ce qu'on attend de lui.
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

			// G�rer si la carte n'est pas jouable. 
			/*
			 * Attention !
			 * Tant qu'il n'y a pas de v�rification, il FAUT que la carte pos�e soit
			 * jouable. Peut-�tre utiliser un retour de fonction finalement ?
			 * Et un do{}while();
			 */
			j.poser(carte_jouee);
			System.out.println("La carte " + carte_jouee + " a �t� d�fauss�e.");

			// syso de la derni�re carte pos�e, via d�fausse, pour confirmation
			System.out.println(Defausse.getInstance().getDerniereCarteJouee());
		}
		else if (ret == 2){
			j.piocher();
			
			// Trouver un moyen de retourner la carte pioch�e, � l'heure actuelle la fonction ne le permet pas
			System.out.println("\nJoueur 1, voici votre main.");
			
			for (int i = 0; i < j.getMain().getCartes().size(); i++) {
				System.out.println(j.getMain().getCartes().get(i));
			}
			
			/*
			 * De m�me qu'au dessus : il faut s�curiser le nextInt() en s'assurant qu'il s'agit bien 
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
				
				
				// G�rer si aucune carte n'est jouable. 
				/*
				 * Attention !
				 * Tant qu'il n'y a pas de v�rification, il FAUT que la carte pos�e soit
				 * jouable. Peut-�tre utiliser un retour de fonction finalement ?
				 * Et un do{}while();
				 */
				j.poser(carte_jouee);
				System.out.println("La carte " + carte_jouee + " a �t� d�fauss�e.");
				
				// syso de la derni�re carte pos�e, via d�fausse, pour confirmation
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
