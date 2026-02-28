package Controller;

import java.util.Random;

import Model.*;

public class GameController {
	
	public Model m;
	Random random = new Random();
	
	public GameController(Model m) {
		this.m=m;
	}
	
	public void commencerCombat(Joueur joueur1, Joueur joueur2) {
		m.partieEnCours=new Partie(joueur1, joueur2, Model.TypeDePartie.JcJ);
		
		for (int i = 0; i < m.TAILLEMAINDEBUT; i++) {
			int indexCarteTiree=random.nextInt(joueur1.deck.size());
			joueur1.main.add(joueur1.deck.get(indexCarteTiree));
			joueur1.deck.remove(indexCarteTiree);
		}
		
		for (int i = 0; i < m.TAILLEMAINDEBUT; i++) {
			int indexCarteTiree=random.nextInt(joueur2.deck.size());
			joueur2.main.add(joueur2.deck.get(indexCarteTiree));
			joueur2.deck.remove(indexCarteTiree);
		}
		
		while (joueur1.actif==null || joueur2.actif==null) {
			
		}
		
		m.partieEnCours.tourDe=tirageJoueur();
		
	}
	
	public int tirageJoueur() {
		return random.nextInt(2);
	}
	
}