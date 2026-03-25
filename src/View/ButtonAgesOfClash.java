package View;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import Model.Palette;

public class ButtonAgesOfClash extends JButton implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ButtonAgesOfClash(String s) {
		super(s);
		setFont(new Font("Serif", Font.BOLD, 18));
		setBackground(Palette.BUTTON_BG);
        setForeground(Palette.BUTTON_TEXT);
        setFocusPainted(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		setBackground(Palette.BUTTON_HOVER);	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		setBackground(Palette.BUTTON_BG);
        setForeground(Palette.BUTTON_TEXT);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
