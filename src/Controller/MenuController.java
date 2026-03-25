package Controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

import Model.Carte;
import Model.Model;
import Model.Model.EtatPossible;
import View.MenuManager;

public class MenuController implements ActionListener, ComponentListener {
	public MenuManager menuManager;
	public Model m;
	
	public MenuController() {
	}


	public void setMenuManager(MenuManager menuManager) {
		this.menuManager=menuManager;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		if (e.getActionCommand()=="SHOWMENU") {
			menuManager.showMenu();
		} else if (e.getActionCommand()=="JOUER") {
		
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
		}
	}
	
	public void changeEtat(Model.EtatPossible etat) {
		Model.etatApp=etat;
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
