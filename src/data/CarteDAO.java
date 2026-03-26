package data;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import Controller.MenuController;
import Model.Carte;
import Model.Effet;
import Model.Model.Faction;
import Model.Objet;
import Model.Personnage;
import Model.Sort;

/**
 * CarteDAO — parse cartes.json sans aucune librairie externe.
 * Utilise uniquement java.lang et java.util (Java pur).
 */
public class CarteDAO {

    private static final String CHEMIN_JSON = "/data/cartes.json";
    private MenuController mc;

    public CarteDAO(MenuController mc) {
        this.mc = mc;
    }

    public List<Carte> chargerToutesLesCartes() {
        List<Carte> cartes = new ArrayList<>();
        try {
            InputStream is = getClass().getResourceAsStream(CHEMIN_JSON);
            if (is == null) {
                System.err.println("ERREUR : cartes.json introuvable à " + CHEMIN_JSON);
                return cartes;
            }
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            cartes.addAll(parserSection(json, "personnages", "PERSONNAGE"));
            cartes.addAll(parserSection(json, "sorts",       "SORT"));
            cartes.addAll(parserSection(json, "objets",      "OBJET"));

            System.out.println("✅ " + cartes.size() + " cartes chargées.");
        } catch (Exception e) {
            System.err.println("ERREUR chargement cartes : " + e.getMessage());
            e.printStackTrace();
        }
        return cartes;
    }

    // -------------------------------------------------------
    // Extrait tous les objets JSON d'un tableau nommé section
    // -------------------------------------------------------
    private List<Carte> parserSection(String json, String section, String type) {
        List<Carte> cartes = new ArrayList<>();

        // Trouver le tableau "section": [ ... ]
        String marqueur = "\"" + section + "\"";
        int debut = json.indexOf(marqueur);
        if (debut == -1) return cartes;

        int debutTableau = json.indexOf('[', debut);
        int finTableau   = trouverFermeture(json, debutTableau, '[', ']');
        if (debutTableau == -1 || finTableau == -1) return cartes;

        String tableau = json.substring(debutTableau + 1, finTableau);

        // Découper en objets { ... }
        List<String> objets = extraireObjets(tableau);
        for (String obj : objets) {
            Carte c = creerCarte(obj, type);
            if (c != null) cartes.add(c);
        }
        return cartes;
    }

    // -------------------------------------------------------
    // Crée une Carte depuis un bloc JSON { ... }
    // -------------------------------------------------------
    private Carte creerCarte(String obj, String type) {
        try {
            String id          = lireChamp(obj, "id");
            String nom         = lireChamp(obj, "nom");
            String image       = lireChamp(obj, "image");
            String factionStr  = lireChamp(obj, "faction");
            String nomEffet    = lireChamp(obj, "effet");
            Faction faction    = factionStr != null ? Faction.valueOf(factionStr) : null;

            switch (type) {
                case "PERSONNAGE": {
                    int pv          = lireInt(obj, "pv");
                    int attaque     = lireInt(obj, "attaque");
                    int coutEnergie = lireInt(obj, "coutEnergie");
                    Personnage p    = new Personnage(id, nom, image, mc, pv, attaque);
                    p.coutEnergie   = coutEnergie;
                    p.faction       = faction;
                    p.nomEffet      = nomEffet;
                    return p;
                }
                case "SORT": {
                    String description = lireChamp(obj, "description");
                    Sort s             = new Sort(id, nom, image, mc, new EffetVide());
                    s.faction          = faction;
                    s.nomEffet         = nomEffet;
                    s.description      = description;
                    return s;
                }
                case "OBJET": {
                    String description = lireChamp(obj, "description");
                    Objet o            = new Objet(id, nom, image, mc, new EffetVide());
                    o.faction          = faction;
                    o.nomEffet         = nomEffet;
                    o.description      = description;
                    return o;
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur parsing carte : " + e.getMessage());
        }
        return null;
    }

    // -------------------------------------------------------
    // Lit la valeur d'un champ string : "clé": "valeur"
    // -------------------------------------------------------
    private String lireChamp(String obj, String cle) {
        String marqueur = "\"" + cle + "\"";
        int idx = obj.indexOf(marqueur);
        if (idx == -1) return null;
        int deuxPoints = obj.indexOf(':', idx);
        if (deuxPoints == -1) return null;
        int debutVal = obj.indexOf('"', deuxPoints);
        if (debutVal == -1) return null;
        int finVal = debutVal + 1;
        while (finVal < obj.length()) {
            if (obj.charAt(finVal) == '"' && obj.charAt(finVal - 1) != '\\') break;
            finVal++;
        }
        return obj.substring(debutVal + 1, finVal);
    }

    // -------------------------------------------------------
    // Lit la valeur d'un champ entier : "clé": 42
    // -------------------------------------------------------
    private int lireInt(String obj, String cle) {
        String marqueur = "\"" + cle + "\"";
        int idx = obj.indexOf(marqueur);
        if (idx == -1) return 0;
        int deuxPoints = obj.indexOf(':', idx);
        if (deuxPoints == -1) return 0;
        int debut = deuxPoints + 1;
        while (debut < obj.length() && (obj.charAt(debut) == ' ' || obj.charAt(debut) == '\n' || obj.charAt(debut) == '\r')) debut++;
        int fin = debut;
        while (fin < obj.length() && Character.isDigit(obj.charAt(fin))) fin++;
        if (debut == fin) return 0;
        return Integer.parseInt(obj.substring(debut, fin));
    }

    // -------------------------------------------------------
    // Trouve la position du caractère fermant correspondant
    // -------------------------------------------------------
    private int trouverFermeture(String s, int debut, char ouvrant, char fermant) {
        int profondeur = 0;
        for (int i = debut; i < s.length(); i++) {
            if (s.charAt(i) == ouvrant)  profondeur++;
            if (s.charAt(i) == fermant) { profondeur--; if (profondeur == 0) return i; }
        }
        return -1;
    }

    // -------------------------------------------------------
    // Extrait la liste des blocs { ... } d'un tableau JSON
    // -------------------------------------------------------
    private List<String> extraireObjets(String tableau) {
        List<String> objets = new ArrayList<>();
        int i = 0;
        while (i < tableau.length()) {
            int debut = tableau.indexOf('{', i);
            if (debut == -1) break;
            int fin = trouverFermeture(tableau, debut, '{', '}');
            if (fin == -1) break;
            objets.add(tableau.substring(debut + 1, fin));
            i = fin + 1;
        }
        return objets;
    }
}