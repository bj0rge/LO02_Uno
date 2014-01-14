package fr.utt.isi.lo02.projet_uno.partie;

import java.util.Scanner;

/**
 * <b>Uno sert à débuter une partie en ligne de commande.</b>
 * <p>Il crée la Partie et permet de jouer.</p>
 */
public class Uno {
	
	final static Scanner sc = new Scanner(System.in);
	
	/**
	 * Méthode principale pour lancer la partie.
	 * @param args
	 */
	public static void main(String[] args){
		
		// Création de la partie.
		Partie.getInstance();
		
		
		System.out.println("--------------------------------------\n"
				+ " *** Bienvenue dans le jeu de Uno *** \n"
				+ "--------------------------------------\n");
		System.out.println("Vous allez débuter une partie.");
		
		// Choix du nombre de joueur humain et ia
		Partie.getInstance().selectionNombreJoueur();
		
		// Choix du mode de jeu et des paramètres
		Partie.getInstance().selectionMode();
		
		// Génération des cartes
		Partie.getInstance().debuterPartie();
		
		// Débuter une partie
		Partie.getInstance().deroulementPartie();
		
		// Affiche le score de fin de partie
		Partie.getInstance().afficherScoreFinal();
		
		Partie.sc.close();
				
	}

}
