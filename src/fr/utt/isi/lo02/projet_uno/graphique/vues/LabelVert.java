package fr.utt.isi.lo02.projet_uno.graphique.vues;

import java.awt.Color;

import javax.swing.JLabel;

/**
 * <b>LabelVert est la classe personnalisée d'un JLabel.</b>
 */
public class LabelVert extends JLabel{
	
	/**
	 * Constante permettant d'avoir une couleur vert tapis
	 */
	public static final Color VERT_TAPIS = PanneauVert.VERT_TAPIS;

	/**
	 * Constructeur du LabelVert
	 */
	public LabelVert() {
		super();
		this.setOpaque(true); // Nécessaire pour changer le bg
		this.setBackground(VERT_TAPIS);
		this.setForeground(Color.WHITE);
	}
	

	/**
	 * Constructeur du LabelVert
	 * @param text
	 * 			Valeur du Label
	 */
	public LabelVert(String text) {
		super(text);
		this.setOpaque(true);
		this.setBackground(VERT_TAPIS);
		this.setForeground(Color.WHITE);
	}
	
	/**
	 * Constructeur du LabelVert
	 * @param text
	 * 			Valeur du Label
	 * @param horizontalAlignment
	 * 			Alignement du label
	 */
	public LabelVert(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		this.setOpaque(true);
		this.setBackground(VERT_TAPIS);
		this.setForeground(Color.WHITE);
	}
	
	
	
}
