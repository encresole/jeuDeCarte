package data;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import Model.*;
import Model.Model.TypeDePartie;

/**
 * Serialiseur — sauvegarde et charge l'état d'une Partie dans parties.json.
 * Utilise uniquement Java pur, sans librairie externe.
 *
 * Format JSON :
 * {
 *   "parties": [
 *     {
 *       "id": "partie_1",
 *       "typeDePartie": "JcJ",
 *       "tour": 3,
 *       "tourDe": 0,
 *       "finPartie": false,
 *       "joueur1": { ... },
 *       "joueur2": { ... }
 *     }
 *   ]
 * }
 */
public class Serialiseur {

    private static final String CHEMIN_JSON = "parties.json";

    // -------------------------------------------------------
    // SAUVEGARDE
    // -------------------------------------------------------

    public void sauvegarderPartie(Partie partie, String idPartie) {
        try {
            String contenuActuel = lireFichier();
            List<String> partiesExistantes = extrairePartiesJson(contenuActuel);

            List<String> filtrees = new ArrayList<>();
            for (String p : partiesExistantes) {
                if (!idPartie.equals(lireChamp(p, "id"))) {
                    filtrees.add(p);
                }
            }
            filtrees.add(partieVersJson(partie, idPartie));

            ecrireFichier(assemblerJson(filtrees));
            System.out.println("✅ Partie '" + idPartie + "' sauvegardée.");

        } catch (Exception e) {
            System.err.println("ERREUR sauvegarde : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------
    // SUPPRESSION
    // -------------------------------------------------------

    /**
     * Supprime une partie sauvegardée par son identifiant.
     * Si l'id est introuvable, ne fait rien.
     */
    public void supprimerPartie(String idPartie) {
        try {
            String contenuActuel = lireFichier();
            List<String> partiesExistantes = extrairePartiesJson(contenuActuel);

            List<String> filtrees = new ArrayList<>();
            boolean trouvee = false;
            for (String p : partiesExistantes) {
                if (idPartie.equals(lireChamp(p, "id"))) {
                    trouvee = true; // on l'ignore → suppression
                } else {
                    filtrees.add(p);
                }
            }

            if (!trouvee) {
                System.err.println("Partie '" + idPartie + "' introuvable, rien à supprimer.");
                return;
            }

            ecrireFichier(assemblerJson(filtrees));
            System.out.println("🗑 Partie '" + idPartie + "' supprimée.");

        } catch (Exception e) {
            System.err.println("ERREUR suppression : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------
    // CHARGEMENT
    // -------------------------------------------------------

    public Partie chargerPartie(String idPartie, List<Carte> toutesLesCartes) {
        try {
            String contenu = lireFichier();
            List<String> partiesJson = extrairePartiesJson(contenu);

            for (String pJson : partiesJson) {
                if (idPartie.equals(lireChamp(pJson, "id"))) {
                    return jsonVersPartie(pJson, toutesLesCartes);
                }
            }
            System.err.println("Partie '" + idPartie + "' introuvable.");
        } catch (Exception e) {
            System.err.println("ERREUR chargement : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<String> listerIdsParties() {
        List<String> ids = new ArrayList<>();
        try {
            String contenu = lireFichier();
            for (String p : extrairePartiesJson(contenu)) {
                String id = lireChamp(p, "id");
                if (id != null) ids.add(id);
            }
        } catch (Exception e) {
            System.err.println("ERREUR listage : " + e.getMessage());
        }
        return ids;
    }

    // -------------------------------------------------------
    // Sérialisation Partie → JSON
    // -------------------------------------------------------

    private String partieVersJson(Partie partie, String idPartie) {
        StringBuilder sb = new StringBuilder();
        sb.append("    {\n");
        sb.append("      \"id\": \"").append(idPartie).append("\",\n");
        sb.append("      \"typeDePartie\": \"").append(partie.typeDePartie.name()).append("\",\n");
        sb.append("      \"tour\": ").append(partie.tour).append(",\n");
        sb.append("      \"tourDe\": ").append(partie.tourDe).append(",\n");
        sb.append("      \"finPartie\": ").append(partie.finPartie).append(",\n");
        sb.append("      \"joueur1\": ").append(joueurVersJson(partie.joueur1)).append(",\n");
        sb.append("      \"joueur2\": ").append(joueurVersJson(partie.joueur2)).append("\n");
        sb.append("    }");
        return sb.toString();
    }

    private String joueurVersJson(Joueur joueur) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("        \"name\": \"").append(joueur.name).append("\",\n");
        if (joueur.actif != null) {
            sb.append("        \"actif\": \"").append(joueur.actif.id).append("\",\n");
        } else {
            sb.append("        \"actif\": null,\n");
        }
        sb.append("        \"banc\": ").append(listeCartesVersJson(joueur.banc)).append(",\n");
        sb.append("        \"main\": ").append(listeCartesVersJson(joueur.main)).append(",\n");
        sb.append("        \"deck\": ").append(listeCartesVersJson(new ArrayList<Carte>(joueur.deck))).append("\n");
        sb.append("      }");
        return sb.toString();
    }

    private String listeCartesVersJson(List<Carte> cartes) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < cartes.size(); i++) {
            sb.append("\"").append(cartes.get(i).id).append("\"");
            if (i < cartes.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // -------------------------------------------------------
    // Désérialisation JSON → Partie
    // -------------------------------------------------------

    private Partie jsonVersPartie(String json, List<Carte> toutesLesCartes) {
        String typeStr    = lireChamp(json, "typeDePartie");
        int tour          = lireInt(json, "tour");
        int tourDe        = lireInt(json, "tourDe");
        boolean finPartie = "true".equals(lireChamp(json, "finPartie"));

        String blocJ1 = extraireBlocJoueur(json, "joueur1");
        String blocJ2 = extraireBlocJoueur(json, "joueur2");

        Joueur j1 = jsonVersJoueur(blocJ1, toutesLesCartes);
        Joueur j2 = jsonVersJoueur(blocJ2, toutesLesCartes);

        TypeDePartie type = TypeDePartie.valueOf(typeStr);
        Partie p = new Partie(j1, j2, type);
        p.tour      = tour;
        p.tourDe    = tourDe;
        p.finPartie = finPartie;
        return p;
    }

    private Joueur jsonVersJoueur(String json, List<Carte> toutesLesCartes) {
        String name = lireChamp(json, "name");
        Joueur joueur = new Joueur(name);

        String actifId = lireChamp(json, "actif");
        if (actifId != null && !actifId.equals("null") && !actifId.isEmpty()) {
            Carte c = trouverCarte(actifId, toutesLesCartes);
            if (c != null) joueur.actif = c.copy();
        }

        for (String id : lireTableauStrings(json, "banc")) {
            Carte c = trouverCarte(id, toutesLesCartes);
            if (c != null) joueur.banc.add(c.copy());
        }

        for (String id : lireTableauStrings(json, "main")) {
            Carte c = trouverCarte(id, toutesLesCartes);
            if (c != null) joueur.main.add(c.copy());
        }

        for (String id : lireTableauStrings(json, "deck")) {
            Carte c = trouverCarte(id, toutesLesCartes);
            if (c != null) joueur.deck.ajouter(c.copy());
        }

        return joueur;
    }

    // -------------------------------------------------------
    // Utilitaires JSON
    // -------------------------------------------------------

    private String assemblerJson(List<String> parties) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n  \"parties\": [\n");
        for (int i = 0; i < parties.size(); i++) {
            sb.append(parties.get(i));
            if (i < parties.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("  ]\n}");
        return sb.toString();
    }

    private String extraireBlocJoueur(String json, String cle) {
        String marqueur = "\"" + cle + "\"";
        int idx = json.indexOf(marqueur);
        if (idx == -1) return "";
        int debutObj = json.indexOf('{', idx);
        if (debutObj == -1) return "";
        int finObj = trouverFermeture(json, debutObj, '{', '}');
        if (finObj == -1) return "";
        return json.substring(debutObj + 1, finObj);
    }

    private List<String> lireTableauStrings(String json, String cle) {
        List<String> result = new ArrayList<>();
        String marqueur = "\"" + cle + "\"";
        int idx = json.indexOf(marqueur);
        if (idx == -1) return result;
        int debutTab = json.indexOf('[', idx);
        if (debutTab == -1) return result;
        int finTab = trouverFermeture(json, debutTab, '[', ']');
        if (finTab == -1) return result;
        String contenu = json.substring(debutTab + 1, finTab).trim();
        if (contenu.isEmpty()) return result;

        int i = 0;
        while (i < contenu.length()) {
            int debut = contenu.indexOf('"', i);
            if (debut == -1) break;
            int fin = debut + 1;
            while (fin < contenu.length() && contenu.charAt(fin) != '"') fin++;
            result.add(contenu.substring(debut + 1, fin));
            i = fin + 1;
        }
        return result;
    }

    private String lireChamp(String obj, String cle) {
        String marqueur = "\"" + cle + "\"";
        int idx = obj.indexOf(marqueur);
        if (idx == -1) return null;
        int deuxPoints = obj.indexOf(':', idx);
        if (deuxPoints == -1) return null;

        int debut = deuxPoints + 1;
        while (debut < obj.length() && obj.charAt(debut) == ' ') debut++;
        if (debut >= obj.length()) return null;

        char premier = obj.charAt(debut);

        if (premier == '"') {
            int finVal = debut + 1;
            while (finVal < obj.length()) {
                if (obj.charAt(finVal) == '"' && obj.charAt(finVal - 1) != '\\') break;
                finVal++;
            }
            return obj.substring(debut + 1, finVal);
        }

        int fin = debut;
        while (fin < obj.length() && obj.charAt(fin) != ',' && obj.charAt(fin) != '\n' && obj.charAt(fin) != '}') fin++;
        return obj.substring(debut, fin).trim();
    }

    private int lireInt(String obj, String cle) {
        String val = lireChamp(obj, cle);
        if (val == null) return 0;
        try { return Integer.parseInt(val.trim()); } catch (Exception e) { return 0; }
    }

    private int trouverFermeture(String s, int debut, char ouvrant, char fermant) {
        int profondeur = 0;
        for (int i = debut; i < s.length(); i++) {
            if (s.charAt(i) == ouvrant) profondeur++;
            if (s.charAt(i) == fermant) { profondeur--; if (profondeur == 0) return i; }
        }
        return -1;
    }

    private List<String> extrairePartiesJson(String json) {
        List<String> parties = new ArrayList<>();
        if (json == null || json.isEmpty()) return parties;
        int idx = json.indexOf("\"parties\"");
        if (idx == -1) return parties;
        int debutTab = json.indexOf('[', idx);
        if (debutTab == -1) return parties;
        int finTab = trouverFermeture(json, debutTab, '[', ']');
        if (finTab == -1) return parties;
        String tableau = json.substring(debutTab + 1, finTab);
        int i = 0;
        while (i < tableau.length()) {
            int debut = tableau.indexOf('{', i);
            if (debut == -1) break;
            int fin = trouverFermeture(tableau, debut, '{', '}');
            if (fin == -1) break;
            parties.add("    " + tableau.substring(debut, fin + 1));
            i = fin + 1;
        }
        return parties;
    }

    // -------------------------------------------------------
    // Fichier I/O
    // -------------------------------------------------------

    private String lireFichier() {
        try {
            Path path = Paths.get(CHEMIN_JSON);
            if (!Files.exists(path)) return "";
            return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "";
        }
    }

    private void ecrireFichier(String contenu) throws IOException {
        Files.write(Paths.get(CHEMIN_JSON), contenu.getBytes(StandardCharsets.UTF_8));
    }

    // -------------------------------------------------------
    // Utilitaire
    // -------------------------------------------------------

    private Carte trouverCarte(String id, List<Carte> cartes) {
        for (Carte c : cartes) {
            if (id.equals(c.id)) return c;
        }
        System.err.println("⚠️ Carte introuvable avec l'id : " + id);
        return null;
    }
}
