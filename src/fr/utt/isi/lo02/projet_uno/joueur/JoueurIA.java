package fr.utt.isi.lo02.projet_uno.joueur;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.isi.lo02.projet_uno.carte.Carte;
import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;
import fr.utt.isi.lo02.projet_uno.manche.Defausse;
import fr.utt.isi.lo02.projet_uno.strategy.Strategy;


/**
 * <b>JoueurIA est la classe représentant un Joueur Artificiel de UNO.</b>
 * <p>
 * Le JoueurIA est caractérisée par :
 * <ul>
 * <li>Son nom</li>
 * <li>Sa Main</li>
 * <li>Son score actuel</li>
 * <li>Le fait qu'il ait ou non dit "UNO" lors de son dernier tour</li>
 * <li>Le fait qu'il ait ou non déjà pioché ce tour</li>
 * <li>Sa stratégie en cours</li>
 * </ul>
 * </p>
 * @see Main
 */
public class JoueurIA extends Joueur {
	
	/**
	 * Stratégie en cours d'utilisation par le JoueurIA.
	 * @see Strategy
	 */
	private Strategy strategie;
	
	/**
	 * Constructeur du JoueurIA.
	 * @param nom
	 * 			Nom du Joueur.
	 * @param strategie
	 * 			Strategy initiale du JoueurIA.
	 */
	public JoueurIA(String nom, Strategy strategie) {
		// On utilise le même constructeur que Joueur, avec pour différence la stratégie de jeu
		super(nom);
		this.setStrategie(strategie);
	}
	
	/**
	 * Permet d'indiquer que le joueur n'est pas humain.
	 * @return <i>Faux</i>
	 */
	public boolean estHumain() {
		return false;
	}
	
	/**
	 * Choisit la carte à jouer en fonction de la {@link #strategie} actuelle, et la pose.
	 */
	public void jouer() {
		boolean fin_tour = false;
		do {
			// Si le joueurIA peut jouer, on applique sa stratégie et il joue une carte en fonction de celle-ci
			if (this.peutJouer()) {
				this.getStrategie().set(this);
				this.getStrategie().jouer(this);
				fin_tour = true;
				
				if (this.getMain().getCartes().size() == 1){
					System.out.println(this + " annonce UNO !\n");
				}
				
			}
			else { // S'il ne peut pas jouer, on lui fait piocher, et s'il a déjà pioché on termine son tour
				if (this.aPioche()) {
					this.passerTour();
					System.out.println(this + " passe son tour.\n");
					fin_tour = true;
				}
				else {
					this.piocher();
					this.setAPioche(true);
					System.out.println(this + " a pioché une carte.");
				}
			}
		}
		while (!fin_tour);		
	}

	/**
	 * Vérifie que le JoueurIA a au moins une Carte jouable dans sa Main.
	 * @return <i>Vrai</i> s'il peut jouer, <i>Faux</i> sinon.
	 * @see Carte#estJouable(Carte)
	 */
	public boolean peutJouer() {
		
		// On compare toutes les cartes de la main à la dernière carte jouée
		boolean peut_jouer = false;
		Iterator<Carte> it = this.getMain().getCartes().iterator();
		while (it.hasNext()) {
			if (it.next().estJouable(Defausse.getInstance().getDerniereCarteJouee())) {
				peut_jouer = true;
			}
		}
		return peut_jouer;
	}
	
	public Couleur choixCouleur() {
		
		
		Iterator<Carte> it;
		int val_max = 0;
		Couleur c_max = null;
		
		// On fait la somme des points des cartes de chaque couleur
		for (Couleur coul : Couleur.values()) {
			
			int tmp = 0;
			it = this.getMain().getCartes().iterator();
			while (it.hasNext()) {
				Carte c = it.next();
				if (c.getCouleur() == coul) {
					tmp += c.getPoints();
				}
			}
			// On choisit la couleur si c'est la somme de ses points est la plus grande
			if (tmp > val_max) {
				val_max = tmp;
				c_max = coul;
			}
		}
		
		// S'il n'y a pas de valeurs max, on choisit une couleur au hasard
		if (c_max == null) {
			ArrayList<Couleur> random_couleur = new ArrayList<Couleur>();
			for (Couleur c : Couleur.values()) {
				random_couleur.add(c);
			}
			c_max = random_couleur.get((int) (Math.random() * Couleur.values().length));
		}
		return c_max; 
	}
	
	/**
	 * 
	 */
	public boolean direTuBluffesMartoni(Joueur j) {
		boolean denoncer = false;
		// Si le Joueur qui pose le +4 a encore au moins 3 Cartes en Main
		if (j.getMain().getCartes().size() >= 3) {
			// S'il a au moins 7 Cartes, on dénonce quoi qu'il arrive 
			if (j.getMain().getCartes().size() >= 7) {
				denoncer = true;
			}
			// Sinon, une chance sur 4
			else {
				int random = (int)(Math.random() * 4);
				if (random < 1) {
					denoncer = true;
				}
			}
		}
		// S'il avait moins de 3 Cartes, on ne dénonce pas.
		return denoncer;
	}
	

	/**
	 * Retourne la {@link #strategie} en cours.
	 * @return Stratégie actuelle du JoueurIA.
	 */
	public Strategy getStrategie() {
		return strategie;
	}

	/**
	 * Met à jour la {@link #strategie} du JoueurIA.
	 * @param strategie
	 * 			Stratégie à remplacer.
	 */
	public void setStrategie(Strategy strategie) {
		this.strategie = strategie;
	}

}
