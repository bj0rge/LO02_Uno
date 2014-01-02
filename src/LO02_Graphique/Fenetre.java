package LO02_Graphique;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Fenetre extends JFrame {

	private JPanel pan = new PanneauDebutJeu(this); // JPanel de l'action en cours
	private JPanel container = new JPanel(); // JPanel principal

	public Fenetre() {
		this.setTitle("UNO");
		this.setSize(400, 500);
		this.setLocationRelativeTo(null); // Pour centrer la JFrame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pour terminer le processus en fermant la fenêtre
		
		container.setLayout(new BorderLayout()); // On définit le système de positionnement en BorderLayout
	    container.add(pan, BorderLayout.CENTER); // Et on ajoute 
	    
		this.setContentPane(container); // On prévient notre JFrame que notre container sera son content pane
		this.setVisible(true);

	}
	
	public void switchPan() {
		if (pan.getClass() == PanneauDebutJeu.class) {
			pan = new PanneauSelectionOptions();
		}
		container.setLayout(new BorderLayout());
	    container.add(pan, BorderLayout.CENTER);
	    this.setContentPane(container);
	}


	public static void main(String args[]) {
		Fenetre f = new Fenetre();
	}

}