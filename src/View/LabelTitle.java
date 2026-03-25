package View;

import java.awt.Font;

import javax.swing.JLabel;

import Model.Palette;

public class LabelTitle extends JLabel{
	private static final long serialVersionUID = 1L;

	public LabelTitle(String s) {
		// TODO Auto-generated constructor stub
		super(s);
		
		setForeground(Palette.MENU_TITLE);
        setFont(new Font("Arial", Font.BOLD, 30));
	}

}
