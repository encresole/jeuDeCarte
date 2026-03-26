package View;

import java.awt.BorderLayout;

public class GameView extends PanelAgesOfClash {
	private static final long serialVersionUID = 1L;

	MenuManager menuManager;
	ButtonAgesOfClash boutonRetour;
	PanelJoueur panelJ1 = new PanelJoueur();
	PanelJoueur panelJ2 = new PanelJoueur();
	
	public GameView(MenuManager menuManager) {
		this.menuManager= menuManager;
		setLayout(new BorderLayout());
		
		boutonRetour = new ButtonAgesOfClash("Retour");
		boutonRetour.setActionCommand("SHOWMENU");
		boutonRetour.addActionListener(menuManager.menuController);
		
		add(panelJ1, BorderLayout.LINE_START);
		add(panelJ2, BorderLayout.LINE_END);
		add(boutonRetour,BorderLayout.PAGE_END);
	}
}
