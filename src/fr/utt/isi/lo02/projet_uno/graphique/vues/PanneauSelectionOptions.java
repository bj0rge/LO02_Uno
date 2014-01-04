package fr.utt.isi.lo02.projet_uno.graphique.vues;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import fr.utt.isi.lo02.projet_uno.enumeration.ModeDeJeu;
import fr.utt.isi.lo02.projet_uno.graphique.controler.NbJoueursControler;
import fr.utt.isi.lo02.projet_uno.graphique.observer.Observable;
import fr.utt.isi.lo02.projet_uno.graphique.observer.Observer;
import fr.utt.isi.lo02.projet_uno.partie.Partie;


public class PanneauSelectionOptions extends PanneauVert implements Observer {
	private CardLayout cl = new CardLayout();
	private String[] listContent = {"Nbj", "Modes"};
	private ArrayList<JPanel> cards = new ArrayList<JPanel>();
	
	private JPanel pan_opt_nbj = new PanneauOptionsNbj(); // Choix du nombre de joueurs
	private JPanel pan_opt_mode = new PanneauVert(); // Choix du mode de jeu
	private Fenetre fenetre_principale;
	private int nbhumains = 1, nbia = 3;
	
	// L'instance de l'objet controleur
	private NbJoueursControler controler = new NbJoueursControler();
	
