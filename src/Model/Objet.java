package Model;

import java.awt.Color;
import java.awt.Graphics;

import Controller.MenuController;

public class Objet extends Carte {
	private static final long serialVersionUID = 1L;
	
	public Effet effet;
	public Model.Faction faction;
	public String nomEffet;
	public String description;

	public Objet(String id, String nom, String image, MenuController mc, Effet effet) {
		super(id, nom, nom, image, mc);
		this.effet = effet;
	}

	@Override
	public Carte copy() {
		return new Objet(id, nom, cheminImage, mc, effet);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.CYAN);
		if (selectionnee) {g.setColor(Color.WHITE);}
        g.fillRect(0, 0, width +15, heigth +15);
		super.paint(g);
	}

}