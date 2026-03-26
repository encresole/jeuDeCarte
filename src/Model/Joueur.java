package Model;

import java.util.ArrayList;

/**
 * Classe Joueur — représente un joueur en jeu
 * Auteur : Raphael
 *
 * Contient la carte active, le banc, la main et le deck du joueur.
 * Fournit les méthodes pour gérer ses cartes pendant la partie.
 */
public class Joueur {

    public String name;
    public Carte actif;
    public ArrayList<Carte> banc = new ArrayList<Carte>();
    public ArrayList<Carte> main = new ArrayList<Carte>();
    public Deck deck = new Deck();

    public Joueur(String name) {
        this.name = name;
    }

    public void ajouterActif(int i) {
        actif = main.get(i);
        main.remove(i);
    }

    public void piocherCarte() {
        if (deck.size() > 0) {
            main.add(deck.get(0));
            deck.remove(0);
        }
    }

    public void ajouterAuBanc(int i) {
        banc.add(main.get(i));
        main.remove(i);
    }

    public boolean estVivant() {
        if (actif != null) {
            if (actif instanceof Personnage) {
                Personnage p = (Personnage) actif;
                if (p.pv > 0) {
                    return true;
                }
            }
        }
        if (banc.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}