package LO02_Graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanneauSelectionOptions extends PanneauVert {
	private JPanel pan_opt = new PanneauOptions();
	private JLabel lab = new LabelVert("Sélection des options", JLabel.CENTER);
	private Fenetre fenetre_principale;
	
	public PanneauSelectionOptions(Fenetre fen) {
		this.fenetre_principale = fen;
		this.setLayout(new BorderLayout());
		this.add(pan_opt, BorderLayout.CENTER);
		this.add(lab, BorderLayout.NORTH);
		Font f = new Font("Dialog", Font.BOLD, 24);
		lab.setFont(f);
		lab.setPreferredSize(new Dimension(120, 120));
	}

	private class PanneauOptions extends PanneauVert {
		private JPanel opt = new PanneauVert(); // Contient les différentes options possibles
		private JPanel opt_container = new PanneauVert();
		
		public PanneauOptions() {
			JPanel jhumains = new OptPan("humans", "humains", 1);
			JPanel jia = new OptPan("ai", "IA", 3);
			this.add(opt_container);
			this.setPreferredSize(new Dimension(550, 300));
			opt_container.setLayout(new BorderLayout());
			
			opt_container.add(opt, BorderLayout.CENTER);
			opt.add(jhumains);
			opt.add(jia);
			
			JButton val_button = new JButton("Valider");
			val_button.addActionListener(new BoutonListener());
			JLabel attention_label = new LabelVert("Attention : ne dépassez pas un total de 10 joueurs");
			JPanel val_panel = new PanneauVert();
			
			val_panel.add(attention_label);
			val_panel.add(val_button);
			opt_container.add(val_panel, BorderLayout.SOUTH);
			
		}
	}
	
	private class OptPan extends PanneauVert {
		private JLabel imglab = new LabelVert();
		private JLabel label = new LabelVert();
		private JComboBox<Integer> combo = new JComboBox<Integer>();
		private JPanel jp = new PanneauVert();
		
		public OptPan(String img, String type, int defaut) {
			this.setPreferredSize(new Dimension(250, 200));
			this.setLayout(new BorderLayout());
			
			imglab.setHorizontalAlignment(JLabel.CENTER);
			imglab.setIcon(new ImageIcon("images/" + img + ".png"));
			this.add(imglab, BorderLayout.NORTH);
			
			label.setText("Nombre de joueurs " + type + " : ");
			label.setHorizontalTextPosition(JLabel.LEFT);
			jp.add(label);
			
			combo.setPreferredSize(new Dimension(35, 20));
			for (int i = 0; i <= 10; i++) {
				combo.addItem(i);
			}
			combo.setSelectedIndex(defaut);
			jp.add(combo);
			
			this.add(jp, BorderLayout.CENTER);			
		}
	}
	
	// Classe écoutant notre bouton
	private class BoutonListener implements ActionListener {
		// Redéfinition de la méthode actionPerformed()
		public void actionPerformed(ActionEvent arg0) {
			fenetre_principale.switchPan(1);
		}
	}
}