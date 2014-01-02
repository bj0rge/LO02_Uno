package LO02_Uno;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;

;

public class Fenetre extends JFrame {

	public Fenetre() {
		this.setTitle("UNO");
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);						// Pour centrer la JFrame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Pour terminer le processus en fermant la fenêtre

		this.setContentPane(new Panneau());	// On prévient notre JFrame que notre JPanel sera son content pane
		this.setVisible(true);
	}
}