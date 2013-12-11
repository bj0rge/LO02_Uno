package LO02_Uno;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

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
	
	final static Scanner sc = new Scanner(System.in);

	/**
	 * L'instance de la Partie.
	 */
	private static volatile Partie instance = null;
	
	/**
	 * La liste des Joueurs qui participent à la Partie.
	 * @see Joueur
	 */
	private ArrayList<Joueur> listeJoueurs;

	/**
	 * Le Mode de jeu de la Partie. 
	 */
	private ModeDeJeu mode;
	
	/**
	 * Le nombre de manches à atteindre pour mettre fin à la Partie.
	 */
	private int nb_manches_max;

	/**
	 * Le nombre de points à atteindre pour mettre fin à la Partie.
	 */
	private int nb_pts_max;
	
	/**
	 * Génère les Cartes, et les envoie dans la Pioche. 
	 * @see Pioche
	 */
	private void construireCartes() {
		for (Couleur couleur : Couleur.values()) {
			for (int val = 1; val <= 9; val++) {
				for (int i = 0; i < 2; i++) {
					Pioche.getInstance().getPioche().add(new CarteNumerotee(val, couleur));
				}
			}
			Pioche.getInstance().getPioche().add(new CarteNumerotee(0, couleur));
			for (int i = 0; i < 2; i++) {
				Pioche.getInstance().getPioche().add(new CartePlusDeux(couleur));
				Pioche.getInstance().getPioche().add(new CarteChangerSens(couleur));
				Pioche.getInstance().getPioche().add(new CartePasserTour(couleur));
			}
		}
		for (int i = 0; i < 4; i++) {
			Pioche.getInstance().getPioche().add(new CarteChangerCouleur());
			Pioche.getInstance().getPioche().add(new CartePlusQuatre());
		}
		Pioche.getInstance().melanger();
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
	 * Construit les Cartes et ajoute les Joueurs
	 */
	public void debuterPartie(){
		
		// On construit les Cartes, et on les envoie dans la Pioche =)
		Partie.getInstance().construireCartes();
		System.out.println("\nCartes générées.\n");
		
		// Ajout de 4 joueurs humains.
		for (int i = 0; i < 4; i++){
			Partie.getInstance().ajouterJoueur(new Joueur());
			System.out.println("Génération du joueur " + (i+1));
		}
	}

	/**
	 * Fait dérouler une Partie du début à la fin.
	 */
	public void deroulementPartie() {
		int num_manche = 0;
		do {
			Manche.getInstance().razManche();
			// On récupère l'index du joueur gagnant et le score gagné.
			int resultatManche[] = new int[2];
			resultatManche = Manche.getInstance().deroulementManche();
			
			Partie.getInstance().calculScore(resultatManche);
			num_manche++;
			
			if (!Partie.getInstance().isTerminee(num_manche)) {
				System.out.print("\n\nAppuyez sur entrée pour passer à la Manche suivante.");
				// Try - Catch qui permet de passer à la manche suivante.
				try {
					System.in.read();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		while (!Partie.getInstance().isTerminee(num_manche));
		System.out.println("\nLe jeu est terminé ! Voici les scores :\n");
	
		Iterator<Joueur> it = Partie.getInstance().getListeJoueurs().iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			System.out.println("Le joueur " + (Partie.getInstance().getListeJoueurs().indexOf(j) + 1) + " a " + j.getScore() + " points.");
		}
	}

	/**
	 * Demande un int via l'interface et retourne la valeur.
	 * @return Un entier, entré par le Joueur.
	 */
	public int demanderInt() {
		int ret;
		// Boucle pour s'assurer que l'utilisateur a bien entré un entier.
		while (!sc.hasNextInt())
		{
			sc.nextLine();
			System.out.print("Valeur incorrecte. Entrez un entier : ");
		}
		ret = sc.nextInt();
		return ret;
	}
	
	/**
	 * Retourne vrai si la Partie est terminée, en fonction du Mode de jeu
	 * @param num_tour
	 * 			Numéro de la manche en cours.
	 * @return <i>true</i> si la Partie est terminée, <i>false</i> sinon.
	 */
	private boolean isTerminee(int num_tour) {
		ModeDeJeu mode = Partie.getInstance().getMode();
		boolean est_fini = false;
		if (mode == ModeDeJeu.STANDARD) {
			Iterator<Joueur> it = Partie.getInstance().getListeJoueurs().iterator();
			while (it.hasNext()) {
				Joueur j = it.next();
				if ((j.getScore() >= Partie.getInstance().getNb_pts_max()) 
						|| ((Partie.getInstance().getNb_manches_max() != 0)
						&& (num_tour >= Partie.getInstance().getNb_manches_max())) ) {
					est_fini = true;	
				}
			}
		}
		return est_fini;
	}
	
	/**
	 * Calcule le résultat d'une manche.
	 * @param resultatManche
	 * 			Tableau : en [0] -> index du joueur qui a gagné la manche. [2]-> score à additionner.
	 */
	private void calculScore(int[] resultatManche){
		
		if (Partie.getInstance().getMode() == ModeDeJeu.STANDARD){
			
			System.out.println("\nLe joueur " + (resultatManche[0] + 1) + " a gagné la manche. Il empoche " + resultatManche[1] + " points.\n");
			
			ArrayList<Joueur> joueurs = Partie.getInstance().getListeJoueurs();
			
			Iterator<Joueur> itj = joueurs.iterator();
			while (itj.hasNext()){
				Joueur j = itj.next();
				StringBuffer sb = new StringBuffer();
				sb.append("Score de Joueur ");
				sb.append(joueurs.indexOf(j)+1);
				sb.append(" : ");
				sb.append(Partie.getInstance().getJoueur(joueurs.indexOf(j)).getScore());
				sb.append(" + ");
				
				if (joueurs.indexOf(j) == resultatManche[0]){
					sb.append(resultatManche[1]);
					j.ajouterPoints(resultatManche[1]);
				}
				else {
					sb.append("0");
				}
				
				sb.append(" = ");
				sb.append(j.getScore());
				sb.append(" points");
				System.out.println(sb.toString());
			}
		}
	}
	
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
					Partie.instance.setNb_manches_max(0);
					Partie.instance.setListeJoueurs(new ArrayList<Joueur>());
				}
			}
		}
		return Partie.instance;
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
	 * Retourne le Mode de jeu de la Partie.
	 * @return Le Mode de jeu, sous la forme d'une instance.
	 */
	public ModeDeJeu getMode() {
		return mode;
	}

	/**
	 * Retourne le nombre de manches à atteindre pour mettre fin à la Partie.
	 * @return Le nombre de manches à atteindre pour mettre fin à la Partie.
	 */
	public int getNb_manches_max() {
		return nb_manches_max;
	}

	/**
	 * Retourne le nombre de points à atteindre pour mettre fin à la Partie.
	 * @return Le nombre de points à atteindre pour mettre fin à la Partie.
	 */
	public int getNb_pts_max() {
		return nb_pts_max;
	}

	/**
	 * Met à jour la Liste des Joueurs présents dans la Partie. 
	 * @param listeJoueurs
	 * 			Liste des Joueurs à mettre à jour. 
	 */
	public void setListeJoueurs(ArrayList<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
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
	 * Met à jour le nombre de manches à atteindre pour mettre fin à la Partie.
	 * @param nb_manches_max
	 * 			Le nombre de manches à mettre à jour.
	 */
	public void setNb_manches_max(int nb_manches_max) {
		this.nb_manches_max = nb_manches_max;
	}

	/**
	 * Met à jour le nombre de points à atteindre pour mettre fin à la Partie.
	 * @param nb_pts_max
	 * 			Le nombre de points à mettre à jour.
	 */
	public void setNb_pts_max(int nb_pts_max) {
		this.nb_pts_max = nb_pts_max;
	}
}
