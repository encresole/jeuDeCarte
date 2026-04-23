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
    
    Deck deckJ1;
    Deck deckJ2;
    
    public GameController(Model m) {
        this.m = m;
    }

    public void commencerCombat(Joueur joueur1, Joueur joueur2) {
        m.partieEnCours = new Partie(joueur1, joueur2, Model.TypeDePartie.JcJ);
        combat = new Combat(m.partieEnCours);
        
        deckJ1 = joueur1.deck.copy();
        deckJ2 = joueur2.deck.copy();
        
        joueur1.banc.removeAll(joueur1.banc);
        joueur1.main.removeAll(joueur1.main);
        joueur1.actif=null;
        
        joueur2.banc.removeAll(joueur2.banc);
        joueur2.main.removeAll(joueur2.main);
        joueur2.actif=null;
        System.out.println(deckJ1);
        
        for (int i = 0; i < m.TAILLEMAINDEBUT; i++) {
        	if (deckJ1.size()==0) {
        		break;
        	}
        	int indexCarte=random.nextInt(deckJ1.size());
        	Carte c = deckJ1.get(indexCarte);
        	c.setPosition(POSITION.MAIN);
        	joueur1.main.add(c);
        	deckJ1.remove(c);
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
        
        if (m.partieEnCours.tourDe==1) {
			m.setJoueurEnCours(m.joueur2);
		} else {
			m.setJoueurEnCours(m.joueur1);
		}
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
        // On joue le sort directement (sans passer par jouerTour pour éviter
        // un double-changement de tourDe) puis on clôture le tour normalement.
        Joueur attaquant = m.joueurEnCours;
        Joueur defenseur = (m.joueurEnCours == m.joueur1) ? m.joueur2 : m.joueur1;
        getCombat().jouerSort(attaquant, defenseur);
        System.out.println("[GameController] UTILISER (sort joué)");
        System.out.println(getCombat().getHistorique());
        finDuTour();
        menuManager.gamePanel.refresh();
    }

    public void retraite() {
        if (m.partieEnCours == null || m.partieEnCours.finPartie) {
            return;
        }
        //getCombat().jouerTour(Model.ActionJoueur.PASSER);
        Joueur j = m.joueurEnCours;
        if (j.banc.size()==0) return;
        Personnage oldActive=(Personnage) j.actif;
        Personnage copy=(Personnage) oldActive.copy();
        
        copy.setPosition(POSITION.BANC);
        copy.setJoueur(j);
        copy.pv=oldActive.pv;
        copy.pvMax=oldActive.pvMax;
        copy.energie= oldActive.energie;
        j.banc.add(copy);
        j.actif=null;
        menuManager.gamePanel.refresh();
        getCombat().promouvoirBanc(j);

        System.out.println("[GameController] RETRAITE");
        System.out.println(getCombat().getHistorique());
        finDuTour();
        menuManager.gamePanel.refresh();
    }

    public void attaquer() {
        if (m.partieEnCours == null || m.partieEnCours.finPartie) {
            return;
        }
        boolean fin = getCombat().jouerTour(Model.ActionJoueur.ATTAQUER);
        System.out.println("[GameController] FIN DU TOUR (attaque)");
        System.out.println(getCombat().getHistorique());
        if (fin) {
            System.out.println("[GameController] Partie terminée !");
        }
        finDuTour();
        menuManager.gamePanel.refresh();
    }
    
    public void carteClique(Carte c,Joueur j) {
		EtatPossible etat = Model.etatApp;
		System.out.println("click "+ c);
		if (c.joueur==m.joueurEnCours) {
			if (etat== EtatPossible.COMBAT) {
				j.unselectAll();
				j.carteSelectionnee=c;
				System.out.println("selectionée en combat "+ j.carteSelectionnee+" par "+j);
				c.setSelectionnee(true);
			}
		}
	}

	public void placerSurBanc() {
		Joueur joueur= m.joueurEnCours;
		Carte c;
		if (joueur.carteSelectionnee==null) return;
		c=joueur.carteSelectionnee;
		if (c.position!=POSITION.MAIN) return;
		Carte nouvelleC=c.copy();
		nouvelleC.setPosition(POSITION.BANC);
		nouvelleC.setJoueur(joueur);
		joueur.banc.add(nouvelleC);
		joueur.main.remove(c);
		joueur.carteSelectionnee=null;
		menuManager.gamePanel.refresh();
	}
	
	public void placerEnActif() {
		Joueur joueur= m.joueurEnCours;
		Carte c;
		if (joueur.carteSelectionnee==null) return;
		c=joueur.carteSelectionnee;
		if (joueur.actif!=null) return;
		if (c.position==POSITION.ACTIF) return;
		Carte nouvelleC=c.copy();
		nouvelleC.setPosition(POSITION.ACTIF);
		nouvelleC.setJoueur(joueur);
		joueur.actif=nouvelleC;
		if (c.position==POSITION.MAIN) {
			joueur.main.remove(c);
		} else {
			joueur.banc.remove(c);
		}
		joueur.carteSelectionnee=null;
		menuManager.gamePanel.refresh();
	}

	public void finDuTour() {
		Boolean ok = true;
		if (m.partieEnCours.tour<2) {
			if (m.joueurEnCours.actif==null) {
				System.err.println("Placer au moins une carte active avant de commencer");
				ok=false;
			}
		}
		
		if (ok) {
			piocherCarte();
			m.partieEnCours.tour++;
			m.joueurEnCours.unselectAll();
			if (m.joueurEnCours==m.joueur1) {
				m.setJoueurEnCours(m.joueur2);
				m.partieEnCours.tourDe=1;
			} else {
				m.setJoueurEnCours(m.joueur1);
				m.partieEnCours.tourDe=0;
			} 
			updateStats(m.joueur1);
			updateStats(m.joueur2);
			menuManager.gamePanel.onTourUpdate();
			menuManager.gamePanel.refresh();
		}
	}
	
	public void updateStats(Joueur joueur) {
		// Personnages sur le banc : régénèrent PV (+10) et énergie (+30 selon Gameplay.txt)
		for (Carte carte : joueur.banc) {
			Personnage p = (Personnage) carte;
			p.pv     = Math.min(p.pvMax,      p.pv     + 10);
			p.energie = Math.min(p.energieMax, p.energie + 30);
		}
		// Personnage actif : régénère uniquement de l'énergie (+20 selon Gameplay.txt)
		if (joueur.actif instanceof Personnage) {
			Personnage p = (Personnage) joueur.actif;
			p.energie = Math.min(p.energieMax, p.energie + 20);
		}
	}
	
	public void piocherCarte() {
		if (m.joueurEnCours==m.joueur1) {
			if (deckJ1.size()==0) return;
			int indexPioche=random.nextInt(deckJ1.size());
			deckJ1.get(indexPioche).position=POSITION.MAIN;
			m.joueurEnCours.main.add(deckJ1.get(indexPioche));
			deckJ1.remove(indexPioche);
		} else {
			if (deckJ2.size()==0) return;
			int indexPioche=random.nextInt(deckJ2.size());
			deckJ2.get(indexPioche).position=POSITION.MAIN;
			m.joueurEnCours.main.add(deckJ2.get(indexPioche));
			deckJ2.remove(indexPioche);
		}
	}
	
	public void setMenuManager(MenuManager menuManager) {
		this.menuManager=menuManager;
	}

}