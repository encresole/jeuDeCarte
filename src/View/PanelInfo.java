package View;

import Model.Palette;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class PanelInfo extends PanelAgesOfClash {
    private static final long serialVersionUID = 1L;

    public MenuManager menuManager;
    public ButtonAgesOfClash buttonRetour;

    // ─── Couleurs faction ─────────────────────────────────────────────────────
    private static final Color COULEUR_CHEVALIER = new Color(180, 140,  60);
    private static final Color COULEUR_SAMOURAI  = new Color(180,  40,  40);
    private static final Color COULEUR_COWBOY    = new Color(160, 100,  40);
    private static final Color COULEUR_SOLDAT    = new Color( 60, 130,  60);
    private static final Color COULEUR_SYNERGIE  = new Color(100, 100, 210);
    private static final Color COULEUR_REGLES    = new Color( 80, 170, 100);

    // ─── Couleurs texte ───────────────────────────────────────────────────────
    private static final Color TEXTE_STAT  = new Color(120, 200, 255);
    private static final Color TEXTE_EFFET = new Color(200, 160, 255);

    // ─── Fonts ────────────────────────────────────────────────────────────────
    private static final Font FONT_TITRE_FACTION = new Font("Serif",      Font.BOLD,  17);
    private static final Font FONT_TITRE_CARTE   = new Font("SansSerif",  Font.BOLD,  12);
    private static final Font FONT_LABEL_TYPE    = new Font("SansSerif",  Font.BOLD,  11);
    private static final Font FONT_STAT          = new Font("Monospaced", Font.PLAIN, 12);
    private static final Font FONT_EFFET         = new Font("SansSerif",  Font.PLAIN, 12);

    public PanelInfo(MenuManager menuManager) {
        this.menuManager = menuManager;
        setLayout(new BorderLayout());

        // ─── Titre ────────────────────────────────────────────────────────────
        LabelTitle titre = new LabelTitle("ENCYCLOPEDIE DES CARTES");
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        titre.setBorder(BorderFactory.createEmptyBorder(14, 0, 10, 0));
        add(titre, BorderLayout.PAGE_START);

        // ─── Contenu scrollable ───────────────────────────────────────────────
        JPanel contenu = new PanelAgesOfClash();
        contenu.setLayout(new BoxLayout(contenu, BoxLayout.Y_AXIS));
        contenu.setBorder(BorderFactory.createEmptyBorder(6, 14, 14, 14));

        contenu.add(creerSectionFaction(
            "CHEVALIERS - Defense convertie en attaque, magie divine",
            COULEUR_CHEVALIER,
            new String[][]{
                {"Sire Aldric l'Indomptable", "PV : 150   ATQ : 50   Energie : 30%",
                 "Bouclier Divin - bloque la premiere attaque recue en combat."},
                {"Paladin Maudit",             "PV : 120   ATQ : 65   Energie : 25%",
                 "Martyr - si KO, inflige 40 degats au personnage adverse qui l'a elimine."},
                {"Ecuyer Brave",               "PV :  70   ATQ : 35   Energie : 15%",
                 "Sacrifice - peut donner tous ses PV restants a Aldric (action gratuite)."}
            },
            new String[][]{
                {"Jugement Divin",     "Energie : 0%  (sort gratuit)",
                 "Inflige 50 degats fixes au personnage adverse actif, ignore tout effet."},
                {"Benediction Royale", "Energie : 0%  (sort gratuit)",
                 "Soigne 35 PV sur n'importe quel personnage allie."}
            },
            new String[][]{
                {"Excalibur",       "Equipement permanent", "+30 ATQ permanent sur le personnage equipe."},
                {"Bouclier du Roi", "Equipement permanent", "Le personnage equipe ne peut pas etre cible par des Sorts adverses."}
            }
        ));

        contenu.add(Box.createVerticalStrut(10));

        contenu.add(creerSectionFaction(
            "SAMOURAI - Vitesse, frappe en premier, combos",
            COULEUR_SAMOURAI,
            new String[][]{
                {"Kenshi la Lame Silencieuse", "PV : 110   ATQ : 80   Energie : 25%",
                 "Priorite - attaque toujours AVANT l'adversaire, meme si c'est son tour."},
                {"Maitre Takeda",              "PV : 130   ATQ : 60   Energie : 30%",
                 "Combo - +40 ATQ si un autre Samurai est sur le banc."},
                {"Ronin Sans Maitre",          "PV :  90   ATQ : 70   Energie : 20%",
                 "Frenesie - ATQ x2 mais perd 20 PV apres chaque attaque."}
            },
            new String[][]{
                {"Iaido Eclair", "Energie : 0%  (sort gratuit)",
                 "Attaque surprise - l'adversaire ne peut pas activer son effet special ce tour."},
                {"Code Bushido", "Energie : 0%  (sort gratuit)",
                 "+20 ATQ a tous les Samurai (actif + banc) pendant 3 tours."}
            },
            new String[][]{
                {"Katana Muramasa",       "Equipement permanent", "+25 ATQ + ignore les effets de protection adverses."},
                {"Armure de Laque Noire", "Equipement permanent", "-20 degats recus permanent sur le personnage equipe."}
            }
        ));

        contenu.add(Box.createVerticalStrut(10));

        contenu.add(creerSectionFaction(
            "COWBOYS - Chance, pieges, imprevisible",
            COULEUR_COWBOY,
            new String[][]{
                {"Jesse Deadshot Kane", "PV : 100   ATQ : 75   Energie : 20%",
                 "Headshot - 30% de chance d'etourdir l'adversaire (perd son prochain tour)."},
                {"Doc Holliday",        "PV :  90   ATQ : 60   Energie : 25%",
                 "Duel - les deux personnages actifs attaquent en meme temps ce tour."},
                {"Calamity Jane",       "PV :  85   ATQ : 65   Energie : 20%",
                 "Esquive - evite automatiquement 1 attaque sur 3 recues."}
            },
            new String[][]{
                {"Coup de Chance", "Energie : 0%  (sort gratuit)",
                 "Aleatoire (50/50) - soit soigne 40 PV allie, soit inflige 40 degats adverse."},
                {"Embuscade",      "Energie : 0%  (sort gratuit)",
                 "Piege - la prochaine attaque adverse lui revient dessus (retournee)."}
            },
            new String[][]{
                {"Colt Peacemaker", "Equipement permanent", "+20 ATQ + cette attaque ne peut pas etre retournee ou esquivee."},
                {"Etoile de Sheriff","Equipement permanent", "Immunise contre etourdissement et effets de paralysie."}
            }
        ));

        contenu.add(Box.createVerticalStrut(10));

        contenu.add(creerSectionFaction(
            "SOLDATS - Technologie, explosifs, degats de zone",
            COULEUR_SOLDAT,
            new String[][]{
                {"Sergent Ironwall Cross", "PV : 140   ATQ : 55   Energie : 35%",
                 "Blindage - les 30 premiers degats recus par tour sont annules."},
                {"Agent Fantome",          "PV :  95   ATQ : 70   Energie : 25%",
                 "Camouflage - ne peut pas etre cible par des effets adverses pendant 2 tours."},
                {"Sniper Elite",           "PV :  85   ATQ : 80   Energie : 30%",
                 "Tir de precision - peut attaquer directement un personnage sur le BANC adverse."}
            },
            new String[][]{
                {"Frappe Aerienne", "Energie : 0%  (sort gratuit)",
                 "80 degats fixes a n'importe quelle cible, impossible a bloquer ou retourner."},
                {"Grenade EMP",     "Energie : 0%  (sort gratuit)",
                 "Desactive tous les Objets equipes adverses pendant 2 tours."}
            },
            new String[][]{
                {"Gilet Pare-balles","Equipement permanent", "-20 degats recus permanent MAIS ATQ -10 (lourd a porter)."},
                {"Drone de Combat",  "Equipement permanent", "Inflige automatiquement 15 degats au perso adverse actif a chaque debut de tour."}
            }
        ));

        contenu.add(Box.createVerticalStrut(10));
        contenu.add(creerSectionSynergies());
        contenu.add(Box.createVerticalStrut(10));
        contenu.add(creerSectionRegles());

        JScrollPane scroll = new JScrollPane(contenu);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(Palette.MENU_BG);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.CENTER);

        // ─── Bouton retour (meme pattern que CreerDeck) ───────────────────────
        buttonRetour = new ButtonAgesOfClash("Retour");
        buttonRetour.addActionListener(menuManager.menuController);
        buttonRetour.setActionCommand("SHOWMENU");
        add(buttonRetour, BorderLayout.SOUTH);
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  Section faction
    // ─────────────────────────────────────────────────────────────────────────
    private JPanel creerSectionFaction(
            String titreEtStyle, Color couleurFaction,
            String[][] personnages, String[][] sorts, String[][] objets) {

        JPanel section = new PanelAgesOfClash();
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBorder(new CompoundBorder(
            new LineBorder(couleurFaction, 2, true),
            BorderFactory.createEmptyBorder(10, 12, 12, 12)
        ));
        section.setAlignmentX(Component.LEFT_ALIGNMENT);

        LabelTitle titreFaction = new LabelTitle(titreEtStyle);
        titreFaction.setFont(FONT_TITRE_FACTION);
        titreFaction.setForeground(couleurFaction);
        titreFaction.setAlignmentX(Component.LEFT_ALIGNMENT);
        section.add(titreFaction);
        section.add(Box.createVerticalStrut(8));

        section.add(creerLabelType("PERSONNAGES"));
        for (String[] p : personnages) {
            section.add(creerCarteInfo(p[0], p[1], p[2], couleurFaction));
            section.add(Box.createVerticalStrut(4));
        }

        section.add(Box.createVerticalStrut(6));
        section.add(creerLabelType("SORTS"));
        for (String[] s : sorts) {
            section.add(creerCarteInfo(s[0], s[1], s[2], new Color(140, 100, 220)));
            section.add(Box.createVerticalStrut(4));
        }

        section.add(Box.createVerticalStrut(6));
        section.add(creerLabelType("OBJETS"));
        for (String[] o : objets) {
            section.add(creerCarteInfo(o[0], o[1], o[2], new Color(80, 160, 160)));
            section.add(Box.createVerticalStrut(4));
        }

        return section;
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  Mini-carte d'info
    // ─────────────────────────────────────────────────────────────────────────
    private JPanel creerCarteInfo(String nom, String stats, String effet, Color accentCouleur) {
        JPanel carte = new PanelAgesOfClash();
        carte.setLayout(new BoxLayout(carte, BoxLayout.Y_AXIS));
        carte.setBorder(new CompoundBorder(
            new MatteBorder(0, 3, 0, 0, accentCouleur),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        carte.setAlignmentX(Component.LEFT_ALIGNMENT);
        carte.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));

        JLabel nomLbl = new JLabel(nom);
        nomLbl.setFont(FONT_TITRE_CARTE);
        nomLbl.setForeground(Palette.MENU_TITLE);
        nomLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        carte.add(nomLbl);

        // Stats seulement si non vide (les synergies n'en ont pas)
        if (!stats.isEmpty()) {
            JLabel statsLbl = new JLabel(stats);
            statsLbl.setFont(FONT_STAT);
            statsLbl.setForeground(TEXTE_STAT);
            statsLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
            statsLbl.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
            carte.add(statsLbl);
        }

        JLabel effetLbl = new JLabel("<html><i>" + effet + "</i></html>");
        effetLbl.setFont(FONT_EFFET);
        effetLbl.setForeground(TEXTE_EFFET);
        effetLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        carte.add(effetLbl);

        return carte;
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  Label sous-categorie (PERSONNAGES / SORTS / OBJETS)
    // ─────────────────────────────────────────────────────────────────────────
    private JLabel creerLabelType(String texte) {
        JLabel lbl = new JLabel("-- " + texte + " --");
        lbl.setFont(FONT_LABEL_TYPE);
        lbl.setForeground(Palette.MENU_SUBTITLE);
        lbl.setBorder(BorderFactory.createEmptyBorder(4, 0, 3, 0));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  Section Synergies
    // ─────────────────────────────────────────────────────────────────────────
    private JPanel creerSectionSynergies() {
        JPanel section = new PanelAgesOfClash();
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBorder(new CompoundBorder(
            new LineBorder(COULEUR_SYNERGIE, 2, true),
            BorderFactory.createEmptyBorder(10, 12, 12, 12)
        ));
        section.setAlignmentX(Component.LEFT_ALIGNMENT);

        LabelTitle titre = new LabelTitle("SYNERGIES INTER-FACTIONS");
        titre.setFont(FONT_TITRE_FACTION);
        titre.setForeground(COULEUR_SYNERGIE);
        titre.setAlignmentX(Component.LEFT_ALIGNMENT);
        section.add(titre);
        section.add(Box.createVerticalStrut(8));

        String[][] synergies = {
            {"Chevalier + Samurai  ->  Code de l'Honneur",
             "+15 ATQ aux deux personnages concernes."},
            {"Cowboy + Soldat  ->  Feu Croise",
             "+30 ATQ combinees sur leurs attaques."},
            {"Samurai + Cowboy  ->  Duel Legendaire",
             "Le premier a attaquer fait x2 degats ce tour."},
            {"Chevalier + Soldat  ->  Bouclier & Blindage",
             "Le personnage le plus defensif protege l'autre des degats excedentaires."}
        };

        for (String[] s : synergies) {
            section.add(creerCarteInfo(s[0], "", s[1], COULEUR_SYNERGIE));
            section.add(Box.createVerticalStrut(4));
        }

        return section;
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  Section Regles rapides
    // ─────────────────────────────────────────────────────────────────────────
    private JPanel creerSectionRegles() {
        JPanel section = new PanelAgesOfClash();
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBorder(new CompoundBorder(
            new LineBorder(COULEUR_REGLES, 2, true),
            BorderFactory.createEmptyBorder(10, 12, 12, 12)
        ));
        section.setAlignmentX(Component.LEFT_ALIGNMENT);

        LabelTitle titre = new LabelTitle("RAPPEL DES REGLES");
        titre.setFont(FONT_TITRE_FACTION);
        titre.setForeground(COULEUR_REGLES);
        titre.setAlignmentX(Component.LEFT_ALIGNMENT);
        section.add(titre);
        section.add(Box.createVerticalStrut(8));

        String[] regles = {
            "Objectif : eliminer tout les personnages adverses (actif + banc).",
            "Mise en place : 5 cartes en main, 1 personnage actif + max 3 sur le banc.",
            "Energie : commence a 100% -- Actif +20%/tour, Banc +30%/tour.",
            "Tour : Piocher -> Jouer Sorts/Objets -> Attaquer (optionnel).",
            "KO : remplacer depuis le banc obligatoire. Banc vide + KO = Defaite.",
            "Sorts : cout 0% d'energie, effet instantane.",
            "Objets : equipes en permanence sur un personnage.",
            "Pas de DEF -- seule l'ATQ compte pour les degats !"
        };

        for (String r : regles) {
            JLabel lbl = new JLabel("  >  " + r);
            lbl.setFont(FONT_STAT);
            lbl.setForeground(Palette.MENU_SUBTITLE);
            lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
            lbl.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
            section.add(lbl);
        }

        return section;
    }
}