package fr.utt.isi.lo02.projet_uno.joueur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import fr.utt.isi.lo02.projet_uno.carte.Carte;
import fr.utt.isi.lo02.projet_uno.enumeration.Couleur;

/**
 * <b>Main est la classe représentant la main d'un Joueur de UNO.</b>
 * <p>
 * La Main est caractérisée par une liste de Cartes
 * </p>
 * @see Carte
 */
public class Main {

	/**
	 * Les Cartes contenues dans la Main.
	 * @see Carte
	 * @see ArrayList
	 */
	private ArrayList<Carte> cartes;
	
	
	/**
	 * Constructeur de la Main. 
	 * @see ArrayList
	 */
	public Main() {
		this.setCartes(new ArrayList<Carte>());
	}

	/** 
	 * Classe les Cartes de la Main par {@link #Couleur} et par Valeur.
	 * @see Carte#getCouleur()
	 * @see Carte#getPoints()
	 */
	public void classer() {
		ArrayList<Carte> al = new ArrayList<Carte>();
		Iterator<Carte> it;
		
		// Pour chacune des Couleurs, on stocke les Cartes de la Main dans une Liste
		for (Couleur c: Couleur.values()) {
			it = this.getCartes().iterator();
			ArrayList<Carte> al2 = new ArrayList<Carte>();
			while (it.hasNext()) {
				Carte ca = it.next();
				if (ca.getCouleur() == c) {
					al2.add(ca);
				}
			}
			// On classe ensuite cette Liste dans l'ordre croissant des valeurs des Cartes
			Collections.sort(al2, new Comparator<Carte>() {
				@Override public int compare(Carte c1, Carte c2) {
					return c1.getPoints() - c2.getPoints();
				 }
			});
			// On ajoute la Liste à celle qui deviendra la nouvelle Main
			al.addAll(al2);
		}
		
		// On termine par ajouter les Cartes Noires
		it = this.getCartes().iterator();
		while(it.hasNext()) {
			Carte ca = it.next();
			if (ca.getCouleur() == null) {
				al.add(ca);
			}
		}
		
		// Et on met à jour la Main triée.
		this.setCartes(al);
	}
	

	/**
	 * Retourne la liste des Cartes contenues dans la Main.
	 * @return La liste des Cartes contenues dans la Main, sous la forme d'une instance d'ArrayList<Carte>.
	 */
	public ArrayList<Carte> getCartes() {
		return cartes;
	}

	/**
	 * Met à jour la Main.
	 * @param cartes
	 * 			Les Cartes qui vont constituer la nouvelle Main, sous la forme d'une instance d'ArrayList<Carte>.
	 */
	public void setCartes(ArrayList<Carte> cartes) {
		this.cartes = cartes;
	}
	
	
	/**
	 * Retourne une représentation de la Main sous forme de String.
	 * @returns Une représentation de la Main sous forme de String. 
	 */
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		
		Iterator<Carte> it = this.getCartes().iterator();
		while (it.hasNext()){
			Carte c = it.next();
			sb.append(c.toString());
			sb.append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
		
	}
	
	
}
