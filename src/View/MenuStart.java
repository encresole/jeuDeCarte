package View;


import java.awt.Component;

import javax.swing.*;


public class MenuStart extends PanelAgesOfClash {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LabelTitle title;
	public ButtonAgesOfClash buttonJouer;
	public MenuManager menuManager;
	public JPanel centerPanel = new PanelAgesOfClash();
	public JLabel description;
	
	public MenuStart(MenuManager menuManager) {
		// TODO Auto-generated constructor stub
		this.menuManager= menuManager;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
       
        
        title = new LabelTitle("AGES OF CLASH");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        buttonJouer = new ButtonAgesOfClash("Jouer");
        buttonJouer.addActionListener(menuManager.menuController);
        buttonJouer.setActionCommand("SHOWMENU");
        buttonJouer.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        description = new JLabel();
        description.setText(
            "<html><div style='color:white; width:400px;'>"
            + "Ages of Clash vous plonge au cœur d’un affrontement légendaire où Chevaliers, "
            + "Samouraïs, Cowboys et Soldats s’opposent dans des duels stratégiques intenses.<br><br>"
            + "Gérez intelligemment votre énergie, anticipez les actions adverses et déclenchez des attaques dévastatrices aux effets spectaculaires.<br><br>"
            + "Chaque faction possède son propre style, ses capacités uniques et des synergies puissantes à exploiter pour dominer le champ de bataille.<br><br>"
            + "Entre tactique, hasard maîtrisé et enchaînements explosifs, chaque partie est un combat imprévisible.<br><br>"
            + "Préparez votre deck, affûtez votre stratégie… et imposez votre ère dans Ages of Clash."
            + "</div></html>");
        centerPanel.add(description);
        
        this.add(Box.createVerticalGlue()); // pousse vers le bas
        
        this.add(title);
        this.add(Box.createVerticalStrut(60)); // espace entre
        this.add(centerPanel);
        this.add(Box.createVerticalStrut(10)); // espace entre
        this.add(buttonJouer);
 
        this.add(Box.createVerticalGlue()); // pousse vers le haut
	}
	
}
