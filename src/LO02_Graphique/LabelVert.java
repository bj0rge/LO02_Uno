package LO02_Graphique;

import java.awt.Color;

import javax.swing.JLabel;

public class LabelVert extends JLabel{
	public static final Color VERT_TAPIS = PanneauVert.VERT_TAPIS;

	public LabelVert() {
		super();
		this.setOpaque(true); // Nécessaire pour changer le bg
		this.setBackground(VERT_TAPIS);
		this.setForeground(Color.WHITE);
	}
	

	
	public LabelVert(String text) {
		super(text);
		this.setOpaque(true);
		this.setBackground(VERT_TAPIS);
		this.setForeground(Color.WHITE);
	}
	
	public LabelVert(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		this.setOpaque(true);
		this.setBackground(VERT_TAPIS);
		this.setForeground(Color.WHITE);
	}
	
	
	
}
