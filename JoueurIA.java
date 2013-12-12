package LO02_Uno;

import java.util.Iterator;


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
	 * Vérifie que le JoueurIA a au moins une Carte jouable dans sa Main.
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
	 * Choisit la Carte à jouer en fonction de la Strategy actuelle et la pose.
	 */
	public void jouer() {
		if (this.peutJouer()) {
			System.out.println("peut jouer");
			this.getStrategie().jouer(this);
		}
		else {
			System.out.println("peut pas jouer");
			this.piocher();
		}
	}
	
	/**
	 * Retourne la Couleur à jouer au prochain tour en fonction des Cartes dans la main
	 * 
	 */
	public Couleur choixCouleur() { return null; }
	
	

	/**
	 * Retourne la Stratégie en cours.
	 * @return Stratégie actuelle du JoueurIA.
	 */
	public Strategy getStrategie() {
		return strategie;
	}

	/**
	 * Met à jour la Stratégie du JoueurIA.
	 * @param strategie
	 * 			Stratégie à remplacer.
	 */
	public void setStrategie(Strategy strategie) {
		this.strategie = strategie;
	}

}
