package fr.utt.isi.lo02.projet_uno.graphique.observer;

/**
 * <b>Observer est l'interface de l'objet observer</b>
 */
public interface Observer {
	
	/**
	 * Met à jour les champs
	 * @param controle_ok
	 * 			Valeur de contrôle
	 */
	public void update(boolean controle_ok);
}
