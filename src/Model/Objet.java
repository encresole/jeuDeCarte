package Model;

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

}