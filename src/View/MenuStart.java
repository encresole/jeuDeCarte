package View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuStart extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MainMenu mainMenu;
	public JButton buttonJouer;
	
	public MenuStart(MainMenu mainMenu) {
		// TODO Auto-generated constructor stub
		this.mainMenu=mainMenu;
        this.setLayout(null);
        JLabel title = new JLabel("AGES OF CLASH");
        title.setBounds(370,270,100,30);
        buttonJouer = new JButton("Jouer");
        buttonJouer.setBounds(370,300,100,30);
        buttonJouer.addActionListener(this);
        this.add(title);
        this.add(buttonJouer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		mainMenu.showCreerDeck();
	}
}
