package data;

import java.util.ArrayList;
import java.util.List;

import Controller.MenuController;
import Model.Carte;

/**
 * ChargeurCartes — façade pour le chargement des cartes.
 *
 * Utilisation :
 *   ChargeurCartes chargeur = new ChargeurCartes(menuController);
 *   List<Carte> cartes = chargeur.charger();
 *
 * Tente d'abord de charger depuis cartes.json (via CarteDAO).
 * Si le fichier est absent ou vide, retourne une liste vide
 * (le Model.java se chargera d'initialiser les cartes par défaut).
 */
public class ChargeurCartes {

    private final MenuController mc;

    public ChargeurCartes(MenuController mc) {
        this.mc = mc;
    }

    /**
     * Charge toutes les cartes disponibles.
     * @return liste des cartes chargées (jamais null)
     */
    public List<Carte> charger() {
        try {
            CarteDAO dao = new CarteDAO(mc);
            List<Carte> cartes = dao.chargerToutesLesCartes();
            if (cartes == null) return new ArrayList<>();
            return cartes;
        } catch (Exception e) {
            System.err.println("ChargeurCartes : impossible de charger cartes.json — " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Retourne uniquement les cartes d'une faction donnée.
     */
    public List<Carte> chargerParFaction(Model.Model.Faction faction) {
        List<Carte> toutes = charger();
        List<Carte> filtrees = new ArrayList<>();
        for (Carte c : toutes) {
            // Les sous-classes Personnage/Sort/Objet exposent un champ faction
            // On utilise la réflexion pour éviter le couplage fort
            try {
                var champ = c.getClass().getField("faction");
                Object val = champ.get(c);
                if (faction.equals(val)) {
                    filtrees.add(c);
                }
            } catch (Exception ignored) {}
        }
        return filtrees;
    }
}
