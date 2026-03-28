package Model;

/**
 * EffetBuff — implémente l'interface Effet
 * Auteur : Raphael
 *
 * Augmente l'attaque du personnage actif du joueur cible.
 * Utilisé par les cartes Sort de type buff offensif.
 *
 * Exemple d'utilisation dans Model.java :
 *   new Sort("s2", "Rage", "/images/sorts/rage.png", mc, new EffetBuff(20));
 */
public class EffetBuff implements Effet {

    private int bonusAttaque;

    public EffetBuff(int bonusAttaque) {
        this.bonusAttaque = bonusAttaque;
    }

    @Override
    public void appliquer(Joueur cible) {
        if (cible.actif instanceof Personnage) {
            Personnage p = (Personnage) cible.actif;
            p.attaque += bonusAttaque;
            System.out.println("[Buff] " + p.nom + " gagne +" + bonusAttaque + " ATQ ! (ATQ : " + p.attaque + ")");
        }
    }

    @Override
    public String getDescription() {
        return "Augmente l'attaque du personnage actif de " + bonusAttaque + ".";
    }
}
