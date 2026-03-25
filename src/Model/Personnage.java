package Model;


import Controller.MenuController;

public class Personnage extends Carte {
	private static final long serialVersionUID = 1L;
	
	public int pv;
	public int attaque;
	public int energie;
	public Boolean estActif;
	
	public Personnage(String nom,String nomComplet,String image,MenuController mc, int pv, int attaque) {
		super(nom,nomComplet,image,mc);
		this.pv = pv;
		this.attaque = attaque;
		this.energie = 100;
		this.estActif = false;
	}

	@Override
	public Carte copy() {
		return new Personnage(nom,nomComplet,cheminImage,mc,pv,attaque);
	}
}
