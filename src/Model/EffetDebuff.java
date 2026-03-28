package Model;

/**
 * EffetDebuff — implémente l'interface Effet
 * Auteur : Raphael
 *
 * Réduit l'attaque du personnage actif du joueur cible (l'adversaire).
 * Math.max(0, ...) empêche l'attaque de passer en négatif.
 * Utilisé par les cartes Sort de type affaiblissement.
 *
 * Exemple d'utilisation dans Model.java :
 *   new Sort("s3", "Malédiction", "/images/sorts/malediction.png", mc, new EffetDebuff(25));
 */
public class EffetDebuff implements Effet {

    private int malusAttaque;

    public EffetDebuff(int malusAttaque) {
        this.malusAttaque = malusAttaque;
    }

    @Override
    public void appliquer(Joueur cible) {
        if (cible.actif instanceof Personnage) {
            Personnage p = (Personnage) cible.actif;
            p.attaque = Math.max(0, p.attaque - malusAttaque);
            System.out.println("[Debuff] " + p.nom + " perd " + malusAttaque + " ATQ ! (ATQ : " + p.attaque + ")");
        }
    }

    @Override
    public String getDescription() {
        return "Réduit l'attaque du personnage adverse de " + malusAttaque + ".";
    }
}
