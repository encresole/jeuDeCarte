package View;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import Model.Carte;

public class CreerDeck extends JPanel {
    private static final long serialVersionUID = 1L;

    public MenuManager menuManager;
    public ArrayList<Carte> cartes;
    public float scale=1; 
    public JButton buttonRetour;
    public JScrollPane scrollable;

    public CreerDeck(MenuManager menuManager) {
        this.menuManager= menuManager;
        this.cartes=menuManager.model.lesCartes;
        
        this.setLayout(new BorderLayout());
        
        this.scrollable= new JScrollPane();
        
        this.buttonRetour= new JButton("<= Retour");
        buttonRetour.setActionCommand("SHOWMENU");
        buttonRetour.addActionListener(menuManager.menuController);
        this.add(buttonRetour,BorderLayout.PAGE_END);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int xOffset = 50; 
        int yOffset = 50;
        for (int i = 0; i < cartes.size(); i++) {
            Carte c = cartes.get(i);
            c.setPosition(xOffset + i * 120, yOffset);
            this.add(c); 
        }
    }
}