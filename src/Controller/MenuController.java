package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Model.Joueur;
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
		
	}

}
