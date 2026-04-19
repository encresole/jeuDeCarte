package View;

import javax.swing.*;

import Controller.GameController;
import Controller.MenuController;
import Model.Model;

import java.awt.*;

public class MenuManager {
	public MenuController menuController;
	public GameController gameController;
	public Model model;
	
	public MenuStart startPanel;
	public MainMenu menuPanel;
	public CreerDeck creePanel;
	public ChoixJoueur choixJoueurPanel;
	public GameView gamePanel;
	
	
	public JPanel cardPanel;
	
	public CardLayout cardLayout;
	
	
	
	public JFrame frame;
	public Dimension frameSize;
	

    public MenuManager(MenuController menuController,Model model, GameController gameController) {
    	this.gameController=gameController;
    	this.menuController= menuController;
    	this.model=model;
    	
        frame = new JFrame("Jeu de Cartes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        startPanel = new MenuStart(this);
        menuPanel= new MainMenu(this);
        creePanel = new CreerDeck(this);
        choixJoueurPanel = new ChoixJoueur(this);
        gamePanel = new GameView(this);
        

        cardPanel.add(startPanel, "Start");
        cardPanel.add(menuPanel,"Menu");
        cardPanel.add(creePanel,"Crée");
        cardPanel.add(choixJoueurPanel,"Choisis");
        cardPanel.add(gamePanel,"Jeu");

        frame.add(cardPanel);
        frame.setVisible(true);
        
        frameSize=frame.getSize();
        frame.addComponentListener(menuController);
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
    
    public void showChoisis() {
    	menuController.changeEtat(Model.EtatPossible.CHOIXJOUEUR);
        cardLayout.show(cardPanel, "Choisis");
    }

    public void showJeu() {
    	menuController.changeEtat(Model.EtatPossible.COMBAT);
    	gameController.commencerCombat(model.joueur1, model.joueur2);
    	gamePanel.refresh();
    	gamePanel.onCombatCommence();
    	cardLayout.show(cardPanel, "Jeu");
    }
    
	public void onFrameResize() {
		// TODO Auto-generated method stub
		creePanel.onFrameResize(frameSize);
	}

}