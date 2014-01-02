package LO02_Graphique;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Fenetre extends JFrame {

	private CardLayout cl = new CardLayout(); // CardLayout, permet d'afficher diff�rents jpanels successifs
	private JPanel container = new JPanel(); // JPanel principal
	private String[] listContent = {"Accueil", "Options"};
	private ArrayList<JPanel> cards = new ArrayList<JPanel>();

	public Fenetre() {
		this.setTitle("UNO");
		this.setSize(400, 500);
		this.setLocationRelativeTo(null); // Pour centrer la JFrame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pour terminer le processus en fermant la fen�tre
		
		// On cr�e les conteneurs des diff�rentes fen�tres
		cards.add(new PanneauDebutJeu(this)); // JPanel de l'action en cours
		cards.add(new PanneauSelectionOptions()); // JPanel de s�lection des options
		
		
		// On d�finit le syst�me de positionnement avec le CardLayer
		container.setLayout(cl);
		
		// Pour chaque card
		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).setName(listContent[i]);			// On lui donne un nom
			container.add(cards.get(i), listContent[i]);	// Et on l'ajoute � la liste de container
			i++;
		}
	    
		this.setContentPane(container); // On pr�vient notre JFrame que notre container sera son content pane
		this.setVisible(true);

	}
	
	public void switchPan(Class c) {
		if (c == PanneauDebutJeu.class) {
			cl.show(container, listContent[0]);
		}
	}

	public static void main(String args[]) {
		Fenetre f = new Fenetre();
	}
}