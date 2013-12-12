package LO02_Uno;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * <b>JoueurIA est la classe repr�sentant un Joueur Artificiel de UNO.</b>
 * <p>
 * Le JoueurIA est caract�ris�e par :
 * <ul>
 * <li>Son nom</li>
 * <li>Sa Main</li>
 * <li>Son score actuel</li>
 * <li>Le fait qu'il ait ou non dit "UNO" lors de son dernier tour</li>
 * <li>Le fait qu'il ait ou non d�j� pioch� ce tour</li>
 * <li>Sa strat�gie en cours</li>
 * </ul>
 * </p>
 * @see Main
 */
public class JoueurIA extends Joueur {
	
	/**
	 * Strat�gie en cours d'utilisation par le JoueurIA.
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
		super(nom);
		this.setStrategie(strategie);
	}
	
	public boolean estHumain() {
		return false;
	}
	
	/**
	 * V�rifie que le JoueurIA a au moins une Carte jouable dans sa Main.
	 * @return <i>true</i> si il peut jouer, <i>false</i> sinon.
	 */
	public boolean peutJouer() {
		boolean peut_jouer = false;
		Iterator<Carte> it = this.getMain().getCartes().iterator();
		while (it.hasNext()) {
			if (it.next().estJouable(Defausse.getInstance().getDerniereCarteJouee())) {
				peut_jouer = true;
			}
		}
		return peut_jouer;
	}
	
	/**
	 * Choisit la Carte � jouer en fonction de la Strategy actuelle et la pose.
	 */
	public void jouer() {
		if (this.peutJouer()) {
			this.getStrategie().jouer(this);
		}
		else {
			this.piocher();
		}
	}
	

	public Couleur choixCouleur() {
		Iterator<Carte> it;
		int val_max = 0;
		Couleur c_max = null;
		
		for (Couleur coul : Couleur.values()) {
			
			int tmp = 0;
			it = this.getMain().getCartes().iterator();
			while (it.hasNext()) {
				Carte c = it.next();
				if (c.getCouleur() == coul) {
					tmp += c.getPoints();
				}
			}
			if (tmp > val_max) {
				val_max = tmp;
				c_max = coul;
			}
		}
		
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
	 * Retourne la Strat�gie en cours.
	 * @return Strat�gie actuelle du JoueurIA.
	 */
	public Strategy getStrategie() {
		return strategie;
	}

	/**
	 * Met � jour la Strat�gie du JoueurIA.
	 * @param strategie
	 * 			Strat�gie � remplacer.
	 */
	public void setStrategie(Strategy strategie) {
		this.strategie = strategie;
	}

}
