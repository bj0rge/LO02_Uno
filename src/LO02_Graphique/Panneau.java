package LO02_Graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panneau extends JPanel {

	private int posX = -150;
	private int posY = -150;
	
	public void paintComponent(Graphics g) {
		try {
			
			//On choisit une couleur de fond pour le rectangle
		    g.setColor(new Color(0,114,61));
		    //On le dessine de sorte qu'il occupe toute la surface
		    g.fillRect(0, 0, this.getWidth(), this.getHeight());
		    
			Image img = ImageIO.read(new File("images/verso.png"));
			/* g.drawImage(img, (this.getWidth()/2 - (110/2 + 150/2)),
			(this.getHeight()/2 - 175/2), this);
			g.drawImage(img, (this.getWidth()/2 - 130 + 25),
			(this.getHeight()/2 - 175/2), this);
			g.drawImage(img, (this.getWidth()/2 - 130 + 50),
			(this.getHeight()/2 - 175/2), this);
			g.drawImage(img, (this.getWidth()/2 - 130 + 75),
			(this.getHeight()/2 - 175/2), this);
			g.drawImage(img, (this.getWidth()/2 - 130 + 100),
			(this.getHeight()/2 - 175/2), this);
			g.drawImage(img, (this.getWidth()/2 - 130 + 125),
			(this.getHeight()/2 - 175/2), this);
			g.drawImage(img, (this.getWidth()/2 - 130 + 150),
			(this.getHeight()/2 - 175/2), this); */
			g.drawImage(img, posX, posY, this);
			// Pour une image de fond
			// g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}