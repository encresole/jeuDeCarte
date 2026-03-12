package View;

import javax.swing.*;

import Controller.MenuController;
import Model.Model;

import java.awt.*;

public class MenuManager {
	public MenuController menuController;
	public JPanel startPanel;
	public JPanel menuPanel;
	public JPanel creePanel;
	public CardLayout cardLayout;
	public JPanel cardPanel;
	public Model model;

    public MenuManager(MenuController menuController,Model model) {
    	
    	this.menuController= menuController;
    	this.model=model;
    	
        JFrame frame = new JFrame("Jeu de Cartes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        startPanel = new MenuStart(this);
        menuPanel= new MainMenu(this);
        creePanel = new CreerDeck(this);
        

        cardPanel.add(startPanel, "Start");
        cardPanel.add(menuPanel,"Menu");
        cardPanel.add(creePanel,"Crée");

        frame.add(cardPanel);
        frame.setVisible(true);
        showStart();

    }

    public void showMenu() {
    	menuController.changeEtat(Model.EtatPossible.MENU);
    	cardLayout.show(cardPanel, "Menu");
    }
    
    public void showStart() {
    	menuController.changeEtat(Model.EtatPossible.START);
        cardLayout.show(cardPanel, "Start");
    }
    
    public void showCreer() {
    	menuController.changeEtat(Model.EtatPossible.CREATIONDECK);
        cardLayout.show(cardPanel, "Crée");
    }

}