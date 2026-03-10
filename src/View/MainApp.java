package View;

import Controller.MenuController;
import Model.Model;

public class MainApp {
	
	public static void main(String[] args) {
		Model model= new Model();
		MenuController menucontroller= new MenuController();
		MenuManager menuManager= new MenuManager(menucontroller,model);
		menucontroller.setMenuManager(menuManager);
	}
}
