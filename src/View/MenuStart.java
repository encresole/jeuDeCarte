package View;

import java.awt.Font;

import javax.swing.*;

public class MenuStart extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JButton buttonJouer;
	public MenuManager menuManager;
	
	public MenuStart(MenuManager menuManager) {
		// TODO Auto-generated constructor stub
		this.menuManager= menuManager;
        this.setLayout(null);
        JLabel title = new JLabel("AGES OF CLASH");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(300,250,800,30);
        buttonJouer = new JButton("Jouer");
        buttonJouer.setBounds(367,300,100,30);
        buttonJouer.addActionListener(menuManager.menuController);
        buttonJouer.setActionCommand("SHOWMENU");
        this.add(title);
        this.add(buttonJouer);
	}
}
