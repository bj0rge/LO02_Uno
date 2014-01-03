package fr.utt.isi.lo02.projet_uno.graphique.controler;

import java.util.ArrayList;

import fr.utt.isi.lo02.projet_uno.graphique.observer.Observable;
import fr.utt.isi.lo02.projet_uno.graphique.observer.Observer;
import fr.utt.isi.lo02.projet_uno.partie.Partie;

public class NbJoueursControler implements Observable {

	private int nbhumains = 1, nbia = 3;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	private Partie partie = Partie.getInstance();

	// Implémentation du pattern observer
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	public void notifyObserver(String str) {
		boolean update_ok = (str.compareTo("ok") == 0) ? true : false;
		for (Observer obs : listObserver)
			obs.update(update_ok);
	}

	
	public void removeObserver() {
		listObserver = new ArrayList<Observer>();
	}
	
	public void control() {
		notifyObserver((nbhumains + nbia > 10 || nbhumains + nbia < 2) ? "ko" : "ok");
	}
	
	

	public int getNbhumains() {
		return nbhumains;
	}

	public void setNbhumains(int nbhumains) {
		this.nbhumains = nbhumains;
	}

	public int getNbia() {
		return nbia;
	}

	public void setNbia(int nbia) {
		this.nbia = nbia;
	}
}
