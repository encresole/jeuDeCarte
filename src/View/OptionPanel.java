package View;

import java.awt.BorderLayout;

public class OptionPanel extends PanelAgesOfClash{
	private static final long serialVersionUID = 1L;

	ButtonAgesOfClash buttonRetour = new ButtonAgesOfClash("Retour");
	ButtonAgesOfClash toggleMusique = new ButtonAgesOfClash("Désactiver la musique");
	ButtonAgesOfClash toggleFond = new ButtonAgesOfClash("fond");
	PanelAgesOfClash centerPanel= new PanelAgesOfClash();
	
	public MenuManager menuManager;
	public OptionPanel(MenuManager m) {
		menuManager=m;

		buttonRetour.setActionCommand("SHOWMENU");
		buttonRetour.addActionListener(m.menuController);
		setLayout(new BorderLayout());
		
		toggleMusique.setActionCommand("MUSIC");
		toggleMusique.addActionListener(menuManager.menuController);
		centerPanel.add(toggleMusique);
		
		toggleFond.setActionCommand("FOND");
		toggleFond.addActionListener(m.menuController);
		centerPanel.add(toggleFond);
		add(buttonRetour,BorderLayout.SOUTH);
		
		add(centerPanel);
	}
}
