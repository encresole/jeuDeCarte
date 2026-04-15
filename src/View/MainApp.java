package View;

import Controller.GameController;
import Controller.MenuController;
import Model.Model;

public class MainApp {
	
	public static void main(String[] args) {
		MenuController menucontroller= new MenuController();
		Model model= new Model(menucontroller);
		GameController gameController = new GameController(model);
		MenuManager menuManager= new MenuManager(menucontroller,model, gameController);
		menucontroller.setGameController(gameController);
		menucontroller.setMenuManager(menuManager);
		menucontroller.setModel(model);
	}
}
