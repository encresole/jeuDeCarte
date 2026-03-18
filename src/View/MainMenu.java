package View;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
	private static final long serialVersionUID = 1L;

	public MenuManager menuManager;
	JLabel title;
	JPanel panelDesButtons;
	JButton buttonJouer;
	JButton buttonCreer;
	JButton buttonOption;
	JButton buttonQuitter;
	JPanel leftWrapper;
	JPanel topPanel;
	
	public MainMenu(MenuManager menuManager) {
		this.menuManager=menuManager;
		this.setLayout(new BorderLayout());
		
		title = new JLabel("AGES OF CLASH");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		this.add(title,BorderLayout.NORTH);
		
		topPanel = new JPanel(new BorderLayout());
		topPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // top, left, bottom, right
		
		topPanel.add(title, BorderLayout.CENTER);
		this.add(topPanel, BorderLayout.NORTH);
		
		panelDesButtons= new JPanel();
		
		panelDesButtons.setLayout(new BoxLayout(panelDesButtons,BoxLayout.Y_AXIS));
	    
		panelDesButtons.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		
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
	    
	    panelDesButtons.add(buttonJouer);
	    panelDesButtons.add(Box.createVerticalStrut(10)); // espace
	    panelDesButtons.add(buttonCreer);
	    panelDesButtons.add(Box.createVerticalStrut(10)); // espace
	    panelDesButtons.add(buttonOption);
	    panelDesButtons.add(Box.createVerticalStrut(10)); // espace
	    panelDesButtons.add(buttonQuitter);
	    
	    leftWrapper = new JPanel();
	    leftWrapper.setLayout(new BoxLayout(leftWrapper, BoxLayout.Y_AXIS));
	    
	    leftWrapper.add(Box.createVerticalGlue());
	    leftWrapper.add(panelDesButtons);
	    leftWrapper.add(Box.createVerticalGlue());

	    this.add(leftWrapper, BorderLayout.LINE_START);
	}
    
}