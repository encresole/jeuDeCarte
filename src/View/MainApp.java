package View;

import Controller.GameController;
import Controller.MenuController;
import Model.Model;

public class MainApp {
	
	public static void main(String[] args) {
		MusicPlayer musicPlayer = new MusicPlayer();
		MenuController menucontroller= new MenuController();
		Model model= new Model(menucontroller);
		GameController gameController = new GameController(model);
		MenuManager menuManager= new MenuManager(menucontroller,model, gameController, musicPlayer);
		menucontroller.setGameController(gameController);
		menucontroller.setMenuManager(menuManager);
		menucontroller.setModel(model);
		gameController.setMenuManager(menuManager);
		musicPlayer.playMusic();
	}	
}
