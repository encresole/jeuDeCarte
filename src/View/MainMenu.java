package View;

import javax.swing.*;
import java.awt.*;

public class MainMenu {

    // Panneaux pour chaque "écran" de ton application
    private JPanel menuPanel;
    private JPanel creerDeckPanel;
    private JPanel combatPanel;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public MainMenu() {
        // Créer la fenêtre principale (JFrame)
        JFrame frame = new JFrame("Jeu de Cartes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Créer un CardLayout et un JPanel qui le contiendra
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Initialiser les différents panneaux (screens)
        int largeur = frame.getWidth();
        int hauteur = frame.getHeight();
        menuPanel = new JPanel();
        menuPanel.setLayout(null);
        JLabel title = new JLabel("AGES OF CLASH");
        int demiLargeur = largeur / 2;
        int demiHauteur = hauteur / 2;
        title.setBounds(370,270,100,30);
        JButton buttonJouer = new JButton("Jouer");
        buttonJouer.setBounds(370,300,100,30);
        menuPanel.add(title);
        menuPanel.add(buttonJouer);
        

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
    
    

    private void showMenu() {
        cardLayout.show(cardPanel, "Menu");
    }

    private void showCreerDeck() {
        cardLayout.show(cardPanel, "Créer un Deck");
    }

    private void showCombat() {
        cardLayout.show(cardPanel, "Combat");
    }

}