package LO02_Graphique;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import java.awt.Dimension;

public class Fenetre extends JFrame {

	private Panneau pan = new Panneau();
	private JButton bouton = new JButton("Autodestruction");
	private JPanel container = new JPanel();

	public Fenetre() {
		this.setTitle("UNO");
		this.setSize(400, 500);
		this.setLocationRelativeTo(null); // Pour centrer la JFrame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pour terminer le processus en fermant la fen�tre
		container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    container.add(pan, BorderLayout.CENTER);
	    container.add(bouton, BorderLayout.NORTH);
	    
		this.setContentPane(container); // On pr�vient notre JFrame que notre JPanel sera son content pane
		this.setVisible(true);
		go();
	}

	private void go() {
		int x = pan.getPosX(), y = pan.getPosY();
		boolean backX = false;
		boolean backY = false;
		
		while (true) {
			// Si la coordonn�e x est inf�rieure � 1, on avance
			if (x < 1)
				backX = false;
			// Si la coordonn�e x est sup�rieure � la taille du Panneau moins la
			// taille de l'image, on recule
			if (x > pan.getWidth() - 110)
				backX = true;
			// Idem pour l'axe y
			if (y < 1)
				backY = false;
			if (y > pan.getHeight() - 175)
				backY = true;

			// Si on avance, on incr�mente la coordonn�e
			if (!backX)
				pan.setPosX(++x);
			// Sinon, on d�cr�mente
			else
				pan.setPosX(--x);
			// Idem pour l'axe Y
			if (!backY)
				pan.setPosY(++y);
			else
				pan.setPosY(--y);

			// On redessine notre Panneau
			pan.repaint();
			// Comme on dit : la pause s'impose ! Ici, trois milli�mes de
			// seconde
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}