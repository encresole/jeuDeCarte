package Model;

import Controller.MenuController;

public class Objet extends Carte {
	private static final long serialVersionUID = 1L;
	
	public Effet effet;

	public Objet(String nom,String image,MenuController mc, Effet effet) {
		super(nom,nom,image, mc);
		this.effet = effet;
	}

	@Override
	public Carte copy() {
		// TODO Auto-generated method stub
		return new Objet(nom,cheminImage,mc,effet);
	}

}
