package fr.utt.isi.lo02.projet_uno.graphique.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanneauDebutJeu extends PanneauVert {
	private JButton bouton = new JButton("Jouer");
	private JPanel pan_acc = new PanneauAccueil();
	private JLabel lab = new LabelVert("Bienvenue dans le jeu de UNO développé par Bastien Jorge et Alexander Proux", JLabel.CENTER);
	private Fenetre fenetre_principale;

	public PanneauDebutJeu(Fenetre fen) {
		this.fenetre_principale = fen;
		this.setLayout(new BorderLayout()); // On définit le système de positionnement en BorderLayout
		this.add(pan_acc, BorderLayout.CENTER); // Et on ajoute
		
		Font f = new Font("Dialog", Font.BOLD, 24); // On change la taille de la fonte
		bouton.setFont(f);
		bouton.setEnabled(true);
		bouton.addActionListener(new BoutonListener());
		
		JPanel jp = new PanneauVert();
	    jp.add(bouton);
	    this.add(jp, BorderLayout.SOUTH);
	    
	    this.add(lab, BorderLayout.NORTH);
		Font f2 = new Font("Dialog", Font.BOLD, 24);
		lab.setFont(f2);
		lab.setPreferredSize(new Dimension(120, 120));
	}

	private class PanneauAccueil extends JPanel {
		public void paintComponent(Graphics g) {
			try {
				// On choisit une couleur de fond pour le rectangle
//				g.setColor(Panneau.VERT_TAPIS);
				// On le dessine de sorte qu'il occupe toute la surface
//				g.fillRect(0, 0, this.getWidth(), this.getHeight());

				Image img = ImageIO.read(new File("images/logo_uno.png"));
				g.drawImage(img, (this.getWidth() / 2 - (350 / 2)),
						(this.getHeight() / 2 - (300 / 2)), this);

				// Pour une image de fond
				// g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(),
				// this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// Classe écoutant notre bouton
	private class BoutonListener implements ActionListener {
		// Redéfinition de la méthode actionPerformed()
		public void actionPerformed(ActionEvent arg0) {
			fenetre_principale.switchPan(0);
		}
	}
}