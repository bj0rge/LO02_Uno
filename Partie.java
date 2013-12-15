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

	public void selectionNombreJoueur(){
		
		int nbj = 0;
		int nbia = 0;
		
		while (nbj == 0) {
			System.out.println("Combien de joueurs voulez-vous ? (10 joueurs max)");
			nbj = this.demanderInt();
			
			switch (nbj)
			{
			case 0:
				System.out.println("Il faut au moins deux joueurs pour jouer au UNO !");
				nbj = 0;
				break;
			case 1:
				System.out.println("Vous ne pouvez pas jouer tout seul !");
				nbj = 0;
				break;
			case 2:
				System.out.println("Si vous n'êtes que deux joueurs, le mode de jeu sera DEUX_JOUEURS. Acceptez-vous ?");
				System.out.println("[0] Oui");
				System.out.println("[1] Non");
				if (this.demanderInt() == 1){
					nbj = 0;
				} else {
					nbj = 2;
					this.setMode(ModeDeJeu.DEUX_JOUEURS);
				}
				break;
			default:
				break;
			}			
		}
		
		System.out.println("Parmi ces " + nbj + " joueurs, combien de joueurs IA ?");
		nbia = Partie.getInstance().demanderInt();
		
		while (nbia > nbj){
			System.out.println("Il ne peut pas y avoir plus de joueurs IA que de joueurs possibles.");
			System.out.println("Vous avez indiqué qu'il y aurait " + nbj + " joueurs. Combien de joueurs IA ?");
			nbia = Partie.getInstance().demanderInt();
		}
		
		for (int i = 1; i <= nbj; i++) {
			System.out.println("\nQuel est le nom du joueur " + i + " ?");
			String nom = sc.nextLine();
			if (i <= (nbj - nbia)) {
				Partie.getInstance().ajouterJoueur(new Joueur(nom));
			}
			else {
					Partie.getInstance().ajouterJoueur(new JoueurIA(nom, new JouerChiffres()));
			}
		}	
	}

	public void selectionMode(){
		
		if (Partie.getInstance().getMode() != ModeDeJeu.DEUX_JOUEURS){
			System.out.println("Quel mode de jeu voulez-vous ?");
			System.out.println("[0] STANDARD");
			System.out.println("[1] EQUIPE");
			System.out.println("[2] CHALLENGE");
		
			int choixMode = this.demanderInt();
					
			if (choixMode == 0){
				this.setMode(ModeDeJeu.STANDARD);
			} else if (choixMode == 1){
				this.setMode(ModeDeJeu.EQUIPE);
				this.constituerEquipe();
			} else if (choixMode == 2){
				this.setMode(ModeDeJeu.CHALLENGE);
			}
		}
		
		
		System.out.println("Voulez-vous changer les paramètres de base ?");
		System.out.println("[0] Ne rien changer.");
		if (this.getMode() != ModeDeJeu.CHALLENGE){
			System.out.println("[1] Changer le nombre de points avant de gagner la partie. (Default : 500 pts)");
			System.out.println("[2] Changer le nombre de manches maximum. (Default : aucun)");
		} else {
			System.out.println("[1] Changer le nombre de points avant qu'un joueur ne soit éliminé. (Default 500 pts)");
		}

		int choixParam = this.demanderInt();
		
		if (choixParam == 1){
			System.out.println("Combien de points ?");
			this.setNb_pts_max(this.demanderInt());
		} else if (choixParam == 2){
			System.out.println("Combien de manches ?");
			this.setNb_manches_max(this.demanderInt());
		}
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
	
	public void constituerEquipe(){
		
		System.out.println("Combien d'équipes y aura-t-il ?");
		int nbEquipe = this.demanderInt();		
		
		Iterator<Joueur> it = this.getListeJoueurs().iterator();
		while (it.hasNext()){
			Joueur j = it.next();
			System.out.println("Dans quelle équipe jouera " + j + " ?");
			j.setEquipe(this.demanderInt());
		}
		
	}
	
	/**
	 * Construit les Cartes
	 */

	public void debuterPartie(){
		
		// On construit les Cartes, et on les envoie dans la Pioche =)
		Partie.getInstance().construireCartes();
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
			
			this.calculScore(resultatManche);
			num_manche++;
			
			if (!this.isTerminee(num_manche)) {
				System.out.print("\n\nAppuyez sur entrée pour passer à la Manche suivante.");
				// Try - Catch qui permet de passer à la manche suivante.
				try {
					System.in.read();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		while (!this.isTerminee(num_manche));
			
		if (this.getMode() == ModeDeJeu.STANDARD || this.getMode() == ModeDeJeu.DEUX_JOUEURS){
			System.out.println("\nLe jeu est terminé ! Voici les scores :\n");
	
			Iterator<Joueur> it = this.getListeJoueurs().iterator();
			while (it.hasNext()) {
				Joueur j = it.next();
				System.out.println(j + " a " + j.getScore() + " points.");
			}
		} else if (this.getMode() == ModeDeJeu.CHALLENGE){
			System.out.println("");
			
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
		ModeDeJeu mode = this.getMode();
		boolean est_fini = false;
		if (mode == ModeDeJeu.STANDARD || mode == ModeDeJeu.DEUX_JOUEURS) {
			Iterator<Joueur> it = this.getListeJoueurs().iterator();
			while (it.hasNext()) {
				Joueur j = it.next();
				if ((j.getScore() >= this.getNb_pts_max()) 
						|| ((this.getNb_manches_max() != 0)
						&& (num_tour >= this.getNb_manches_max())) ) {
					est_fini = true;	
				}
			}
		} else if (mode == ModeDeJeu.CHALLENGE) {
			
			
			
		}
		return est_fini;
	}
	
	/**
	 * Calcule le résultat d'une manche.
	 * @param resultatManche
	 * 			Tableau : en [0] -> index du joueur qui a gagné la manche. [2]-> score à additionner.
	 */
	private void calculScore(int[] resultatManche){
		
		if (this.getMode() == ModeDeJeu.STANDARD || this.getMode() == ModeDeJeu.DEUX_JOUEURS){
			
			System.out.println("\n" + this.getListeJoueurs().get(resultatManche[0]) + " a gagné la manche ! Il empoche " + resultatManche[1] + " points.\n");
			
			ArrayList<Joueur> joueurs = this.getListeJoueurs();
			
			Iterator<Joueur> itj = joueurs.iterator();
			while (itj.hasNext()){
				Joueur j = itj.next();
				StringBuffer sb = new StringBuffer();
				sb.append("Score de ");
				sb.append(j);
				sb.append(" : ");
				sb.append(this.getJoueur(joueurs.indexOf(j)).getScore());
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
