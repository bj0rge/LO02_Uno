package LO02_Uno;

import java.util.Scanner;

public class Uno {
	
	final static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args){
		
		// Création de la partie. Pas nécessaire, mais je trouve ça plus joli.
		Partie.getInstance();
		
		
		System.out.println("--------------------\n"
				+ "Bienvenue dans le jeu de Uno !\n"
				+ "--------------------\n");
		System.out.println("Vous allez débuter une partie. Combien de joueurs humains voulez-vous ?");
		int nbj = Partie.getInstance().demanderInt();
		
		for (int i = 1; i <= nbj; i++) {
			System.out.println("\nQuel est le nom du joueur " + i + " ?");
			String nom = sc.nextLine();
			Partie.getInstance().ajouterJoueur(new Joueur((nom)));
		}
		Partie.getInstance().ajouterJoueur(new JoueurIA("Robocoop", new JouerChiffres()));
		
		// Génération des cartes et ajout des joueurs
		Partie.getInstance().debuterPartie();
		
		
		// Le mode de jeu sera STANDARD
		Partie.getInstance().setMode(ModeDeJeu.STANDARD);
		
	
		Partie.getInstance().deroulementPartie();
		
		Partie.sc.close();
				
	}

}
