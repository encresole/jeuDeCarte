package Model;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Controller.MenuController;

public abstract class Carte extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	public String nom;
	public String nomComplet;
    public int width = 100;
    public int heigth = 150;
    public MenuController mc;

    // Constructeur de la carte
    public Carte(String nom, String nomComplet, MenuController mc) {
        this.nom = nom;
        this.nomComplet=nomComplet;
        this.mc=mc;
        this.setLayout(null);
        this.addMouseListener(this);
        this.setBounds(0, 0, width, heigth);
    }

    // Dessiner la carte à une position (x, y) avec un facteur d'échelle
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // toujours appeler le super
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width, heigth);
        g.setColor(Color.WHITE);
        g.fillRect(1, 1, width - 1, heigth - 1);
        BufferedImage image = null;
        
		try {
			image = ImageIO.read(getClass().getResource("/images/personnages/Sire Aldric l'indomptable.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        g.drawImage(image, 0, 0, width - 1, heigth - 1, this);
        
        g.setColor(Color.WHITE);
        g.drawString(nom, 10, 20);
    }
    
    public void setPosition(int x,int y) {
    	this.setLocation(x,y);
    }
    
    @Override
    public String toString() {
    	return this.nom;
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
    	mc.carteClique(nom);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
}