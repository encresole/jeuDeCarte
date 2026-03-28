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
	public static enum EtatPossible {START,MENU,COMBAT,CREATIONDECK, CHOIXJOUEUR};
	
	public static int TAILLEDECK=20;
	public int TAILLEMAINDEBUT=5;
	
	public static EtatPossible etatApp=EtatPossible.START;

	public Partie partieEnCours;
	
	public ArrayList<Carte> lesCartes = new ArrayList<Carte>();
	
	public Joueur joueur1 = new Joueur("Player 1");
	public Joueur joueur2 = new Joueur("Player 2");
	public Joueur joueurEnCours=joueur1;
	
	public void setJoueurEnCours(Joueur j) {
		joueurEnCours = j;
	}
	
	public void initCartes() {
		// --- Personnages ---
		lesCartes.add(new Personnage("","Aldric","Sire Aldric l'Indomptable","/images/personnages/Sire Aldric l'indomptable.jpg",mc,150,100));
		lesCartes.add(new Personnage("","Kenshi","Kenshi la Lame Silencieuse","/images/personnages/Kenshi la lame silencieuse.jpg",mc,110,80));
		lesCartes.add(new Personnage("","AgentFantome","Agent Fantôme","/images/personnages/Agent Fantome.jpg",mc,110,80));
		lesCartes.add(new Personnage("","CalamityJane","Calamity Jane","/images/personnages/Calamity Jane.jpg",mc,110,80));
		lesCartes.add(new Personnage("","DocHolliday","Doc Holliday","/images/personnages/Doc Holliday.png",mc,110,80));
		lesCartes.add(new Personnage("","maitreTakeda","Maître Takeda","/images/personnages/Maître Takeda.jpg",mc,110,80));
		lesCartes.add(new Personnage("","Paladin","Paladin Maudit","/images/personnages/Paladin Maudit.jpg",mc,110,80));
		lesCartes.add(new Personnage("","Ronin","Ronin sans maître","/images/personnages/Ronin sans maître.jpg",mc,110,80));
		lesCartes.add(new Personnage("","SniperElite","Sniper Elite","/images/personnages/Sniper Elite.jpg",mc,110,80));

		// --- Sorts (Raphael) — utilisent EffetSoin, EffetBuff, EffetDebuff ---
		lesCartes.add(new Sort("s1","Potion","/images/sorts/potion.png",mc, new EffetSoin(40)));
		lesCartes.add(new Sort("s2","Grande Potion","/images/sorts/grande_potion.png",mc, new EffetSoin(80)));
		lesCartes.add(new Sort("s3","Rage","/images/sorts/rage.png",mc, new EffetBuff(20)));
		lesCartes.add(new Sort("s4","Malédiction","/images/sorts/malediction.png",mc, new EffetDebuff(25)));
		lesCartes.add(new Sort("s5","Affaiblissement","/images/sorts/affaiblissement.png",mc, new EffetDebuff(15)));
	}
}
