package LO02_Uno;

public class Observable {

	// Attributs
	private Observer[] listeObservers;
	
	// M�thodes
	public void notifObserver(){
	}
	public void addObserver(Observer obs){
	}
	public void deleteObserver(Observer obs){
	}
	public Observer[] getObservers(){
		return this.listeObservers;
	}
	
}
