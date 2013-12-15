package LO02_Uno;

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
		
		
		System.out.println("--------------------\n"
				+ " *** Bienvenue dans le jeu de Uno ! *** \n"
				+ "--------------------\n");
		System.out.println("Vous allez d�buter une partie.");
		
		Partie.getInstance().selectionNombreJoueur();
		
		if (Partie.getInstance().getMode() != ModeDeJeu.DEUX_JOUEURS){
			Partie.getInstance().selectionMode();
		}
		
		// G�n�ration des cartes
		Partie.getInstance().debuterPartie();
		
		Partie.getInstance().deroulementPartie();
		
		Partie.sc.close();
				
	}

}
