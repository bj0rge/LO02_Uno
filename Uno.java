package LO02_Uno;

public class Uno {

	public static void main(String[] args){
		
		// Cr�ation de la partie. Pas n�cessaire, mais je trouve �a plus joli.
		Partie.getInstance();
		
		// G�n�ration des cartes et ajout des joueurs
		Partie.getInstance().debuterPartie();
		
		// Le mode de jeu sera STANDARD
		Partie.getInstance().setMode(ModeDeJeu.STANDARD);
		
	
		Partie.getInstance().deroulementPartie();
		
		Partie.sc.close();
				
	}

}
