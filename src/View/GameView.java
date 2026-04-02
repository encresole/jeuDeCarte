package View;

import java.awt.BorderLayout;

import java.awt.Dimension;

public class GameView extends PanelAgesOfClash {
	private static final long serialVersionUID = 1L;

	MenuManager menuManager;
	ButtonAgesOfClash boutonRetour;
	PanelJoueur panelJ1;
	PanelJoueur panelJ2;
	PanelAgesOfClash panelDesBoutons = new PanelAgesOfClash();
	
	public GameView(MenuManager menuManager) {
		this.menuManager= menuManager;
		setLayout(new BorderLayout());
		
		
		
		
		panelJ1 = new PanelJoueur(false,menuManager.model.joueur1, menuManager.menuController);
		
		panelJ2 = new PanelJoueur(true,menuManager.model.joueur2, menuManager.menuController);
		
		panelJ1.setPreferredSize(new Dimension(400,600));
		panelJ2.setPreferredSize(new Dimension(400,600));
		
		add(panelJ1, BorderLayout.LINE_START);
		add(panelJ2, BorderLayout.LINE_END);
		
		
		panelDesBoutons.setLayout(new BorderLayout());
		
		
		
		add(panelDesBoutons,BorderLayout.SOUTH);
		
		panelDesBoutons.add(new ButtonAgesOfClash("Utiliser"),BorderLayout.LINE_START);
		panelDesBoutons.add(new ButtonAgesOfClash("Retraite"));
		panelDesBoutons.add(new ButtonAgesOfClash("Fin du tour"),BorderLayout.LINE_END);
		
		boutonRetour = new ButtonAgesOfClash("Retour");
		boutonRetour.setActionCommand("SHOWMENU");
		boutonRetour.addActionListener(menuManager.menuController);
		panelDesBoutons.add(boutonRetour,BorderLayout.PAGE_END);
	}
}
