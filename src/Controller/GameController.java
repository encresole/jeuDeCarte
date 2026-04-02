package Controller;

/**
 * GameController — Contrôleur principal du combat
 *
 * Ce qui a été fait :
 * - commencerCombat() : initialise la Partie, distribue les mains de départ, tire le premier joueur
 * - getCombat()       : retourne (ou crée) l'instance Combat liée à la partie en cours
 * - utiliser()        : action JOUER_SORT — joue le premier sort de la main du joueur actif
 * - retraite()        : action PASSER — le joueur actif passe son tour
 * - finDuTour()       : action ATTAQUER — le joueur actif attaque l'adversaire
 */

import java.util.Random;
import Model.*;

public class GameController {

    public Model m;
    Random random = new Random();
    public Combat combat;

    public GameController(Model m) {
        this.m = m;
    }

    public void commencerCombat(Joueur joueur1, Joueur joueur2) {
        m.partieEnCours = new Partie(joueur1, joueur2, Model.TypeDePartie.JcJ);
        combat = new Combat(m.partieEnCours);

        for (int i = 0; i < m.TAILLEMAINDEBUT; i++) {
            if (joueur1.deck.size() > i) {
                joueur1.main.add(joueur1.deck.get(i));
            }
        }

        for (int i = 0; i < m.TAILLEMAINDEBUT; i++) {
            if (joueur2.deck.size() > i) {
                joueur2.main.add(joueur2.deck.get(i));
            }
        }

        m.partieEnCours.tourDe = tirageJoueur();
    }

    public Combat getCombat() {
        if (combat == null && m.partieEnCours != null) {
            combat = new Combat(m.partieEnCours);
        }
        return combat;
    }

    public int tirageJoueur() {
        return random.nextInt(2);
    }

    public void utiliser() {
        if (m.partieEnCours == null || m.partieEnCours.finPartie) {
            return;
        }
        getCombat().jouerTour(Model.ActionJoueur.JOUER_SORT);
        System.out.println("[GameController] UTILISER (sort joué)");
        System.out.println(getCombat().getHistorique());
    }

    public void retraite() {
        if (m.partieEnCours == null || m.partieEnCours.finPartie) {
            return;
        }
        getCombat().jouerTour(Model.ActionJoueur.PASSER);
        System.out.println("[GameController] RETRAITE (tour passé)");
        System.out.println(getCombat().getHistorique());
    }

    public void finDuTour() {
        if (m.partieEnCours == null || m.partieEnCours.finPartie) {
            return;
        }
        boolean fin = getCombat().jouerTour(Model.ActionJoueur.ATTAQUER);
        System.out.println("[GameController] FIN DU TOUR (attaque)");
        System.out.println(getCombat().getHistorique());
        if (fin) {
            System.out.println("[GameController] Partie terminée !");
        }
    }
}