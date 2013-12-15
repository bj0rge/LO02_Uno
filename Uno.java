package LO02_Uno;

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
		
		
		System.out.println("--------------------\n"
				+ " *** Bienvenue dans le jeu de Uno ! *** \n"
				+ "--------------------\n");
		System.out.println("Vous allez débuter une partie.");
		
		Partie.getInstance().selectionNombreJoueur();
		
		if (Partie.getInstance().getMode() != ModeDeJeu.DEUX_JOUEURS){
			Partie.getInstance().selectionMode();
		}
		
		// Génération des cartes
		Partie.getInstance().debuterPartie();
		
		Partie.getInstance().deroulementPartie();
		
		Partie.sc.close();
				
	}

}
