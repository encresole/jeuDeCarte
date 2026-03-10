package Model;

import java.awt.Color;

import java.awt.Graphics;

import javax.swing.JPanel;

import Controller.MenuController;

public abstract class Carte extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public String nom;
    public int width = 100;
    public int heigth = 150;
    public MenuController mc;

    // Constructeur de la carte
    public Carte(String nom, MenuController mc) {
        this.nom = nom;
        this.mc=mc;
        this.setLayout(null);
        this.addMouseListener(mc);
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
        g.setColor(Color.BLACK);
        g.drawString(nom, 10, 20);
    }
    
    public void setPosition(int x,int y) {
    	this.setLocation(x,y);
    }
}