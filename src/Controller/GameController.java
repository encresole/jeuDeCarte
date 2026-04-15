package Controller;
import java.io.Console;

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
import Model.Carte.POSITION;
import Model.Model.EtatPossible;

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
        
        joueur1.banc.removeAll(joueur1.banc);
        joueur1.main.removeAll(joueur1.main);
        
        joueur2.banc.removeAll(joueur2.banc);
        joueur2.main.removeAll(joueur2.main);
        
        for (int i = 0; i < m.TAILLEMAINDEBUT; i++) {
        	int indexCarte=random.nextInt(joueur1.deck.size());
        	Carte c = joueur1.deck.get(indexCarte).copy();
        	c.setPosition(POSITION.MAIN);
        	joueur1.main.add(c);
        	System.out.println(joueur1.main);
        }

        for (int i = 0; i < m.TAILLEMAINDEBUT; i++) {
        	int indexCarte=random.nextInt(joueur2.deck.size());
        	Carte c = joueur2.deck.get(indexCarte).copy();
        	c.setPosition(POSITION.MAIN);
        	joueur2.main.add(c);
        	System.out.println(joueur2.main);
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
    
    public void carteClique(Carte c,Joueur j) {
		EtatPossible etat = Model.etatApp;
		if (etat== EtatPossible.COMBAT) {
			for (Carte carte : j.main) {
				System.out.println(carte);
				carte.setSelectionnee(false);
			}
			j.carteSelectionnee=c;
			c.setSelectionnee(true);
			System.out.println("caca");
		}
	}
}