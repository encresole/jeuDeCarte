package View;

import java.awt.BorderLayout;

public class SavePanel extends PanelAgesOfClash{
	private static final long serialVersionUID = 1L;

	MenuManager menuManager;
	ButtonAgesOfClash buttonRetour= new ButtonAgesOfClash("Retour à la partie");
	public SavePanel(MenuManager m) {
		menuManager=m;
		
		setLayout(new BorderLayout());
		buttonRetour.setActionCommand("REPRENDRE");
		buttonRetour.addActionListener(menuManager.menuController);
		add(buttonRetour,BorderLayout.SOUTH);
	}
}
