package LO02_Graphique;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Fenetre extends JFrame {
	
	private CardLayout cl = new CardLayout(); // CardLayout, permet d'afficher diff�rents jpanels successifs
	private JPanel container = new PanneauVert(); // JPanel principal
	private String[] listContent = {"Accueil", "Options"};
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
		switch(num) {
		case 0:
			cl.show(container, listContent[1]);
			break;
		case 1:
			System.out.println("Ouiiiii, �a marche !");
			break;
		}
		
	}

	public static void main(String args[]) {
		Fenetre f = new Fenetre();
	}
}