package Controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

import Model.Carte;
import Model.Carte.POSITION;
import Model.Deck;
import Model.Joueur;
import Model.Model;
import Model.Model.EtatPossible;
import Model.Personnage;
import View.MenuManager;

public class MenuController implements ActionListener, ComponentListener {
	public MenuManager menuManager;
	public Model m;
	public GameController gc;
	public MenuController() {
	}


	public void setMenuManager(MenuManager menuManager) {
		this.menuManager=menuManager;
	}
	
	public  void setGameController(GameController gc) {
		this.gc=gc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if (e.getActionCommand()=="SHOWMENU") {
			menuManager.showMenu();
		} else if (e.getActionCommand()=="SAVE") {
			menuManager.showSave();
		} else if (e.getActionCommand()=="JOUER") {
			Boolean ok = peutCommencer();
			if (ok) {
				menuManager.showJeu();
			} else {
				System.err.println("deck d'un des joueurs incomplet");
			}
		} else if (e.getActionCommand()=="CREER") {
			menuManager.showCreer();
		
		} else if (e.getActionCommand()=="OPTION") {
		
		} else if (e.getActionCommand()=="QUITTER") {
			System.exit(0);
		} else if (e.getActionCommand()=="REPRENDRE") {
			menuManager.showJeuSansRecommancer();
		} else if (e.getActionCommand()=="CHOISIS") {
			menuManager.showChoisis();
		} else if (e.getActionCommand()=="INFO") {
			menuManager.showInfo();
		}else if (e.getActionCommand()=="SETJOUEUR1") {
			m.setJoueurEnCours(m.joueur1);
			menuManager.creePanel.onPlayerModified();
			menuManager.showCreer();
		} else if (e.getActionCommand()=="SETJOUEUR2") {
			m.setJoueurEnCours(m.joueur2);
			menuManager.creePanel.onPlayerModified();
			menuManager.showCreer();
		} else if (e.getActionCommand()=="REINITIALISER") {
			m.joueurEnCours.deck.removeAll(m.joueurEnCours.deck);
			menuManager.creePanel.onReinitialiser();
			menuManager.showCreer();
		}
	}
	
	public void carteClique(Carte c) {
		EtatPossible etat = Model.etatApp;
		System.out.println(c.position);
		if (etat== EtatPossible.CREATIONDECK) {
			System.out.println(c);
			if (c.position==null) {
				Carte copy=c.copy();
				copy.setJoueur(m.joueurEnCours);
				copy.setPosition(Carte.POSITION.DECK);
				Boolean ajouté = m.joueurEnCours.deck.ajouter(copy);
				if (ajouté) {
					menuManager.creePanel.onDeckModified(copy);
				}
				System.out.println(c.nom);
				System.out.println(m.joueurEnCours.deck.size());
				System.out.println(m.joueurEnCours.deck);
			} else {
				System.err.println("dedans");
			}
			
		} else if (etat == EtatPossible.COMBAT) {
			gc.carteClique(c, m.joueurEnCours);
		}
	}
	
	public void carteClique(Carte c,Joueur j,POSITION p) {
		EtatPossible etat = Model.etatApp;
		System.out.println(p);
		if (etat== EtatPossible.CREATIONDECK) {
			Boolean ajouté = m.joueurEnCours.deck.ajouter(c);
			if (ajouté) {
				menuManager.creePanel.onDeckModified(c);
			}
			System.out.println(c.nom);
			System.out.println(m.joueurEnCours.deck.size());
			System.out.println(m.joueurEnCours.deck);
		}
	}
	
	public void changeEtat(Model.EtatPossible etat) {
		Model.etatApp=etat;
	}
	
	/**
	 * Verifie que chaque joueur a au moins un Personnage dans son deck.
	 * Sans Personnage, il est impossible de placer un actif → crash garanti.
	 */
	public Boolean peutCommencer() {
		if (!aPersonnageDansDeck(m.joueur1.deck)) {
			System.err.println("Joueur 1 doit avoir au moins un Personnage dans son deck !");
			return false;
		}
		if (!aPersonnageDansDeck(m.joueur2.deck)) {
			System.err.println("Joueur 2 doit avoir au moins un Personnage dans son deck !");
			return false;
		}
		return true;
	}

	/** Retourne true si le deck contient au moins un Personnage. */
	private boolean aPersonnageDansDeck(Deck deck) {
		for (Carte c : deck) {
			if (c instanceof Personnage) return true;
		}
		return false;
	}


	@Override
	public void componentResized(ComponentEvent e) {
		if (e.getSource() instanceof JFrame) {
            JFrame frame = (JFrame) e.getSource();
            menuManager.frameSize=frame.getSize();
            menuManager.onFrameResize();
		}
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {		
	}


	public void setModel(Model model) {
		this.m=model;
	}

}
