package Controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

import Model.Carte;
import Model.Carte.POSITION;
import Model.Joueur;
import Model.Model;
import Model.Model.EtatPossible;
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
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		if (e.getActionCommand()=="SHOWMENU") {
			menuManager.showMenu();
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
		} else if (e.getActionCommand()=="CHOISIS") {
			menuManager.showChoisis();
		} else if (e.getActionCommand()=="SETJOUEUR1") {
			m.setJoueurEnCours(m.joueur1);
			menuManager.creePanel.onPlayerModified();
			menuManager.showCreer();
		} else if (e.getActionCommand()=="SETJOUEUR2") {
			m.setJoueurEnCours(m.joueur2);
			menuManager.creePanel.onPlayerModified();
			menuManager.showCreer();
		}
	}
	
	public void carteClique(Carte c) {
		EtatPossible etat = Model.etatApp;
		
		if (etat== EtatPossible.CREATIONDECK) {
			Boolean ajouté = m.joueurEnCours.deck.ajouter(c);
			if (ajouté) {
				menuManager.creePanel.onDeckModified(c);
			}
			System.out.println(c.nom);
			System.out.println(m.joueurEnCours.deck.size());
			System.out.println(m.joueurEnCours.deck);
		} else if (etat == EtatPossible.COMBAT) {
			System.out.println("combat dans menuController");
			m.joueurEnCours.carteSelectionnee=c;
			carteClique(c, m.joueurEnCours,c.position);
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
		} else if (etat == EtatPossible.COMBAT) {
			gc.carteClique(c, j);
		}
	}
	
	public void changeEtat(Model.EtatPossible etat) {
		Model.etatApp=etat;
	}
	
	public Boolean peutCommencer() {
		if (m.joueur1.deck.size()==0) {
			if (m.joueur2.deck.size()==0) {
				System.err.println("0 carte dans 1 des decks");
				return false;
			}
		}
		
		if (m.joueur1.deck.size()==20 || m.joueur1.deck.size()==0) {
			if (m.joueur2.deck.size()==20 || m.joueur2.deck.size()==0) {
				return true;
			}
		}
		// A MODIFIER PLUS TARD METTRE FALSE POUR QUE LE COMBAT SE LANCE PAS TANT QUE DECK PAS COMPLET
		return true; 
	}


	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() instanceof JFrame) {
            JFrame frame = (JFrame) e.getSource();
            menuManager.frameSize=frame.getSize();
            menuManager.onFrameResize();
		}
	}


	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.m=model;
	}

}
