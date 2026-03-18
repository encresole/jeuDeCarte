package View;

import java.awt.Component;
import java.awt.Font;

import javax.swing.*;

public class MenuStart extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JLabel title;
	public JButton buttonJouer;
	public MenuManager menuManager;
	public JPanel centerPanel;
	
	public MenuStart(MenuManager menuManager) {
		// TODO Auto-generated constructor stub
		this.menuManager= menuManager;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
       
        
        title = new JLabel("AGES OF CLASH");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        buttonJouer = new JButton("Jouer");
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
