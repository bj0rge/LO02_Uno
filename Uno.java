package LO02_Uno;

public class Uno {

	public static void main(String[] args){
		
		// Création de la partie. Pas nécessaire, mais je trouve ça plus joli.
		Partie.getInstance();
		
		// Génération des cartes et ajout des joueurs
		Partie.getInstance().debuterPartie();
		
		// Le mode de jeu sera STANDARD
		Partie.getInstance().setMode(ModeDeJeu.STANDARD);
		
	
		Partie.getInstance().deroulementPartie();
		
		Partie.sc.close();
				
	}

}
