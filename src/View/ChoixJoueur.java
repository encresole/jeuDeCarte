package View;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChoixJoueur extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public MenuManager menuManager;
	
	public JPanel centerPanel;
	public JPanel panelPourLabel = new JPanel();
	public JPanel panelDesButtons= new JPanel();
	public JLabel labelChoix;
	public ButtonAgesOfClash buttonJoueur1 = new ButtonAgesOfClash("Joueur 1");
	public ButtonAgesOfClash buttonJoueur2 = new ButtonAgesOfClash("Joueur 2");
	public ButtonAgesOfClash buttonRetour = new ButtonAgesOfClash("Retour");
	
	public ChoixJoueur(MenuManager menuManager) {
		this.menuManager=menuManager;
		this.setLayout(new BorderLayout());
		
		labelChoix = new JLabel("Quel joueur va créer son deck ?");
		panelPourLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
		panelPourLabel.add(labelChoix);
		
		panelDesButtons.add(buttonJoueur1);
		panelDesButtons.add(buttonJoueur2);

		buttonRetour.setActionCommand("SHOWMENU");
		buttonRetour.addActionListener(menuManager.menuController);
		
		buttonJoueur1.setActionCommand("SETJOUEUR1");
		buttonJoueur2.setActionCommand("SETJOUEUR2");
		buttonJoueur1.addActionListener(menuManager.menuController);
		buttonJoueur2.addActionListener(menuManager.menuController);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(Box.createVerticalGlue());
		centerPanel.add(panelDesButtons);
		centerPanel.add(Box.createVerticalGlue());
		
		add(panelPourLabel,BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(buttonRetour,BorderLayout.SOUTH);

	}

}
