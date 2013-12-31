package LO02_Graphique;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Fenetre extends JFrame implements ActionListener {

	private Panneau pan = new Panneau();
	private JButton bouton = new JButton("Clic count");
	private JButton bouton2 = new JButton("Joli bouton");
	private JPanel container = new JPanel();
	private JLabel label = new JLabel("Trololo !");
	private int compteur = 0;

	public Fenetre() {
		this.setTitle("UNO");
		this.setSize(400, 500);
		this.setLocationRelativeTo(null); // Pour centrer la JFrame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pour terminer le processus en fermant la fenêtre
		
		
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
	    container.add(pan, BorderLayout.CENTER);
	    
	    bouton.addActionListener(this);
	    bouton2.addActionListener(this);
	    
	    JPanel bouton_pan = new JPanel();
	    bouton_pan.add(bouton);
	    bouton_pan.add(bouton2);
	    
	    container.add(bouton_pan, BorderLayout.NORTH);
	    
	    container.add(label, BorderLayout.SOUTH);	
	    
	    Font police = new Font("Tahoma", Font.BOLD, 16);
	    label.setFont(police);
	    label.setForeground(new Color(35,31,32));
	    label.setHorizontalAlignment(JLabel.CENTER);
	    
	    
	    
		this.setContentPane(container); // On prévient notre JFrame que notre container sera son content pane
		this.setVisible(true);
		go();
	}

	private void go() {
		int x = pan.getPosX(), y = pan.getPosY();
		boolean backX = false;
		boolean backY = false;
		
		while (true) {
			// Si la coordonnée x est inférieure à 1, on avance
			if (x < 1)
				backX = false;
			// Si la coordonnée x est supérieure à la taille du Panneau moins la
			// taille de l'image, on recule
			if (x > pan.getWidth() - 110)
				backX = true;
			// Idem pour l'axe y
			if (y < 1)
				backY = false;
			if (y > pan.getHeight() - 175)
				backY = true;

			// Si on avance, on incrémente la coordonnée
			if (!backX)
				pan.setPosX(++x);
			// Sinon, on décrémente
			else
				pan.setPosX(--x);
			// Idem pour l'axe Y
			if (!backY)
				pan.setPosY(++y);
			else
				pan.setPosY(--y);

			// On redessine notre Panneau
			pan.repaint();
			// Comme on dit : la pause s'impose ! Ici, trois millièmes de
			// seconde
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == bouton) {
			this.compteur++;
			label.setText("Vous avez cliqué " + this.compteur + " fois sur le compteur");
		}
		if(arg0.getSource() == bouton2) {
			label.setText("Vous avez cliqué sur le joli bouton");
		}
	}
}