package View;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class PanelInfo extends PanelAgesOfClash {
    private static final long serialVersionUID = 1L;

    // ─── Palette de couleurs par faction ───────────────────────────────────────
    private static final Color COULEUR_CHEVALIER  = new Color(180, 140, 60);   // or
    private static final Color COULEUR_SAMOURAI   = new Color(180, 40,  40);   // rouge
    private static final Color COULEUR_COWBOY     = new Color(160, 100, 40);   // brun
    private static final Color COULEUR_SOLDAT     = new Color(60,  120, 60);   // vert militaire

    private static final Color BG_DARK            = new Color(18,  18,  28);
    private static final Color BG_CARD            = new Color(28,  28,  45);
    private static final Color BG_SECTION         = new Color(35,  35,  55);
    private static final Color TEXTE_PRINCIPAL    = new Color(230, 230, 240);
    private static final Color TEXTE_SECONDAIRE   = new Color(160, 160, 180);
    private static final Color TEXTE_STAT         = new Color(120, 200, 255);
    private static final Color TEXTE_EFFET        = new Color(200, 160, 255);

    private static final Font FONT_TITRE_FACTION  = new Font("Serif",       Font.BOLD,   18);
    private static final Font FONT_TITRE_CARTE    = new Font("SansSerif",   Font.BOLD,   13);
    private static final Font FONT_LABEL_TYPE     = new Font("SansSerif",   Font.BOLD,   11);
    private static final Font FONT_STAT           = new Font("Monospaced",  Font.PLAIN,  12);
    private static final Font FONT_DESCRIPTION    = new Font("SansSerif",   Font.ITALIC, 12);
    private static final Font FONT_EFFET          = new Font("SansSerif",   Font.PLAIN,  12);
    
    public ButtonAgesOfClash buttonRetour = new ButtonAgesOfClash("Retour");

    public PanelInfo(MenuManager menuManager) {
        setLayout(new BorderLayout());
        setBackground(BG_DARK);
        
        buttonRetour.addActionListener(menuManager.menuController);
        buttonRetour.setActionCommand("SHOWMENU");
        add(buttonRetour,BorderLayout.SOUTH);

        // ─── Titre principal ───────────────────────────────────────────────────
        JLabel titrePrincipal = new JLabel("📖  ENCYCLOPÉDIE DES CARTES", SwingConstants.CENTER);
        titrePrincipal.setFont(new Font("Serif", Font.BOLD, 22));
        titrePrincipal.setForeground(new Color(220, 200, 100));
        titrePrincipal.setBorder(BorderFactory.createEmptyBorder(14, 0, 10, 0));
        titrePrincipal.setOpaque(true);
        titrePrincipal.setBackground(BG_DARK);
        add(titrePrincipal, BorderLayout.NORTH);

        // ─── Conteneur principal scrollable ────────────────────────────────────
        JPanel contenu = new JPanel();
        contenu.setLayout(new BoxLayout(contenu, BoxLayout.Y_AXIS));
        contenu.setBackground(BG_DARK);
        contenu.setBorder(BorderFactory.createEmptyBorder(6, 14, 14, 14));

        // ─── Les 4 factions ────────────────────────────────────────────────────
        contenu.add(creerSectionFaction(
            "⚔️  CHEVALIERS",
            "Défense convertie en attaque, magie divine",
            COULEUR_CHEVALIER,
            new String[][]{
                {"👤 Sire Aldric l'Indomptable", "PV : 150   ATQ : 50   Énergie : 30%",
                 "Bouclier Divin — bloque la première attaque reçue en combat."},
                {"👤 Paladin Maudit",             "PV : 120   ATQ : 65   Énergie : 25%",
                 "Martyr — si KO, inflige 40 dégâts au personnage adverse qui l'a éliminé."},
                {"👤 Écuyer Brave",               "PV :  70   ATQ : 35   Énergie : 15%",
                 "Sacrifice — peut donner tous ses PV restants à Aldric (action gratuite)."}
            },
            new String[][]{
                {"✨ Jugement Divin",    "Énergie : 0%  (sort gratuit)",
                 "Inflige 50 dégâts fixes au personnage adverse actif, ignore tout effet."},
                {"✨ Bénédiction Royale","Énergie : 0%  (sort gratuit)",
                 "Soigne 35 PV sur n'importe quel personnage allié."}
            },
            new String[][]{
                {"🎒 Excalibur",          "Équipement permanent",
                 "+30 ATQ permanent sur le personnage équipé."},
                {"🎒 Bouclier du Roi",    "Équipement permanent",
                 "Le personnage équipé ne peut pas être ciblé par des Sorts adverses."}
            }
        ));

        contenu.add(Box.createVerticalStrut(10));

        contenu.add(creerSectionFaction(
            "🗡️  SAMOURAÏS",
            "Vitesse, frappe en premier, combos",
            COULEUR_SAMOURAI,
            new String[][]{
                {"👤 Kenshi la Lame Silencieuse", "PV : 110   ATQ : 80   Énergie : 25%",
                 "Priorité — attaque toujours AVANT l'adversaire, même si c'est son tour."},
                {"👤 Maître Takeda",              "PV : 130   ATQ : 60   Énergie : 30%",
                 "Combo — +40 ATQ si un autre Samouraï est sur le banc."},
                {"👤 Ronin Sans Maître",          "PV :  90   ATQ : 70   Énergie : 20%",
                 "Frénésie — ATQ x2 mais perd 20 PV après chaque attaque."}
            },
            new String[][]{
                {"✨ Iaïdo Éclair",   "Énergie : 0%  (sort gratuit)",
                 "Attaque surprise — l'adversaire ne peut pas activer son effet spécial ce tour."},
                {"✨ Code Bushido",   "Énergie : 0%  (sort gratuit)",
                 "+20 ATQ à tous les Samouraïs (actif + banc) pendant 3 tours."}
            },
            new String[][]{
                {"🎒 Katana Muramasa",       "Équipement permanent",
                 "+25 ATQ + ignore les effets de protection adverses."},
                {"🎒 Armure de Laque Noire", "Équipement permanent",
                 "-20 dégâts reçus permanent sur le personnage équipé."}
            }
        ));

        contenu.add(Box.createVerticalStrut(10));

        contenu.add(creerSectionFaction(
            "🤠  COWBOYS",
            "Chance, pièges, imprévisible",
            COULEUR_COWBOY,
            new String[][]{
                {"👤 Jesse \"Deadshot\" Kane", "PV : 100   ATQ : 75   Énergie : 20%",
                 "Headshot — 30% de chance d'étourdir l'adversaire (perd son prochain tour)."},
                {"👤 Doc Holliday",            "PV :  90   ATQ : 60   Énergie : 25%",
                 "Duel — les deux personnages actifs attaquent en même temps ce tour."},
                {"👤 Calamity Jane",           "PV :  85   ATQ : 65   Énergie : 20%",
                 "Esquive — évite automatiquement 1 attaque sur 3 reçues."}
            },
            new String[][]{
                {"✨ Coup de Chance", "Énergie : 0%  (sort gratuit)",
                 "Aléatoire (50/50) — soit soigne 40 PV allié, soit inflige 40 dégâts adverse."},
                {"✨ Embuscade",      "Énergie : 0%  (sort gratuit)",
                 "Piège — la prochaine attaque adverse lui revient dessus (retournée)."}
            },
            new String[][]{
                {"🎒 Colt Peacemaker", "Équipement permanent",
                 "+20 ATQ + cette attaque ne peut pas être retournée ou esquivée."},
                {"🎒 Étoile de Shérif","Équipement permanent",
                 "Immunisé contre étourdissement et effets de paralysie."}
            }
        ));

        contenu.add(Box.createVerticalStrut(10));

        contenu.add(creerSectionFaction(
            "💣  SOLDATS",
            "Technologie, explosifs, dégâts de zone",
            COULEUR_SOLDAT,
            new String[][]{
                {"👤 Sergent \"Ironwall\" Cross", "PV : 140   ATQ : 55   Énergie : 35%",
                 "Blindage — les 30 premiers dégâts reçus par tour sont annulés."},
                {"👤 Agent Fantôme",              "PV :  95   ATQ : 70   Énergie : 25%",
                 "Camouflage — ne peut pas être ciblé par des effets adverses pendant 2 tours."},
                {"👤 Sniper Elite",               "PV :  85   ATQ : 80   Énergie : 30%",
                 "Tir de précision — peut attaquer directement un personnage sur le BANC adverse."}
            },
            new String[][]{
                {"✨ Frappe Aérienne", "Énergie : 0%  (sort gratuit)",
                 "80 dégâts fixes à n'importe quelle cible, impossible à bloquer ou retourner."},
                {"✨ Grenade EMP",     "Énergie : 0%  (sort gratuit)",
                 "Désactive tous les Objets équipés adverses pendant 2 tours."}
            },
            new String[][]{
                {"🎒 Gilet Pare-balles","Équipement permanent",
                 "-20 dégâts reçus permanent MAIS ATQ -10 (lourd à porter)."},
                {"🎒 Drone de Combat",  "Équipement permanent",
                 "Inflige automatiquement 15 dégâts au personnage adverse actif à chaque début de tour."}
            }
        ));

        contenu.add(Box.createVerticalStrut(10));
        contenu.add(creerSectionSynergies());

        contenu.add(Box.createVerticalStrut(10));
        contenu.add(creerSectionRegles());

        // ─── ScrollPane ────────────────────────────────────────────────────────
        JScrollPane scroll = new JScrollPane(contenu);
        scroll.setBackground(BG_DARK);
        scroll.getViewport().setBackground(BG_DARK);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(scroll, BorderLayout.CENTER);
    }

    // ───────────────────────────────────────────────────────────────────────────
    //  Crée le bloc d'une faction complète
    // ───────────────────────────────────────────────────────────────────────────
    private JPanel creerSectionFaction(
            String nomFaction, String styleFaction, Color couleurFaction,
            String[][] personnages, String[][] sorts, String[][] objets) {

        JPanel section = new JPanel();
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBackground(BG_SECTION);
        section.setBorder(new CompoundBorder(
            new LineBorder(couleurFaction, 2, true),
            BorderFactory.createEmptyBorder(10, 12, 12, 12)
        ));
        section.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Titre faction
        JLabel titreFaction = new JLabel(nomFaction);
        titreFaction.setFont(FONT_TITRE_FACTION);
        titreFaction.setForeground(couleurFaction);
        titreFaction.setAlignmentX(Component.LEFT_ALIGNMENT);
        section.add(titreFaction);

        // Sous-titre style
        JLabel styleLbl = new JLabel("Style : " + styleFaction);
        styleLbl.setFont(FONT_DESCRIPTION);
        styleLbl.setForeground(TEXTE_SECONDAIRE);
        styleLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        styleLbl.setBorder(BorderFactory.createEmptyBorder(1, 0, 8, 0));
        section.add(styleLbl);

        // ── Personnages ──
        section.add(creerLabelType("👤  PERSONNAGES"));
        for (String[] p : personnages) {
            section.add(creerCarteInfo(p[0], p[1], p[2], couleurFaction));
            section.add(Box.createVerticalStrut(4));
        }

        section.add(Box.createVerticalStrut(6));

        // ── Sorts ──
        section.add(creerLabelType("✨  SORTS"));
        for (String[] s : sorts) {
            section.add(creerCarteInfo(s[0], s[1], s[2], new Color(140, 100, 220)));
            section.add(Box.createVerticalStrut(4));
        }

        section.add(Box.createVerticalStrut(6));

        // ── Objets ──
        section.add(creerLabelType("🎒  OBJETS"));
        for (String[] o : objets) {
            section.add(creerCarteInfo(o[0], o[1], o[2], new Color(80, 160, 160)));
            section.add(Box.createVerticalStrut(4));
        }

        return section;
    }

    // ───────────────────────────────────────────────────────────────────────────
    //  Crée une mini-carte d'info (nom + stats + effet)
    // ───────────────────────────────────────────────────────────────────────────
    private JPanel creerCarteInfo(String nom, String stats, String effet, Color accentCouleur) {
        JPanel carte = new JPanel();
        carte.setLayout(new BoxLayout(carte, BoxLayout.Y_AXIS));
        carte.setBackground(BG_CARD);
        carte.setBorder(new CompoundBorder(
            new MatteBorder(0, 3, 0, 0, accentCouleur),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        carte.setAlignmentX(Component.LEFT_ALIGNMENT);
        carte.setMaximumSize(new Dimension(Integer.MAX_VALUE, carte.getPreferredSize().height));

        // Nom de la carte
        JLabel nomLbl = new JLabel(nom);
        nomLbl.setFont(FONT_TITRE_CARTE);
        nomLbl.setForeground(TEXTE_PRINCIPAL);
        nomLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        carte.add(nomLbl);

        // Stats (PV / ATQ / Énergie)
        JLabel statsLbl = new JLabel(stats);
        statsLbl.setFont(FONT_STAT);
        statsLbl.setForeground(TEXTE_STAT);
        statsLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        statsLbl.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
        carte.add(statsLbl);

        // Description / Effet
        JLabel effetLbl = new JLabel("<html><i>" + effet + "</i></html>");
        effetLbl.setFont(FONT_EFFET);
        effetLbl.setForeground(TEXTE_EFFET);
        effetLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        carte.add(effetLbl);

        return carte;
    }

    // ───────────────────────────────────────────────────────────────────────────
    //  Label de sous-catégorie (PERSONNAGES / SORTS / OBJETS)
    // ───────────────────────────────────────────────────────────────────────────
    private JLabel creerLabelType(String texte) {
        JLabel lbl = new JLabel(texte);
        lbl.setFont(FONT_LABEL_TYPE);
        lbl.setForeground(new Color(180, 180, 200));
        lbl.setBorder(BorderFactory.createEmptyBorder(4, 0, 3, 0));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    // ───────────────────────────────────────────────────────────────────────────
    //  Section Synergies
    // ───────────────────────────────────────────────────────────────────────────
    private JPanel creerSectionSynergies() {
        JPanel section = new JPanel();
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBackground(BG_SECTION);
        section.setBorder(new CompoundBorder(
            new LineBorder(new Color(100, 100, 200), 2, true),
            BorderFactory.createEmptyBorder(10, 12, 12, 12)
        ));
        section.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel titre = new JLabel("🔗  SYNERGIES INTER-FACTIONS");
        titre.setFont(FONT_TITRE_FACTION);
        titre.setForeground(new Color(140, 140, 255));
        titre.setAlignmentX(Component.LEFT_ALIGNMENT);
        section.add(titre);
        section.add(Box.createVerticalStrut(8));

        String[][] synergies = {
            {"⚔️ Chevalier + 🗡️ Samouraï", "Code de l'Honneur",
             "+15 ATQ aux deux personnages concernés."},
            {"🤠 Cowboy + 💣 Soldat",       "Feu Croisé",
             "+30 ATQ combinées sur leurs attaques."},
            {"🗡️ Samouraï + 🤠 Cowboy",    "Duel Légendaire",
             "Le premier à attaquer fait x2 dégâts ce tour."},
            {"⚔️ Chevalier + 💣 Soldat",    "Bouclier & Blindage",
             "Le personnage le plus défensif protège l'autre des dégâts excédentaires."}
        };

        for (String[] s : synergies) {
            JPanel ligne = new JPanel();
            ligne.setLayout(new BoxLayout(ligne, BoxLayout.Y_AXIS));
            ligne.setBackground(BG_CARD);
            ligne.setBorder(new CompoundBorder(
                new MatteBorder(0, 3, 0, 0, new Color(100, 100, 200)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
            ));
            ligne.setAlignmentX(Component.LEFT_ALIGNMENT);
            ligne.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

            JLabel factions = new JLabel(s[0] + "  →  " + s[1]);
            factions.setFont(FONT_TITRE_CARTE);
            factions.setForeground(new Color(200, 200, 255));
            factions.setAlignmentX(Component.LEFT_ALIGNMENT);
            ligne.add(factions);

            JLabel desc = new JLabel("<html><i>" + s[2] + "</i></html>");
            desc.setFont(FONT_EFFET);
            desc.setForeground(TEXTE_EFFET);
            desc.setAlignmentX(Component.LEFT_ALIGNMENT);
            ligne.add(desc);

            section.add(ligne);
            section.add(Box.createVerticalStrut(4));
        }

        return section;
    }

    // ───────────────────────────────────────────────────────────────────────────
    //  Section Règles rapides
    // ───────────────────────────────────────────────────────────────────────────
    private JPanel creerSectionRegles() {
        JPanel section = new JPanel();
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBackground(BG_SECTION);
        section.setBorder(new CompoundBorder(
            new LineBorder(new Color(80, 160, 100), 2, true),
            BorderFactory.createEmptyBorder(10, 12, 12, 12)
        ));
        section.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel titre = new JLabel("📜  RAPPEL DES RÈGLES");
        titre.setFont(FONT_TITRE_FACTION);
        titre.setForeground(new Color(100, 200, 120));
        titre.setAlignmentX(Component.LEFT_ALIGNMENT);
        section.add(titre);
        section.add(Box.createVerticalStrut(8));

        String[] regles = {
            "🎯  Objectif : éliminer 5 personnages adverses (actif + banc).",
            "🃏  Mise en place : 5 cartes en main, 1 personnage actif + max 3 sur le banc.",
            "⚡  Énergie : commence à 100% — Actif +20%/tour, Banc +30%/tour.",
            "🔄  Tour : Piocher → Jouer Sorts/Objets → Attaquer (optionnel).",
            "💀  KO : remplacer depuis le banc obligatoire. Banc vide + KO = Défaite.",
            "✨  Sorts : coût 0% d'énergie, effet instantané.",
            "🎒  Objets : équipés en permanence sur un personnage.",
            "🛡️  Pas de DEF — seule l'ATQ compte pour les dégâts !"
        };

        for (String r : regles) {
            JLabel lbl = new JLabel(r);
            lbl.setFont(FONT_STAT);
            lbl.setForeground(TEXTE_SECONDAIRE);
            lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
            lbl.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
            section.add(lbl);
        }

        return section;
    }
}