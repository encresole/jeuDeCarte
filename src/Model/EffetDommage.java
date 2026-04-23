package Model;

/**
 * EffetDommage — implémente l'interface Effet
 * Auteur : Raphael
 *
 * Inflige des dégâts fixes au personnage actif du joueur cible (l'adversaire).
 * Contrairement à une attaque normale, ces dégâts ignorent l'énergie et s'appliquent
 * directement (utilisé par les sorts offensifs comme Jugement Divin ou Frappe Aérienne).
 *
 * Exemples d'utilisation dans Model.java :
 *   new Sort("s_jugement", "Jugement Divin", ..., new EffetDommage(50));
 *   new Sort("s_frappe",   "Frappe Aerienne", ..., new EffetDommage(80));
 */
public class EffetDommage implements Effet {

    private int degats;

    public EffetDommage(int degats) {
        this.degats = degats;
    }

    @Override
    public void appliquer(Joueur cible) {
        if (cible.actif instanceof Personnage) {
            Personnage p = (Personnage) cible.actif;
            p.pv -= degats;
            System.out.println("[Dommage] " + p.nom + " subit " + degats
                    + " dégâts fixes ! (PV : " + p.pv + ")");
        }
    }

    @Override
    public String getDescription() {
        return "Inflige " + degats + " dégâts fixes au personnage actif adverse.";
    }
}
