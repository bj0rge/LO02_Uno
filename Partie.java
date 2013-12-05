package LO02_Uno;

import java.util.ArrayList;

public class Partie 
{
	
	// Attributs
	private static Partie partie;
	private ModeDeJeu mode;
	private ArrayList<Joueur> listeJoueurs;
	private int nb_pts_max;
	private int nb_manches_max;
	private int manche;
	
	// Constructeur
	private Partie(ModeDeJeu mode, int nb_joueurs_humains, int difficulte[], int nb_ia, int nb_pts_max, int nb_tours_max){
		this.mode = mode;
		
	}
	
	
	public ArrayList<Joueur> getListeJoueurs(){
		return this.listeJoueurs;
	}
	
	
	
//	public static void main(String[] args){
//		
//	}
}
