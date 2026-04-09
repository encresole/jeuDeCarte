package Model;


import java.awt.Color;
import java.awt.Graphics;

import Controller.MenuController;

public class Personnage extends Carte {
	private static final long serialVersionUID = 1L;
	
	public int pv;
	public int pvMax;
	public int attaque;
	public int energie;
	public Boolean estActif;
	public int coutEnergie;
	public Model.Faction faction;
	public String nomEffet;
	
	public Personnage(String id, String nom, String nomComplet, String image, MenuController mc, int pv, int attaque) {
		super(id, nom, nomComplet, image, mc);
		this.pvMax=pv;
		this.pv = pv;
		this.attaque = attaque;
		this.energie = 100;
		this.estActif = false;
	}

	@Override
	public Carte copy() {
		return new Personnage(id, nom, nomComplet, cheminImage, mc, pv, attaque);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.YELLOW);
        g.fillRect(0, 0, width +15, heigth +15);

		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(5, 155, (Integer)width+5, (int) ((int)heigth*0.05));
		
		pv=55;
		
		if ((pv / (double) pvMax) <0.25) {
			g.setColor(Color.RED);
		} else if ((pv / (double) pvMax) <0.50) {
			g.setColor(Color.ORANGE);
		} else {
			g.setColor(Color.GREEN);
		}
		g.fillRect(5, 155, (int)((pv / (double) pvMax) * width) + 5, (int)(heigth * 0.05));
		g.setColor(Color.WHITE);
		g.drawString(pv+"/"+pvMax+"pv", 7, 150);
	}
}
