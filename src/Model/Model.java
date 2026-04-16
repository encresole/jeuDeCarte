package Model;

/**
 * Model — Modèle central de l'application
 *
 * Ce qui a été fait :
 * - Ajout de gameController : référence au GameController accessible depuis la vue
 *   pour que les boutons Utiliser, Retraite, Fin du tour puissent appeler la logique Combat
 */

import java.util.ArrayList;

import Controller.GameController;
import Controller.MenuController;

public class Model {

    MenuController mc;

    public Model(MenuController mc) {
        this.mc = mc;
        initCartes();
    }

    public static enum Faction {CHEVALIER, SAMURAI, COWBOY, SOLDAT};
    public static enum ActionJoueur {ATTAQUER, JOUER_SORT, EQUIPER, PASSER};
    public static enum TypeDePartie {JcJ, JcE};
    public static enum EtatPossible {START, MENU, COMBAT, CREATIONDECK, CHOIXJOUEUR};

    public static int TAILLEDECK = 20;
    public int TAILLEMAINDEBUT = 5;

    public static EtatPossible etatApp = EtatPossible.START;

    public Partie partieEnCours;

    public ArrayList<Carte> lesCartes = new ArrayList<Carte>();

    public Joueur joueur1 = new Joueur("Player 1");
    public Joueur joueur2 = new Joueur("Player 2");
    public Joueur joueurEnCours = joueur1;

    public GameController gameController = new GameController(this);

    public void setJoueurEnCours(Joueur j) {
        joueurEnCours = j;
    }

    public void initCartes() {
        lesCartes.add(new Personnage("", "Aldric", "Sire Aldric l'Indomptable", "/images/personnages/Sire Aldric l'indomptable.jpg", mc, 150, 100));
        lesCartes.add(new Personnage("", "Kenshi", "Kenshi la Lame Silencieuse", "/images/personnages/Kenshi la lame silencieuse.jpg", mc, 110, 80));
        lesCartes.add(new Personnage("", "AgentFantome", "Agent Fantôme", "/images/personnages/Agent Fantome.jpg", mc, 110, 80));
        lesCartes.add(new Personnage("", "CalamityJane", "Calamity Jane", "/images/personnages/Calamity Jane.jpg", mc, 110, 80));
        lesCartes.add(new Personnage("", "DocHolliday", "Doc Holliday", "/images/personnages/Doc Holliday.png", mc, 110, 80));
        lesCartes.add(new Personnage("", "maitreTakeda", "Maître Takeda", "/images/personnages/Maître Takeda.jpg", mc, 110, 80));
        lesCartes.add(new Personnage("", "Paladin", "Paladin Maudit", "/images/personnages/Paladin Maudit.jpg", mc, 110, 80));
        lesCartes.add(new Personnage("", "Ronin", "Ronin sans maître", "/images/personnages/Ronin sans maître.jpg", mc, 110, 80));
        lesCartes.add(new Personnage("", "SniperElite", "Sniper Elite", "/images/personnages/Sniper Elite.jpg", mc, 110, 80));
        lesCartes.add(new Sort("", "Eclair", "/images/sorts/Eclair.jpg", mc, new EffetVide()));
        lesCartes.add(new Sort("", "Bénédiction Royale", "/images/sorts/Bénédiction Royale.jpg", mc, new EffetVide()));
        lesCartes.add(new Sort("", "Code Bushido", "/images/sorts/Code Bushido.jpg", mc, new EffetVide()));
        lesCartes.add(new Sort("", "Frappe Aerienne", "/images/sorts/Frappe Aerienne.jpg", mc, new EffetVide()));
        lesCartes.add(new Sort("", "Grenade EMP", "/images/sorts/Grenade EMP.jpg", mc, new EffetVide()));
        lesCartes.add(new Sort("", "Jugement Divin", "/images/sorts/Jugement Divin.jpg", mc, new EffetVide()));
        lesCartes.add(new Sort("", "Sort de Chance", "/images/sorts/Sort de Chance.jpg", mc, new EffetVide()));
        lesCartes.add(new Sort("", "Sort Embuscade", "/images/sorts/Sort Embuscade.jpg", mc, new EffetVide()));
        lesCartes.add(new Objet("", "Armure De Laque Noir", "/images/objets/Armure De Laque Noir.jpg",mc,new EffetVide()));
        lesCartes.add(new Objet("", "Bouclier du Roi", "/images/objets/Bouclier du Roi.jpg",mc,new EffetVide()));
        lesCartes.add(new Objet("", "Colt Peacemaker", "/images/objets/Colt Peacemaker.jpg",mc,new EffetVide()));
        lesCartes.add(new Objet("", "Drone de combat", "/images/objets/Drone de combat.jpg",mc,new EffetVide()));
        lesCartes.add(new Objet("", "Etoile de sherif", "/images/objets/Etoile de sherif.jpg",mc,new EffetVide()));
        lesCartes.add(new Objet("", "Exalibur", "/images/objets/Exalibur.jpg",mc,new EffetVide()));
        lesCartes.add(new Objet("", "Gilet par balle", "/images/objets/Gilet Pare-balles.jpg",mc,new EffetVide()));
        lesCartes.add(new Objet("", "Grande potion de Soin", "/images/objets/Grande potion de Soin.jpg",mc,new EffetVide()));
        lesCartes.add(new Objet("", "Katana MUramasa", "/images/objets/Katana MUramasa.jpg",mc,new EffetVide()));
        lesCartes.add(new Objet("", "Petite potion de soin", "/images/objets/Petite potion de soin.jpg",mc,new EffetVide()));
    }
}