package View;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import Model.Carte;

public class CreerDeck extends JPanel {
    private static final long serialVersionUID = 1L;

    public MenuManager menuManager;
    public ArrayList<Carte> cartes;
    public float scale=1; 
    public JButton buttonRetour;
    public JScrollPane cartesDispo;
    public JScrollPane cartesDuDeck;
    public JPanel leftPanel;

    public CreerDeck(MenuManager menuManager) {
        this.menuManager= menuManager;
        this.cartes=menuManager.model.lesCartes;
        
        this.setLayout(new BorderLayout());
        
        this.cartesDispo= new JScrollPane();
        
        JPanel grandLeftPanel= new JPanel();
        grandLeftPanel.setLayout(new BorderLayout());
        
        JPanel panelPourLeLabel = new JPanel();
        JLabel labelCartesDispo= new JLabel("CARTES DISPONIBLES :");
        labelCartesDispo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panelPourLeLabel.add(labelCartesDispo);
        grandLeftPanel.add(panelPourLeLabel,BorderLayout.PAGE_START);
        labelCartesDispo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        leftPanel= new JPanel();
        leftPanel.setLayout(new GridLayout(0, 2, 10, 10)); 
        leftPanel.setPreferredSize(new Dimension(400, Math.round(200*cartes.size())/2)); 
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        
        for (Carte c : cartes) {
            leftPanel.add(c);
        }

        grandLeftPanel.add(leftPanel,BorderLayout.CENTER);     
        cartesDispo.setViewportView(grandLeftPanel);
        
        
        this.cartesDuDeck= new JScrollPane();
        JPanel rightPanel= new JPanel();
        cartesDuDeck.add(rightPanel);
        
        this.add(cartesDispo,BorderLayout.LINE_START);
        this.add(cartesDuDeck,BorderLayout.LINE_END);
        
        this.buttonRetour= new JButton("Retour");
        buttonRetour.setActionCommand("SHOWMENU");
        buttonRetour.addActionListener(menuManager.menuController);
        this.add(buttonRetour,BorderLayout.PAGE_END);
     
    }
    
    public void onFrameResize(Dimension size) {
    	leftPanel.setPreferredSize(new Dimension(size.width/2, Math.round(200*cartes.size())/2));
    	leftPanel.revalidate();
    	leftPanel.repaint();
    	System.out.println(size);
    }
    
    
    
}