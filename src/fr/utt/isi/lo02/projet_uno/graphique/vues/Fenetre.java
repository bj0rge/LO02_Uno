package fr.utt.isi.lo02.projet_uno.graphique.vues;

import javax.swing.*;

import fr.utt.isi.lo02.projet_uno.graphique.controler.NbJoueursControler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Fenetre extends JFrame {
	
	public static final int DEBUT_JEU = 0, SELECTION_OPTIONS = 1, TOUR = 2;
	private CardLayout cl = new CardLayout(); // CardLayout, permet d'afficher diff�rents jpanels successifs
	private JPanel container = new PanneauVert(); // JPanel principal
	private String[] listContent = {"Accueil", "Options", "Tour"};
	private ArrayList<JPanel> cards = new ArrayList<JPanel>();

	public Fenetre() {
		this.setTitle("UNO");
		this.setLocationRelativeTo(null); // Pour centrer la JFrame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pour terminer le processus en fermant la fen�tre
		
		// Passage en plein �cran
		GraphicsDevice myDevice = java.awt.GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (myDevice.isFullScreenSupported()) {
				myDevice.setFullScreenWindow(this);
		} 
		else {
			this.setSize(400, 500);
		}
		
		
		// On cr�e les conteneurs des diff�rentes fen�tres
		cards.add(new PanneauDebutJeu(this)); // JPanel de l'action en cours
		cards.add(new PanneauSelectionOptions(this)); // JPanel de s�lection des options
		cards.add(new PanneauTour(this)); // JPanel de tour de jeu
		
		// On d�finit le syst�me de positionnement avec le CardLayer
		container.setLayout(cl);
		
		// Pour chaque card
		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).setName(listContent[i]);			// On lui donne un nom
			container.add(cards.get(i), listContent[i]);	// Et on l'ajoute � la liste de container
		}
	    
		this.setContentPane(container); // On pr�vient notre JFrame que notre container sera son content pane
		this.setVisible(true);

	}
	
	public void switchPan(int num) {		
		cl.show(container, listContent[num]);		
	}

	public static void main(String args[]) {
		Fenetre f = new Fenetre();
	}
}