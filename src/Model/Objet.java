package Model;

import Controller.MenuController;

public class Objet extends Carte {
	private static final long serialVersionUID = 1L;
	
	public Effet effet;

	public Objet(String nom,MenuController mc, Effet effet) {
		super(nom,nom, mc);
		this.effet = effet;
	}

}
