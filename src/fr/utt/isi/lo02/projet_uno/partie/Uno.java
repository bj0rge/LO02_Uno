package fr.utt.isi.lo02.projet_uno.partie;

import java.util.Scanner;

/**
 * <b>Crée la Partie et permet de jouer.</b>
 *
 */
public class Uno {
	
	final static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args){
		
		// Création de la partie. Pas nécessaire, mais je trouve ça plus joli.
		Partie.getInstance();
		
		
		System.out.println("--------------------------------------\n"
				+ " *** Bienvenue dans le jeu de Uno *** \n"
				+ "--------------------------------------\n");
		System.out.println("Vous allez débuter une partie.");
		
		// Choix du nb de joueur humain et ia
		Partie.getInstance().selectionNombreJoueur();
		
		// Choix du mode de jeu et des paramètres
		Partie.getInstance().selectionMode();
		
		// Génération des cartes
		Partie.getInstance().debuterPartie();
		
		// Débute une partie
		Partie.getInstance().deroulementPartie();
		
		// Affiche le score de fin de partie
		Partie.getInstance().afficherScoreFinal();
		
		Partie.sc.close();
				
	}

}
