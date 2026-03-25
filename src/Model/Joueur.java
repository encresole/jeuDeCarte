package Model;

import java.util.ArrayList;

public class Joueur {
	
	public String name;
	public Carte actif; 
	public ArrayList<Carte> banc = new ArrayList<Carte>();
	public ArrayList<Carte> main = new ArrayList<Carte>();
	public Deck deck = new Deck();
	
	public Joueur(String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
	}
	
	public void ajouterActif(int i) {
		actif=main.get(i);
		main.remove(i);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
