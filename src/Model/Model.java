package Model;

import java.util.ArrayList;

import Controller.MenuController;

public class Model {
	
	MenuController mc;
	
	public Model(MenuController mc) {
		// TODO Auto-generated constructor stub
		this.mc=mc;
		initCartes();
	}
	
	public static enum Faction {CHEVALIER,SAMURAI,COWBOY,SOLDAT};
	public static enum ActionJoueur {ATTAQUER, JOUER_SORT, EQUIPER, PASSER};
	public static enum TypeDePartie {JcJ,JcE};
	public static enum EtatPossible {START,MENU,COMBAT,CREATIONDECK};
	
	public static int TAILLEDECK=20;
	public int TAILLEMAINDEBUT=5;
	
	public static EtatPossible etatApp=EtatPossible.START;

	public Partie partieEnCours;
	
	public ArrayList<Carte> lesCartes = new ArrayList<Carte>();
	
	public void initCartes() {
		lesCartes.add(new Personnage("Aldric","Sire Aldric l'Indomptable","/images/personnages/Sire Aldric l'indomptable.jpg",mc,150,100));
		lesCartes.add(new Personnage("Kenshi","Kenshi la Lame Silencieuse","/images/personnages/Kenshi la lame silencieuse.jpg",mc,110,80));
		
	}
}
