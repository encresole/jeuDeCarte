package View;

import java.awt.BorderLayout;

public class OptionPanel extends PanelAgesOfClash{
	private static final long serialVersionUID = 1L;

	ButtonAgesOfClash buttonRetour = new ButtonAgesOfClash("Retour");
	
	public MenuManager menuManager;
	public OptionPanel(MenuManager m) {
		menuManager=m;
		buttonRetour.setActionCommand("SHOWMENU");
		buttonRetour.addActionListener(m.menuController);
		setLayout(new BorderLayout());
		add(buttonRetour,BorderLayout.SOUTH);
	}
}
