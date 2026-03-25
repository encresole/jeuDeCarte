package View;


import java.awt.Component;

import javax.swing.*;


public class MenuStart extends PanelAgesOfClash {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LabelTitle title;
	public ButtonAgesOfClash buttonJouer;
	public MenuManager menuManager;
	public JPanel centerPanel;
	
	public MenuStart(MenuManager menuManager) {
		// TODO Auto-generated constructor stub
		this.menuManager= menuManager;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
       
        
        title = new LabelTitle("AGES OF CLASH");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        buttonJouer = new ButtonAgesOfClash("Jouer");
        buttonJouer.addActionListener(menuManager.menuController);
        buttonJouer.setActionCommand("SHOWMENU");
        buttonJouer.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        
        
        this.add(Box.createVerticalGlue()); // pousse vers le bas
        
        this.add(title);
        this.add(Box.createVerticalStrut(20)); // espace entre
        this.add(buttonJouer);
 
        this.add(Box.createVerticalGlue()); // pousse vers le haut
	}
	
}
