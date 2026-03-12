package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Joueur;
import Model.Model;
import Model.Model.EtatPossible;
import View.MenuManager;

public class MenuController implements ActionListener {
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
	
	public void carteClique(String nom) {
		EtatPossible etat = Model.etatApp;
		
		if (etat== EtatPossible.CREATIONDECK) {
			joueur.deck.ajouter(null);
		}
		System.out.println("carte "+ nom);
		System.out.println("cliquée dans "+ etat);
		System.out.println(joueur.deck);
		System.out.println(joueur.deck.size());
	}
	
	public void changeEtat(Model.EtatPossible etat) {
		Model.etatApp=etat;
	}

}
