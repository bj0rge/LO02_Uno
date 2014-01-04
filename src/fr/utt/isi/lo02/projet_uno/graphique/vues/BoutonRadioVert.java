package fr.utt.isi.lo02.projet_uno.graphique.vues;

import java.awt.Color;

import javax.swing.JRadioButton;

public class BoutonRadioVert extends JRadioButton {
	public static final Color VERT_TAPIS = PanneauVert.VERT_TAPIS;
	public BoutonRadioVert(String str) {
		super(str);
		this.setBackground(VERT_TAPIS);
		this.setForeground(Color.WHITE);
	}
}
