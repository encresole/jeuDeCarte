package Model;

import Model.Model.TypeDePartie;

public class Partie {
	public int tour;
	public int tourDe;
	
	public Model.TypeDePartie typeDePartie;
	public boolean finPartie;
	
	public Joueur joueur1;
	public Deck deckInGame1;
	public Joueur joueur2;
	public Deck deckInGame2;
	
	
	
	
	public Partie(Joueur joueur1,Joueur joueur2, TypeDePartie typeDePartie) {
		this.joueur1=joueur1;
		deckInGame1=joueur1.deck;
		this.joueur2=joueur2;
		deckInGame2=joueur2.deck;
		this.tour = 0;
		this.finPartie = false;
		this.typeDePartie = typeDePartie;
		
	}
}



