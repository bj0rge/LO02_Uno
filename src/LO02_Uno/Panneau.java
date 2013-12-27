package LO02_Uno;

import java.awt.Graphics;
import javax.swing.JPanel;

public class Panneau extends JPanel {
	public void paintComponent(Graphics g) {
		int TAILLE = 80;
		int x1 = (this.getWidth() / 2) - (TAILLE/2);
		int y1 = (this.getHeight() / 2) - (TAILLE / 2);
		g.fillOval(x1, y1, TAILLE, TAILLE);
	}
}