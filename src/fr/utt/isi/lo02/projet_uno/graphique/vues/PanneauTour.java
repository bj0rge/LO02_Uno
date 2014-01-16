package fr.utt.isi.lo02.projet_uno.graphique.vues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utt.isi.lo02.projet_uno.partie.Partie;

/**
 * Panneau du tour de jeu<br>
 * <b>Attention, classe non terminée à l'heure actuelle !</b> Elle lance le jeu de Uno en mode graphique pour la suite.
 */
public class PanneauTour extends PanneauVert {
	private Fenetre fenetre_principale;
	private JLabel lab = new LabelVert("Déroulement", JLabel.CENTER);
	private JButton bouton = new JButton("Lancer la suite du jeu en mode console");
	
	public PanneauTour(Fenetre fen) {
		super();
		this.fenetre_principale = fen;
		this.setLayout(new BorderLayout());
		Font f = new Font("Dialog", Font.BOLD, 24);
		lab.setFont(f);
		lab.setPreferredSize(new Dimension(900, 120));
		this.add(lab, BorderLayout.NORTH);
		
		JPanel jp = new PanneauVert();
		this.add(jp, BorderLayout.CENTER);
		jp.add(bouton);
		bouton.addActionListener(new BoutonListener());
	}
	
	
	private class BoutonListener implements ActionListener {
		// Redéfinition de la méthode actionPerformed()
		public void actionPerformed(ActionEvent arg0) {
			Partie.getInstance().debuterPartie();
			Partie.getInstance().deroulementPartie();
			Partie.getInstance().afficherScoreFinal();
		}
	}

}
