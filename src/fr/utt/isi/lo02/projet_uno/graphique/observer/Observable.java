package fr.utt.isi.lo02.projet_uno.graphique.observer;

/**
 * <b>Observable est l'interface de l'objet observable</b>
 */
public interface Observable {
	
	/**
	 * Permet d'ajouter un {@link Observer}
	 * @param obs
	 * 			Observer à ajouter
	 */
	public void addObserver(Observer obs);
	
	/**
	 * Permet de supprimer l'{@link Observer}
	 */
	public void removeObserver();
	
	/**
	 * Permet de notifier les observer
	 * @param str
	 * 			Message
	 */
	public void notifyObserver(String str);
}
