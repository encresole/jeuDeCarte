package Model;

import java.util.ArrayList;

public class Joueur {
	public Carte actif; 
	public ArrayList<Carte> banc;
	public ArrayList<Carte> main;
	public Deck deck;
	
	
	public void ajouterActif(int i) {
		actif=main.get(i);
		main.remove(i);
	}
}
