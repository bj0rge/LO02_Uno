package fr.utt.isi.lo02.projet_uno.graphique.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utt.isi.lo02.projet_uno.graphique.controler.NbJoueursControler;
import fr.utt.isi.lo02.projet_uno.graphique.observer.Observable;
import fr.utt.isi.lo02.projet_uno.graphique.observer.Observer;


public class PanneauSelectionOptions extends PanneauVert implements Observer {
	private JPanel pan_opt = new PanneauOptions();
	private JLabel lab = new LabelVert("Sélection des options", JLabel.CENTER);
	private Fenetre fenetre_principale;
	private int nbhumains = 1, nbia = 3;
	
	// L'instance de l'objet controlleur
	private NbJoueursControler controler = new NbJoueursControler();
	
	public PanneauSelectionOptions(Fenetre fen) {
		// On ajoute l'observer au controler
		controler.addObserver(this);
		
		this.fenetre_principale = fen;
		this.setLayout(new BorderLayout());
		this.add(pan_opt, BorderLayout.CENTER);
		this.add(lab, BorderLayout.NORTH);
		Font f = new Font("Dialog", Font.BOLD, 24);
		lab.setFont(f);
		lab.setPreferredSize(new Dimension(120, 120));
	}

	public Observable getControler() {
		return controler;
	}
	
	
	
	private class PanneauOptions extends PanneauVert {
		private JPanel opt = new PanneauVert(); // Contient les différentes options possibles
		private JPanel opt_container = new PanneauVert();
		private JLabel attention_label = new LabelVert("Attention : ne dépassez pas un total de 10 joueurs - Veillez à avoir au moins 2 joueurs");
		
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
			JPanel val_panel = new PanneauVert();

			
			val_panel.add(attention_label);
			val_panel.add(val_button);
			opt_container.add(val_panel, BorderLayout.SOUTH);
		}
		
		public void setColorAttentionLabel(Color c) {
			attention_label.setForeground(c);
		}
		public Color getColorAttentionLabel() {
			return attention_label.getForeground();
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
			
			combo.setPreferredSize(new Dimension(40, 20));
			for (int i = 0; i <= 10; i++) {
				combo.addItem(i);
			}
			combo.setSelectedIndex(defaut);
			jp.add(combo);
			combo.addActionListener((type == "IA") ? new ListListenerIA() : new ListListenerHumains());
			
			this.add(jp, BorderLayout.CENTER);			
		}
	}
	
	
	
	
	// Classe écoutant notre bouton
	private class BoutonListener implements ActionListener {
		// Redéfinition de la méthode actionPerformed()
		public void actionPerformed(ActionEvent arg0) {
			controler.setNbhumains(nbhumains);
			controler.setNbia(nbia);			
			controler.control();
		}
	}
	
	private class ListListenerHumains implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			nbhumains = (int) (((JComboBox<Integer>)arg0.getSource()).getSelectedItem());
		}
	}
	
	private class ListListenerIA implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			nbia = (int) (((JComboBox<Integer>)arg0.getSource()).getSelectedItem());
		}
	}
	
	
	
	
	@Override
	public void update(boolean controle_ok) {
		if (controle_ok) {
			System.out.println("On passe à la suite !");
		}
		else {
			// On fait clignoter le label
			TimerTask task = new TimerTask() {
				private int iterator = 0;
					@Override
					public void run() {
						((PanneauOptions) pan_opt).setColorAttentionLabel((((PanneauOptions) pan_opt).getColorAttentionLabel() == Color.RED) ? Color.WHITE : Color.RED);
						if (iterator < 3) {
							iterator++;
						} else {
							this.cancel();
						}
					}
				};
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(task, 0, 200);
		}
	}

	
	
}