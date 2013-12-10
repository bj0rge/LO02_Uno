package LO02_Uno;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;;



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
	
	private final static Scanner sc = new Scanner(System.in);

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
	 * G�n�re les Cartes, et les envoie dans la Pioche. 
	 * @see Pioche
	 */
	public void construireCartes() {
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
	 * Demande un int via l'interface et retourne la valeur.
	 * @return Un entier, entr� par le Joueur.
	 */
	public int demanderInt() {
		int ret;
		// Boucle pour s'assurer que l'utilisateur a bien entr� un entier.
		while (!sc.hasNextInt())
		{
			sc.nextLine();
			System.out.print("Valeur incorrecte. Entrez un entier : ");
		}
		ret = sc.nextInt();
		return ret;
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
		
		
		// Cr�ation de la partie. Pas n�cessaire, mais je trouve �a plus joli.
		Partie.getInstance();
		
		// On construit les Cartes, et on les envoie dans la Pioche =)
		Partie.getInstance().construireCartes();
		System.out.println("\nCartes g�n�r�es.\n");
		
		// Ajout de 4 joueurs humains.
		for (int i = 0; i < 4; i++){
			Partie.getInstance().ajouterJoueur(new Joueur());
			System.out.println("G�n�ration du joueur " + i);
		}
		
		System.out.println("\nLa pioche contient :");
		System.out.println(Pioche.getInstance().getPioche());
		
		Manche.getInstance().distribuer();
		System.out.println("\nLes joueurs ont pioch�.\n");
		
		
		Manche.getInstance().retournerPremiereCarte();
		
		
		System.out.println("\nVoici les mains des joueurs :");
		
		Iterator<Joueur> it = Partie.getInstance().listeJoueurs.iterator(); // Cr�ation d'un it�rateur it de listeJoueurs
		while (it.hasNext()){												// it.hasNext retourne true s'il reste encore des �l�ments � l'ensemble
			Joueur j = (Joueur) it.next();									// it.next renvoie l'�l�ment qui a �t� "saut�", le cast Joueur pour �tre sur du type
			System.out.println(j.getMain().getCartes());					// syso pour renvoyer le toString
		}
		
		
		Joueur j = Partie.getInstance().getJoueur(0);
				
		// Tour du Joueur j
		Manche.getInstance().jouerTour(j);
		
		
		
		

		
		sc.close();
		
	}
}
