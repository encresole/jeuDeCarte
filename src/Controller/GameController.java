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
import Model.Carte.POSITION;
import Model.Model.EtatPossible;
import View.MenuManager;

public class GameController {

    public Model m;
    Random random = new Random();
    public Combat combat;
    public MenuManager menuManager;

    public GameController(Model m) {
        this.m = m;
    }

    public void commencerCombat(Joueur joueur1, Joueur joueur2) {
        m.partieEnCours = new Partie(joueur1, joueur2, Model.TypeDePartie.JcJ);
        combat = new Combat(m.partieEnCours);
        
        Deck deckJ1 = joueur1.deck.copy();
        Deck deckJ2 = joueur2.deck.copy();
        
        joueur1.banc.removeAll(joueur1.banc);
        joueur1.main.removeAll(joueur1.main);
        
        joueur2.banc.removeAll(joueur2.banc);
        joueur2.main.removeAll(joueur2.main);
        
        for (int i = 0; i < m.TAILLEMAINDEBUT; i++) {
        	if (deckJ1.size()==0) {
        		break;
        	}
        	int indexCarte=random.nextInt(deckJ1.size());
        	Carte c = deckJ1.get(indexCarte);
        	c.setPosition(POSITION.MAIN);
        	joueur1.main.add(c);
        	deckJ1.remove(c);
        	System.out.println("deck :"+deckJ1);
        }

        for (int i = 0; i < m.TAILLEMAINDEBUT; i++) {
        	if (deckJ2.size()==0) {
        		break;
        	}
        	int indexCarte=random.nextInt(deckJ2.size());
        	Carte c = deckJ2.get(indexCarte);
        	c.setPosition(POSITION.MAIN);
        	joueur2.main.add(c);
        	deckJ2.remove(c);
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
		}
	}

	public void placerSurBanc() {
		Joueur joueur= m.joueurEnCours;
		Carte c;
		if (joueur.carteSelectionnee==null) return;
		c=joueur.carteSelectionnee;
		if (c.position!=POSITION.MAIN) return;
		Carte nouvelleC=c.copy();
		nouvelleC.position=POSITION.BANC;
		joueur.banc.add(nouvelleC);
		joueur.main.remove(c);
		System.out.println(joueur.banc);
		joueur.carteSelectionnee=null;
		menuManager.gamePanel.initialiser();
	}

	public void setMenuManager(MenuManager menuManager) {
		// TODO Auto-generated method stub
		this.menuManager=menuManager;
	}
}