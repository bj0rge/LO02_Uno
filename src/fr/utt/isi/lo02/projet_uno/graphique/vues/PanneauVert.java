package fr.utt.isi.lo02.projet_uno.graphique.vues;

import java.awt.Color;
import javax.swing.JPanel;

/**
 * <b>PanneauVert est la classe personnalisée d'un JPanel.</b>
 */
public class PanneauVert extends JPanel {
	/**
	 * Constante permettant d'avoir une couleur vert tapis
	 */
	public static final Color VERT_TAPIS = new Color(0, 114, 61);
	
	/**
	 * Constructeur du PanneauVert
	 */
	public PanneauVert() {
		super();
		this.setBackground(VERT_TAPIS);
	}

}
