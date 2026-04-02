package Model;

import java.awt.Color;
import java.awt.Dimension;
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
	
	public String id;
	public String nom;
	public String nomComplet;
    public int width = 100;
    public int heigth = 150;
    public MenuController mc;
    public String cheminImage;
    public BufferedImage image = null;

    public boolean selectionnee = false;
    
    public abstract Carte copy();
    
    public Carte(String id, String nom, String nomComplet, String cheminImage, MenuController mc) {
        this.id = id;
        this.nom = nom;
        this.nomComplet = nomComplet;
        this.cheminImage = cheminImage;
        this.mc = mc;
        this.setLayout(null);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(width+10, heigth+10)); 
        this.addMouseListener(this);
        try {
            image = ImageIO.read(getClass().getResource(this.cheminImage));
        } catch (IOException e) {
            System.err.println("Image pas trouvée");
        }
    }
    
    public Carte(Carte c) {
    	this(c.id, c.nom, c.nomComplet, c.cheminImage, c.mc);
    }


    public boolean contains(int x, int y) {
        return x >= 0 && x < width+10 && y >= 0 && y < heigth+10;
    }
    
    public void setSelectionnee(Boolean b) {
    	selectionnee=b;
    	revalidate();
    	repaint();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width+10, heigth+10);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 5, 5, width +5, heigth + 5, this);
        g.setColor(Color.WHITE);
        g.drawString(nom, 10, 20);
    }
    
    @Override
    public String toString() {
    	return this.nom;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    	mc.carteClique(this);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {}
    
    @Override
    public void mouseExited(MouseEvent e) {}
    
    @Override
    public void mousePressed(MouseEvent e) {}
    
    @Override
    public void mouseReleased(MouseEvent e) {}
}