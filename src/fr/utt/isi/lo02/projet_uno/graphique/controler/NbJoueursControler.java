package fr.utt.isi.lo02.projet_uno.graphique.controler;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;
import fr.utt.isi.lo02.projet_uno.enumeration.ModeDeJeu;
import fr.utt.isi.lo02.projet_uno.graphique.observer.Observable;
import fr.utt.isi.lo02.projet_uno.graphique.observer.Observer;
import fr.utt.isi.lo02.projet_uno.joueur.Joueur;
import fr.utt.isi.lo02.projet_uno.joueur.JoueurIA;
import fr.utt.isi.lo02.projet_uno.partie.Partie;
import fr.utt.isi.lo02.projet_uno.strategy.JouerChiffres;

/**
 * <b>NbJoueursControler est la classe contrôlant l'option du nombre de joueurs. Mais elle doit être codée avec les pieds.</b>
 * <p>
 * Le controler est caractérisé par :
 * <ul>
 * <li>Le nombre de joueurs humains à implémenter</li>
 * <li>Le nombre de joueurs ia à implémenter</li>
 * <li>Une liste des Observers qui observent</li>
 * <li>L'instance de Partie</li>
 * </ul>
 * </p>
 * @see Partie
 */
public class NbJoueursControler implements Observable {

	/**
	 * Représente un nombre de joueurs (humain ou artificiel).
	 */
	private int nbhumains = 1, nbia = 3;
	
	/**
	 * Liste d'observateurs, sous forme d'ArrayList.
	 */
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	/**
	 * L'instance de la partie.
	 */
	private Partie partie = Partie.getInstance();

	
	// Implémentation du pattern observer
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}
	
	
	public void notifyObserver(String str) {
		boolean update_ok = (str.compareTo("ok") == 0) ? true : false;
		for (Observer obs : listObserver)
			obs.update(update_ok);
	}

	
	public void removeObserver() {
		listObserver = new ArrayList<Observer>();
	}
	
	/**
	 * Action de contrôle qui permet de faire clignoter le message en cas
	 * d'erreur de nombre, et qui crée les joueurs le cas échéant. 
	 */
	public void control() {
		if (!(nbhumains + nbia > 10 || nbhumains + nbia < 2)) {
			// Remise à 0 de la liste si le lanceur s'est planté et veut recommencer
			partie.setListeJoueurs(new ArrayList<Joueur>());
			for (int i = 0; i < nbhumains; i++) {
				DialogBoxUsername perso = new DialogBoxUsername(true, i+1);
				partie.ajouterJoueur(new Joueur(perso.getNom()));
			}
			for (int i = 0; i < nbia; i++) {
				DialogBoxUsername perso = new DialogBoxUsername(false, i+1+nbhumains);
				partie.ajouterJoueur(new JoueurIA(perso.getNom(), new JouerChiffres()));
			}
		}
		notifyObserver((nbhumains + nbia > 10 || nbhumains + nbia < 2) ? "ko" : "ok");
	}

	private class DialogBoxUsername {
		
		ImageIcon icon;
		String nom;

		public DialogBoxUsername(boolean est_humain, int num) {
			icon = new ImageIcon(
					(est_humain) ? "images/human_black.png"
							: "images/ai_black.png");
			String nouveau_nom = (String)JOptionPane.showInputDialog(null,
					"Quel est le nom du joueur " + num + " ?",
					"Nouveau joueur " + (est_humain ? "humain" : "artificiel"),
					JOptionPane.QUESTION_MESSAGE,
					icon, 
					null,
					null);

			this.nom = nouveau_nom;
		}

		public String getNom() {
			return this.nom;
		}
	}
	
	
	/**
	 * Retourne le nombre de joueurs humains.
	 * @return Un entier, qui correspond au nombre de joueurs humains.
	 */
	public int getNbhumains() {
		return nbhumains;
	}

	/**
	 * Met à jour le nombre de joueurs humains.
	 * @param nbhumains
	 * 			Le nouveau nombre de joueurs.
	 */
	public void setNbhumains(int nbhumains) {
		this.nbhumains = nbhumains;
	}
	
	/**
	 * Retourne le nombre de joueurs ia.
	 * @return Un entier, qui correspond au nombre de joueurs ia.
	 */
	public int getNbia() {
		return nbia;
	}

	/**
	 * Met à jour le nombre de joueurs ia.
	 * @param nbia
	 * 			Le nouveau nombre de joueurs ia.
	 */
	public void setNbia(int nbia) {
		this.nbia = nbia;
	}
}
