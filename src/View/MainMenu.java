package View;

import javax.swing.*;
import java.awt.*;

public class MainMenu {

    // Panneaux pour chaque "écran" de ton application
	public JPanel menuPanel;
	public JPanel creerDeckPanel;
	public JPanel combatPanel;
	public CardLayout cardLayout;
	public JPanel cardPanel;

    public MainMenu() {
        // Créer la fenêtre principale (JFrame)
        JFrame frame = new JFrame("Jeu de Cartes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Créer un CardLayout et un JPanel qui le contiendra
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Initialiser les différents panneaux (screens)
        menuPanel = new MenuStart(this);
        

        creerDeckPanel = new JPanel();
        creerDeckPanel.add(new JLabel("Créer un Deck"));

        combatPanel = new JPanel();
        combatPanel.add(new JLabel("Mode Combat"));

        // Ajouter les panneaux au cardPanel avec un nom unique pour chaque "carte"
        cardPanel.add(menuPanel, "Menu");
        cardPanel.add(creerDeckPanel, "Créer un Deck");
        cardPanel.add(combatPanel, "Combat");

        // Ajouter le cardPanel à la fenêtre
        frame.add(cardPanel);

        // Afficher la fenêtre
        frame.setVisible(true);

        // Afficher le premier panneau (Menu)
        showMenu();


    }
    
    

    public void showMenu() {
        cardLayout.show(cardPanel, "Menu");
    }

    public void showCreerDeck() {
        cardLayout.show(cardPanel, "Créer un Deck");
    }

    public void showCombat() {
        cardLayout.show(cardPanel, "Combat");
    }

}