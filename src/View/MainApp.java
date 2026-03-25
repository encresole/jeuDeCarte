package View;

import Controller.MenuController;
import Model.Model;

public class MainApp {
	
	public static void main(String[] args) {
		MenuController menucontroller= new MenuController();
		Model model= new Model(menucontroller);
		MenuManager menuManager= new MenuManager(menucontroller,model);
		menucontroller.setMenuManager(menuManager);
		menucontroller.setModel(model);
	}
}
