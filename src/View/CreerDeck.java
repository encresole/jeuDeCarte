package View;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import Model.Carte;
import Model.Carte.POSITION;

public class CreerDeck extends JPanel {
    private static final long serialVersionUID = 1L;

    public MenuManager menuManager;
    public ArrayList<Carte> cartes;
    public float scale=1; 
    public ButtonAgesOfClash buttonRetour;
    public JScrollPane cartesDispo;
    public JScrollPane cartesDuDeck;
    public JPanel leftPanel;
    public JPanel grandLeftPanel;
    public JPanel panelPourLeLabel;
    public JLabel labelCartesDispo;
    public JPanel rightPanel;
    public JPanel grandRightPanel;
    public JPanel panelPourLeLabelDeck;
    public JLabel labelCartesDeck;
    public Dimension size;
    
    public CreerDeck(MenuManager menuManager) {
        this.menuManager= menuManager;
        this.cartes=menuManager.model.lesCartes;
        
        this.setLayout(new BorderLayout());
        
        // left
        
        this.cartesDispo= new JScrollPane();
        
        grandLeftPanel= new PanelAgesOfClash();
        grandLeftPanel.setLayout(new BorderLayout());
        
        panelPourLeLabel = new PanelAgesOfClash();
        labelCartesDispo= new LabelTitle("CARTES DISPONIBLES :");
        labelCartesDispo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panelPourLeLabel.add(labelCartesDispo);
        grandLeftPanel.add(panelPourLeLabel,BorderLayout.PAGE_START);
        labelCartesDispo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        leftPanel= new PanelAgesOfClash();
        leftPanel.setLayout(new GridLayout(0, 2, 10, 10)); 
        leftPanel.setPreferredSize(new Dimension(400, Math.round(200*cartes.size())/2)); 
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        
        
        for (Carte c : cartes) {
        	leftPanel.add(c.copy());
        }
        	
        grandLeftPanel.add(leftPanel,BorderLayout.CENTER);     
        cartesDispo.setViewportView(grandLeftPanel);
        
        // right
        
        this.cartesDuDeck= new JScrollPane();
        
        grandRightPanel= new PanelAgesOfClash();
        grandRightPanel.setLayout(new BorderLayout());
        
        panelPourLeLabelDeck = new PanelAgesOfClash();
        labelCartesDeck= new LabelTitle("VOUS AVEZ 0/20 CARTES");
        labelCartesDeck.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panelPourLeLabelDeck.add(labelCartesDeck);
        grandRightPanel.add(panelPourLeLabelDeck,BorderLayout.PAGE_START);
        labelCartesDeck.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        
        rightPanel= new PanelAgesOfClash();
        rightPanel.setLayout(new GridLayout(0, 2, 10, 10)); 
        rightPanel.setPreferredSize(new Dimension(380, Math.round(200*cartes.size())/2)); 
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        
        for (Carte c : menuManager.model.joueurEnCours.deck) {
        	rightPanel.add(c.copy());
        }
        
        grandRightPanel.add(rightPanel,BorderLayout.CENTER);     
        cartesDuDeck.setViewportView(grandRightPanel);
        
        // end (left/right)
        
        this.add(cartesDispo,BorderLayout.LINE_START);
        this.add(cartesDuDeck,BorderLayout.LINE_END);
        
        this.buttonRetour= new ButtonAgesOfClash("Retour");
        buttonRetour.setActionCommand("SHOWMENU");
        buttonRetour.addActionListener(menuManager.menuController);
        this.add(buttonRetour,BorderLayout.PAGE_END);
     
    }
    
    public void onFrameResize(Dimension size) {
    	this.size=size;
    	leftPanel.setPreferredSize(new Dimension(size.width/2-20, Math.round(200*cartes.size())/2));
    	leftPanel.revalidate();
    	leftPanel.repaint();
    	rightPanel.setPreferredSize(new Dimension(size.width/2, Math.round(200*menuManager.model.joueurEnCours.deck.size())/2));
    	rightPanel.revalidate();
    	rightPanel.repaint();
    	System.out.println(size);
    }
    
    public void onDeckModified(Carte c) {
        rightPanel.add(c);
        rightPanel.setPreferredSize(new Dimension(size.width/2, Math.round(200*menuManager.model.joueurEnCours.deck.size())/2));
        this.labelCartesDeck.setText("VOUS AVEZ "+ menuManager.model.joueurEnCours.deck.size() +"/20 CARTES");
        rightPanel.revalidate();
    	rightPanel.repaint();
    }
    
    public void onPlayerModified() {
    	rightPanel.removeAll();
    	System.out.println("player modified to "+ menuManager.model.joueurEnCours);
    	this.labelCartesDeck.setText("VOUS AVEZ "+ menuManager.model.joueurEnCours.deck.size() +"/20 CARTES");
    	for (Carte carte : menuManager.model.joueurEnCours.deck) {
    		Carte copy= carte.copy();
    		copy.setPosition(POSITION.DECK);
    		copy.setJoueur(carte.joueur);
			rightPanel.add(copy);
		}
    	rightPanel.revalidate();
    	rightPanel.repaint();
    }
}