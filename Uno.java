package LO02_Uno;

import java.util.Scanner;

public class Uno {
	
	final static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args){
		
		// Cr�ation de la partie. Pas n�cessaire, mais je trouve �a plus joli.
		Partie.getInstance();
		
		
		System.out.println("--------------------\n"
				+ " *** Bienvenue dans le jeu de Uno ! *** \n"
				+ "--------------------\n");
		System.out.println("Vous allez d�buter une partie. Combien de joueurs voulez-vous ?");
		int nbj = Partie.getInstance().demanderInt();
		System.out.println("Dont combien de joueurs IA ?");
		int nbia = Partie.getInstance().demanderInt();
		
		for (int i = 1; i <= nbj; i++) {
			System.out.println("\nQuel est le nom du joueur " + i + " ?");
			String nom = sc.nextLine();
			if (i <= (nbj - nbia)) {
				Partie.getInstance().ajouterJoueur(new Joueur(nom));
			}
			else {
					Partie.getInstance().ajouterJoueur(new JoueurIA(nom, new Attaquer()));
			}
		}
		
		// G�n�ration des cartes et ajout des joueurs
		Partie.getInstance().debuterPartie();
		
		
		// Le mode de jeu sera STANDARD
		Partie.getInstance().setMode(ModeDeJeu.STANDARD);
		
		
	
		Partie.getInstance().deroulementPartie();
		
		Partie.sc.close();
				
	}

}
