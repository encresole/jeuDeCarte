package View;

import javax.swing.*;

import java.awt.*;

public class MainMenu extends PanelAgesOfClash {
	private static final long serialVersionUID = 1L;

	public MenuManager menuManager;
	LabelTitle title;
	PanelAgesOfClash panelDesButtons;
	ButtonAgesOfClash buttonJouer;
	ButtonAgesOfClash buttonCreer;
	ButtonAgesOfClash buttonInfo;
	ButtonAgesOfClash buttonOption;
	ButtonAgesOfClash buttonQuitter;
	PanelAgesOfClash leftWrapper;
	PanelAgesOfClash topPanel;
	
	public MainMenu(MenuManager menuManager) {
		this.menuManager=menuManager;
		this.setLayout(new BorderLayout());
		title = new LabelTitle("AGES OF CLASH");
		title.setHorizontalAlignment(JLabel.CENTER);
		this.add(title,BorderLayout.NORTH);
		
		topPanel = new PanelAgesOfClash(new BorderLayout());
		topPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // top, left, bottom, right
		topPanel.add(title, BorderLayout.CENTER);
		this.add(topPanel, BorderLayout.NORTH);
		
		panelDesButtons= new PanelAgesOfClash();
		
		panelDesButtons.setLayout(new BoxLayout(panelDesButtons,BoxLayout.Y_AXIS));
	    
		panelDesButtons.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		
	    buttonJouer=new ButtonAgesOfClash("Jouer");
	    buttonCreer=new ButtonAgesOfClash("Creer un deck");
	    buttonInfo = new ButtonAgesOfClash("Infos");
	    buttonOption=new ButtonAgesOfClash("Option");
	    buttonQuitter=new ButtonAgesOfClash("Quitter");
	    buttonJouer.addActionListener(menuManager.menuController);
	    buttonCreer.addActionListener(menuManager.menuController);
	    buttonInfo.addActionListener(menuManager.menuController);
	    buttonOption.addActionListener(menuManager.menuController);
	    buttonQuitter.addActionListener(menuManager.menuController);
	    buttonJouer.setActionCommand("JOUER");
	    buttonCreer.setActionCommand("CHOISIS");
	    buttonInfo.setActionCommand("INFO");
	    buttonOption.setActionCommand("OPTION");
	    buttonQuitter.setActionCommand("QUITTER");
	    
	    panelDesButtons.add(buttonJouer);
	    panelDesButtons.add(Box.createVerticalStrut(10)); // espace
	    panelDesButtons.add(buttonCreer);
	    panelDesButtons.add(Box.createVerticalStrut(10)); // espace
	    panelDesButtons.add(buttonInfo);
	    panelDesButtons.add(Box.createVerticalStrut(10)); // espace
	    panelDesButtons.add(buttonOption);
	    panelDesButtons.add(Box.createVerticalStrut(10)); // espace
	    panelDesButtons.add(buttonQuitter);
	    
	    leftWrapper = new PanelAgesOfClash();
	    leftWrapper.setLayout(new BoxLayout(leftWrapper, BoxLayout.Y_AXIS));
	    leftWrapper.add(Box.createVerticalGlue());
	    leftWrapper.add(panelDesButtons);
	    leftWrapper.add(Box.createVerticalGlue());

	    this.add(leftWrapper, BorderLayout.LINE_START);
	}
    
}