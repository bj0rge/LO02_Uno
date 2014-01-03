package fr.utt.isi.lo02.projet_uno.graphique.observer;

public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	public void notifyObserver(String str);
}
