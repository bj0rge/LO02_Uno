package fr.utt.isi.lo02.projet_uno.partie;

import java.util.Scanner;

/**
 * <b>Cr�e la Partie et permet de jouer.</b>
 *
 */
public class Uno {
	
	final static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args){
		
		// Cr�ation de la partie. Pas n�cessaire, mais je trouve �a plus joli.
		Partie.getInstance();
		
		
		System.out.println("--------------------------------------\n"
				+ " *** Bienvenue dans le jeu de Uno *** \n"
				+ "--------------------------------------\n");
		System.out.println("Vous allez d�buter une partie.");
		
		// Choix du nb de joueur humain et ia
		Partie.getInstance().selectionNombreJoueur();
		
		// Choix du mode de jeu et des param�tres
		Partie.getInstance().selectionMode();
		
		// G�n�ration des cartes
		Partie.getInstance().debuterPartie();
		
		// D�bute une partie
		Partie.getInstance().deroulementPartie();
		
		// Affiche le score de fin de partie
		Partie.getInstance().afficherScoreFinal();
		
		Partie.sc.close();
				
	}

}
