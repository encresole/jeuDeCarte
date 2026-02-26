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
        menuPanel = new JPanel();
        menuPanel.add(new JLabel("Menu Principal"));

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

        // Simuler le changement d'écran après quelques secondes
        Timer timer = new Timer(3000, e -> showCreerDeck());  // Après 3 secondes, passer à "Créer un Deck"
        timer.setRepeats(false);  // Ne répète pas l'événement
        timer.start();
    }

    // Méthodes pour afficher les différents panneaux
    private void showMenu() {
        cardLayout.show(cardPanel, "Menu");
    }

    private void showCreerDeck() {
        cardLayout.show(cardPanel, "Créer un Deck");

        // Simuler la transition vers "Combat" après quelques secondes
        Timer timer = new Timer(3000, e -> showCombat());
        timer.setRepeats(false);  // Ne répète pas l'événement
        timer.start();
    }

    private void showCombat() {
        cardLayout.show(cardPanel, "Combat");
    }

}