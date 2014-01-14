package fr.utt.isi.lo02.projet_uno.partie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

import fr.utt.isi.lo02.projet_uno.carte.CarteChangerCouleur;
import fr.utt.isi.lo02.projet_uno.carte.CarteChangerSens;
import fr.utt.isi.lo02.projet_uno.carte.CarteNumerotee;
import fr.utt.isi.lo02.projet_uno.carte.CartePasserTour;
import fr.utt.isi.lo02.projet_uno.carte.CartePlusDeux;
import fr.utt.isi.lo02.projet_uno.carte.CartePlusQuatre;
import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;
import fr.utt.isi.lo02.projet_uno.enumeration.ModeDeJeu;
import fr.utt.isi.lo02.projet_uno.joueur.Joueur;
import fr.utt.isi.lo02.projet_uno.joueur.JoueurIA;
import fr.utt.isi.lo02.projet_uno.manche.Manche;
import fr.utt.isi.lo02.projet_uno.manche.Pioche;
import fr.utt.isi.lo02.projet_uno.strategy.JouerChiffres;

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
		// On construit les cartes en fonction des quatre couleurs
		for (Couleur couleur : Couleur.values()) {
			
			// Les cartes numérotées qui sont au nombre de deux vont de 1 à 9
			for (int val = 1; val <= 9; val++) {
				for (int i = 0; i < 2; i++) {
					Pioche.getInstance().getPioche().add(new CarteNumerotee(val, couleur));
				}
			}
			// Il n'y a qu'une carte 0 par couleur
			Pioche.getInstance().getPioche().add(new CarteNumerotee(0, couleur));
			
			// Il y a deux cartes spéciales par couleur
			for (int i = 0; i < 2; i++) {
				Pioche.getInstance().getPioche().add(new CartePlusDeux(couleur));
				Pioche.getInstance().getPioche().add(new CarteChangerSens(couleur));
				Pioche.getInstance().getPioche().add(new CartePasserTour(couleur));
			}
		}
		
		// Il y a quatre CarteChangeCouleur et quatre CartePlusQuatre
		for (int i = 0; i < 4; i++) {
			Pioche.getInstance().getPioche().add(new CarteChangerCouleur());
			Pioche.getInstance().getPioche().add(new CartePlusQuatre());
		}
	}

	/**
	 * Permet à l'utilisateur de choisir le nombre de joueurs (IA et Humain), et de donner leur nom.
	 * Son choix permet d'ajouter des joueurs à {@link Partie#listeJoueurs}.
	 */
	public void selectionNombreJoueur(){
		
		int nbj = 0;
		int nbia = 0;
		
		// Le UNO se joue au moins à 2 joueurs et à 10 joueurs maximum
		while (nbj <= 1 || nbj > 10) {
			System.out.println("Combien de joueurs voulez-vous ?");
			nbj = this.demanderInt();
			
			if (nbj <= 1){
				System.out.println("Il faut au moins deux joueurs pour jouer au UNO !");
			} else if (nbj == 2){
				
				int reponse = 0;
				
				// On propose de changer le mode de jeu à 2 joueurs s'il n'y a que deux joueurs
				while (reponse != 1 && reponse != 2) {
					System.out.println("Si vous n'êtes que deux joueurs, le mode de jeu sera DEUX_JOUEURS. Acceptez-vous ?");
					System.out.println("[1] Oui");
					System.out.println("[2] Non");
				
					reponse = this.demanderInt();
				
					if (reponse == 1){
						this.setMode(ModeDeJeu.DEUX_JOUEURS);
					} else if (reponse == 2){
						nbj = 0;
					} else {
						System.out.println("Erreur de commande.");
					}
				}
			} else if (nbj > 10){
				System.out.println("Il ne peut y avoir plus de 10 joueurs !");
			}
		}
		
		System.out.println("Parmi ces " + nbj + " joueurs, combien de joueurs IA ?");
		nbia = Partie.getInstance().demanderInt();
		
		while (nbia > nbj){
			System.out.println("Il ne peut pas y avoir plus de joueurs IA que de joueurs possibles.");
			System.out.println("Vous avez indiqué qu'il y aurait " + nbj + " joueurs. Combien de joueurs IA ?");
			nbia = Partie.getInstance().demanderInt();
		}
		
		// On récupère les noms des joueurs et on ajoute dans la liste de joueurs au fur et à mesure
		for (int i = 1; i <= nbj; i++) {
			System.out.println("\nQuel est le nom du joueur " + i + " ?");
			String nom = sc.next();
			if (i <= (nbj - nbia)) {
				Partie.getInstance().ajouterJoueur(new Joueur(nom));
			}
			else {
					Partie.getInstance().ajouterJoueur(new JoueurIA(nom, new JouerChiffres()));
			}
		}
	}
	
	/**
	 * Permet à l'utilisateur de choisir le mode de jeu qu'il souhaite. Son choix modifie l'attribue {@link Partie#mode}.
	 */
	public void selectionMode(){
		
		int choixMode = -1;
		
		// Si le mode deux joueurs a déjà été indiqué, il est inutile de proposer un mode de jeu
		if (Partie.getInstance().getMode() != ModeDeJeu.DEUX_JOUEURS){
			while (choixMode == -1) {
				System.out.println("Quel mode de jeu voulez-vous ?");
				System.out.println("[0] STANDARD");
				System.out.println("[1] CHALLENGE");
				if ((this.getListeJoueurs().size()%2) == 0){
					System.out.println("[2] EQUIPE");
				}
			
				choixMode = this.demanderInt();
					
				if (choixMode == 0){
					this.setMode(ModeDeJeu.STANDARD);
				} else if (choixMode == 1){
					this.setMode(ModeDeJeu.CHALLENGE);
				} else if (choixMode == 2 && ((this.getListeJoueurs().size()%2) == 0)){
					this.setMode(ModeDeJeu.EQUIPE);
					this.constituerEquipe();
				} else {
					System.out.println("Erreur de commande.");
					choixMode = -1;
				}
			}
		}
		
		int choixParam = -1;
		
		while (choixParam == -1){
			System.out.println("Voulez-vous changer les paramètres de base ?");
			System.out.println("[0] Ne rien changer. (Par défaut : 500 points et aucune manche maximum)");
			if (this.getMode() != ModeDeJeu.CHALLENGE){
				System.out.println("[1] Changer le nombre de points avant de gagner la partie.");
				System.out.println("[2] Changer le nombre de manches maximum.");
			} else { // Il ne peux pas y avoir de manches maximum en mode Challenge
				System.out.println("[1] Changer le nombre de points avant qu'un joueur ne soit éliminé.");
			}

			choixParam = this.demanderInt();
			
			int val = 0;
		
			if (choixParam == 0){
				// Au UNO, par défaut le nombre de points max à atteindre est 500
				this.setNb_pts_max(500);
			} else if (choixParam == 1){ // Le joueur veut changer le nombre de points
				while (val == 0){
					System.out.println("Combien de points ?");
					val = this.demanderInt();
					if (val <= 0) {
						System.out.println("Impossible de fixer un nombre de points max inférieur ou égal à 0");
						val = 0;
					} else {
						this.setNb_pts_max(val);
					}
				}
			} else if (choixParam == 2){
				while (val == 0){
					System.out.println("Combien de manches ?");
					val = this.demanderInt();
					if (val <= 0) {
						System.out.println("Impossible de fixer un nombre de manches max inférieur ou égal à 0");
						val = 0;
					} else {
						this.setNb_manches_max(val);
					}
				}
			} else {
				System.out.println("Erreur de commande.");
				choixParam = -1;
			}
		}
	}
	
	/**
	 * Ajoute un Joueur à la {@link Partie#listeJoueurs}.
	 * @param j
	 * 			Joueur à ajouter.
	 * @see Joueur
	 */
	public void ajouterJoueur(Joueur j) {
		this.getListeJoueurs().add(j);
	}
	
	/**
	 * Indique qui joue avec qui en mode {@link ModeDeJeu#EQUIPE}.
	 */
	public void constituerEquipe(){
		
		// Les équipes sont des binomes, et sont formées pour faire en sorte que chaque équipe joue les uns à la suite des autres
		// Pour cela, une équipe joue toutes les X fois, X étant la moitié du nombre de joueurs présents, soit le nombre d'équipe
		int x = (this.getListeJoueurs().size() / 2);
		
		System.out.println("\n");
		
		for (int i = 0; i < x; i++){
			System.out.println(this.getListeJoueurs().get(i) + " joue avec " + this.getListeJoueurs().get(i + x) + ".");
		}
		System.out.println("\n");
	}
	
	/**
	 * Construit les Cartes.
	 * @see Partie#construireCartes()
	 */

	public void debuterPartie(){
		
		// On construit les Cartes, et on les envoie dans la Pioche.
		Partie.getInstance().construireCartes();
	}

	/**
	 * Fait dérouler une Partie du début à la fin.
	 */
	public void deroulementPartie() {
		int num_manche = 0;
		do {
			Manche.getInstance().razManche();
			// On récupère l'index du joueur gagnant
			int index_vainqueur_manche;
			index_vainqueur_manche = Manche.getInstance().deroulementManche();
			
			this.calculScore(index_vainqueur_manche);
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
	}
	/**
	 * Demande un <i>int</i> via l'interface et retourne la valeur.
	 * @return Un entier, entré par le Joueur.
	 */
	public int demanderInt() {
		int ret;
		// Boucle pour s'assurer que l'utilisateur a bien entré un entier.
		while (!sc.hasNextInt())
		{
			sc.next();
			System.out.print("Valeur incorrecte. Entrez un entier : ");
		}
		
		ret = sc.nextInt();
		return ret;
	}
	
	/**
	 * Retourne si la Partie est terminée, en fonction du Mode de jeu
	 * @param num_tour
	 * 			Numéro de la manche en cours.
	 * @return <i>Vrai</i> si la Partie est terminée, <i>Faux</i> sinon.
	 */
	private boolean isTerminee(int num_tour) {
		
		ModeDeJeu mode = this.getMode();
		boolean est_fini = false;
		
		// En-dehors du mode Challenge, une partie est terminée soit ; 
		// - quand on a atteint/dépassé le nombre de points max
		// - quand on atteint le nombre de manche max
		if (mode != ModeDeJeu.CHALLENGE) {
			Iterator<Joueur> it = this.getListeJoueurs().iterator();
			while (it.hasNext()) {
				Joueur j = it.next();
				if ((j.getScore() >= this.getNb_pts_max()) 
						|| ((this.getNb_manches_max() != 0)
						&& (num_tour >= this.getNb_manches_max())) ) {
					est_fini = true;	
				}
			}
		} else { // En mode Challenge, la partie est terminée lorsqu'il ne reste plus qu'un joueur
			
			if (this.getListeJoueurs().size() == 1){
				est_fini = true;
			}
		}
		return est_fini;
	}
	
	/**
	 * Calcule le résultat d'une manche.
	 * @param index_vainqueur
	 * 			L'inex du joueur vainqueur de la manche.
	 */
	private void calculScore(int index_vainqueur){
		
		int points = 0;
		ArrayList<Joueur> joueurs = this.getListeJoueurs();
		Iterator<Joueur> it = joueurs.iterator();
		
		// En mode Standard ou Deux Joueurs, l'affichage est le même : 1 joueur = 1 score
		if (this.getMode() == ModeDeJeu.STANDARD || this.getMode() == ModeDeJeu.DEUX_JOUEURS){
			
			while (it.hasNext()) {
				Joueur j = it.next();
				points += Manche.getInstance().compterPoints(j);
				}
			
			System.out.println(this.getListeJoueurs().get(index_vainqueur) + " a gagné la manche ! Il empoche " + points + " points.\n");
			
			it = joueurs.iterator();
			
			// On affiche sous la forme : score précédent + points gagnés = nouveau score
			while (it.hasNext()){
				Joueur j = it.next();
				StringBuffer sb = new StringBuffer();
				sb.append("Score de ");
				sb.append(j);
				sb.append(" : ");
				sb.append(j.getScore());
				sb.append(" + ");
				
				// Seul le joueur vainqueur gagne des points
				if (joueurs.indexOf(j) == index_vainqueur){
					sb.append(points);
					j.ajouterPoints(points);
				}
				else {
					sb.append("0");
				}
				
				sb.append(" = ");
				sb.append(j.getScore());
				sb.append(" points");
				System.out.println(sb.toString());
			}
		} else if (this.getMode() == ModeDeJeu.CHALLENGE){
			
			System.out.println("\n" + joueurs.get(index_vainqueur) + " a gagné la manche !");
			
			ArrayList<Joueur> joueurs_elimines = new ArrayList<>();
			
			// On affiche sous la forme : score précédent + points gagnés = nouveau score
			while (it.hasNext()){
				Joueur j = it.next();
				StringBuffer sb = new StringBuffer();
				sb.append("Score de ");
				sb.append(j);
				sb.append(" : ");
				sb.append(j.getScore());
				sb.append(" + ");
				
				// En mode Challenge, seul le joueur vainqueur ne gagne PAS de points
				if (joueurs.indexOf(j) != index_vainqueur){
					sb.append(Manche.getInstance().compterPoints(j));
					j.ajouterPoints(Manche.getInstance().compterPoints(j));
				}
				else {
					sb.append("0");
				}
			
				sb.append(" = ");
				sb.append(j.getScore());
				sb.append(" points");
				
				// Si le score d'un joueur dépasse la limite de points autorisé, il est éliminé
				if (j.getScore() >= this.nb_pts_max){
					sb.append(" => Eliminé !");
					// On crée une liste fictive dans le cas où il y a plusieurs joueurs éliminés en même temps
					joueurs_elimines.add(j);
				}
				System.out.println(sb.toString());
			}
			
			// Si la liste fictive contient des joueurs éliminés, on retire ces joueurs de la liste principale
			if (joueurs_elimines.isEmpty() == false){
				joueurs.removeAll(joueurs_elimines);
			}			
		} else if (this.getMode() == ModeDeJeu.EQUIPE) { // En mode Equipe, l'affichage est de la forme X joueurs = 1 score
			
			System.out.println("\n" + joueurs.get(index_vainqueur) + " a gagné la manche !");
			
			// Les équipes sont des binomes et sont formées pour faire en sorte que chaque équipe joue les uns à la suite des autres
			// Pour cela, une équipe joue toutes les X fois, X étant la moitié du nombre de joueurs présents, soit le nombre d'équipe
			int x = (this.getListeJoueurs().size() / 2);
			int index_vainqueur2;
			
			// On récupère l'index du deuxième binome qui a gagné
			if (index_vainqueur < x){
				index_vainqueur2 = index_vainqueur + x;
			} else {
				index_vainqueur2 = index_vainqueur - x;
			}
			
			// On compte les points des joueurs perdants
			while (it.hasNext()) {
				Joueur j = it.next();
				if (joueurs.indexOf(j) != index_vainqueur && joueurs.indexOf(j) != index_vainqueur2){
					points += Manche.getInstance().compterPoints(j);
				}
			}
			
			// On affiche sous la forme : score précédent + points gagnés = nouveau score
			for (int i = 0; i < x; i++){
				StringBuffer sb = new StringBuffer();
				sb.append("Score de ");
				sb.append(joueurs.get(i));
				sb.append(" et ");
				sb.append(joueurs.get(i + x));
				sb.append(" : ");
				sb.append(joueurs.get(i).getScore());
				sb.append(" + ");
				
				// Seul les joueurs vainqueurs gagne des points
				if (i == index_vainqueur || i == index_vainqueur2){
					sb.append(points);
					joueurs.get(i).ajouterPoints(points);
					joueurs.get(i+x).ajouterPoints(points);
				} else {
					sb.append("0");
				}
				
				sb.append(" = ");
				sb.append(joueurs.get(i).getScore());
				sb.append(" points");
				System.out.println(sb.toString());
			}		
		}
	}	
	
	/**
	 * Affiche le score final de la partie en fonction du mode de jeu.
	 */
	public void afficherScoreFinal(){
		
		if (this.getMode() == ModeDeJeu.STANDARD || this.getMode() == ModeDeJeu.DEUX_JOUEURS){
			System.out.println("\nLe jeu est terminé ! Voici les scores :\n");
	
			Iterator<Joueur> it = this.getListeJoueurs().iterator();
			while (it.hasNext()) {
				Joueur j = it.next();
				System.out.println(j + " a " + j.getScore() + " points.");
			}
		} else if (this.getMode() == ModeDeJeu.CHALLENGE){
			
			// A la fin d'un mode Challenge, le dernier joueur en jeu est le vainqueur
			Joueur vainqueur = this.getListeJoueurs().get(0);
			
			System.out.println("\nLe jeu est terminé ! Le vainqueur est " + vainqueur + ".");
		} else if (this.getMode() == ModeDeJeu.EQUIPE){
			System.out.println("\nLe jeu est terminé ! Voici les scores :\n");
			
			// Les équipes sont des binomes et sont formées pour faire en sorte que chaque équipe joue les uns à la suite des autres
			// Pour cela, une équipe joue toutes les X fois, X étant la moitié du nombre de joueurs présents, soit le nombre d'équipe
			int x = (this.getListeJoueurs().size() / 2);
			
			for (int i = 0; i < x; i++){
			
				System.out.println(this.getListeJoueurs().get(i) + " et " + this.getListeJoueurs().get(i + x) + " ont " + this.getListeJoueurs().get(i).getScore() + " points.");
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
	 * @return La liste des Joueurs présents dans la Partie, sous forme d'une instance d'{@link ArrayList}.
	 * @see Joueur
	 */
	public ArrayList<Joueur> getListeJoueurs(){
		return this.listeJoueurs;
	}

	/**
	 * Retourne un Joueur en fonction de sa position dans {@link Partie#listeJoueurs}.
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
