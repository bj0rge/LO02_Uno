package fr.utt.isi.lo02.projet_uno.graphique.controler;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.utt.isi.lo02.projet_uno.graphique.observer.Observable;
import fr.utt.isi.lo02.projet_uno.graphique.observer.Observer;
import fr.utt.isi.lo02.projet_uno.joueur.Joueur;
import fr.utt.isi.lo02.projet_uno.joueur.JoueurIA;
import fr.utt.isi.lo02.projet_uno.partie.Partie;
import fr.utt.isi.lo02.projet_uno.strategy.JouerChiffres;

public class NbJoueursControler implements Observable {

	private int nbhumains = 1, nbia = 3;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
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
	
	public void control() {
		if (!(nbhumains + nbia > 10 || nbhumains + nbia < 2)) {
			
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
		// JPanel panIcon = new JPanel();
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
			// panIcon.setLayout(new BorderLayout());
			// panIcon.add(icon);
			this.nom = nouveau_nom;
		}

		public String getNom() {
			return this.nom;
		}
	}
	
	

	public int getNbhumains() {
		return nbhumains;
	}

	public void setNbhumains(int nbhumains) {
		this.nbhumains = nbhumains;
	}

	public int getNbia() {
		return nbia;
	}

	public void setNbia(int nbia) {
		this.nbia = nbia;
	}
}
