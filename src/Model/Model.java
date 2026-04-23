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

        // =====================================================================
        // FACTION CHEVALIERS
        // =====================================================================

        Personnage aldric = new Personnage("p_aldric", "Aldric",
                "Sire Aldric l'Indomptable",
                "/images/personnages/Sire Aldric l'indomptable.jpg", mc, 150, 50);
        aldric.faction     = Faction.CHEVALIER;
        aldric.coutEnergie = 30;
        lesCartes.add(aldric);

        Personnage paladin = new Personnage("p_paladin", "Paladin",
                "Paladin Maudit",
                "/images/personnages/Paladin Maudit.jpg", mc, 120, 65);
        paladin.faction     = Faction.CHEVALIER;
        paladin.coutEnergie = 25;
        lesCartes.add(paladin);

        Personnage ecuyer = new Personnage("p_ecuyer", "Ecuyer",
                "Écuyer Brave",
                "/images/personnages/Ecuyer Brave.jpg", mc, 70, 35);
        ecuyer.faction     = Faction.CHEVALIER;
        ecuyer.coutEnergie = 15;
        lesCartes.add(ecuyer);

        // =====================================================================
        // FACTION SAMOURAI
        // =====================================================================

        Personnage kenshi = new Personnage("p_kenshi", "Kenshi",
                "Kenshi la Lame Silencieuse",
                "/images/personnages/Kenshi la lame silencieuse.jpg", mc, 110, 80);
        kenshi.faction     = Faction.SAMURAI;
        kenshi.coutEnergie = 25;
        lesCartes.add(kenshi);

        Personnage takeda = new Personnage("p_takeda", "Takeda",
                "Maître Takeda",
                "/images/personnages/Maître Takeda.jpg", mc, 130, 60);
        takeda.faction     = Faction.SAMURAI;
        takeda.coutEnergie = 30;
        lesCartes.add(takeda);

        Personnage ronin = new Personnage("p_ronin", "Ronin",
                "Ronin sans maître",
                "/images/personnages/Ronin sans maître.jpg", mc, 90, 70);
        ronin.faction     = Faction.SAMURAI;
        ronin.coutEnergie = 20;
        lesCartes.add(ronin);

        // =====================================================================
        // FACTION COWBOY
        // =====================================================================

        Personnage jesse = new Personnage("p_jesse", "Jesse",
                "Jesse Kane",
                "/images/personnages/Jesse Kane.jpg", mc, 100, 75);
        jesse.faction     = Faction.COWBOY;
        jesse.coutEnergie = 20;
        lesCartes.add(jesse);

        Personnage doc = new Personnage("p_doc", "DocHolliday",
                "Doc Holliday",
                "/images/personnages/Doc Holliday.png", mc, 90, 60);
        doc.faction     = Faction.COWBOY;
        doc.coutEnergie = 25;
        lesCartes.add(doc);

        Personnage calamity = new Personnage("p_calamity", "CalamityJane",
                "Calamity Jane",
                "/images/personnages/Calamity Jane.jpg", mc, 85, 65);
        calamity.faction     = Faction.COWBOY;
        calamity.coutEnergie = 20;
        lesCartes.add(calamity);

        // =====================================================================
        // FACTION SOLDAT
        // =====================================================================

        Personnage cross = new Personnage("p_cross", "Cross",
                "Sergent Cross",
                "/images/personnages/Sergent Cross.jpg", mc, 140, 55);
        cross.faction     = Faction.SOLDAT;
        cross.coutEnergie = 35;
        lesCartes.add(cross);

        Personnage agent = new Personnage("p_agent", "AgentFantome",
                "Agent Fantôme",
                "/images/personnages/Agent Fantome.jpg", mc, 95, 70);
        agent.faction     = Faction.SOLDAT;
        agent.coutEnergie = 25;
        lesCartes.add(agent);

        Personnage sniper = new Personnage("p_sniper", "SniperElite",
                "Sniper Elite",
                "/images/personnages/Sniper Elite.jpg", mc, 85, 80);
        sniper.faction     = Faction.SOLDAT;
        sniper.coutEnergie = 30;
        lesCartes.add(sniper);

        // =====================================================================
        // SORTS — effets réels assignés (Auteur : Raphael)
        // =====================================================================

        // Chevaliers
        lesCartes.add(new Sort("s_jugement", "Jugement Divin",
                "/images/sorts/Jugement Divin.jpg", mc,
                new EffetDommage(50)));   // 50 dégâts fixes sur l'adversaire

        lesCartes.add(new Sort("s_benediction", "Bénédiction Royale",
                "/images/sorts/Bénédiction Royale.jpg", mc,
                new EffetSoin(35)));      // soigne 35 PV à l'allié actif

        // Samouraïs
        lesCartes.add(new Sort("s_eclair", "Eclair",
                "/images/sorts/Eclair.jpg", mc,
                new EffetDommage(40)));   // frappe surprise : 40 dégâts fixes

        lesCartes.add(new Sort("s_bushido", "Code Bushido",
                "/images/sorts/Code Bushido.jpg", mc,
                new EffetBuff(20)));      // +20 ATQ à l'actif allié

        // Cowboys
        lesCartes.add(new Sort("s_chance", "Sort de Chance",
                "/images/sorts/Sort de Chance.jpg", mc,
                new EffetSoin(40)));      // simplifié : soigne 40 PV

        lesCartes.add(new Sort("s_embuscade", "Sort Embuscade",
                "/images/sorts/Sort Embuscade.jpg", mc,
                new EffetDebuff(20)));    // affaiblit l'adversaire de 20 ATQ

        // Soldats
        lesCartes.add(new Sort("s_frappe", "Frappe Aerienne",
                "/images/sorts/Frappe Aerienne.jpg", mc,
                new EffetDommage(80)));   // 80 dégâts fixes impossibles à bloquer

        lesCartes.add(new Sort("s_emp", "Grenade EMP",
                "/images/sorts/Grenade EMP.jpg", mc,
                new EffetDebuff(30)));    // réduit ATQ adverse de 30

        // =====================================================================
        // OBJETS — équipements (effets à implémenter dans une prochaine étape)
        // =====================================================================

        lesCartes.add(new Objet("o_armure",    "Armure De Laque Noir",
                "/images/objets/Armure De Laque Noir.jpg",  mc, new EffetVide()));
        lesCartes.add(new Objet("o_bouclier",  "Bouclier du Roi",
                "/images/objets/Bouclier du Roi.jpg",       mc, new EffetVide()));
        lesCartes.add(new Objet("o_colt",      "Colt Peacemaker",
                "/images/objets/Colt Peacemaker.jpg",       mc, new EffetVide()));
        lesCartes.add(new Objet("o_drone",     "Drone de combat",
                "/images/objets/Drone de combat.jpg",       mc, new EffetVide()));
        lesCartes.add(new Objet("o_etoile",    "Etoile de sherif",
                "/images/objets/Etoile de sherif.jpg",      mc, new EffetVide()));
        lesCartes.add(new Objet("o_excalibur", "Excalibur",
                "/images/objets/Exalibur.jpg",              mc, new EffetVide()));
        lesCartes.add(new Objet("o_gilet",     "Gilet Pare-balles",
                "/images/objets/Gilet Pare-balles.jpg",     mc, new EffetVide()));
        lesCartes.add(new Objet("o_gpotion",   "Grande potion de Soin",
                "/images/objets/Grande potion de Soin.jpg", mc, new EffetVide()));
        lesCartes.add(new Objet("o_katana",    "Katana Muramasa",
                "/images/objets/Katana Muramasa.jpg",       mc, new EffetVide()));
        lesCartes.add(new Objet("o_ppotion",   "Petite potion de Soin",
                "/images/objets/Petite potion de Soin.jpg", mc, new EffetVide()));
    }
}