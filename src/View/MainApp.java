package View;

import Controller.MenuController;
import Model.Joueur;
import Model.Model;

public class MainApp {
	
	public static void main(String[] args) {
		Joueur joueur1 = new Joueur();
		MenuController menucontroller= new MenuController(joueur1);
		Model model= new Model(menucontroller);
		
		MenuManager menuManager= new MenuManager(menucontroller,model);
		menucontroller.setMenuManager(menuManager);
	}
}