	public PanneauSelectionOptions(Fenetre fen) {
		// On ajoute l'observer au controler
		controler.addObserver(this);
				
		this.fenetre_principale = fen;
		
		cards.add(pan_opt_nbj);	
//		cards.add(pan_opt_mode);
		this.setLayout(cl);

		// Pour chaque card
		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).setName(listContent[i]); // On lui donne un nom
			this.add(cards.get(i), listContent[i]); // Et on l'ajoute � la liste
		}
		
	}

	public Observable getControler() {
		return controler;
	}
	
	
	
	private class PanneauOptionsNbj extends PanneauVert {
		private JPanel opt = new PanneauVert(); // Contient les diff�rentes options possibles
		private JPanel opt_container = new PanneauVert();
		private JLabel attention_label = new LabelVert("Attention : ne d�passez pas un total de 10 joueurs - Veillez � avoir au moins 2 joueurs");
		private JLabel lab = new LabelVert("Options : nombre de joueurs", JLabel.CENTER);
		
		public PanneauOptionsNbj() {
			this.add(lab, BorderLayout.NORTH);
			Font f = new Font("Dialog", Font.BOLD, 24);
			lab.setFont(f);
			lab.setPreferredSize(new Dimension(900, 120));
			
			JPanel jhumains = new OptPan("humans", "humains", 1);
			JPanel jia = new OptPan("ai", "IA", 3);
			this.add(opt_container);
			this.setPreferredSize(new Dimension(550, 500));
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
	}
	
	
	private class PanneauOptionsMode extends PanneauVert {
		private JLabel lab = new LabelVert("Options : Modalit�s de jeu", JLabel.CENTER);
		private JPanel opt = new PanneauVert(); // Contient les diff�rentes options possibles
		private JPanel opt_container = new PanneauVert();
		
		public PanneauOptionsMode() {
//			this.setLayout(new BorderLayout());
			this.add(lab, BorderLayout.NORTH);
			Font f = new Font("Dialog", Font.BOLD, 24);
			lab.setFont(f);
			lab.setPreferredSize(new Dimension(900, 120));
			
			JPanel mode = new OptPan(1);
			JPanel conditions = new OptPan(2);
			this.add(opt_container);
			this.setPreferredSize(new Dimension(550, 500));
			opt_container.setLayout(new BorderLayout());
			
			opt_container.add(opt, BorderLayout.CENTER);
			opt.add(mode);
			opt.add(conditions);
			
			JButton ret_button = new JButton("Annuler");
//			ret_button.addActionListener(new BoutonListenerBack());
			JButton val_button = new JButton("Valider");
//			val_button.addActionListener(new BoutonListenerVal());
			JPanel val_panel = new PanneauVert();

			
			val_panel.add(ret_button);
			val_panel.add(val_button);
			opt_container.add(val_panel, BorderLayout.SOUTH);
			
			
		}
		
		private class OptPan extends PanneauVert implements ActionListener {
			private JLabel imglab = new LabelVert();
			private JLabel label = new LabelVert();
			private JPanel jp = new PanneauVert();
			private JPanel opt_jcomponent_container = new PanneauVert(); // JPanel g�n�rique qui contient notre JComponent
			
			public OptPan(int num) {
				
				if (num == 1) {
					imglab.setIcon(new ImageIcon("images/game_mode.png"));
					label.setText("Mode de jeu :");
					int nbj = nbhumains + nbia;
					
					
					ButtonGroup group = new ButtonGroup();
					JRadioButton standardButton = new BoutonRadioVert("Standard");
					standardButton.setActionCommand("STANDARD");
					
					JRadioButton deuxJoueursButton = new BoutonRadioVert("Deux joueurs");
					deuxJoueursButton.setActionCommand("DEUX_JOUEURS");
					
					JRadioButton equipeButton = new BoutonRadioVert("Par �quipe");
					equipeButton.setActionCommand("EQUIPE");
					
					JRadioButton challengeButton = new BoutonRadioVert("Uno Challenge");
					challengeButton.setActionCommand("CHALLENGE");
					
					if (nbj == 2) { // S'il n'y a que deux joueurs, seul le mode 2 joueurs sera autoris�
						standardButton.setEnabled(false);
						deuxJoueursButton.setSelected(true);
						equipeButton.setEnabled(false);
						challengeButton.setEnabled(false);
					}
					else {
						standardButton.setSelected(true);
						deuxJoueursButton.setEnabled(false);
					}
					if ((nbj%2)!=0) { // On interdit le mode �quipe si les joueurs sont impairs
						equipeButton.setEnabled(false);
					}
					
					group.add(standardButton);
					group.add(deuxJoueursButton);
					group.add(equipeButton);
					group.add(challengeButton);

					standardButton.addActionListener(this);
					deuxJoueursButton.addActionListener(this);
					equipeButton.addActionListener(this);
					challengeButton.addActionListener(this);
					
					
					opt_jcomponent_container.setLayout(new GridLayout(0,1));
					opt_jcomponent_container.add(standardButton);
					opt_jcomponent_container.add(deuxJoueursButton);
					opt_jcomponent_container.add(equipeButton);
					opt_jcomponent_container.add(challengeButton);
				}
				else {
					imglab.setIcon(new ImageIcon("images/victory_params.png"));
					label.setText("Conditions de victoire :");
				}
				this.setPreferredSize(new Dimension(250, 250));
				this.setLayout(new BorderLayout());
				
				imglab.setHorizontalAlignment(JLabel.CENTER);
				this.add(imglab, BorderLayout.NORTH);
				
				label.setHorizontalTextPosition(JLabel.LEFT);
				jp.add(label);
				jp.add(opt_jcomponent_container);
				
//				combo.setPreferredSize(new Dimension(40, 20));
//				for (int i = 0; i <= 10; i++) {
//					combo.addItem(i);
//				}
//				combo.setSelectedIndex(defaut);
//				jp.add(combo);
//				combo.addActionListener((type == "IA") ? new ListListenerIA() : new ListListenerHumains());
				
				this.add(jp, BorderLayout.CENTER);			
			}
			
			public void actionPerformed(ActionEvent e) {
				Partie partie = Partie.getInstance();
				switch(e.getActionCommand()) {
				case "STANDARD":
					partie.setMode(ModeDeJeu.STANDARD);
					break;
				case "DEUX_JOUEURS":
					partie.setMode(ModeDeJeu.DEUX_JOUEURS);
					break;
				case "EQUIPE":
					partie.setMode(ModeDeJeu.EQUIPE);
					break;
				case "CHALLENGE":
					partie.setMode(ModeDeJeu.CHALLENGE);
					break;
				}
		    }
		}
	}
	
	
	
	
	
	
	// Classe �coutant notre bouton
	private class BoutonListener implements ActionListener {
		// Red�finition de la m�thode actionPerformed()
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
//			fenetre_principale.switchPan(Fenetre.DEBUT_JEU);
			pan_opt_mode = new PanneauOptionsMode();
			this.add(pan_opt_mode, listContent[1]);
			cl.next(this);
		}
		else {
			// On fait clignoter le label
			TimerTask task = new TimerTask() {
				private int iterator = 0;
					@Override
					public void run() {
						((PanneauOptionsNbj) pan_opt_nbj).setColorAttentionLabel((((PanneauOptionsNbj) pan_opt_nbj).getColorAttentionLabel() == Color.RED) ? Color.WHITE : Color.RED);
						if (iterator < 3) {
							iterator++;
						} else {
							this.cancel();
						}
					}
				};
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(task, 0, 100);
		}
	}

	
	
}