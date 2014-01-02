package LO02_Graphique;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanneauSelectionOptions extends JPanel {
	private JPanel pan_opt = new PanneauOptions();

	public PanneauSelectionOptions() {
		this.setLayout(new BorderLayout());
		this.add(pan_opt, BorderLayout.CENTER);
	}

	private class PanneauOptions extends JPanel {
		public PanneauOptions() {
			this.setBackground(new Color(0, 114, 61));
			JLabel lab = new JLabel("Pouet");
		}
	}

}
