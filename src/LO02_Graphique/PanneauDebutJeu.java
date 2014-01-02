package LO02_Graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PanneauDebutJeu extends JPanel {
	private JButton bouton = new JButton("Jouer");
	private JPanel pan_acc = new PanneauAccueil();
	private Fenetre fenetre_principale;

	public PanneauDebutJeu(Fenetre fen) {
		this.fenetre_principale = fen;
		this.setLayout(new BorderLayout()); // On définit le système de positionnement en BorderLayout
		this.add(pan_acc, BorderLayout.CENTER); // Et on ajoute
		
		
		bouton.setEnabled(true);
		bouton.addActionListener(new BoutonListener());
	    this.add(bouton, BorderLayout.SOUTH);
	}

	private class PanneauAccueil extends JPanel {
		public void paintComponent(Graphics g) {
			try {
				// On choisit une couleur de fond pour le rectangle
				g.setColor(new Color(0, 114, 61));
				// On le dessine de sorte qu'il occupe toute la surface
				g.fillRect(0, 0, this.getWidth(), this.getHeight());

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
	class BoutonListener implements ActionListener {
		// Redéfinition de la méthode actionPerformed()
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("On vient de me cliquer !");
			fenetre_principale.switchPan();
		}
	}
}