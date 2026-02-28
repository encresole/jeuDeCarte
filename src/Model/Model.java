package Model;

public class Model {
	
	public static enum Faction {CHEVALIER,SAMURAI,COWBOY,SOLDAT};
	public static enum ActionJoueur {ATTAQUER, JOUER_SORT, EQUIPER, PASSER};
	public static enum TypeDePartie {JcJ,JcE}
	public static enum EtatApp {MENU,COMBAT,CREATIONDECK}
	
	public static int TAILLEDECK=20;
	public int TAILLEMAINDEBUT=5;
	
	public static EtatApp ETAT;

	public Partie partieEnCours;
}
