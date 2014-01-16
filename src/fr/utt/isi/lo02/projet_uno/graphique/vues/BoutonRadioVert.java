package fr.utt.isi.lo02.projet_uno.graphique.vues;

import java.awt.Color;

import javax.swing.JRadioButton;

/**
 * <b>BoutonRadioVert est la classe personnalisée d'un JRadioButton.</b>
 */
public class BoutonRadioVert extends JRadioButton {
	
	/**
	 * Constante permettant d'avoir une couleur vert tapis
	 */
	public static final Color VERT_TAPIS = PanneauVert.VERT_TAPIS;
	
	/**
	 * Constructeur du BoutonRadio
	 * @param str
	 * 			Label du BoutonRadio
	 */
	public BoutonRadioVert(String str) {
		super(str);
		this.setBackground(VERT_TAPIS);
		this.setForeground(Color.WHITE);
	}
}
