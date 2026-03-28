package Model;

/**
 * EffetSoin — implémente l'interface Effet
 * Auteur : Raphael
 *
 * Restaure des PV au personnage actif du joueur cible.
 * Utilisé par les cartes Sort de type soin.
 *
 * Exemple d'utilisation dans Model.java :
 *   new Sort("s1", "Potion", "/images/sorts/potion.png", mc, new EffetSoin(40));
 */
public class EffetSoin implements Effet {

    private int quantiteSoin;

    public EffetSoin(int quantiteSoin) {
        this.quantiteSoin = quantiteSoin;
    }

    @Override
    public void appliquer(Joueur cible) {
        if (cible.actif instanceof Personnage) {
            Personnage p = (Personnage) cible.actif;
            p.pv += quantiteSoin;
            System.out.println("[Soin] " + p.nom + " récupère " + quantiteSoin + " PV ! (PV : " + p.pv + ")");
        }
    }

    @Override
    public String getDescription() {
        return "Restaure " + quantiteSoin + " PV au personnage actif.";
    }
}
