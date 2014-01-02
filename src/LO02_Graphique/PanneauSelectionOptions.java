package LO02_Graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanneauSelectionOptions extends JPanel {
	private JPanel pan_opt = new PanneauOptions();

	public PanneauSelectionOptions() {
		this.setLayout(new BorderLayout());
		this.add(pan_opt, BorderLayout.CENTER);
		
		JLabel lab = new JLabel("Pouet");
		this.add(lab, BorderLayout.SOUTH);
	}

	private class PanneauOptions extends JPanel {
		public PanneauOptions() {
			this.setBackground(new Color(0, 114, 61));
		}
	}
}
