package Controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

import Model.Carte;
import Model.Joueur;
import Model.Model;
import Model.Model.EtatPossible;
import View.MenuManager;

public class MenuController implements ActionListener, ComponentListener {
	public MenuManager menuManager;
	public Joueur joueur;
	
	public MenuController(Joueur joueur) {
		this.joueur=joueur;
	}


	public void setMenuManager(MenuManager menuManager) {
		this.menuManager=menuManager;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand()=="SHOWMENU") {
			menuManager.showMenu();
		} else if (e.getActionCommand()=="JOUER") {
		
		} else if (e.getActionCommand()=="CREER") {
			menuManager.showCreer();
		
		} else if (e.getActionCommand()=="OPTION") {
		
		} else if (e.getActionCommand()=="QUITTER") {
			System.exit(0);
		}
	}
	
	public void carteClique(Carte c) {
		EtatPossible etat = Model.etatApp;
		
		if (etat== EtatPossible.CREATIONDECK) {
			joueur.deck.ajouter(c);
			System.out.println(c.nom);
			System.out.println(joueur.deck.size());
			System.out.println(joueur.deck);
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

}
