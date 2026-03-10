package View;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
	private static final long serialVersionUID = 1L;

	public MenuManager menuManager;
	JButton buttonJouer;
	JButton buttonCreer;
	JButton buttonOption;
	JButton buttonQuitter;
	public MainMenu(MenuManager menuManager) {
		
		this.menuManager=menuManager;
		this.setLayout(null);
	
		JLabel title = new JLabel("Ages of Clash");
		title.setBounds(370,50,100,30);
		
		this.add(title,BorderLayout.NORTH);
		
	    JPanel actions = new JPanel();
	    actions.setBounds(0,300,800,550);
	    actions.setLayout(new FlowLayout());
	    
	    buttonJouer=new JButton("Jouer");
	    buttonCreer=new JButton("Creer un deck");
	    buttonOption=new JButton("Option");
	    buttonQuitter=new JButton("Quitter");
	    buttonJouer.addActionListener(menuManager.menuController);
	    buttonCreer.addActionListener(menuManager.menuController);
	    buttonOption.addActionListener(menuManager.menuController);
	    buttonQuitter.addActionListener(menuManager.menuController);
	    buttonJouer.setActionCommand("JOUER");
	    buttonCreer.setActionCommand("CREER");
	    buttonOption.setActionCommand("OPTION");
	    buttonQuitter.setActionCommand("QUITTER");
	    actions.add(buttonJouer);
	    actions.add(buttonCreer);
	    actions.add(buttonOption);
	    actions.add(buttonQuitter);

	    this.add(actions, BorderLayout.CENTER);
	}
    
  
}