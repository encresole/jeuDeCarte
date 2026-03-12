package Model;

import java.util.ArrayList;

public class Joueur {
	public Carte actif; 
	public ArrayList<Carte> banc = new ArrayList<Carte>();
	public ArrayList<Carte> main = new ArrayList<Carte>();
	public Deck deck = new Deck();
	
	
	public void ajouterActif(int i) {
		actif=main.get(i);
		main.remove(i);
	}
}
